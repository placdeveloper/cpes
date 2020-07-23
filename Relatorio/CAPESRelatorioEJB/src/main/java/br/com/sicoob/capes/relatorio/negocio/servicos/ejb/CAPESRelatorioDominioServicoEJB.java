/*
 * SICOOB
 * 
 * CAPESRelatorioDominioServicoEJB.java(br.com.sicoob.capes.relatorio.negocio.servicos.ejb.CAPESRelatorioDominioServicoEJB)
 */
package br.com.sicoob.capes.relatorio.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.negocio.servicos.ejb.BancoobServicoEJB;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroCrudDelegate;
import br.com.sicoob.capes.comum.negocio.vo.InstituicaoVO;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.relatorio.negocio.relatorios.RelatorioDominio;
import br.com.sicoob.capes.relatorio.negocio.servicos.CAPESRelatorioDominioServico;
import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO;
import br.com.sicoob.relatorio.api.dto.RetornoProcessamentoRelatorioDTO;
import br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico;

/**
 * The Class CAPESRelatorioDominioServicoEJB.
 * 
 * @param <T>
 *            the generic type
 */
public abstract class CAPESRelatorioDominioServicoEJB<T extends CAPESEntidade<?>> extends BancoobServicoEJB implements
		CAPESRelatorioDominioServico<T>, IProcessamentoRelatorioServico {

	/**
	 * Recupera o código do relatório
	 * 
	 * @return o código do relatório
	 */
	protected abstract String getCodigoRelatorio();

	/**
	 * Recupera o título do relatório
	 * 
	 * @return o título
	 */
	protected abstract String getTituloRelatorio();

	/**
	 * Recupera o nome do arquivo de template do relatório
	 * 
	 * @return o nome do template
	 */
	protected String getTemplateRelatorio() {

		return "RelatorioDominio.jasper";
	}

	/**
	 * Gera o relatório utilizando o {@code templateRelatorio} e o {@code codigoRelatorio} com todos os registros
	 * contidos em {@code dadosRelatorio}
	 * 
	 * @param dadosRelatorio
	 *            A lista com os dados do relatório
	 * 
	 * @return O relatório
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RetornoProcessamentoRelatorioDTO gerarRelatorio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
		List<T> lista = obterDadosRelatorio();
		
		if (lista.isEmpty()) {
			throw new NegocioException("Não foram encontrados resultados para os filtros selecionados.");
		}

		UsuarioBancoob usuario = obterUsuario();
		Integer idInstituicao = Integer.valueOf(usuario.getIdInstituicao());
		InstituicaoVO cooperativa = obterInstituicaoPorId(idInstituicao);
		String siglaCooperativa = cooperativa.getSiglaInstituicao();
		Integer numeroCooperativa = cooperativa.getNumero();
		RelatorioDominio<?> relatorio = new RelatorioDominio(getTemplateRelatorio(), getTituloRelatorio(), getCodigoRelatorio(), lista, numeroCooperativa, siglaCooperativa);

		return relatorio.gerarRelatorio(rDto);
	}

	@SuppressWarnings("unchecked")
	protected List<T> obterDadosRelatorio() throws BancoobException {
		return (List<T>) obterDelegate().listar();
	}

	/**
	 * Obter delegate.
	 * 
	 * @return CAPES cadastro crud delegate
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	protected abstract CAPESCadastroCrudDelegate<?, ?> obterDelegate() throws BancoobException;

	/**
	 * Recupera a instituição do usuário para os relatórios em processamento.
	 * 
	 * @return a instituição do usuário.
	 */
	protected Instituicao obterInstituicaoUsuarioRelatorio() {

		InformacoesUsuario usuario = InformacoesUsuario.getInstance();

		Instituicao instituicao = new Instituicao();
		instituicao.setIdInstituicao(Integer.valueOf(usuario.getIdInstituicao()));
		instituicao.setIdUnidadeInst(Integer.valueOf(usuario.getIdUnidadeInstituicao()));
		return instituicao;
	}

	/**
	 * 
	 * @return
	 */
	protected UsuarioBancoob obterUsuario() {

		UsuarioBancoob usuarioBancoob = null;
		InformacoesUsuario usuario = InformacoesUsuario.getInstance();

		if (usuario != null) {
			usuarioBancoob = new UsuarioBancoob();
			usuarioBancoob.setCooperativa(usuario.getCooperativa());
			usuarioBancoob.setDominio(usuario.getDominio());
			usuarioBancoob.setIdInstituicao(usuario.getIdInstituicao());
			usuarioBancoob.setIdUnidadeInstituicao(usuario.getIdUnidadeInstituicao());
			usuarioBancoob.setLogin(usuario.getLogin());
			usuarioBancoob.setPac(usuario.getPac());
		}

		return usuarioBancoob;
	}
	
	/**
	 * Obter instituicao por id.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @return InstituicaoVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected InstituicaoVO obterInstituicaoPorId(Integer idInstituicao) throws BancoobException {
		SCIIntegracaoDelegate sciIntegracaoDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
		return sciIntegracaoDelegate.obterInstituicaoPorId(idInstituicao);
	}

}