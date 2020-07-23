package br.com.sicoob.capes.corporativo.fachada;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJBTransactionRolledbackException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.excecao.ViolacaoIntegridadeException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.api.negocio.delegates.BemDelegate;
import br.com.sicoob.capes.api.negocio.delegates.CAPESApiFabricaDelegates;
import br.com.sicoob.capes.api.negocio.filtros.FiltroConsultaAPIAbstrato;
import br.com.sicoob.capes.api.negocio.vo.BemVO;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.vo.DefinicoesComponenteGedVO;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.comum.util.Constantes;

/**
 * Fachada base para o componente de pesquisa de bens.
 *
 * @author bruno.carneiro
 */
@RemoteService
public class ProcurarBemFachada extends CAPESCorporativoFachada {

	protected static final String CHAVE_BEM = "bem";
	protected String chaveMapa;
	
	public ProcurarBemFachada() {
		this(CHAVE_BEM);
	}

	/**
	 * Construtor padrão da fachada.
	 * 
	 * @param chaveMapa
	 */
	public ProcurarBemFachada(String chaveMapa) {
		super();
		this.chaveMapa = chaveMapa;
	}

	/**
	 * Método utilizado pelo componente de pesquisa de bens para recuperar o bem
	 * a partir do seu código.
	 * 
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterPorCodigo(RequisicaoReqDTO dto) throws BancoobException {
		try {
			RetornoDTO retorno = new RetornoDTO();

			Number idBem = (Number) dto.getDados().get("idBem");
			retorno.getDados().put(CHAVE_BEM, obterBemDelegate().obterPorIdBem(idBem.longValue()));

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

	public RetornoDTO obterProprietarios(RequisicaoReqDTO dto) throws BancoobException {
		try {
			RetornoDTO retorno = new RetornoDTO();
			Number idBem = (Number) dto.getDados().get("idBem");
			retorno.getDados().put("proprietarios", obterBemDelegate().obterProprietarios(idBem.longValue()));
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
	
	protected <T extends FiltroConsultaAPIAbstrato> T obterFiltro(ConsultaDto<? extends BemVO> consultaDto){
		return null;
	}
	
	protected BemDelegate obterBemDelegate() {
		return CAPESApiFabricaDelegates.getInstance().criarBemDelegate();
	}
	
	protected CAPESCadastroFabricaDelegates obterFabricaCadastroDelegate() {
		return CAPESCadastroFabricaDelegates.getInstance();
	}

	/**
	 * Obtém as definições do GED (Siglas das chaves de negócio).
	 * 
	 * @param dto
	 * @return
	 */
	protected List<DefinicoesComponenteGedVO> obterDefinicoesGED(RequisicaoReqDTO dto) {
		List<DefinicoesComponenteGedVO> listaDefinicoesGed = new ArrayList<DefinicoesComponenteGedVO>();

		Set<String> chavesNegocio = new LinkedHashSet<String>();
		chavesNegocio.add(Constantes.Negocio.GED_SIGLA_CHAVE_DOCUMENTO_GRUPO_COMPARTILHAMENTO);
		chavesNegocio.add(Constantes.Negocio.GED_CHAVE_TIPO_CLASSIFICACAO_BEM);
		chavesNegocio.add(Constantes.Negocio.GED_CHAVE_TIPO_BEM);

		List<String> listaSiglas = new ArrayList<String>();
		listaSiglas.add(Constantes.Negocio.GED_SIGLA_TIPO_DOCUMENTO_BEM);
		listaSiglas.add(Constantes.Negocio.GED_SIGLA_LAUDO_AVALIACAO_BEM);
		listaSiglas.add(Constantes.Negocio.GED_SIGLA_CERTIFICADO_CADASTRO_IMOVEL_RURAL);
		listaSiglas.add(Constantes.Negocio.GED_SIGLA_IMPOSTO_SOBRE_PROPRIEDADE_TERRITORIAL);
		listaSiglas.add(Constantes.Negocio.GED_SIGLA_CONTRATO_POSSE_USO_IMOVEL_BENEFICIADO);

		for (String sigla : listaSiglas) {
			DefinicoesComponenteGedVO definicaoComponenteGedVO = new DefinicoesComponenteGedVO();
			definicaoComponenteGedVO.setIdTipoPessoa(TipoPessoaEnum.PESSOA_FISICA.getCodigo());
			definicaoComponenteGedVO.setSiglaTipoDocumento(sigla);
			definicaoComponenteGedVO.setChavesNegocio(chavesNegocio);
			listaDefinicoesGed.add(definicaoComponenteGedVO);
		}
		
		return listaDefinicoesGed;
	}

}