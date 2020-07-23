package br.com.sicoob.capes.cadastro.fachada;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.ReferenciaDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TelefoneDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoReferenciaDelegate;
import br.com.sicoob.capes.cadastro.negocio.vo.ContaVO;
import br.com.sicoob.capes.comum.negocio.vo.AgenciaCafVO;
import br.com.sicoob.capes.comum.negocio.vo.BancoCafVO;
import br.com.sicoob.capes.integracao.negocio.delegates.ADMIntegracaoDelegate;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.Referencia;
import br.com.sicoob.capes.negocio.entidades.vigente.Telefone;

/**
 * Fachada para as referencias.
 * 
 * @author juan.damasceno
 */
@RemoteService
public class ReferenciaFachada extends CAPESCadastroBOCrudFachada<Referencia> {

	/** A constante LISTA_TIPOS_REFERENCIA. */
	private static final String LISTA_TIPOS_REFERENCIA = "listaTiposReferencia";
	
	/** A constante REFERENCIA. */
	private static final String REFERENCIA = "referencia";

	/** O atributo tipoReferenciaDelegate. */
	private TipoReferenciaDelegate tipoReferenciaDelegate = CAPESCadastroFabricaDelegates
			.getInstance().criarTipoReferenciaDelegate();
	
	/** O atributo delegate. */
	private ReferenciaDelegate delegate = CAPESCadastroFabricaDelegates.getInstance()
			.criarReferenciaDelegate();
	
	/** O atributo telefoneDelegate. */
	private TelefoneDelegate telefoneDelegate = CAPESCadastroFabricaDelegates.getInstance()
			.criarTelefoneDelegate();
	
	/**
	 * Instancia um novo ReferenciaFachada.
	 */
	public ReferenciaFachada() {
		super(REFERENCIA);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		try {
			Map<String, Object> dados = retorno.getDados();
			dados.put(LISTA_TIPOS_REFERENCIA, tipoReferenciaDelegate.listar());
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
		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ReferenciaDelegate obterDelegate() {
		return this.delegate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Referencia obterEntidade(RequisicaoReqDTO dto) {
		return (Referencia) dto.getDados().get(REFERENCIA);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<Referencia> listar(RequisicaoReqDTO dto) throws BancoobException {
		PessoaCompartilhamento pessoa = (PessoaCompartilhamento) dto.getDados().get(PESSOA);
		ConsultaDto<Referencia> criterios = new ConsultaDto<Referencia>();
		Referencia filtro = new Referencia();
		filtro.setPessoa(pessoa);
		criterios.setFiltro(filtro);

		List<Referencia> lista = obterDelegate().listar(criterios);

		if (dto.getDados().containsKey("carregarTelPessoaRef")) {
			for (Referencia referencia : lista) {
				// busca o primeiro telefone da pessoa de referência para exibir na listagem
				if (referencia.getPessoaReferencia() != null) {
					Telefone telefone = new Telefone();
					telefone.setPessoaCompartilhamento(referencia.getPessoaReferencia());

					ConsultaDto<Telefone> consulta = new ConsultaDto<Telefone>();
					consulta.setFiltro(telefone);
					List<Telefone> telefones = telefoneDelegate.listar(consulta);
					if (!telefones.isEmpty()) {
						telefone = telefones.get(0);
						if (telefone.getDdd() != null) {
							referencia.setDdd(Short.valueOf(telefone.getDdd()));
						}
						referencia.setTelefone(telefone.getTelefone());
					}
				}
			}
		}
		return lista;
	}

	/**
	 * Obter agencia.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterAgencia(RequisicaoReqDTO dto)
			throws BancoobException {
		
		Short numAgencia = Short.parseShort((String) dto.getDados().get("NUM_AGENCIA"));
		Short numBanco =  Short.parseShort((String) dto.getDados().get("NUM_BANCO"));

		ADMIntegracaoDelegate admIntegracaoDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarADMIntegracaoDelegate();
		AgenciaCafVO agencia = admIntegracaoDelegate.obterAgenciaCaf(numBanco, numAgencia);
		
		RetornoDTO retornoDTO = new RetornoDTO();
		retornoDTO.setDados(new HashMap<String, Object>());
		
		if (agencia != null) {
			ContaVO contaVO = new ContaVO();
			contaVO.setNomeAgencia(agencia.getDescricaoAgencia());
			retornoDTO.getDados().put("agencia", contaVO);
		}
		
		return retornoDTO;
	}

	/**
	 * Obter banco.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterBanco(RequisicaoReqDTO dto) throws BancoobException {
		String numBanco = (String) dto.getDados().get("NUM_BANCO");

		ADMIntegracaoDelegate admIntegracaoDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarADMIntegracaoDelegate();
		BancoCafVO banco = admIntegracaoDelegate.obterBancoCaf(Short.parseShort(numBanco));
		
		ContaVO contaVO = null;
		RetornoDTO retornoDTO = new RetornoDTO();
		retornoDTO.setDados(new HashMap<String, Object>());
		
		if (banco != null) {
			contaVO = new ContaVO();
			contaVO.setNomeBanco(banco.getDescBanco());
			retornoDTO.getDados().put("banco", contaVO);
		}
		
		return retornoDTO;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO obterDados(RequisicaoReqDTO dto) throws BancoobException {
		
		try {
			Integer idReferencia = (Integer) dto.getDados().get("idReferencia");
			Referencia entidadePersistente = obterDelegate().obter(Long.valueOf(idReferencia));
			
			RetornoDTO retorno = obterMapRetorno(this.chaveMapa, entidadePersistente);
			
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
		return null; 
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO excluirDados(RequisicaoReqDTO dto) throws BancoobException {
		try {
			Referencia entidade = obterEntidade(dto);
			obterDelegate().excluirEntidade(entidade);
			return obterMapRetorno(this.chaveMapa, entidade);
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
		return null;
	}
	
}