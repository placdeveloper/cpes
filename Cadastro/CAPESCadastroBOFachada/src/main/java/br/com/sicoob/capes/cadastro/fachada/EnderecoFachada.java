/**
 * 
 */
package br.com.sicoob.capes.cadastro.fachada;

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
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.helper.ParametroPilotoHelper;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizacaoCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.EnderecoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoEnderecoDelegate;
import br.com.sicoob.capes.cadastro.negocio.proxies.EnderecoProxy;
import br.com.sicoob.capes.cadastro.negocio.vo.DefinicoesComponenteGedVO;
import br.com.sicoob.capes.cadastro.util.IntegracaoUtil;
import br.com.sicoob.capes.comum.negocio.enums.TipoEnderecoEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.comum.util.Constantes.Negocio;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.LOCIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.TipoEndereco;
import br.com.sicoob.capes.negocio.entidades.TipoPessoaContato;
import br.com.sicoob.capes.negocio.entidades.vigente.Endereco;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Fachada para os endereços.
 * 
 * @author Erico.Junior
 */
@RemoteService
public class EnderecoFachada extends EntidadeCadastroFachada<Endereco> {

	/** A constante ENDERECO. */
	private static final String ENDERECO = "endereco";

	/**
	 * Instancia um novo EnderecoFachada.
	 */
	public EnderecoFachada() {
		super(ENDERECO);
	}
	
	/** O atributo fabrica. */
	private CAPESCadastroFabricaDelegates fabrica = 
			CAPESCadastroFabricaDelegates.getInstance();
	
	/** O atributo delegate. */
	private EnderecoDelegate delegate = fabrica.criarEnderecoDelegate();
	
	/** O atributo tipoEnderecoDelegate. */
	private TipoEnderecoDelegate tipoEnderecoDelegate = fabrica.criarTipoEnderecoDelegate();
	
	/** O atributo autorizacaoCadastroDelegate. */
	private AutorizacaoCadastroDelegate autorizacaoCadastroDelegate =
			CAPESCadastroFabricaDelegates.getInstance()
					.criarAutorizacaoCadastroDelegate();

	/** O atributo localidadeDelegate. */
	private LOCIntegracaoDelegate localidadeDelegate = CAPESIntegracaoFabricaDelegates
					.getInstance().criarLOCIntegracaoDelegate();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EnderecoDelegate obterDelegate() {
		return delegate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Endereco obterEntidade(RequisicaoReqDTO dto) {
		return (Endereco) dto.getDados().get(ENDERECO);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		
		try {
			retorno.getDados().put("tiposEndereco", consultarTipoEndereco(dto));
			retorno.getDados().put("tiposLogradouro", IntegracaoUtil.converterTiposLogradouro(localidadeDelegate.listarTiposLogradouro()));
			retorno.getDados().put("definicoesComponenteGED", montarObjetoDefinicoesGED(Short.parseShort(((Integer) dto.getDados().get("idTipoPessoa")).toString())));
			retorno.getDados().put("pilotoHabilitado", ParametroPilotoHelper.isPilotoHabilitado());
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
	 * Consultar tipo endereco.
	 *
	 * @param dto o valor de dto
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<TipoEndereco> consultarTipoEndereco(RequisicaoReqDTO dto) throws BancoobException {
		ConsultaDto<TipoEndereco> criterios = new ConsultaDto<TipoEndereco>();
		TipoEndereco filtro = new TipoEndereco();
		TipoPessoaContato tipoPessoaContato = new TipoPessoaContato();
		tipoPessoaContato.setCodigo(Short.parseShort(((Integer) dto.getDados().get("idTipoPessoa")).toString()));
		filtro.setTipoPessoaContato(tipoPessoaContato);
		criterios.setFiltro(filtro);
		return tipoEnderecoDelegate.listarTipoEnderecoIncluindoAmbos(criterios);
	}
	
	/**
	 * Tornar padrao correspondencia.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO tornarPadraoCorrespondencia(RequisicaoReqDTO dto) throws BancoobException {
		
		try {
			Endereco endereco = obterEntidade(dto);
			if(isProdutosBancoob(dto)) {
				obterDelegate().tornarPadraoCorrespondencia(endereco, obterBancoob());
			} else {
				obterDelegate().tornarPadraoCorrespondencia(endereco);
			}
			
			return obterMapRetorno(this.chaveMapa, endereco);
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
	@SuppressWarnings("unchecked")
	public DadosSelGeralDTO obterDadosSelecao(RequisicaoReqDTO dto) throws BancoobException {
		try {
			PessoaCompartilhamento pessoa = (PessoaCompartilhamento) dto.getDados().get(PESSOA);
			Endereco endereco = new Endereco();
			endereco.setPessoaCompartilhamento(pessoa);
			ConsultaDto<Endereco> consulta = new ConsultaDto<Endereco>();
			consulta.setFiltro(endereco);
			
			List<EnderecoProxy> lista = null;
			if(isProdutosBancoob(dto)) {
				lista = obterDelegate().listarEnderecos(consulta, obterBancoob());
			} else {
				lista = obterDelegate().listarEnderecos(consulta);
			}
			
			DadosSelGeralDTO resultadoDto = new DadosSelGeralDTO();
			resultadoDto.getDados().put(NOME_PADRAO_LISTA, lista);
			return resultadoDto;
			
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
	 * Obter bancoob.
	 *
	 * @return Instituicao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private Instituicao obterBancoob() throws BancoobException {
		Instituicao instituicao = new Instituicao();
		instituicao.setIdInstituicao(Constantes.Comum.ID_INSTITUICAO_BANCOOB);
		return instituicao;
	}
	
	/**
	 * Verifica se eh produtos bancoob.
	 *
	 * @param dto o valor de dto
	 * @return {@code true}, se for produtos bancoob
	 */
	private Boolean isProdutosBancoob(RequisicaoReqDTO dto) {
		
		boolean produtosBancoob = false;
		Boolean produtos = (Boolean) dto.getDados().get(PRODUTOS_BANCOOB);;
		
		if(produtos != null) {
			produtosBancoob = produtos.booleanValue();
		}
		return produtosBancoob;
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
		vo.setSiglaTipoDocumento(Negocio.GED_SIGLA_TIPO_DOCUMENTO_COMPROVANTE_ENDERECO);
		
		chavesNegocio.add(Negocio.GED_SIGLA_CHAVE_DOCUMENTO_GRUPO_COMPARTILHAMENTO);
		chavesNegocio.add(Negocio.GED_SIGLA_CHAVE_DOCUMENTO_TIPO_ENDERECO);
		chavesNegocio.add(Negocio.GED_SIGLA_CHAVE_DOCUMENTO_CEP);
		
		vo.setChavesNegocio(chavesNegocio);
		
		return vo;
	} 

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO obterDados(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = super.obterDados(dto);

		try {
			// Verifica se o registro está bloqueado para alteração.
			Endereco endereco = (Endereco) retorno.getDados().get(ENDERECO);
			if (endereco != null) {
				endereco.setDocumentosComprobatorios(autorizacaoCadastroDelegate.obterDocumentosComprobatorios(endereco));
				retorno.getDados().put(ENDERECO, endereco);
				retorno.getDados().put("isRegistroBloqueadoAlteracao", autorizacaoCadastroDelegate.isRegistroBloqueadoAlteracao(endereco));
			}
			
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
	
	@Override
	public RetornoDTO excluirDados(RequisicaoReqDTO dto) throws BancoobException {
		try {
			Endereco entidade = validarEndereco(dto, true);
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
	
	@Override
	public RetornoDTO alterarDados(RequisicaoReqDTO dto) throws BancoobException {
		try {
			Endereco entidade = validarEndereco(dto, false);
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
		return null;
	}
	
	private TipoEnderecoEnum getTipoEnderecoObrigatorio(PessoaCompartilhamento pessoa) {
		if (TipoPessoaEnum.isPessoaJuridica(pessoa.getPessoa().getTipoPessoa().getCodTipoPessoa())) {
			return TipoEnderecoEnum.COMERCIAL;
		}
		return TipoEnderecoEnum.RESIDENCIAL;
	}
	
	private Endereco validarEndereco(RequisicaoReqDTO dto, boolean isExclusao) throws BancoobException {
		
		Endereco entidade = obterEntidade(dto);
		if (!ParametroPilotoHelper.isPilotoHabilitado()) {
			return entidade;
		}

		boolean vigente = isVigente(entidade);

		if (vigente) {
			
			if(entidade.getTipoEndereco()==null ){
				entidade.setTipoEndereco(delegate.obter(entidade.getId()).getTipoEndereco());
			}

			PessoaCompartilhamento pessoa = (PessoaCompartilhamento) dto.getDados().get(PESSOA);
			List<Endereco> enderecos = listarEnderecosPessoa(pessoa);
		
			TipoEnderecoEnum tipoEnderecoObrigatorio = getTipoEnderecoObrigatorio(pessoa);
			
			if (validarObrigatoriedade(tipoEnderecoObrigatorio, entidade, enderecos, isExclusao)) {
				if (isExclusao) {
					throw new NegocioException("Não é possível excluir este endereço, gentileza atualize as informações.");
				}
				if (TipoEnderecoEnum.COMERCIAL.equals(tipoEnderecoObrigatorio)){
					throw new NegocioException("Endereço Comercial é obrigatório para Pessoa Jurídica, gentileza atualize as informações.");	
				}
				throw new NegocioException("Endereço Residencial é obrigatório para Pessoa Física, gentileza atualize as informações.");
			}
		}
		return entidade;
	}

	private boolean validarObrigatoriedade(TipoEnderecoEnum tipoEndereco, Endereco entidade, 
									List<Endereco> enderecos, boolean isExclusao) throws NegocioException {
		boolean alterouTipo = false;
		boolean tipoAnteriorObrigatorio = false;
		int registrosRepetidos = 0;

		for (Endereco endereco : enderecos) {
			if (endereco.getId().equals(entidade.getId())) {
				alterouTipo = !endereco.getTipoEndereco().getCodigo().equals(entidade.getTipoEndereco().getCodigo());
				tipoAnteriorObrigatorio = tipoEndereco.getCodigo().equals(endereco.getTipoEndereco().getCodigo());
			}else if (isObrigatorio(tipoEndereco, endereco)){
				registrosRepetidos++;
			}
		}
		
		if (isExclusao) {
			if (enderecos.size() == 1) {
				return true;// é o ultimo da lista, porem é obrigatório
			}
			if (tipoAnteriorObrigatorio) {
				return (registrosRepetidos == 0);//
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
	
	private List<Endereco> listarEnderecosPessoa(PessoaCompartilhamento pessoa) throws BancoobException {
		Endereco endereco = new Endereco();
		endereco.setPessoaCompartilhamento(pessoa);

		ConsultaDto<Endereco> consulta = new ConsultaDto<Endereco>();
		consulta.setFiltro(endereco);
		
		return obterDelegate().listar(consulta);
	}
	
	private boolean isObrigatorio(TipoEnderecoEnum tipoEnderecoEnum, Endereco endereco) {
		return tipoEnderecoEnum.getCodigo().equals(endereco.getTipoEndereco().getCodigo());
	}
	
	private boolean isVigente(Endereco endereco) {
		return endereco.getDataHoraInicio() != null && 
				endereco.getIdInstituicaoAtualizacao() == null;
	}
}