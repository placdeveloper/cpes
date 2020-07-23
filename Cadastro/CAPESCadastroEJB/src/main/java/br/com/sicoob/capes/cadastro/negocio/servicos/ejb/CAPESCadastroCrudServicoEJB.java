/*
 * SICOOB
 * 
 * CAPESCadastroCrudServicoEJB.java(br.com.sicoob.capes.cadastro.negocio.servicos.ejb.CAPESCadastroCrudServicoEJB)
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.math.RoundingMode;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.bancoob.negocio.servicos.ejb.BancoobCrudServicoEJB;
import br.com.sicoob.capes.cadastro.negocio.servicos.CAPESCadastroCrudServico;
import br.com.sicoob.capes.cadastro.persistencia.dao.CAPESCadastroCrudDaoIF;
import br.com.sicoob.capes.comum.negocio.annotation.IgnorarAutorizar;
import br.com.sicoob.capes.comum.negocio.contexto.InformacoesUsuarioCAPES;
import br.com.sicoob.capes.comum.negocio.vo.InstituicaoVO;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.Instituicao;

/**
 * Implementacao padrao do contrato de servicos CRUD de todo o sistema
 * CadastroUnicoClientesComum
 * 
 * @author Stefanini IT Solutions
 */
public abstract class CAPESCadastroCrudServicoEJB<T extends CAPESEntidade<?>>
		extends BancoobCrudServicoEJB<T> implements CAPESCadastroCrudServico<T> {
	
	/**
	 * @return
	 */
	@Override
	protected abstract CAPESCadastroCrudDaoIF<T> getDAO();

	/**
	 * Recupera a institui��o do usu�rio logado.
	 * @return a institui��o do usu�rio logado.
	 */
	protected Instituicao obterInstituicaoUsuarioLogado() {

		UsuarioBancoob usuario = obterUsuario();

		Instituicao instituicao = new Instituicao();
		instituicao.setIdInstituicao(Integer.valueOf(usuario.getIdInstituicao()));
		instituicao.setIdUnidadeInst(Integer.valueOf(usuario.getIdUnidadeInstituicao()));
		instituicao.setNumero(usuario.getCooperativa());
		return instituicao;
	}
	
	/**
	 * Recupera a institui��o do usu�rio logado.
	 * 
	 * <pre>
	 * <b>ATEN��O:</b> esse m�todo pode retornar uma institui��o NULA, 
	 * caso as informa��es do usu�rio n�o tenham sido instanciadas.
	 * 
	 * M�todo para utiliza��o no processamento, para verifica��o do usu�rio logado.
	 * </pre>
	 * 
	 * @return
	 */
	protected Instituicao obterInstituicaoUsuarioLogadoThreadSafe() {
		Instituicao instituicao = null;
		UsuarioBancoob usuario = obterUsuarioThreadSafe();

		if (usuario != null) {
			instituicao = new Instituicao();
			instituicao.setIdInstituicao(Integer.valueOf(usuario.getIdInstituicao()));
			instituicao.setIdUnidadeInst(Integer.valueOf(usuario.getIdUnidadeInstituicao()));
			instituicao.setNumero(usuario.getCooperativa());
		}

		return instituicao;
	}

	/**
	 * Recupera a institui��o no SCI para a institui��o informada.
	 * @param instituicao A institui��o do cadastro �nico.
	 * @return a institui��o no SCI para a institui��o informada.
	 * @throws BancoobException Caso ocorra alguma exce��o
	 */
	protected InstituicaoVO obterInstituicaoSCI(Instituicao instituicao) throws BancoobException {
		SCIIntegracaoDelegate sciDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
		return sciDelegate.obterInstituicaoPorId(instituicao.getIdInstituicao());
	}
	
	/**
	 * Recupera a institui��o no SCI para a institui��o informada.
	 * @param idInstituicao o id da Instituicao do cadastro �nico.
	 * @return a institui��o no SCI para a institui��o informada.
	 * @throws BancoobException Caso ocorra alguma exce��o
	 */
	public InstituicaoVO obterInstituicaoSCI(Integer idInstituicao) throws BancoobException {
		SCIIntegracaoDelegate sciDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
		return sciDelegate.obterInstituicaoPorId(idInstituicao);
	}

	/**
	 * Recupera o usu�rio logado
	 *
	 * @return o usu�rio
	 */
	protected String recuperarLoginSemDominio() {
		
		String login = null;
		UsuarioBancoob usuario = obterUsuario();
		if(usuario != null) {
			login = usuario.getLogin(); 
		}
		
		return login; 
	}
	
	/**
	 * Obter usuario.
	 * 
	 * @return usuario bancoob
	 */
	protected UsuarioBancoob obterUsuario() {
		
		InformacoesUsuarioCAPES informacoesUsuarioCAPES = InformacoesUsuarioCAPES.getInstance();

		if (informacoesUsuarioCAPES == null) {
			throw new UnsupportedOperationException("O InformacoesUsuarioCAPES n�o foi instanciado!");
		}
		
		UsuarioBancoob usuarioBancoob = new UsuarioBancoob();
		usuarioBancoob.setCooperativa(informacoesUsuarioCAPES.getCooperativa());
		usuarioBancoob.setIdInstituicao(informacoesUsuarioCAPES.getIdInstituicao());
		usuarioBancoob.setIdUnidadeInstituicao(informacoesUsuarioCAPES.getIdUnidadeInstituicao());
		usuarioBancoob.setLogin(informacoesUsuarioCAPES.getLogin());
		usuarioBancoob.setPac(informacoesUsuarioCAPES.getPac());

		return usuarioBancoob;
	}
	
	/**
	 * Obt�m o usu�rio logado sem gerar exce��o caso seja nulo.
	 * 
	 * <pre>
	 * <b>ATEN��O</b>: esse m�todo pode retornar um usu�rio bancoob NULO, caso o
	 * mesmo n�o tenha sido instanciado.
	 * 
	 * Uso indicado para o processamento.
	 * </pre>
	 * 
	 * @return {@code UsuarioBancoob} o usu�rio logado.
	 */
	protected UsuarioBancoob obterUsuarioThreadSafe() {
		UsuarioBancoob usuarioBancoob = null;

		InformacoesUsuarioCAPES informacoesUsuarioCAPES = InformacoesUsuarioCAPES.getInstance();
		if (informacoesUsuarioCAPES != null) {
			usuarioBancoob = new UsuarioBancoob();

			usuarioBancoob.setCooperativa(informacoesUsuarioCAPES.getCooperativa());
			usuarioBancoob.setIdInstituicao(informacoesUsuarioCAPES.getIdInstituicao());
			usuarioBancoob.setIdUnidadeInstituicao(informacoesUsuarioCAPES.getIdUnidadeInstituicao());
			usuarioBancoob.setLogin(informacoesUsuarioCAPES.getLogin());
			usuarioBancoob.setPac(informacoesUsuarioCAPES.getPac());
		}

		return usuarioBancoob;
	}

	/**
	 * Este m�todo foi criado como solu��o paliativa devido ao bug encontrado no
	 * db2 com rela��o ao BigDecimal e o valor expreso em exponencial e/ou
	 * convertido pelo flex em double.
	 * 
	 * @see https://www-304.ibm.com/support/docview.wss?uid=swg1IC62092
	 */
	protected BigDecimal tratarValor(BigDecimal valor) {
		BigDecimal novoValor = null;
		if(valor != null) {
			novoValor = valor.divide(BigDecimal.ONE, 2, RoundingMode.CEILING);
		}
		return novoValor;
	}

	/**
	 * @see br.com.bancoob.negocio.servicos.ejb.BancoobCrudServicoEJB#excluir(java.io.Serializable)
	 * @param chave A chave do registro que ser� exclu�do.
	 */
	public void excluirEntidade(T objeto) throws BancoobException  {
		getDAO().excluirEntidade(objeto);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void removerObjetoSessao(T objeto) throws BancoobException {
		getDAO().removerObjetoSessao(objeto);
	}
	
	@IgnorarAutorizar
	public void alterarSemValidacao(T objeto) throws BancoobException {
		getDAO().alterar(objeto);
	}

}