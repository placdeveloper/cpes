package br.com.sicoob.capes.cadastro.fachada;

import java.util.List;

import javax.ejb.EJBTransactionRolledbackException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.excecao.ViolacaoIntegridadeException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroCrudDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.MensagemDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoDestinoExibicaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoMensagemDelegate;
import br.com.sicoob.capes.negocio.entidades.Mensagem;
import br.com.sicoob.capes.negocio.entidades.MensagemBase;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.TipoDestinoExibicao;

/**
 * Fachada reponsável por disponibilizar os servicos para o UC Mensagem
 * individual.
 * 
 * @author juan.damasceno
 */
@RemoteService
public class MensagemFachada extends CAPESCadastroBOCrudFachada<Mensagem> {

	/** A constante CHAVE_MENSAGEM. */
	private static final String CHAVE_MENSAGEM = "mensagem";
	
	/** A constante TIPO_DESTIONO_EXIBICAO. */
	private static final String TIPO_DESTIONO_EXIBICAO = "tipoDestinoExibicao";
	
	/** O atributo mensagemDelegate. */
	private MensagemDelegate mensagemDelegate = obterFabricaDelegates().criarMensagemDelegate();
	
	/** O atributo tipoMensagemDelegate. */
	private TipoMensagemDelegate tipoMensagemDelegate = obterFabricaDelegates().criarTipoMensagemDelegate();
	
	/** O atributo tipoDestinoExibicaoDelegate. */
	private TipoDestinoExibicaoDelegate tipoDestinoExibicaoDelegate = obterFabricaDelegates().criarTipoDestinoExibicaoDelegate();

	/**
	 * Instancia um novo MensagemFachada.
	 */
	public MensagemFachada() {
		super(CHAVE_MENSAGEM);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESCadastroCrudDelegate<Mensagem, br.com.sicoob.capes.cadastro.negocio.servicos.MensagemServico> obterDelegate() {
		return mensagemDelegate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO excluirDados(RequisicaoReqDTO dto) throws BancoobException {
		try {
			Mensagem entidade = obterEntidade(dto);
			obterDelegate().excluir(entidade.getId());
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
		return new RetornoDTO();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO incluirDados(RequisicaoReqDTO dto) throws BancoobException {
		try {
			Mensagem entidade = obterEntidade(dto);
			entidade.setDataHoraInicio(new DateTimeDB());
			return super.incluirDados(dto);
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
	 * Obter definicoes.
	 *
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterDefinicoes() throws BancoobException {
		try {
			RetornoDTO retorno = new RetornoDTO();
			retorno.getDados().put("tiposDestinoExibicao", tipoDestinoExibicaoDelegate.listar());
			return retorno;
		} catch (NullPointerException e) {
			registrarLogNullPointerException(e, new RequisicaoReqDTO());
		} catch (ViolacaoIntegridadeException e) {
			registrarLogViolacaoIntegridadeException(e, new RequisicaoReqDTO());
		} catch (EJBTransactionRolledbackException e) {
			registrarLogTransactionRolledbackException(e, new RequisicaoReqDTO());
		} catch (EntityNotFoundException e) {
			registrarLogEntityNotFoundException(e, new RequisicaoReqDTO());
		} catch (PersistenceException e) {
			registrarLogPersistenceException(e, new RequisicaoReqDTO());
		} catch (NegocioException e) {
			throw(e);
		} catch (Exception e) {//nosonar
			registrarLogException(e, new RequisicaoReqDTO());
		} 
		return new RetornoDTO();
	}

	/**
	 * Obter tipo mensagem.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterTipoMensagem(RequisicaoReqDTO dto) throws BancoobException {
		TipoDestinoExibicao tipoDestinoExibicao = (TipoDestinoExibicao) dto.getDados().get(TIPO_DESTIONO_EXIBICAO);
		RetornoDTO retorno = new RetornoDTO();
		retorno.getDados().put("tiposMensagem", tipoMensagemDelegate.listaDeTipoMensagensDoTipoDestinoExibicao(tipoDestinoExibicao.getCodTipoDestinoExibicao()));
		return retorno;
	}

	/**
	 * Obter tipo mensagens destino exibicao.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterTipoMensagensDestinoExibicao(RequisicaoReqDTO dto) throws BancoobException {
		MensagemBase mensagem = (MensagemBase) dto.getDados().get(CHAVE_MENSAGEM);
		RetornoDTO retorno = new RetornoDTO();

		retorno.getDados().put("tiposDestinoExibicao", tipoDestinoExibicaoDelegate.listar());
		retorno.getDados().put("tiposMensagem", tipoMensagemDelegate.listaDeTipoMensagensDoTipoDestinoExibicao(mensagem.getCodigoTipoDestinoExibicao()));
		retorno.getDados().put("mensagem", mensagem);

		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<Mensagem> listar(RequisicaoReqDTO dto) throws BancoobException {
		ConsultaDto<Mensagem> criterios = new ConsultaDto<Mensagem>();
		Mensagem mensagem = new Mensagem();
		mensagem.setPessoa((Pessoa) dto.getDados().get(PESSOA));
		mensagem.setIdInstituicao(Integer.valueOf(obterUsuarioLogado().getIdInstituicao()));
		criterios.setFiltro(mensagem);
		
		return obterDelegate().pesquisar(criterios).getResultado();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Mensagem obterEntidade(RequisicaoReqDTO dto) {
		return (Mensagem) dto.getDados().get(chaveMapa);
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
}