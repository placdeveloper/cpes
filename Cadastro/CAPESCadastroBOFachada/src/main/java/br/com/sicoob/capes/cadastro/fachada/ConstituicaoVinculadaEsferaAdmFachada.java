package br.com.sicoob.capes.cadastro.fachada;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroCrudDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoFormaConstituicaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoFormaConstituicaoEsferaAdministrativaDelegate;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.negocio.entidades.TipoFormaConstituicao;
import br.com.sicoob.capes.negocio.entidades.TipoFormaConstituicaoEsferaAdministrativa;
import br.com.sicoob.capes.negocio.entidades.pk.TipoFormaConstituicaoEsferaAdministrativaPK;

/**
 * A Classe ConstituicaoVinculadaEsferaAdmFachada.
 */
@RemoteService
public class ConstituicaoVinculadaEsferaAdmFachada extends CAPESCadastroBOCrudFachada<TipoFormaConstituicao> {

	/** O atributo fabricaIntegracao. */
	private transient CAPESIntegracaoFabricaDelegates fabricaIntegracao = CAPESIntegracaoFabricaDelegates.getInstance();

	/** O atributo tipoFormaConstituicaoDelegate. */
	private TipoFormaConstituicaoDelegate tipoFormaConstituicaoDelegate = CAPESCadastroFabricaDelegates.getInstance().criarTipoFormaConstituicaoDelegate();

	/** O atributo tipoFormaConstituicaoEsferaAdministrativaDelegate. */
	private TipoFormaConstituicaoEsferaAdministrativaDelegate tipoFormaConstituicaoEsferaAdministrativaDelegate = CAPESCadastroFabricaDelegates.getInstance()
			.criarTipoFormaConstituicaoEsferaAdministrativaDelegate();

	/**
	 * Instancia um novo ConstituicaoVinculadaEsferaAdmFachada.
	 */
	public ConstituicaoVinculadaEsferaAdmFachada() {
		super("tipoFormaConstituicao");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESCadastroCrudDelegate<TipoFormaConstituicao, ?> obterDelegate() {
		return this.tipoFormaConstituicaoDelegate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoFormaConstituicao obterEntidade(RequisicaoReqDTO dto) {
		return (TipoFormaConstituicao) dto.getDados().get(this.chaveMapa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		Map<String, Object> dados = retorno.getDados();

		dados.put("esferasAdministrativas", fabricaIntegracao.criarADMIntegracaoDelegate().listarEsferasAdministrativas());

		return retorno;
	}

	/**
	 * Consultar constituicao vinculada.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO consultarConstituicaoVinculada(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		Integer codigo = (Integer) dto.getDados().get("codigo");
		List<TipoFormaConstituicaoEsferaAdministrativa> listaRetorno = consultarTipoContituicaoEsferaAdm(codigo);

		retorno.getDados().put("listaConstituicaoVinculada", listaRetorno);

		return retorno;
	}

	/**
	 * Consultar tipo contituicao esfera adm.
	 *
	 * @param codigo o valor de codigo
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<TipoFormaConstituicaoEsferaAdministrativa> consultarTipoContituicaoEsferaAdm(Integer codigo) throws BancoobException {
		ConsultaDto<TipoFormaConstituicaoEsferaAdministrativa> consultaDto = prepararConsultaConstituicaoVinculada(codigo.shortValue());
		List<TipoFormaConstituicaoEsferaAdministrativa> listaRetorno = tipoFormaConstituicaoEsferaAdministrativaDelegate.listar(consultaDto);
		return listaRetorno;
	}
	
	/**
	 * Salvar constituicao vinculada esfera adm.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@SuppressWarnings("unchecked")
	public RetornoDTO salvarConstituicaoVinculadaEsferaAdm(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		
		List<TipoFormaConstituicao> listaConstituicaoSelecionados = (List<TipoFormaConstituicao>) dto.getDados().get("constituicoesSelecionados");
		Integer codigoEsfera = (Integer) dto.getDados().get("codigo");
		
		tipoFormaConstituicaoEsferaAdministrativaDelegate.gravarAlteracoes(codigoEsfera, listaConstituicaoSelecionados);
		
		return retorno;
	}
	
	/**
	 * Consultar constituicao vinculada alteracao.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO consultarConstituicaoVinculadaAlteracao(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		Map<String, Object> dados = retorno.getDados();
		
		Integer codigo = (Integer) dto.getDados().get("codigo");
		
		List<TipoFormaConstituicaoEsferaAdministrativa> listaTipoFormaConstituicaoEsferaAdministrativas = consultarTipoContituicaoEsferaAdm(codigo);
		
		List<TipoFormaConstituicao> listaTipoFormaConstituicoesDisponiveis = tipoFormaConstituicaoDelegate.listar();
		List<TipoFormaConstituicao> listaTipoFormaConstituicoesVinculadas = verificarFormasVinculadas(listaTipoFormaConstituicaoEsferaAdministrativas, listaTipoFormaConstituicoesDisponiveis);
		
		dados.put("listaTipoFormaConstituicoesDisponiveis", listaTipoFormaConstituicoesDisponiveis);
		dados.put("listaTipoFormaConstituicoesVinculadas", listaTipoFormaConstituicoesVinculadas);
		
		return retorno;
	}

	/**
	 * Verificar formas vinculadas.
	 *
	 * @param listaTipoFormaConstituicaoEsferaAdministrativas o valor de lista tipo forma constituicao esfera administrativas
	 * @param listaTipoFormaConstituicoesDisponiveis o valor de lista tipo forma constituicoes disponiveis
	 * @return List
	 */
	private List<TipoFormaConstituicao> verificarFormasVinculadas(
			List<TipoFormaConstituicaoEsferaAdministrativa> listaTipoFormaConstituicaoEsferaAdministrativas,
			List<TipoFormaConstituicao> listaTipoFormaConstituicoesDisponiveis) {
		
		List<TipoFormaConstituicao> listaTipoFormaConstituicoesVinculadas = new ArrayList<TipoFormaConstituicao>();
		List<TipoFormaConstituicao> listaTipoFormaConstituicoesDisponiveisTemp = new ArrayList<TipoFormaConstituicao>(listaTipoFormaConstituicoesDisponiveis);
		
		for (TipoFormaConstituicao tipoFormaConstituicao : listaTipoFormaConstituicoesDisponiveisTemp) {
			for (TipoFormaConstituicaoEsferaAdministrativa tipoFormaEsfera : listaTipoFormaConstituicaoEsferaAdministrativas) {
				if(tipoFormaEsfera.getTipoFormaConstituicao().getCodigo().equals(tipoFormaConstituicao.getCodigo())) {
					listaTipoFormaConstituicoesVinculadas.add(tipoFormaConstituicao);
					listaTipoFormaConstituicoesDisponiveis.remove(tipoFormaConstituicao);
				}
			}
		}
		
		return listaTipoFormaConstituicoesVinculadas;
	}

	/**
	 * Preparar consulta constituicao vinculada.
	 *
	 * @param codigo o valor de codigo
	 * @return ConsultaDto
	 */
	private ConsultaDto<TipoFormaConstituicaoEsferaAdministrativa> prepararConsultaConstituicaoVinculada(Short codigo) {
		ConsultaDto<TipoFormaConstituicaoEsferaAdministrativa> consultaDto = new ConsultaDto<TipoFormaConstituicaoEsferaAdministrativa>();
		TipoFormaConstituicaoEsferaAdministrativa filtro = new TipoFormaConstituicaoEsferaAdministrativa();
		TipoFormaConstituicaoEsferaAdministrativaPK id = new TipoFormaConstituicaoEsferaAdministrativaPK();
		id.setCodigoEsferaAdministrativa(codigo);
		filtro.setId(id);
		consultaDto.setFiltro(filtro);
		return consultaDto;
	}

}
