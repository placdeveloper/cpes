package br.com.sicoob.capes.cadastro.fachada;

import java.util.List;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroCrudDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.EmpreendimentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.FinalidadeEmpreendimentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.UnidadeMedidaDelegate;
import br.com.sicoob.capes.negocio.entidades.Empreendimento;

/**
 * Fachada responsavel por disponibilizar os serviços de empreendimento.
 *
 * @author diego.rezende
 */
@RemoteService
public class EmpreendimentoFachada extends CAPESCadastroBOCrudFachada<Empreendimento> {
	
	/** A constante CHAVE_CERTIDAO. */
	private static final String CHAVE_CERTIDAO = "empreendimento";
	
	/** O atributo certidaoDelegate. */
	private EmpreendimentoDelegate certidaoDelegate = obterFabricaDelegates().criarEmpreendimentoDelegate();
	
	/** O atributo unidadeMedidaDelegate. */
	private UnidadeMedidaDelegate unidadeMedidaDelegate = obterFabricaDelegates().criarUnidadeMedidaDelegate();
	
	/** O atributo finalidadeEmpreendimentoDelegate. */
	private FinalidadeEmpreendimentoDelegate finalidadeEmpreendimentoDelegate = obterFabricaDelegates().criarFinalidadeEmpreendimentoDelegate();

	/**
	 * Instancia um novo EmpreendimentoFachada.
	 */
	public EmpreendimentoFachada() {
		super(CHAVE_CERTIDAO);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Empreendimento obterEntidade(RequisicaoReqDTO dto) {
		return (Empreendimento) dto.getDados().get(chaveMapa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESCadastroCrudDelegate<Empreendimento, ?> obterDelegate() {
		return certidaoDelegate;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public DadosSelGeralDTO obterDadosSelecao(SelGeralReqDTO dto)
			throws BancoobException {
		dto.setNaoPaginar(true);
		ConsultaDto<Empreendimento> consultaDto = montarConsultaDto(dto, Empreendimento.class);
		DadosSelGeralDTO dadosSelGeralDTO = montarResultado(obterDelegate().pesquisar(consultaDto));
		List<Empreendimento> lista = (List<Empreendimento>) dadosSelGeralDTO.getDados().get("lista");
		dadosSelGeralDTO.getDados().put("lista", lista);
		return dadosSelGeralDTO;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		Map<String, Object> dados = retorno.getDados();
		dados.put("listaFinalidades", finalidadeEmpreendimentoDelegate.listar());
		dados.put("listaUnidade", unidadeMedidaDelegate.listar());
		dados.put("codigo", certidaoDelegate.pesquisarProximoCodigo());
		return retorno;
	}

	/**
	 * Define o valor de certidaoDelegate.
	 *
	 * @param certidaoDelegate o novo valor de certidaoDelegate
	 */
	protected void setCertidaoDelegate(EmpreendimentoDelegate certidaoDelegate) {
		this.certidaoDelegate = certidaoDelegate;
	}

	/**
	 * Define o valor de unidadeMedidaDelegate.
	 *
	 * @param unidadeMedidaDelegate o novo valor de unidadeMedidaDelegate
	 */
	protected void setUnidadeMedidaDelegate(UnidadeMedidaDelegate unidadeMedidaDelegate) {
		this.unidadeMedidaDelegate = unidadeMedidaDelegate;
	}

	/**
	 * Define o valor de finalidadeEmpreendimentoDelegate.
	 *
	 * @param finalidadeEmpreendimentoDelegate o novo valor de finalidadeEmpreendimentoDelegate
	 */
	protected void setFinalidadeEmpreendimentoDelegate(
			FinalidadeEmpreendimentoDelegate finalidadeEmpreendimentoDelegate) {
		this.finalidadeEmpreendimentoDelegate = finalidadeEmpreendimentoDelegate;
	}
}
