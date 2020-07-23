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
public abstract class RelatorioFichaCadastralNova extends RelatorioCapes {

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
	public RelatorioFichaCadastralNova(FichaCadastralVO fichaCadastralVO) throws BancoobException {
		super("FichaCadastralNovaPessoa.jasper");
		
		try {

			Map parametros = getParametros();

			if (fichaCadastralVO.getIdInstituicao() == BANCOOB) {
				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				getParametros().put("URL_LOGO", classLoader.getResource(
						"br/com/sicoob/capes/relatorio/bancoob.jpg"));
			}

			parametros.put("tributacao", fichaCadastralVO.getTributacao());
			parametros.put("pessoa", fichaCadastralVO.getPessoa());
			parametros.put("filiacao", fichaCadastralVO.getFiliacao());

			parametros.put("pessoaInstituicao", fichaCadastralVO.getPessoaInstituicao());
			parametros.put("TITULO_RELATORIO", recuperarTituloRelatorio());
			parametros.put("NUM_COOPERATIVA", obterNumCooperativa(fichaCadastralVO.getIdInstituicao()));
			parametros.put("ID_UNIDADE_INST", fichaCadastralVO.getIdUnidadeInst());
			parametros.put("ID_INSTITUICAO", fichaCadastralVO.getIdInstituicao());
			parametros.put("LABEL_FILTRO_DATA", fichaCadastralVO.getLabelFiltroData());
			parametros.put("DATA_ULTIMA_ATUALIZACAO", fichaCadastralVO.getDataUltimaAtualizacao());
			parametros.put("SUB_HEADER", recuperarRelatorio("CabecalhoFichaCadastral_NOVO.jasper"));
			
			parametros.put("SUB_DADOS_PESSOAIS", recuperarRelatorioDadosPessoais());
			parametros.put("DS_SUB_DADOS_PESSOAIS", criarDS(fichaCadastralVO.getPessoasCompartilhamento()));
			
			parametros.put("SUB_CONTATOS", recuperarRelatorio("FichaCadastralNova_Contatos.jasper"));
			parametros.put("SUB_ENDERECOS", recuperarRelatorio("FichaCadastralNova_Endereco.jasper"));
			parametros.put("SUB_TELEFONES", recuperarRelatorio("FichaCadastralNova_Telefone.jasper"));
			parametros.put("SUB_EMAILS", recuperarRelatorio("FichaCadastralNova_Email.jasper"));
			parametros.put("DS_SUB_CONTATOS", criarDS(fichaCadastralVO.getContatos()));

			parametros.put("SUB_REFERENCIAS", recuperarRelatorio("FichaCadastralNova_Referencia.jasper"));
			parametros.put("DS_SUB_REFERENCIAS", criarDS(fichaCadastralVO.getReferencias()));

			parametros.put("SUB_RELACIONAMENTOS", recuperarRelatorio("FichaCadastralNova_Relacionamento.jasper"));
			parametros.put("DS_SUB_RELACIONAMENTOS", criarDS(fichaCadastralVO.getRelacionamentos()));

			parametros.put("SUB_FONTES_DE_RENDA", recuperarRelatorio("FichaCadastralNova_FonteDeRenda.jasper"));
			parametros.put("DS_SUB_FONTES_DE_RENDA", criarDS(fichaCadastralVO.getFontesDeRenda()));
			parametros.put("TOTAL_RENDA_MENSAL", fichaCadastralVO.getTotalFontesDeRendaMensal());
			

			parametros.put("SUB_BENS", recuperarRelatorio("FichaCadastralNova_Bem.jasper"));
			parametros.put("SUB_BEM_DETALHE", recuperarRelatorio("FichaCadastralNova_BemDetalhe.jasper"));
			parametros.put("SUB_BEM_DETALHE_IMOVEL", recuperarRelatorio("FichaCadastralNova_BemDetalheImovel.jasper"));
			parametros.put("DS_SUB_BENS", criarDS(fichaCadastralVO.getBensDTO()));	
			parametros.put("TOTAL_VALOR_PARTICIPACAO", fichaCadastralVO.getTotalParticipacao());
			parametros.put("TOTAL_VALOR_PATRIMONIO", fichaCadastralVO.getTotalPatrimonio());

			parametros.put("SUB_TRIBUTACAO", recuperarRelatorio("FichaCadastralNova_PerfilTributario.jasper"));
			parametros.put("DS_SUB_TRIBUTACAO", criarDS(fichaCadastralVO.getTributacoes()));

			parametros.put("SUB_BENS_DEPENDENCIAS", recuperarRelatorio("FichaCadastralNova_BemDependencias.jasper"));
			parametros.put("SUB_BENS_ONUS", recuperarRelatorio("FichaCadastralNova_BemOnus.jasper"));
			parametros.put("SUB_BENS_POSSES", recuperarRelatorio("FichaCadastralNova_BemPosse.jasper"));
			parametros.put("SUB_BENS_REGISTROS", recuperarRelatorio("FichaCadastralNova_BemRegistro.jasper"));

			parametros.put("SUB_PRODUTOR", recuperarRelatorio("FichaCadastralNova_Produtor.jasper"));
			parametros.put("DS_SUB_PRODUTOR", criarDS(fichaCadastralVO.getProdutores()));
			
			
			parametros.put("SUB_PRODUTIVIDADE", recuperarRelatorio("FichaCadastralNova_Produtividade.jasper"));
			parametros.put("DS_SUB_PRODUTIVIDADE", criarDS(fichaCadastralVO.getProdutividades()));
			parametros.put("EXIBIR_TOTAL_PRODUTIVIDADE", fichaCadastralVO.getExibirTotalProdutividade());
			
			
			
			parametros.put("DESCRICAO_ESFERA_ADMINISTRATIVA", fichaCadastralVO.getDescricaoEsferaAdministrativa());
			
			
			parametros.put("SUB_BENS_NOVO", recuperarRelatorio("FichaCadastralNova_BemNovo.jasper"));
			parametros.put("SUB_BENS_NOVO_DETALHE_IMOVEL", recuperarRelatorio("FichaCadastralNova_BemNovo_Detalhes_Imovel.jasper"));
			parametros.put("SUB_BENS_NOVO_DETALHE_MOVEL", recuperarRelatorio("FichaCadastralNova_BemNovo_Detalhes_Movel.jasper"));
			parametros.put("SUB_BENS_NOVO_DETALHE_AVALIACAO_MOVEL", recuperarRelatorio("FichaCadastralNova_BemNovo_Detalhes_Avaliacao_Movel.jasper"));
			parametros.put("SUB_BENS_NOVO_DETALHE_AVALIACAO_IMOVEL", recuperarRelatorio("FichaCadastralNova_BemNovo_Detalhes_Avaliacao_Imovel.jasper"));
			parametros.put("SUB_BENS_NOVO_DETALHE_AVALIACAO_IMOVEL_PARCEIRO", recuperarRelatorio("FichaCadastralNova_BemNovo_Detalhes_Imovel_parceiro.jasper"));
			parametros.put("SUB_BENS_NOVO_OUTRO", recuperarRelatorio("FichaCadastralNova_BemNovo_Outro.jasper"));
			//parametros.put("SUB_BENS_NOVO_OUTRO_DETALHE",null);
			parametros.put("SUB_BENS_NOVO_OUTRO_DETALHE", recuperarRelatorio("FichaCadastralNova_BemNovo_Outro_Detalhe.jasper"));
			parametros.put("DS_SUB_BENS_NOVO", criarDS(fichaCadastralVO.getBensNovos()));	

		} catch (JRException e) {
			throw new BancoobException(e);
		}
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
	protected abstract Object recuperarRelatorioDadosPessoais() throws JRException;

}