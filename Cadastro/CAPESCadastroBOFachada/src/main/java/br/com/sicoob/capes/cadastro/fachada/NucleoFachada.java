package br.com.sicoob.capes.cadastro.fachada;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.NucleoDelegate;
import br.com.sicoob.capes.negocio.entidades.Nucleo;

/**
 * A Classe NucleoFachada.
 */
@RemoteService
public class NucleoFachada extends
		CAPESCadastroBOCrudFachada<Nucleo> {

	/** O atributo delegateNucleo. */
	private final NucleoDelegate delegateNucleo = CAPESCadastroFabricaDelegates
			.getInstance().criarNucleoDelegate();
	
	/**
	 * Instancia um novo NucleoFachada.
	 */
	public NucleoFachada() {
		super("nucleo");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DadosSelGeralDTO obterDadosSelecao(SelGeralReqDTO dto)
			throws BancoobException {
		ConsultaDto<Nucleo> consultaDto = new ConsultaDto<Nucleo>();
		popularConsultaDto(consultaDto, dto);
		return montarResultado(obterDelegate().pesquisarPorInstituicao(consultaDto));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Nucleo obterEntidade(RequisicaoReqDTO dto) {
		return (Nucleo) dto.getDados().get(this.chaveMapa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected NucleoDelegate obterDelegate() {
		return this.delegateNucleo;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		retorno.getDados().put("codigo", this.delegateNucleo.pesquisarProximoCodigo());
		return retorno;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO excluirDados(RequisicaoReqDTO dto)
			throws BancoobException {
		Nucleo entidade = obterEntidade(dto);
		obterDelegate().excluir(entidade);
		return obterMapRetorno(this.chaveMapa, entidade);
	}
}
