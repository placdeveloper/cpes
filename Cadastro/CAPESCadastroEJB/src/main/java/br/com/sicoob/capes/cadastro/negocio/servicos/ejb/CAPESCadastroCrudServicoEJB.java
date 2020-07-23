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
	 * Recupera a instituição do usuário logado.
	 * @return a instituição do usuário logado.
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
	 * Recupera a instituição do usuário logado.
	 * 
	 * <pre>
	 * <b>ATENÇÃO:</b> esse método pode retornar uma instituição NULA, 
	 * caso as informações do usuário não tenham sido instanciadas.
	 * 
	 * Método para utilização no processamento, para verificação do usuário logado.
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
	 * Recupera a instituição no SCI para a instituição informada.
	 * @param instituicao A instituição do cadastro único.
	 * @return a instituição no SCI para a instituição informada.
	 * @throws BancoobException Caso ocorra alguma exceção
	 */
	protected InstituicaoVO obterInstituicaoSCI(Instituicao instituicao) throws BancoobException {
		SCIIntegracaoDelegate sciDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
		return sciDelegate.obterInstituicaoPorId(instituicao.getIdInstituicao());
	}
	
	/**
	 * Recupera a instituição no SCI para a instituição informada.
	 * @param idInstituicao o id da Instituicao do cadastro único.
	 * @return a instituição no SCI para a instituição informada.
	 * @throws BancoobException Caso ocorra alguma exceção
	 */
	public InstituicaoVO obterInstituicaoSCI(Integer idInstituicao) throws BancoobException {
		SCIIntegracaoDelegate sciDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
		return sciDelegate.obterInstituicaoPorId(idInstituicao);
	}

	/**
	 * Recupera o usuário logado
	 *
	 * @return o usuário
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
			throw new UnsupportedOperationException("O InformacoesUsuarioCAPES não foi instanciado!");
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
	 * Obtém o usuário logado sem gerar exceção caso seja nulo.
	 * 
	 * <pre>
	 * <b>ATENÇÃO</b>: esse método pode retornar um usuário bancoob NULO, caso o
	 * mesmo não tenha sido instanciado.
	 * 
	 * Uso indicado para o processamento.
	 * </pre>
	 * 
	 * @return {@code UsuarioBancoob} o usuário logado.
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
	 * Este método foi criado como solução paliativa devido ao bug encontrado no
	 * db2 com relação ao BigDecimal e o valor expreso em exponencial e/ou
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
	 * @param chave A chave do registro que será excluído.
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