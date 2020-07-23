package br.com.sicoob.capes.cadastro.fachada;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizacaoCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.EntidadeCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TributacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.vo.DefinicoesComponenteGedVO;
import br.com.sicoob.capes.cadastro.util.SerializacaoUtils;
import br.com.sicoob.capes.comum.util.Constantes.Negocio;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.vigente.Tributacao;

/**
 * Fachada responsavel por disponibilizar os serviços de tributacao.
 * @author Juan.Damasceno
 *
 */
@RemoteService
public class TributacaoFachada extends CAPESCadastroBOCrudFachada<Tributacao> {
	
	/** O atributo tributacaoDelegate. */
	private TributacaoDelegate tributacaoDelegate = obterFabricaDelegates().criarTributacaoDelegate();
	
	/** O atributo autorizacaoDelegate. */
	private AutorizacaoDelegate autorizacaoDelegate = CAPESCadastroFabricaDelegates
			.getInstance().criarAutorizacaoDelegate();
	
	/** O atributo autorizacaoCadastroDelegate. */
	private AutorizacaoCadastroDelegate autorizacaoCadastroDelegate = obterFabricaDelegates()
			.criarAutorizacaoCadastroDelegate();
	
	/** A constante CHAVE_TRIBUTACAO. */
	private static final String CHAVE_TRIBUTACAO = "tributacao";
	
	/** A constante TRIBUTACAO_VIGENTE. */
	private static final String TRIBUTACAO_VIGENTE = "tributacaoVigente";
	
	/** A constante TRIBUTACAO_ALTERACAO. */
	private static final String TRIBUTACAO_ALTERACAO = "tributacaoAlteracao";
	
	/** A constante DEFINICOES_COMP_GED. */
	private static final String DEFINICOES_COMP_GED = "definicoesComponenteGED";

	/**
	 * Instancia um novo TributacaoFachada.
	 */
	public TributacaoFachada() {
		super(CHAVE_TRIBUTACAO);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EntidadeCadastroDelegate<Tributacao, ?> obterDelegate() {
		return tributacaoDelegate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO obterDados(RequisicaoReqDTO dto) throws BancoobException {
		return obterDadosAlteracao(dto);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Tributacao obterEntidade(RequisicaoReqDTO dto) {
		return (Tributacao) dto.getDados().get(chaveMapa);
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
	 * Obter definicoes.
	 *
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterDefinicoes() throws BancoobException {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO incluirDados(RequisicaoReqDTO dto)throws BancoobException {
		super.incluirDados(dto);
		return obterDadosAlteracao(dto);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO alterarDados(RequisicaoReqDTO dto) throws BancoobException {
		super.alterarDados(dto);
		return obterDadosAlteracao(dto);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO excluirDados(RequisicaoReqDTO dto) throws BancoobException {
		super.excluirDados(dto);
		return obterDadosAlteracao(dto);
	}
	
	/**
	 * Validar cadastro.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO validarCadastro(RequisicaoReqDTO dto) throws BancoobException{
		RetornoDTO retornoDTO = new RetornoDTO();
		Tributacao tributacao = (Tributacao) dto.getDados().get(CHAVE_TRIBUTACAO);
		tributacaoDelegate.validarCadatroPessoa(tributacao.getPessoaCompartilhamento());
		
		Tributacao tributacaoRetorno = tributacaoDelegate.obterPorPessoaComValidacao(tributacao.getPessoaCompartilhamento());
		retornoDTO.getDados().put(TRIBUTACAO_VIGENTE, tributacaoRetorno);
		
		return retornoDTO;
	}
	
	/**
	 * Obter dados alteracao.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private RetornoDTO obterDadosAlteracao(RequisicaoReqDTO dto) throws BancoobException {
		Tributacao tributacao = (Tributacao) dto.getDados().get(CHAVE_TRIBUTACAO);
		RetornoDTO retornoDTO = new RetornoDTO();
		
		Tributacao tributacaoRetorno = tributacaoDelegate.obterPorPessoa(tributacao.getPessoaCompartilhamento());
		
		retornoDTO.getDados().put(TRIBUTACAO_VIGENTE, tributacaoRetorno);
		
		if(tributacaoRetorno != null){
			Autorizacao autorizacao = autorizacaoDelegate.obterPorEntidade((Tributacao) retornoDTO.getDados().get(TRIBUTACAO_VIGENTE));
			if(autorizacao != null){
				Tributacao tributacaoAlteracao = SerializacaoUtils.deserializarJson(Tributacao.class, autorizacao.getAlteracao());
				tributacaoAlteracao.setDocumentosComprobatorios(autorizacaoCadastroDelegate.obterDocumentosComprobatorios(tributacaoAlteracao));
				tributacaoAlteracao.setSituacaoAprovacao(autorizacao.obterSituacao());
				retornoDTO.getDados().put(TRIBUTACAO_ALTERACAO, tributacaoAlteracao);
			}
		}
		
		retornoDTO.getDados().put(DEFINICOES_COMP_GED, montarObjetoDefinicoesGED(Short.parseShort(((Integer) dto.getDados().get("idTipoPessoa")).toString())));
		
		return retornoDTO;
	}
	
	/**
	 * Montar objeto definicoes ged.
	 *
	 * @param idTipoPessoa o valor de id tipo pessoa
	 * @return List
	 */
	private List<DefinicoesComponenteGedVO> montarObjetoDefinicoesGED(Short idTipoPessoa){
		List<DefinicoesComponenteGedVO> retorno = new ArrayList<DefinicoesComponenteGedVO>();
		DefinicoesComponenteGedVO vo = new DefinicoesComponenteGedVO();
		Set<String> chavesNegocio = new LinkedHashSet<String>();
		
		vo.setIdTipoPessoa(idTipoPessoa);
		vo.setSiglaTipoDocumento(Negocio.GED_SIGLA_TIPO_DOCUMENTO_COMPROVANTE_DECLARACAO_ISENCAO_TRIBUTARIA);
		chavesNegocio.add(Negocio.GED_SIGLA_CHAVE_DOCUMENTO_GRUPO_COMPARTILHAMENTO);
		vo.setChavesNegocio(chavesNegocio);
		retorno.add(vo);
		
		vo = new DefinicoesComponenteGedVO();
		chavesNegocio = new LinkedHashSet<String>();
		vo.setIdTipoPessoa(idTipoPessoa);
		vo.setSiglaTipoDocumento(Negocio.GED_SIGLA_TIPO_DOCUMENTO_COMPROVANTE_REGIME_TRIBUTARIO);
		chavesNegocio.add(Negocio.GED_SIGLA_CHAVE_DOCUMENTO_GRUPO_COMPARTILHAMENTO);
		vo.setChavesNegocio(chavesNegocio);
		retorno.add(vo);
		
		return retorno;
	}

}
