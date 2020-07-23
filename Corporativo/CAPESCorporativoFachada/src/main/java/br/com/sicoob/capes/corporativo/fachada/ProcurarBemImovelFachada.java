package br.com.sicoob.capes.corporativo.fachada;

import javax.ejb.EJBTransactionRolledbackException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.excecao.ViolacaoIntegridadeException;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.api.negocio.delegates.BemImovelDelegate;
import br.com.sicoob.capes.api.negocio.delegates.CAPESApiFabricaDelegates;
import br.com.sicoob.capes.api.negocio.filtros.FiltroBemImovel;
import br.com.sicoob.capes.api.negocio.filtros.FiltroConsultaAPIAbstrato;
import br.com.sicoob.capes.api.negocio.vo.BemImovelVO;
import br.com.sicoob.capes.api.negocio.vo.BemVO;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoRelacionamentoBemImovelDelegate;
import br.com.sicoob.capes.negocio.entidades.bem.TipoLocalizacaoBem;
import br.com.sicoob.capes.negocio.entidades.bem.TipoUsoBemImovel;

/**
 * Fachada do componente de pesquisa de bem imóvel.
 *
 * @author bruno.carneiro
 */
@RemoteService
public class ProcurarBemImovelFachada extends ProcurarBemFachada {
	
	/**
	 * Método construtor
	 */
	public ProcurarBemImovelFachada() {
		super("bemImovel");
	}
	
	public DadosSelGeralDTO obterDadosSelecao(SelGeralReqDTO dto) throws BancoobException {
		try {
			ConsultaDto<BemImovelVO> consultaDto = new ConsultaDto<BemImovelVO>();
			popularConsultaDto(consultaDto, dto);
			ConsultaDto<BemImovelVO> resultado = obterImovelDelegate().pesquisarPaginado(obterFiltro(consultaDto), consultaDto.getPagina(), consultaDto.getTamanhoPagina());
			return montarResultado(resultado);
		} catch (NullPointerException e) {
			registrarLogNullPointerException(e, dto);
		} catch (ViolacaoIntegridadeException e) {
			registrarLogViolacaoIntegridadeException(e, dto);
		} catch (EJBTransactionRolledbackException e) {
			registrarLogTransactionRolledbackException(e, dto);
		} catch (EntityNotFoundException e) {
			registrarLogEntityNotFoundException(e, dto);
		} catch (PersistenceException e) {
			registrarLogPersistenceException(e, dto);			
		} catch (NegocioException e) {
			throw(e);
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		} 
		return new DadosSelGeralDTO();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	protected <T extends FiltroConsultaAPIAbstrato> T obterFiltro(ConsultaDto<? extends BemVO> consultaDto) {
		BemImovelVO bemImovelVO = (BemImovelVO) consultaDto.getFiltro();
		
		FiltroBemImovel filtro = new FiltroBemImovel();
		filtro.setCodigoTipoBem(bemImovelVO.getCodigoTipoBem());
		filtro.setCodigoTipoLocalizacaoBem(bemImovelVO.getCodigoTipoLocalizacaoBem());
		filtro.setCodigoTipoUsoBem(bemImovelVO.getCodigoTipoUsoBem());
		filtro.setDenominacao(bemImovelVO.getDenominacao());
		filtro.setDescricao(bemImovelVO.getDescricao());
		filtro.setIdBem(bemImovelVO.getIdBem());
		filtro.setIncra(bemImovelVO.getIncra());
		filtro.setMatricula(bemImovelVO.getMatricula());
		filtro.setNirf(bemImovelVO.getNirf());
		return (T) filtro;
	}
	
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		try {
			RetornoDTO retorno = new RetornoDTO();
			retorno.getDados().put("listaTiposLocalizacao", obterFabricaCadastroDelegate().criarTipoLocalizacaoBemDelegate().listar());
//		retorno.getDados().put("listaTipoLogradouro", IntegracaoUtil.converterTiposLogradouro(CAPESIntegracaoFabricaDelegates.getInstance().criarLOCIntegracaoDelegate().listarTiposLogradouro()));

			retorno.getDados().put("listaTiposImplantacao", obterFabricaCadastroDelegate().criarTipoImplantacaoBemImovelDelegate().listar());
			retorno.getDados().put("listaTiposEstadoConservacao", obterFabricaCadastroDelegate().criarTipoEstadoConservacaoDelegate().listar(Boolean.TRUE));
			retorno.getDados().put("listaTiposPadraoAcabamento", obterFabricaCadastroDelegate().criarTipoPadraoAcabamentoBemImovelDelegate().listar());
			retorno.getDados().put("listaTiposServicosCondominiais", obterFabricaCadastroDelegate().criarTipoServicoCondominialBemImovelDelegate().listar());

			retorno.getDados().put("listaTiposOnus", obterFabricaCadastroDelegate().criarTipoOnusBemDelegate().listar());
			
			retorno.getDados().put("definicoesComponenteGED", obterDefinicoesGED(dto));

			return retorno;
		} catch (NullPointerException e) {
			registrarLogNullPointerException(e, dto);
		} catch (ViolacaoIntegridadeException e) {
			registrarLogViolacaoIntegridadeException(e, dto);
		} catch (EJBTransactionRolledbackException e) {
			registrarLogTransactionRolledbackException(e, dto);
		} catch (EntityNotFoundException e) {
			registrarLogEntityNotFoundException(e, dto);
		} catch (PersistenceException e) {
			registrarLogPersistenceException(e, dto);			
		} catch (NegocioException e) {
			throw(e);
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		} 
		return new RetornoDTO();
	}

	/**
	 * Obtém os tipo de uso do bem a partir do {@code TipoLocalizacaoBem}
	 * 
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterTiposUsoBem(RequisicaoReqDTO dto) throws BancoobException {
		try {
			RetornoDTO retorno = new RetornoDTO();

			TipoLocalizacaoBem tipoLocalizacaoBem = (TipoLocalizacaoBem) dto.getDados().get("tipoLocalizacaoBem");

			ConsultaDto<TipoUsoBemImovel> criterios = new ConsultaDto<TipoUsoBemImovel>();
			TipoUsoBemImovel filtro = new TipoUsoBemImovel();
			filtro.setTipoLocalizacaoBem(tipoLocalizacaoBem);
			criterios.setFiltro(filtro);

			ConsultaDto<TipoUsoBemImovel> consulta = obterFabricaCadastroDelegate().criarTipoUsoBemDelegate().pesquisar(criterios);

			retorno.getDados().put("listaTiposUsoBem", consulta.getResultado());
			return retorno;
		} catch (NullPointerException e) {
			registrarLogNullPointerException(e, dto);
		} catch (ViolacaoIntegridadeException e) {
			registrarLogViolacaoIntegridadeException(e, dto);
		} catch (EJBTransactionRolledbackException e) {
			registrarLogTransactionRolledbackException(e, dto);
		} catch (EntityNotFoundException e) {
			registrarLogEntityNotFoundException(e, dto);
		} catch (PersistenceException e) {
			registrarLogPersistenceException(e, dto);			
		} catch (NegocioException e) {
			throw(e);
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		} 
		return new RetornoDTO();
	}

	/**
	 * Obtém as informações da tela de associar parceiro.
	 * 
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterDefinicoesAssociarParceiro(RequisicaoReqDTO dto) throws BancoobException {
		try {
			RetornoDTO retorno = new RetornoDTO();

			TipoRelacionamentoBemImovelDelegate tipoRelacionamentoBemImovelDelegate = CAPESCadastroFabricaDelegates.getInstance().criarTipoRelacionamentoBemImovelDelegate();
			retorno.getDados().put("listaTiposRelacionamento", tipoRelacionamentoBemImovelDelegate.listar());

			return retorno;
		} catch (NullPointerException e) {
			registrarLogNullPointerException(e, dto);
		} catch (ViolacaoIntegridadeException e) {
			registrarLogViolacaoIntegridadeException(e, dto);
		} catch (EJBTransactionRolledbackException e) {
			registrarLogTransactionRolledbackException(e, dto);
		} catch (EntityNotFoundException e) {
			registrarLogEntityNotFoundException(e, dto);
		} catch (PersistenceException e) {
			registrarLogPersistenceException(e, dto);			
		} catch (NegocioException e) {
			throw(e);
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		} 
		return new RetornoDTO();
	}

	/**
	 * Obtém a lista dos tipos de relacionamento.
	 * 
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterTipoRelacionamentos(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

//		Bem bem = (Bem) dto.getDados().get(CHAVE_BEM);
//		List<BemImovelTipoRelacionamentoPessoa> relacionamentos = obterDelegate().obterRelacionamentosBemImovel(bem.getIdBem());
//		retorno.getDados().put("relacionamentos", relacionamentos);

		return retorno;
	}

	protected BemImovelDelegate obterImovelDelegate() {
		return CAPESApiFabricaDelegates.getInstance().criarBemImovelDelegate();
	}

}