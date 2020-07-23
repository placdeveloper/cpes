package br.com.sicoob.capes.cadastro.fachada;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbr.negocio.dto.PesquisaDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.GrupoEconomicoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.ParametroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaInstituicaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.enums.ParametroEnum;
import br.com.sicoob.capes.cadastro.negocio.excecao.PessoaEmOutroGrupoException;
import br.com.sicoob.capes.cadastro.negocio.vo.ParametroVO;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomico;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao;

/**
 * A Classe GrupoEconomicoFachada.
 */
@RemoteService
public class GrupoEconomicoFachada extends
		CAPESCadastroBOCrudFachada<GrupoEconomico> {

	/** A constante CHAVE_MAPA_PESSOA. */
	private static final String CHAVE_MAPA_PESSOA = "pessoa";
	
	/** A constante CHAVE_MAPA. */
	protected static final String CHAVE_MAPA = "grupoEconomico";
	
	/** O atributo delegate. */
	private GrupoEconomicoDelegate delegate;
	
	private ParametroDelegate parametroDelegate = CAPESCadastroFabricaDelegates.getInstance().criarParametroDelegate();

	/**
	 * Instancia um novo GrupoEconomicoFachada.
	 */
	public GrupoEconomicoFachada() {
		super(CHAVE_MAPA);
	}

	
	/**
	 * Obter definicoes.
	 *
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterDefinicoes() throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		ParametroVO parametro = parametroDelegate.obterParametro(ParametroEnum.SERVICOS_GRUPO_ECONOMICO.getCodigo(), getIdInstituicaoUsuarioLogado());
		retorno.getDados().put("destinoTela", parametro.getValor());
		return retorno;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected GrupoEconomico obterEntidade(RequisicaoReqDTO dto) {
		return (GrupoEconomico) dto.getDados().get(CHAVE_MAPA);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected GrupoEconomicoDelegate obterDelegate() {
		if (delegate == null) {
			delegate = CAPESCadastroFabricaDelegates.getInstance()
					.criarGrupoEconomicoDelegate();
		}
		return delegate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DadosSelGeralDTO obterDadosSelecao(SelGeralReqDTO dto) throws BancoobException {
		PesquisaDTO pesquisaDTO = null;
		if (dto instanceof PesquisaDTO) {
			pesquisaDTO = (PesquisaDTO) dto;
			GrupoEconomico filtro = (GrupoEconomico) pesquisaDTO.getFiltro();
			filtro.setIdInstituicao(Integer.valueOf(obterUsuarioLogado().getIdInstituicao()));
		}
		return super.obterDadosSelecao(dto);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO incluirDados(RequisicaoReqDTO dto) throws BancoobException {
		
		GrupoEconomico grupo = (GrupoEconomico) dto.getDados().get(CHAVE_MAPA);
		grupo.setIdInstituicao(Integer.valueOf(obterUsuarioLogado().getIdInstituicao()));
		obterDelegate().incluir(grupo);
		return new RetornoDTO();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO alterarDados(RequisicaoReqDTO dto) throws BancoobException {
		
		GrupoEconomico grupo = (GrupoEconomico) dto.getDados().get(CHAVE_MAPA);
		grupo.setIdInstituicao(Integer.valueOf(obterUsuarioLogado().getIdInstituicao()));
		obterDelegate().alterar(grupo);
		return new RetornoDTO();
	}
	
	/**
	 * Validar pessoa.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO validarPessoa(RequisicaoReqDTO dto) throws BancoobException {
		
		PessoaInstituicaoDelegate pessoaDelegate = null;
		pessoaDelegate = CAPESCadastroFabricaDelegates.getInstance()
				.criarPessoaInstituicaoDelegate();
		
		// prepara a pessoa
		PessoaInstituicao pessoa = (PessoaInstituicao) dto.getDados().get(CHAVE_MAPA_PESSOA);
		pessoa = pessoaDelegate.obter(pessoa.getIdPessoaInstituicao());

		// prepara o grupo
		GrupoEconomico grupo = (GrupoEconomico) dto.getDados().get(CHAVE_MAPA);
		grupo.setIdInstituicao(Integer.valueOf(obterUsuarioLogado().getIdInstituicao()));

		// prepara o retorno
		RetornoDTO retorno = new RetornoDTO();
		retorno.getDados().put(CHAVE_MAPA_PESSOA, pessoa);
		
		// realiza a validação
		try {
			obterDelegate().validarpessoa(grupo, pessoa);
		} catch(PessoaEmOutroGrupoException e) {
			retorno.getDados().put("erro", e.getMessage());
		}
		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO excluirDados(RequisicaoReqDTO dto) throws BancoobException {
		GrupoEconomico entidade = obterEntidade(dto);
		obterDelegate().excluir(entidade.getIdGrupoEconomico());
		return obterMapRetorno(CHAVE_MAPA , entidade);
	}
	
	/**
	 * Recupera o valor de idInstituicaoUsuarioLogado.
	 *
	 * @return o valor de idInstituicaoUsuarioLogado
	 */
	private Integer getIdInstituicaoUsuarioLogado() {
		return Integer.valueOf(obterUsuarioLogado().getIdInstituicao());
	}

	
}
