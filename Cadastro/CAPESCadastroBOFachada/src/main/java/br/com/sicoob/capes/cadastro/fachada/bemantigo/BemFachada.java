package br.com.sicoob.capes.cadastro.fachada.bemantigo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJBTransactionRolledbackException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.excecao.ViolacaoIntegridadeException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.fachada.EntidadeCadastroFachada;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizacaoCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.bemantigo.BemAntigoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.bemantigo.BemImovelAntigoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.bemantigo.SituacaoBemAntigoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.bemantigo.SubTipoBemAntigoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.bemantigo.TipoBemAntigoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.bemantigo.TipoPosseBemAntigoDelegate;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoBemEnum;
import br.com.sicoob.capes.cadastro.negocio.vo.DefinicoesComponenteGedVO;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.negocio.entidades.bemantigo.Bem;
import br.com.sicoob.capes.negocio.entidades.bemantigo.SituacaoBem;
import br.com.sicoob.capes.negocio.entidades.bemantigo.SubTipoBem;
import br.com.sicoob.capes.negocio.entidades.bemantigo.TipoBem;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Fachada para o UC Manter bem.
 *
 * @author Juan.Damasceno
 */
@RemoteService
public class BemFachada extends EntidadeCadastroFachada<Bem> {

	/** A constante CHAVE_SITUACOES_BEM. */
	private static final String CHAVE_SITUACOES_BEM = "situacoesBem";
	
	/** A constante CHAVE_SUB_TIPOS_BEM. */
	private static final String CHAVE_SUB_TIPOS_BEM = "subTiposBem";
	
	/** A constante COD_BEM_IMOVEL. */
	private static final Short COD_BEM_IMOVEL = TipoBemEnum.IMOVEL.getIdTipoBem();
	
	/** A constante CHAVE_BEM. */
	private static final String CHAVE_BEM = "bem";

	/** O atributo bemImovelDelegate. */
	private BemImovelAntigoDelegate bemImovelDelegate = obterFabricaDelegates().criarBemImovelAntigoDelegate();
	
	/** O atributo tipoBemDelegate. */
	private TipoBemAntigoDelegate tipoBemDelegate = obterFabricaDelegates().criarTipoBemAntigoDelegate();
	
	/** O atributo subTipoBemDelegate. */
	private SubTipoBemAntigoDelegate subTipoBemDelegate = obterFabricaDelegates().criarSubTipoBemAntigoDelegate();
	
	/** O atributo situacaoBemDelegate. */
	private SituacaoBemAntigoDelegate situacaoBemDelegate = obterFabricaDelegates().criarSituacaoBemAntigoDelegate();
	
	/** O atributo tipoPosseBemDelegate. */
	private TipoPosseBemAntigoDelegate tipoPosseBemDelegate = obterFabricaDelegates().criarTipoPosseBemAntigoDelegate();
	
	/** O atributo autorizacaoCadastroDelegate. */
	private AutorizacaoCadastroDelegate autorizacaoCadastroDelegate =  CAPESCadastroFabricaDelegates
			.getInstance().criarAutorizacaoCadastroDelegate();
	
	/**
	 * Construtor
	 */
	public BemFachada() {
		super(CHAVE_BEM);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		try {
			RetornoDTO retornoDto = super.obterDefinicoes(dto);

			// definições GED/GFT
			Integer codTipoPessoa = (Integer) dto.getDados().get("codTipoPessoa");
			Set<String> chavesNegocio = new LinkedHashSet<String>();
			chavesNegocio.add(Constantes.Negocio.GED_SIGLA_CHAVE_DOCUMENTO_GRUPO_COMPARTILHAMENTO);
			chavesNegocio.add(Constantes.Negocio.GED_SIGLA_CHAVE_DOCUMENTO_TIPO_BEM);

			List<String> listaSiglas = new ArrayList<String>();
			listaSiglas.add(Constantes.Negocio.GED_SIGLA_TIPO_DOCUMENTO_LAUDO_AVALIACAO);
			listaSiglas.add(Constantes.Negocio.GED_SIGLA_TIPO_DOCUMENTO_CONTRATO_ARRENDAMENTO);
			listaSiglas.add(Constantes.Negocio.GED_SIGLA_TIPO_DOCUMENTO_COMPROVANTE_PROPRIEDADE);
			listaSiglas.add(Constantes.Negocio.GED_SIGLA_TIPO_DOCUMENTO_CERTIFICADO_CADASTRO_IMOVEL_RURAL);
			listaSiglas.add(Constantes.Negocio.GED_SIGLA_TIPO_DOCUMENTO_PROPRIEDADE_TERRITORIAL_RURAL);

			List<DefinicoesComponenteGedVO> listaDefinicoesGed = new ArrayList<DefinicoesComponenteGedVO>();
			for (String sigla : listaSiglas) {
				DefinicoesComponenteGedVO definicaoComponenteGedVO = new DefinicoesComponenteGedVO();
				definicaoComponenteGedVO.setIdTipoPessoa(codTipoPessoa.shortValue());
				definicaoComponenteGedVO.setSiglaTipoDocumento(sigla);
				definicaoComponenteGedVO.setChavesNegocio(chavesNegocio);
				listaDefinicoesGed.add(definicaoComponenteGedVO);
			}

			retornoDto.getDados().put("tiposBem", tipoBemDelegate.listarTiposComSubtipo());
			retornoDto.getDados().put("tiposPosseBem", tipoPosseBemDelegate.listar());
			retornoDto.getDados().put("definicoesComponenteGED", listaDefinicoesGed);

			return retornoDto;
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
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		} 
		return new RetornoDTO();
	}
	
	/**
	 * Criar delegate.
	 *
	 * @return BemDelegate
	 */
	protected BemAntigoDelegate criarDelegate() {
		return CAPESCadastroFabricaDelegates.getInstance().criarBemAntigoDelegate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<Bem> listar(RequisicaoReqDTO dto) throws BancoobException {
		PessoaCompartilhamento pessoa = (PessoaCompartilhamento) dto.getDados().get(PESSOA);
		return obterDelegate().listarPorPessoa(pessoa);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Bem consultarEntidade(Bem entidade) throws BancoobException {
		
		Bem retorno = null;
		if (entidade != null) {
			if (entidade.getSubTipo() != null && COD_BEM_IMOVEL.equals(entidade.getSubTipo().getCodTipoBem())) {
				retorno = bemImovelDelegate.obter(entidade.getIdBem());
			} else {
				retorno = super.consultarEntidade(entidade);
			}
		}
		return retorno;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO obterDados(RequisicaoReqDTO dto) throws BancoobException {

		try {
			RetornoDTO dtoRetorno = super.obterDados(dto);

			Bem bem = (Bem) dtoRetorno.getDados().get(CHAVE_BEM);

			dtoRetorno.getDados().put(CHAVE_SUB_TIPOS_BEM, obterSubtiposBem(bem.getSubTipo().getTipoBem()));
			dtoRetorno.getDados().put(CHAVE_SITUACOES_BEM, obterSituacoesBem(bem.getSubTipo()));
			
			bem.setDocumentosComprobatorios(autorizacaoCadastroDelegate.obterDocumentosComprobatorios(bem));
			
			//Verifica se o registro está bloqueado para alteração.
			dtoRetorno.getDados().put("isRegistroBloqueadoAlteracao", 
					autorizacaoCadastroDelegate.isRegistroBloqueadoAlteracao(bem));
			
			return dtoRetorno;
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
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		} 
		return new RetornoDTO();
	}
	
	/**
	 * Obter sub tipos bem.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterSubTiposBem(RequisicaoReqDTO dto) throws BancoobException {

		TipoBem tipoBem = (TipoBem) dto.getDados().get("tipoBem");
		
		RetornoDTO dtoRetorno = new RetornoDTO();
		dtoRetorno.setDados(new HashMap<String, Object>());
		List<SubTipoBem> subTipos = obterSubtiposBem(tipoBem);
		
		dtoRetorno.getDados().put(CHAVE_SUB_TIPOS_BEM, subTipos);

		return dtoRetorno;
	}

	/**
	 * Obter subtipos bem.
	 *
	 * @param tipoBem o valor de tipo bem
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<SubTipoBem> obterSubtiposBem(TipoBem tipoBem)
			throws BancoobException {
		ConsultaDto<SubTipoBem> criterios = new ConsultaDto<SubTipoBem>();
		
		SubTipoBem filtro = new SubTipoBem();
		filtro.setTipoBem(tipoBem);
		criterios.setFiltro(filtro);

		return subTipoBemDelegate.pesquisarPorTipo(criterios);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemAntigoDelegate obterDelegate() {
		return obterFabricaDelegates().criarBemAntigoDelegate();
	}

	/**
	 * Obter situacoes bem.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterSituacoesBem(RequisicaoReqDTO dto) throws BancoobException {
		SubTipoBem subTipoBem = (SubTipoBem) dto.getDados().get("subTipo");
		
		RetornoDTO dtoRetorno = new RetornoDTO();
		dtoRetorno.setDados(new HashMap<String, Object>());
		
		dtoRetorno.getDados().put(CHAVE_SITUACOES_BEM, obterSituacoesBem(subTipoBem));
		
		return dtoRetorno;
	}

	/**
	 * Obter situacoes bem.
	 *
	 * @param subTipoBem o valor de sub tipo bem
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<SituacaoBem> obterSituacoesBem(SubTipoBem subTipoBem)
			throws BancoobException {
		ConsultaDto<SituacaoBem> criteriosSb = new ConsultaDto<SituacaoBem>();
		criteriosSb.setFiltro(subTipoBem);
		
		return situacaoBemDelegate.pesquisar(criteriosSb).getResultado();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Bem obterEntidade(RequisicaoReqDTO dto) {
		return (Bem) dto.getDados().get(CHAVE_BEM);		
	}

	/**
	 * Obter dependencias sub tipos bem.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterDependenciasSubTiposBem(RequisicaoReqDTO dto)
			throws BancoobException {
		return obterSituacoesBem(dto);
	}

	/**
	 * Criar registro sem patrimonio.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO criarRegistroSemPatrimonio(RequisicaoReqDTO dto) throws BancoobException {
		Long idPessoaCompartilhamento = ((Number) dto.getDados().get("idPessoaCompartilhamento"))
		        .longValue();
		criarDelegate().criarRegistroSemPatrimonio(idPessoaCompartilhamento);
		return new RetornoDTO();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO excluirDados(RequisicaoReqDTO dto) throws BancoobException {
		Bem bem = obterEntidade(dto);
		if (SubTipoBem.CODIGO_SUBTIPO_SEM_PATRIMONIO.equals(bem.getSubTipo()
		        .getCodigo())) {
			bem.setVerificarAutorizacao(false);
		}
	    return super.excluirDados(dto);
	}
}