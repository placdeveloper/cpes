package br.com.sicoob.capes.relatorio.negocio.relatorios;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.EnderecoBase;
import br.com.sicoob.capes.negocio.entidades.vigente.Telefone;
import br.com.sicoob.capes.relatorio.negocio.vo.FichaCadastralVO;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;

public abstract class RelatorioFichaCadastralSimples extends RelatorioCapes{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int BANCOOB = 1;
	private final Short TELEFONE_RESIDENCIAL = 0;
	private final Short TELEFONE_COMERCIAL = 1;
	private final Short ENDERECO_RESIDENCIAL = 0;
	private final Short ENDERECO_COMERCIAL = 1;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected RelatorioFichaCadastralSimples(FichaCadastralVO fichaCadastralVO) throws BancoobException {
		super("FichaCadastralSimples.jasper");
		
		try {
			Map parametros = getParametros();
			
			if (fichaCadastralVO.getIdInstituicao() == BANCOOB) {
				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				getParametros().put("URL_LOGO", classLoader.getResource(
						"br/com/sicoob/capes/relatorio/bancoob.jpg"));
			}
			
			parametros.put("SUB_HEADER", recuperarRelatorio("CabecalhoFichaCadastral_NOVO.jasper"));
			parametros.put("pessoa", fichaCadastralVO.getPessoa());
			parametros.put("filiacao", fichaCadastralVO.getFiliacao());
			parametros.put("LABEL_FILTRO_DATA", fichaCadastralVO.getLabelFiltroData());
			parametros.put("NUM_COOPERATIVA", obterNumCooperativa(fichaCadastralVO.getIdInstituicao()));
			parametros.put("ID_UNIDADE_INST", fichaCadastralVO.getIdUnidadeInst());
			parametros.put("ID_INSTITUICAO", fichaCadastralVO.getIdInstituicao());
			parametros.put("DATA_ULTIMA_ATUALIZACAO", fichaCadastralVO.getDataUltimaAtualizacao());
			
			parametros.put("SUB_DADOS_PESSOAIS", recuperaRelatorioDadosPessoais());
			parametros.put("DS_SUB_DADOS_PESSOAIS", criarDS(fichaCadastralVO.getPessoasCompartilhamento()));
			parametros.put("CONJUGE", fichaCadastralVO.getConjuge());
			
			parametros.put("SUB_RELACIONAMENTOS", recuperarRelatorio("FichaCadastralSimples_Relacionamentos.jasper"));
			parametros.put("DS_SUB_RELACIONAMENTOS", criarDS(fichaCadastralVO.getRelacionamentos()));
			
			if(!fichaCadastralVO.getContatos().isEmpty()){
				parametros.put("TELEFONE_RESIDENCIAL", recuperaTelefoneResidencialPessoa(fichaCadastralVO.getContatos().get(0).getTelefones()));
				parametros.put("ENDERECO_RESIDENCIAL", recuperaEnderecoResidencialPessoa(fichaCadastralVO.getContatos().get(0).getEnderecos()));
				parametros.put("TELEFONE_COMERCIAL", recuperaTelefoneComercialPessoa(fichaCadastralVO.getContatos().get(0).getTelefones()));
				parametros.put("ENDERECO_COMERCIAL", recuperaEnderecoComercialPessoa(fichaCadastralVO.getContatos().get(0).getEnderecos()));
			}
			
			parametros.put("SUB_TRIBUTACAO", recuperarRelatorio("FichaCadastral_PerfilTributario.jasper"));
			parametros.put("DS_SUB_TRIBUTACAO", criarDS(fichaCadastralVO.getTributacoes()));
			
			parametros.put("TOTAL_RENDA_MENSAL", fichaCadastralVO.getTotalFontesDeRendaMensal());
			parametros.put("TOTAL_VALOR_PATRIMONIO", fichaCadastralVO.getTotalParticipacao());
			
			parametros.put("SUB_REFERENCIAS", recuperarRelatorio("FichaCadastralSimples_Referencias.jasper"));
			parametros.put("DS_SUB_REFERENCIAS", criarDS(fichaCadastralVO.getReferencias()));
			
			if (StringUtils.isNotBlank(fichaCadastralVO.getLoginUsuarioAprovacao()) && 
					StringUtils.isNotBlank(fichaCadastralVO.getLoginUsuarioEnvioAprovacao())) {
				parametros.put("LOGIN_USUARIO_ENVIO_APROVACAO", fichaCadastralVO.getLoginUsuarioEnvioAprovacao());
				parametros.put("LOGIN_USUARIO_APROVACAO", fichaCadastralVO.getLoginUsuarioAprovacao());
			}
			
		} catch (JRException e) {
			throw new BancoobException(e);
		}
	}

	@Override
	protected JRDataSource getDataSource() throws BancoobException {
		return new JREmptyDataSource();
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
	
	private Integer obterNumCooperativa(Integer idInstituicao) throws BancoobException {
		SCIIntegracaoDelegate sciDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
		return sciDelegate.obterNumeroCooperativa(idInstituicao);
	}
	
	protected abstract Object recuperaRelatorioDadosPessoais() throws JRException;
	
	protected abstract EnderecoBase recuperaEnderecoResidencialPessoa(List<EnderecoBase> enderecos) throws BancoobException;
	
	protected abstract Telefone recuperaTelefoneResidencialPessoa(List<Telefone> telefones) throws BancoobException;
	
	protected abstract EnderecoBase recuperaEnderecoComercialPessoa(List<EnderecoBase> enderecos) throws BancoobException;
	
	protected abstract Telefone recuperaTelefoneComercialPessoa(List<Telefone> telefones) throws BancoobException;

	public Short getTelefoneResidencial() {
		return TELEFONE_RESIDENCIAL;
	}

	public Short getTelefoneComercial() {
		return TELEFONE_COMERCIAL;
	}

	public Short getEnderecoResidencial() {
		return ENDERECO_RESIDENCIAL;
	}

	public Short getEnderecoComercial() {
		return ENDERECO_COMERCIAL;
	}
}
