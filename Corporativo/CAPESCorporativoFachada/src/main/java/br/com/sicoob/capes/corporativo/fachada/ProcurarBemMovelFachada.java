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
import br.com.sicoob.capes.api.negocio.delegates.BemMovelDelegate;
import br.com.sicoob.capes.api.negocio.delegates.CAPESApiFabricaDelegates;
import br.com.sicoob.capes.api.negocio.filtros.FiltroBemMovel;
import br.com.sicoob.capes.api.negocio.filtros.FiltroConsultaAPIAbstrato;
import br.com.sicoob.capes.api.negocio.vo.BemImovelVO;
import br.com.sicoob.capes.api.negocio.vo.BemMovelVO;
import br.com.sicoob.capes.api.negocio.vo.BemVO;

/**
 * Fachada do componente de pesquisa de bem móvel.
 *
 * @author bruno.carneiro
 */
@RemoteService
public class ProcurarBemMovelFachada extends ProcurarBemFachada {

	/**
	 * Construtor
	 */
	public ProcurarBemMovelFachada() {
		super("BemMovel");
	}

	public DadosSelGeralDTO obterDadosSelecao(SelGeralReqDTO dto) throws BancoobException {
		ConsultaDto<BemImovelVO> consultaDto = new ConsultaDto<BemImovelVO>();
		popularConsultaDto(consultaDto, dto);
		ConsultaDto<BemImovelVO> resultado = obterMovelDelegate().pesquisarPaginado(obterFiltro(consultaDto), consultaDto.getPagina(), consultaDto.getTamanhoPagina());
		return montarResultado(resultado);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	protected <T extends FiltroConsultaAPIAbstrato> T obterFiltro(ConsultaDto<? extends BemVO> consultaDto) {
		BemMovelVO bemMovelVO = (BemMovelVO) consultaDto.getFiltro();
		
		FiltroBemMovel filtro = new FiltroBemMovel();
		filtro.setCodigoTipoBem(bemMovelVO.getCodigoTipoBem());
		filtro.setInscricaoEmbarcacao(bemMovelVO.getInscricaoEmbarcacao());
		filtro.setMatriculaAeronave(bemMovelVO.getMatriculaAeronave());
		filtro.setNumeroChassi(bemMovelVO.getNumeroChassi());
		filtro.setPlaca(bemMovelVO.getPlaca());
		filtro.setRenavam(bemMovelVO.getRenavam());
		filtro.setUf(bemMovelVO.getUf());
		filtro.setDescricao(bemMovelVO.getDescricao());
		filtro.setIdBem(bemMovelVO.getIdBem());
		return (T) filtro;
	}
	
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		try {
			RetornoDTO retorno = new RetornoDTO();
			
//		retorno.getDados().put("ufs", listarUFs());
			retorno.getDados().put("listaTiposBem", obterFabricaCadastroDelegate().criarTipoBemMovelDelegate().listar());
			retorno.getDados().put("listaTiposChassi", obterFabricaCadastroDelegate().criarTipoChassiDelegate().listar());
			retorno.getDados().put("listaTiposCores", obterFabricaCadastroDelegate().criarTipoCorAutomovelDelegate().listar());
			retorno.getDados().put("listaTiposEstadoConservacao", obterFabricaCadastroDelegate().criarTipoEstadoConservacaoDelegate().listar(Boolean.FALSE));

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
	
//	/**
//	 * Obtém as ufs
//	 * 
//	 * @return
//	 * @throws BancoobException
//	 */
//	private List<UF> listarUFs() throws BancoobException {
//		LOCIntegracaoDelegate locDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarLOCIntegracaoDelegate();
//		List<UF> ufs = new ArrayList<UF>();
//		for (LOCIntegracaoUFVO vo : locDelegate.listarUFs()) {
//			ufs.add(IntegracaoUtil.converterUF(vo));
//		}
//		return ufs;
//	}
	
	protected BemMovelDelegate obterMovelDelegate() {
		return CAPESApiFabricaDelegates.getInstance().criarBemMovelDelegate();
	}

}