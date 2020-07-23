package br.com.sicoob.capes.cadastro.fachada;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJBTransactionRolledbackException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import org.apache.commons.beanutils.BeanComparator;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.excecao.ViolacaoIntegridadeException;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.capes.cadastro.helper.ParametroPilotoHelper;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizacaoCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.CertidaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.EntidadeCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.vo.DefinicoesComponenteGedVO;
import br.com.sicoob.capes.comum.util.Constantes.Negocio;
import br.com.sicoob.capes.negocio.entidades.vigente.Certidao;

/**
 * Fachada responsavel por disponibilizar os serviços de certidao.
 *
 * @author Juan.Damasceno
 */
@RemoteService
public class CertidaoFachada extends EntidadeCadastroFachada<Certidao> {
	
	/** O atributo fabrica. */
	private CAPESCadastroFabricaDelegates fabrica = CAPESCadastroFabricaDelegates.getInstance();
	
	/** O atributo certidaoDelegate. */
	private CertidaoDelegate certidaoDelegate = obterFabricaDelegates().criarCertidaoDelegate();
	
	/** O atributo autorizacaoCadastroDelegate. */
	private AutorizacaoCadastroDelegate autorizacaoCadastroDelegate = fabrica
			.criarAutorizacaoCadastroDelegate();
	
	/** A constante CHAVE_CERTIDAO. */
	private static final String CHAVE_CERTIDAO = "certidao";

	/**
	 * Instancia um novo CertidaoFachada.
	 */
	public CertidaoFachada() {
		super(CHAVE_CERTIDAO);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EntidadeCadastroDelegate<Certidao, ?> obterDelegate() {
		return certidaoDelegate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Certidao obterEntidade(RequisicaoReqDTO dto) {
		return (Certidao) dto.getDados().get(chaveMapa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public DadosSelGeralDTO obterDadosSelecao(SelGeralReqDTO dto)throws BancoobException {
		try {
			dto.setNaoPaginar(true);
			ConsultaDto<Certidao> consultaDto = montarConsultaDto(dto, Certidao.class);
			
			DadosSelGeralDTO dadosSelGeralDTO = montarResultado(obterDelegate().pesquisar(consultaDto));
			List<Certidao> lista = (List<Certidao>) dadosSelGeralDTO.getDados().get("lista");
			Collections.sort(lista, new BeanComparator<Certidao>("idRegistroControlado"));
			dadosSelGeralDTO.getDados().put("lista", lista);
			
			return dadosSelGeralDTO;
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
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		
		try {
			RetornoDTO retornoDTO = super.obterDefinicoes(dto);
			retornoDTO.getDados().put("dataHoraServidor", DataUtil.obterDataAtual());
			
			retornoDTO.getDados().put("definicoesComponenteGED", montarObjetoDefinicoesGED(Short.parseShort(((Integer) dto.getDados().get("idTipoPessoa")).toString())));
			retornoDTO.getDados().put("pilotoHabilitado", ParametroPilotoHelper.isPilotoHabilitado());
			
			return retornoDTO;
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
	 * Calcula o número de páginas.
	 *
	 * @param tamanhoPagina
	 * @param totalRegistros
	 * @return o número de páginas.
	 */
	@Override
	protected int calcularNumeroPaginas(Integer tamanhoPagina, Integer totalRegistros) {
		return 1;
	}
	
	/**
	 * Montar objeto definicoes ged.
	 *
	 * @param idTipoPessoa o valor de id tipo pessoa
	 * @return DefinicoesComponenteGedVO
	 */
	private DefinicoesComponenteGedVO montarObjetoDefinicoesGED(Short idTipoPessoa){
		DefinicoesComponenteGedVO vo = new DefinicoesComponenteGedVO();
		Set<String> chavesNegocio = new LinkedHashSet<String>();
		
		vo.setIdTipoPessoa(idTipoPessoa);
		vo.setSiglaTipoDocumento(Negocio.GED_SIGLA_TIPO_DOCUMENTO_CERTIDAO);
		
		chavesNegocio.add(Negocio.GED_SIGLA_CHAVE_DOCUMENTO_GRUPO_COMPARTILHAMENTO);
		chavesNegocio.add(Negocio.GED_SIGLA_CHAVE_DOCUMENTO_TIPO_CERTIDAO);
		chavesNegocio.add(Negocio.GED_SIGLA_CHAVE_DOCUMENTO_NUMERO_DOCUMENTO);
		
		vo.setChavesNegocio(chavesNegocio);
		
		return vo;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO obterDados(RequisicaoReqDTO dto) throws BancoobException {
		try {
			RetornoDTO retorno = super.obterDados(dto);
			
			//Verifica se o registro está bloqueado para alteração.
			Certidao certidao = (Certidao) retorno.getDados().get(CHAVE_CERTIDAO);
			certidao.setDocumentosComprobatorios(autorizacaoCadastroDelegate
					.obterDocumentosComprobatorios(certidao));
			retorno.getDados().put(CHAVE_CERTIDAO, certidao);
			retorno.getDados().put("isRegistroBloqueadoAlteracao",
					autorizacaoCadastroDelegate.isRegistroBloqueadoAlteracao(certidao));
			
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
}