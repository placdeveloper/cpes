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
import br.com.sicoob.capes.cadastro.helper.ParametroPilotoHelper;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroCrudDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.TelefoneDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoTelefoneDelegate;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoTelefoneEnum;
import br.com.sicoob.capes.negocio.entidades.TipoPessoaContato;
import br.com.sicoob.capes.negocio.entidades.TipoTelefone;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.Telefone;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * Fachada para os telefones
 * 
 * @author Erico.Junior
 */
@RemoteService
public class TelefoneFachada extends CAPESCadastroBOCrudFachada<Telefone> {

	/**
	 * Instancia um novo TelefoneFachada.
	 */
	public TelefoneFachada() {
		super("telefone");
	}

	/** O atributo delegate. **/
	private TelefoneDelegate delegate = CAPESCadastroFabricaDelegates.getInstance()
			.criarTelefoneDelegate();

	/** O atributo tipoTelefoneDelegate. */
	private TipoTelefoneDelegate tipoTelefoneDelegate = CAPESCadastroFabricaDelegates
			.getInstance().criarTipoTelefoneDelegate();
	
	/** A constante LOGGER. */
	protected static final SicoobLoggerPadrao LOGGER = SicoobLoggerPadrao
			.getInstance(TelefoneFachada.class);
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<Telefone> listar(RequisicaoReqDTO dto) throws BancoobException {
		PessoaCompartilhamento pessoa = (PessoaCompartilhamento) dto.getDados().get(PESSOA);
		Telefone Telefone = new Telefone();
		Telefone.setPessoaCompartilhamento(pessoa);

		ConsultaDto<Telefone> consulta = new ConsultaDto<Telefone>();
		consulta.setFiltro(Telefone);
		
		return obterDelegate().listar(consulta);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESCadastroCrudDelegate<Telefone, ?> obterDelegate() {
		return delegate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Telefone obterEntidade(RequisicaoReqDTO dto) {
		return (Telefone) dto.getDados().get(this.chaveMapa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		try {
			RetornoDTO retorno = new RetornoDTO();
			retorno.getDados().put("tiposTelefone", consultarTipoTelefone(dto));
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
	 * Consultar tipo telefone.
	 *
	 * @param dto o valor de dto
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<TipoTelefone> consultarTipoTelefone(RequisicaoReqDTO dto) throws BancoobException {
		Integer idTipoPessoa = (Integer) dto.getDados().get("idTipoPessoa");
		ConsultaDto<TipoTelefone> criterios = new ConsultaDto<TipoTelefone>();
		TipoTelefone filtro = new TipoTelefone();
		TipoPessoaContato tipoPessoaContato = new TipoPessoaContato();
		tipoPessoaContato.setCodigo(idTipoPessoa != null ? idTipoPessoa.shortValue() : null);
		filtro.setAtivo(Boolean.TRUE);
		filtro.setTipoPessoaContato(tipoPessoaContato);
		criterios.setFiltro(filtro);
		return tipoTelefoneDelegate.listarTipoTelefoneIncluindoAmbos(criterios);
	}
	
	
	@Override
	public RetornoDTO alterarDados(RequisicaoReqDTO dto) throws BancoobException {
		try {
			Telefone entidade = validarTelefone(dto, false);
			
			obterDelegate().alterar(entidade);
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
	public RetornoDTO excluirDados(RequisicaoReqDTO dto) throws BancoobException {
		try {
			Telefone entidade = validarTelefone(dto, true);
			
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
		return new RetornoDTO();
	}
	
	private Telefone validarTelefone(RequisicaoReqDTO dto, boolean isExclusao) throws BancoobException {
		
		Telefone entidade = obterEntidade(dto);
		if (!ParametroPilotoHelper.isPilotoHabilitado()) {
			return entidade;
		}

		PessoaCompartilhamento pessoa = (PessoaCompartilhamento) dto.getDados().get(PESSOA);
		List<Telefone> telefones = listar(dto);
	
		// Obtem o Padrão TipoTelefone
		TipoTelefoneEnum tipoTelefone = TipoTelefoneEnum.RESIDENCIAL;
		if (TipoPessoaEnum.isPessoaJuridica(pessoa.getPessoa().getTipoPessoa().getCodTipoPessoa())) {
			tipoTelefone = TipoTelefoneEnum.COMERCIAL;
		}
		if (isTipoObrigatorioNaoRepetido(tipoTelefone, entidade, telefones, isExclusao)) {
			if (isExclusao) {
				throw new NegocioException("Não é possível excluir este telefone, gentileza atualize as informações.");
			}
			
			if (TipoTelefoneEnum.COMERCIAL.equals(tipoTelefone)){
				throw new NegocioException("Telefone Comercial é obrigatório para Pessoa Jurídica, gentileza atualize as informações.");	
			}
			
			throw new NegocioException("Telefone Residencial é obrigatório para Pessoa Física, gentileza atualize as informações.");
		}
		return entidade;
	}
	
	private boolean isTipoObrigatorioNaoRepetido(TipoTelefoneEnum tipoTelefoneEnum, Telefone entidade,
			List<Telefone> telefones, boolean isExclusao) {
		
		boolean alterouTipo = false;
		boolean tipoAnteriorObrigatorio = false;
		int registrosRepetidos = 0;

		for (Telefone telefone : telefones) {
			if (telefone.getId().equals(entidade.getId())) {
				alterouTipo = !telefone.getTipoTelefone().getCodigo().equals(entidade.getTipoTelefone().getCodigo());
				tipoAnteriorObrigatorio = tipoTelefoneEnum.getCodigo().equals(telefone.getTipoTelefone().getCodigo());
			}else if (tipoTelefoneEnum.getCodigo().equals(telefone.getTipoTelefone().getCodigo())) {
				registrosRepetidos++;
			} 
		}
		
		if (isExclusao) {
			if (telefones.size() == 1) {
				return true;// é o ultimo da lista, porem é obrigatório
			}
			if (tipoAnteriorObrigatorio) {
				return(registrosRepetidos == 0);//
			}
		} else {
			// Não alterou o TIPO
			if (alterouTipo) {
				if (tipoAnteriorObrigatorio) {
					if (registrosRepetidos == 0) {
						// é obrigatorio
						return true;
					}
				}
			}
		} 
		return false;

	}
}