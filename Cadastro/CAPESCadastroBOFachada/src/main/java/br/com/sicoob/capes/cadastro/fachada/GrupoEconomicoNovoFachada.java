package br.com.sicoob.capes.cadastro.fachada;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.BooleanUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbr.negocio.dto.PesquisaDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.GrupoEconomicoNovoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.GrupoEconomicoNovoLimpoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.ParametroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.enums.ParametroEnum;
import br.com.sicoob.capes.cadastro.negocio.excecao.PessoaEmOutroGrupoException;
import br.com.sicoob.capes.cadastro.negocio.vo.ParametroVO;
import br.com.sicoob.capes.comum.negocio.contexto.InformacoesUsuarioCAPES;
import br.com.sicoob.capes.comum.negocio.enums.TipoGrupoEconomicoEnum;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoNovo;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoNovoLimpo;
import br.com.sicoob.capes.negocio.entidades.TipoGrupoEconomico;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * A Classe GrupoEconomicoFachada.
 */
@RemoteService
public class GrupoEconomicoNovoFachada extends CAPESCadastroBOCrudFachada<GrupoEconomicoNovo> {

	/** A constante CHAVE_MAPA_PESSOA. */
	private static final String CHAVE_MAPA_PESSOA = "pessoa";

	/** A constante CHAVE_MAPA. */
	protected static final String CHAVE_MAPA = "grupoEconomico";

	/** O atributo delegate. */
	private GrupoEconomicoNovoDelegate delegate;
	
	private GrupoEconomicoNovoLimpoDelegate delegateLimpo = CAPESCadastroFabricaDelegates.getInstance().criarGrupoEconomicoNovoLimpoDelegate();

	private ParametroDelegate parametroDelegate = CAPESCadastroFabricaDelegates.getInstance().criarParametroDelegate();

	private PessoaCompartilhamentoDelegate pessoaCompartilhamentoDelegate = CAPESCadastroFabricaDelegates.getInstance()
			.criarPessoaCompartilhamentoDelegate();

	/**
	 * Instancia um novo GrupoEconomicoFachada.
	 */
	public GrupoEconomicoNovoFachada() {
		super(CHAVE_MAPA);
	}

	/**
	 * Obter definicoes.
	 *
	 * @return RetornoDTO
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	public RetornoDTO obterDefinicoes() throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		ParametroVO parametro = parametroDelegate.obterParametro(ParametroEnum.SERVICOS_GRUPO_ECONOMICO.getCodigo(), getIdInstituicaoUsuarioLogado());
		retorno.getDados().put("destinoTela", parametro.getValor());
		Integer idInstituicao = Integer.valueOf(InformacoesUsuarioCAPES.getInstance().getIdInstituicao());
		ParametroVO parametroVO = parametroDelegate.obterParametro(ParametroEnum.ALTERAR_NOME_GRUPO_ECONOMICO_AUTOMATICO.getCodigo(), idInstituicao);
		Boolean habilitado = BooleanUtils.toBoolean(parametroVO.getValor());
		retorno.getDados().put("permitirAlterarNomeAutomatico", habilitado);
		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected GrupoEconomicoNovo obterEntidade(RequisicaoReqDTO dto) {
		return (GrupoEconomicoNovo) dto.getDados().get(CHAVE_MAPA);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected GrupoEconomicoNovoDelegate obterDelegate() {
		if (delegate == null) {
			delegate = CAPESCadastroFabricaDelegates.getInstance().criarGrupoEconomicoNovoDelegate();
		}
		return delegate;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO alterarDados(RequisicaoReqDTO dto) throws BancoobException {

		GrupoEconomicoNovo grupo = (GrupoEconomicoNovo) dto.getDados().get(CHAVE_MAPA);
		if(grupo.getTipo().getCodigo().equals(TipoGrupoEconomicoEnum.MANUAL.getCodigo())) {
			grupo.setIdInstituicao(Integer.valueOf(obterUsuarioLogado().getIdInstituicao()));
		} else {
			grupo.setIdInstituicao(null);
		}
		obterDelegate().alterar(grupo);
		return new RetornoDTO();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DadosSelGeralDTO obterDadosSelecao(SelGeralReqDTO dto) throws BancoobException {
		if (dto instanceof PesquisaDTO) {
			PesquisaDTO pesquisaDTO = (PesquisaDTO) dto;
			GrupoEconomicoNovo filtro = (GrupoEconomicoNovo) pesquisaDTO.getFiltro();
			filtro.setIdInstituicao(Integer.valueOf(obterUsuarioLogado().getIdInstituicao()));
		}
		ConsultaDto<GrupoEconomicoNovoLimpo> consultaDto = new ConsultaDto<>();
		popularConsultaDto(consultaDto, dto);
		consultaDto.setProcurarPor("excluirAutomaticosComManual");
		ConsultaDto<GrupoEconomicoNovoLimpo> resultado = delegateLimpo.pesquisar(consultaDto);
		final List<GrupoEconomicoNovo> listaRetorno = new ArrayList<>(resultado.getResultado().size()); 
		for(GrupoEconomicoNovoLimpo obj : resultado.getResultado()) {
			GrupoEconomicoNovo objFinal = new GrupoEconomicoNovo();
			objFinal.setId(obj.getId());
			objFinal.setNome(obj.getNome());
			objFinal.setTipo(obj.getTipo());
			objFinal.setIntegrantesAutomatico(null);
			objFinal.setIntegrantesManual(null);
			listaRetorno.add(objFinal);
		}
		ConsultaDto<GrupoEconomicoNovo> resultadoFinal = new ConsultaDto<>();
		resultadoFinal.setFiltro(resultado.getFiltro());
		resultadoFinal.setOrdemCrescente(resultado.isOrdemCrescente());
		resultadoFinal.setOrdenacao(resultado.getOrdenacao());
		resultadoFinal.setPagina(resultado.getPagina());
		resultadoFinal.setProcurarPor(resultado.getProcurarPor());
		resultadoFinal.setResultado(listaRetorno);
		resultadoFinal.setTamanhoPagina(resultado.getTamanhoPagina());
		resultadoFinal.setTipoProcura(resultado.getTipoProcura());
		resultadoFinal.setTotalRegistros(resultado.getTotalRegistros());
		return montarResultado(resultadoFinal);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO incluirDados(RequisicaoReqDTO dto) throws BancoobException {
		GrupoEconomicoNovo entidade = (GrupoEconomicoNovo) dto.getDados().get(CHAVE_MAPA);
		entidade.setIdInstituicao(Integer.valueOf(obterUsuarioLogado().getIdInstituicao()));
		entidade.setTipo(new TipoGrupoEconomico(TipoGrupoEconomicoEnum.MANUAL.getCodigo()));
		entidade.setIdUsuarioCadastro(obterUsuarioLogado().getLogin());
		obterDelegate().incluir(entidade);
		return new RetornoDTO();
	}

	/**
	 * Validar pessoa.
	 *
	 * @param dto
	 *            o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	public RetornoDTO validarPessoa(RequisicaoReqDTO dto) throws BancoobException {
		Integer idInstituicao = Integer.valueOf(obterUsuarioLogado().getIdInstituicao());

		// prepara a pessoa
		PessoaCompartilhamento param = (PessoaCompartilhamento) dto.getDados().get(CHAVE_MAPA_PESSOA);
		PessoaCompartilhamento pessoaCompartilhamento = pessoaCompartilhamentoDelegate.consultarPorIdPessoaInstituicao(param.getPessoa().getId(),
				idInstituicao);

		// prepara o grupo
		GrupoEconomicoNovo grupo = (GrupoEconomicoNovo) dto.getDados().get(CHAVE_MAPA);
		grupo.setIdInstituicao(Integer.valueOf(obterUsuarioLogado().getIdInstituicao()));

		// prepara o retorno
		RetornoDTO retorno = new RetornoDTO();
		retorno.getDados().put(CHAVE_MAPA_PESSOA, pessoaCompartilhamento);

		// realiza a validação
		try {
			obterDelegate().validarpessoa(grupo, pessoaCompartilhamento);
		} catch (PessoaEmOutroGrupoException e) {
			retorno.getDados().put("erro", e.getMessage());
			retorno.getDados().put("grupoAutomatico", e.getGrupoAutomatico());
			retorno.getDados().put("origemGrupo", e.getOrigemGrupo());
		}
		return retorno;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO excluirDados(RequisicaoReqDTO dto) throws BancoobException {
		GrupoEconomicoNovo entidade = obterEntidade(dto);
		obterDelegate().excluirEntidade(entidade);
		return obterMapRetorno(CHAVE_MAPA, entidade);
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
