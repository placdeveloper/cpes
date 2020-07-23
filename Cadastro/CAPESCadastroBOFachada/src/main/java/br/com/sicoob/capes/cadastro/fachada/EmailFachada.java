/**
 * 
 */
package br.com.sicoob.capes.cadastro.fachada;

import java.util.List;

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
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroCrudDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.EmailDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoEmailDelegate;
import br.com.sicoob.capes.negocio.entidades.TipoEmail;
import br.com.sicoob.capes.negocio.entidades.TipoPessoaContato;
import br.com.sicoob.capes.negocio.entidades.vigente.Email;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * Fachada para os emails.
 * 
 * @author Erico.Junior
 */
@RemoteService
public class EmailFachada extends CAPESCadastroBOCrudFachada<Email> {

	/** A constante TIPOS_EMAIL. */
	private static final String TIPOS_EMAIL = "tiposEmail";
	
	/** A constante EMAIL. */
	private static final String EMAIL = "email";
	
	/** A constante LOGGER. */
	protected static final SicoobLoggerPadrao LOGGER = SicoobLoggerPadrao
			.getInstance(EmailFachada.class);
	

	/**
	 * Instancia um novo EmailFachada.
	 */
	public EmailFachada() {
		super(EMAIL);
	}

	/** O atributo delegate. */
	private EmailDelegate delegate = CAPESCadastroFabricaDelegates.getInstance()
			.criarEmailDelegate();

	/** O atributo tipoEmailDelegate. */
	private TipoEmailDelegate tipoEmailDelegate = CAPESCadastroFabricaDelegates
			.getInstance().criarTipoEmailDelegate();

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<Email> listar(RequisicaoReqDTO dto) throws BancoobException {
		PessoaCompartilhamento pessoa = (PessoaCompartilhamento) dto.getDados().get(PESSOA);
		Email Email = new Email();
		Email.setPessoaCompartilhamento(pessoa);

		ConsultaDto<Email> consulta = new ConsultaDto<Email>();
		consulta.setFiltro(Email);
		
		return obterDelegate().listar(consulta);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESCadastroCrudDelegate<Email, ?> obterDelegate() {
		return delegate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Email obterEntidade(RequisicaoReqDTO dto) {
		return (Email) dto.getDados().get(EMAIL);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		try {
			RetornoDTO retorno = new RetornoDTO();
			retorno.getDados().put(TIPOS_EMAIL, consultarTipoEmail(dto));
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
	 * Consultar tipo email.
	 *
	 * @param dto o valor de dto
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<TipoEmail> consultarTipoEmail(RequisicaoReqDTO dto) throws BancoobException {
		Integer idTipoPessoa = (Integer) dto.getDados().get("idTipoPessoa");
		ConsultaDto<TipoEmail> criterios = new ConsultaDto<TipoEmail>();
		TipoEmail filtro = new TipoEmail();
		TipoPessoaContato tipoPessoaContato = new TipoPessoaContato();
		tipoPessoaContato.setCodigo(idTipoPessoa != null ? idTipoPessoa.shortValue() : null);
		filtro.setTipoPessoaContato(tipoPessoaContato);
		criterios.setFiltro(filtro);
		return tipoEmailDelegate.listarTipoEmailIncluindoAmbos(criterios);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO excluirDados(RequisicaoReqDTO dto) throws BancoobException {
		try {
			Email entidade = obterEntidade(dto);
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
		} catch (BancoobException e) {
			throw(e);
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		} 
		return new RetornoDTO();	
	}

}