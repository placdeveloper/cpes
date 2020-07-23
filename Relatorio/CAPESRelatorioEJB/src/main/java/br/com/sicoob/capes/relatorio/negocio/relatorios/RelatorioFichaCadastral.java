package br.com.sicoob.capes.relatorio.negocio.relatorios;

import java.util.Collection;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;
import br.com.sicoob.capes.relatorio.negocio.vo.FichaCadastralVO;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;

/**
 * A Classe RelatorioFichaCadastral.
 */
public abstract class RelatorioFichaCadastral extends RelatorioCapes {

	/** A constante BANCOOB. */
	private static final int BANCOOB = 1;
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia um novo RelatorioFichaCadastral.
	 *
	 * @param fichaCadastralVO o valor de ficha cadastral vo
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RelatorioFichaCadastral(FichaCadastralVO fichaCadastralVO) throws BancoobException {
		super("FichaCadastralPessoa.jasper");
		
		Map parametros = getParametros();

		if (fichaCadastralVO.getIdInstituicao() == BANCOOB) {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			getParametros().put("URL_LOGO", classLoader.getResource(
					"br/com/sicoob/capes/relatorio/bancoob.jpg"));
		}

		parametros.put("tributacao", fichaCadastralVO.getTributacao());
		parametros.put("pessoa", fichaCadastralVO.getPessoa());

		parametros.put("pessoaInstituicao", fichaCadastralVO.getPessoaInstituicao());
		parametros.put("TITULO_RELATORIO", recuperarTituloRelatorio());
		parametros.put("NUM_COOPERATIVA", obterNumCooperativa(fichaCadastralVO.getIdInstituicao()));
		parametros.put("ID_UNIDADE_INST", fichaCadastralVO.getIdUnidadeInst());
		parametros.put("ID_INSTITUICAO", fichaCadastralVO.getIdInstituicao());
		parametros.put("LABEL_FILTRO_DATA", fichaCadastralVO.getLabelFiltroData());
		parametros.put("DATA_ULTIMA_ATUALIZACAO", fichaCadastralVO.getDataUltimaAtualizacao());
		parametros.put("SUB_HEADER", recuperarRelatorio("FichaCadastral_Header.jasper"));
		
		parametros.put("SUB_DADOS_PESSOAIS", recuperarRelatorioDadosPessoais());
		parametros.put("DS_SUB_DADOS_PESSOAIS", criarDS(fichaCadastralVO.getPessoasCompartilhamento()));
		
		parametros.put("SUB_TRIBUTACAO", recuperarRelatorio("FichaCadastral_PerfilTributario.jasper"));
		parametros.put("DS_SUB_TRIBUTACAO", criarDS(fichaCadastralVO.getTributacoes()));

		parametros.put("SUB_PESSOA_INSTITUICAO", recuperarRelatorio("FichaCadastral_PessoaInstituicao.jasper"));
		parametros.put("DS_SUB_PESSOA_INSTITUICAO", criarDS(fichaCadastralVO.getPessoaInstituicoes()));
		
		parametros.put("SUB_CONTATOS", recuperarRelatorio("FichaCadastral_Contatos.jasper"));
		parametros.put("SUB_ENDERECOS", recuperarRelatorio("FichaCadastral_Endereco.jasper"));
		parametros.put("SUB_TELEFONES", recuperarRelatorio("FichaCadastral_Telefone.jasper"));
		parametros.put("SUB_EMAILS", recuperarRelatorio("FichaCadastral_Email.jasper"));
		parametros.put("DS_SUB_CONTATOS", criarDS(fichaCadastralVO.getContatos()));

		parametros.put("SUB_INFORMACOES_COMPLEMENTARES", recuperarRelatorio("FichaCadastral_InformacoesComplementares.jasper"));

		parametros.put("SUB_REFERENCIAS", recuperarRelatorio("FichaCadastral_Referencia.jasper"));
		parametros.put("DS_SUB_REFERENCIAS", criarDS(fichaCadastralVO.getReferencias()));

		parametros.put("SUB_RELACIONAMENTOS", recuperarRelatorio("FichaCadastral_Relacionamento.jasper"));
		parametros.put("DS_SUB_RELACIONAMENTOS", criarDS(fichaCadastralVO.getRelacionamentos()));

		parametros.put("SUB_CERTIDOES", recuperarRelatorio("FichaCadastral_Certidao.jasper"));
		parametros.put("DS_SUB_CERTIDOES", criarDS(fichaCadastralVO.getCertidoes()));

		parametros.put("SUB_FONTES_DE_RENDA", recuperarRelatorio("FichaCadastral_FonteDeRenda.jasper"));
		parametros.put("DS_SUB_FONTES_DE_RENDA", criarDS(fichaCadastralVO.getFontesDeRenda()));

		parametros.put("SUB_BENS", recuperarRelatorio("FichaCadastral_Bem.jasper"));
		parametros.put("SUB_BEM_DETALHE", recuperarRelatorio("FichaCadastral_BemDetalhe.jasper"));
		parametros.put("SUB_BEM_DETALHE_IMOVEL", recuperarRelatorio("FichaCadastral_BemDetalheImovel.jasper"));
		parametros.put("DS_SUB_BENS", criarDS(fichaCadastralVO.getBensDTO()));	


		parametros.put("SUB_BENS_DEPENDENCIAS", recuperarRelatorio("FichaCadastral_BemDependencias.jasper"));
		parametros.put("SUB_BENS_ONUS", recuperarRelatorio("FichaCadastral_BemOnus.jasper"));
		parametros.put("SUB_BENS_POSSES", recuperarRelatorio("FichaCadastral_BemPosse.jasper"));
		parametros.put("SUB_BENS_REGISTROS", recuperarRelatorio("FichaCadastral_BemRegistro.jasper"));

		parametros.put("SUB_PRODUTOR", recuperarRelatorio("FichaCadastral_Produtor.jasper"));
		parametros.put("DS_SUB_PRODUTOR", criarDS(fichaCadastralVO.getProdutores()));

		parametros.put("SUB_PRODUTIVIDADE", recuperarRelatorio("FichaCadastral_Produtividade.jasper"));
		parametros.put("DS_SUB_PRODUTIVIDADE", criarDS(fichaCadastralVO.getProdutividades()));
		parametros.put("EXIBIR_TOTAL_PRODUTIVIDADE", fichaCadastralVO.getExibirTotalProdutividade());
		
		parametros.put("DESCRICAO_ESFERA_ADMINISTRATIVA", fichaCadastralVO.getDescricaoEsferaAdministrativa());

		parametros.put("SUB_DECLARACAO", recuperarRelatorio("FichaCadastral_Declaracao.jasper"));
	}

	/**
	 * Obter num cooperativa.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @return Integer
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private Integer obterNumCooperativa(Integer idInstituicao) throws BancoobException {
		SCIIntegracaoDelegate sciDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
		return sciDelegate.obterNumeroCooperativa(idInstituicao);
	}

	/**
	 * Criar ds.
	 *
	 * @param colecao o valor de colecao
	 * @return EntidadeCadastroCollectionDataSource
	 */
	private EntidadeCadastroCollectionDataSource criarDS(Collection<?> colecao) {
		return new EntidadeCadastroCollectionDataSource(colecao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected JRDataSource getDataSource() throws BancoobException {
		return new JREmptyDataSource();
	}

	/**
	 * Recuperar titulo relatorio.
	 *
	 * @return String
	 */
	protected abstract String recuperarTituloRelatorio();

	/**
	 * Recuperar relatorio dados pessoais.
	 *
	 * @return Object
	 * @throws JRException lança a exceção JRException
	 */
	protected abstract Object recuperarRelatorioDadosPessoais();

}