package br.com.sicoob.capes.cadastro.fachada;

import static br.com.sicoob.capes.comum.util.Constantes.Comum.ID_INSTITUICAO_BANCOOB;
import static br.com.sicoob.capes.comum.util.Constantes.Comum.ID_UNIDADEINST_AGENCIA_SEDE;
import static br.com.sicoob.capes.comum.util.Constantes.Comum.ID_UNIDADEINST_PAB_AGENCIA_BSB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJBTransactionRolledbackException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang.ArrayUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.excecao.ViolacaoIntegridadeException;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.FuncionarioDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.GrupoEconomicoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.GrupoEconomicoNovoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.ParametroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.PerfilTarifarioDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaInstituicaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TributacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.enums.ParametroEnum;
import br.com.sicoob.capes.cadastro.negocio.vo.ParametroVO;
import br.com.sicoob.capes.comum.negocio.vo.UnidadeInstituicaoVO;
import br.com.sicoob.capes.negocio.entidades.Funcionario;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Nucleo;
import br.com.sicoob.capes.negocio.entidades.PerfilTarifario;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;
import br.com.sicoob.capes.negocio.entidades.pk.PerfilTarifarioPK;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.Tributacao;

/**
 * Fachada responsavel por disponibilizar os serviços de pessoa instiuicao.
 *
 * @author Juan.Damasceno
 */
@RemoteService
public class PessoaInstituicaoFachada extends
		CAPESCadastroBOCrudFachada<PessoaInstituicao> {

	/** A constante CHAVE_PESSOA_INSTITUICAO. */
	private static final String CHAVE_PESSOA_INSTITUICAO = "pessoaInstituicao";
	
	/** A constante UNIDADES_INSTITUICAO_PERMITIDAS. */
	private static final Integer[] UNIDADES_INSTITUICAO_PERMITIDAS = new Integer[] {
			ID_UNIDADEINST_AGENCIA_SEDE, ID_UNIDADEINST_PAB_AGENCIA_BSB };
	
	private static final String DESTINO_NOVO_GRUPO = "NOVO";
	
	/** O atributo pessoaInstituicaoDelegate. */
	private PessoaInstituicaoDelegate pessoaInstituicaoDelegate = obterFabricaDelegates()
			.criarPessoaInstituicaoDelegate();
	
	private ParametroDelegate parametroDelegate = CAPESCadastroFabricaDelegates.getInstance().criarParametroDelegate();
	
	/**
	 * Instancia um novo PessoaInstituicaoFachada.
	 */
	public PessoaInstituicaoFachada() {
		super(CHAVE_PESSOA_INSTITUICAO);
	}

	private PessoaCompartilhamentoDelegate pessoaCompartilhamentoDelegate = CAPESCadastroFabricaDelegates.getInstance()
			.criarPessoaCompartilhamentoDelegate();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected PessoaInstituicaoDelegate obterDelegate() {
		return pessoaInstituicaoDelegate;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO incluirDados(RequisicaoReqDTO dto) throws BancoobException {
		try {
			Integer idInstituicao = obterIdInstituicao(dto);

			PessoaInstituicao cliente = (PessoaInstituicao) dto.getDados()
					.get(CHAVE_PESSOA_INSTITUICAO);
			cliente.setIdInstituicao(idInstituicao);
			cliente.setIdUsuarioAprovacao(obterUsuarioLogado().getLogin());
			
			Instituicao instituicao = new Instituicao();
			instituicao.setIdInstituicao(idInstituicao);
			Boolean produtoBancoob = (Boolean) dto.getDados().get(
			        EntidadeCadastroFachada.PRODUTOS_BANCOOB);
			cliente = obterDelegate().incluir(cliente, instituicao, produtoBancoob);
			
			RetornoDTO retorno = obterMapRetorno(CHAVE_PESSOA_INSTITUICAO, cliente);
			retorno.getDados().put("tributacao", obterTributacao(cliente.getPessoa()));
			retorno.getDados().put("habilitaNivelRisco", obterStatusCOP(dto));
			
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
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO alterarDados(RequisicaoReqDTO dto) throws BancoobException {
		try {
			RetornoDTO retorno = super.alterarDados(dto);
			PessoaInstituicao cliente = (PessoaInstituicao) dto.getDados().get(CHAVE_PESSOA_INSTITUICAO);
			retorno.getDados().put("tributacao", obterTributacao(cliente.getPessoa()));
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
	public RetornoDTO obterDados(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();

		try {
			PessoaCompartilhamento pessoaCompartilhamento = (PessoaCompartilhamento) dto.getDados().get("pessoaCompartilhamento");
			Pessoa pessoa = (Pessoa) dto.getDados().get(PESSOA);

			retornoDTO.setDados(new HashMap<String, Object>());
			PessoaInstituicao pessoaInstituicao = obterPessoaInstituicao(dto);
			retornoDTO.getDados().put(CHAVE_PESSOA_INSTITUICAO, pessoaInstituicao);
			retornoDTO.getDados().put("nucleos", listarNucleos(dto));
			retornoDTO.getDados().put("unidades", listarUnidades(dto, pessoaCompartilhamento));
			retornoDTO.getDados().put("funcionarios", listarFuncionarios(dto));
			retornoDTO.getDados().put("perfisTarifarios", listarPerfisTarifarios(dto));
			retornoDTO.getDados().put("tributacao", obterTributacao(pessoa));
			retornoDTO.getDados().put("gruposEconomicos", listarGrupoEconomico(pessoa));
			retornoDTO.getDados().put("habilitaNivelRisco", obterStatusCOP(dto));
			retornoDTO.getDados().put("categoriasCadastro", getCategoriaCadastro(pessoaCompartilhamento));
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
		return retornoDTO;
	}

	private String getCategoriaCadastro(PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException {
		String perfil = "";
		PessoaCompartilhamento pessoa = pessoaCompartilhamentoDelegate.obter(pessoaCompartilhamento.getId());
		if (pessoa != null && pessoa.getPerfilCadastro() != null) {
			perfil = pessoa.getPerfilCadastro().getDescricao();
		}
		return perfil;
	}
	
	private List<?> listarGrupoEconomico(Pessoa pessoa) throws BancoobException {
		ParametroVO parametro = parametroDelegate.obterParametro(ParametroEnum.SERVICOS_GRUPO_ECONOMICO.getCodigo(), getIdInstituicaoUsuarioLogado());
		if(parametro.getValor().equalsIgnoreCase(DESTINO_NOVO_GRUPO)) {
			GrupoEconomicoNovoDelegate delegate = obterFabricaDelegates().criarGrupoEconomicoNovoDelegate();
			return delegate.listarPorPessoaInstituicao(pessoa.getId(), getIdInstituicaoUsuarioLogado());
		} else {
			GrupoEconomicoDelegate delegate = obterFabricaDelegates().criarGrupoEconomicoDelegate();
			return delegate.listarPorPessoa(pessoa);
		}
	}
	
	/**
	 * Obter pessoa instituicao.
	 *
	 * @param dto o valor de dto
	 * @return PessoaInstituicao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private PessoaInstituicao obterPessoaInstituicao(RequisicaoReqDTO dto) throws BancoobException {
		PessoaInstituicao pessoaInstituicao = null;
		PessoaInstituicao pessoaInstituicaoFiltro = new PessoaInstituicao();
		pessoaInstituicaoFiltro.setIdInstituicao(obterIdInstituicao(dto));
		pessoaInstituicaoFiltro.setPessoa((Pessoa) dto.getDados().get(PESSOA));

		pessoaInstituicao = pessoaInstituicaoDelegate.obterPorPessoaInstituicaoSemValidacao(pessoaInstituicaoFiltro);

		return pessoaInstituicao;
	}

	/**
	 * Obter tributacao.
	 *
	 * @param pessoa o valor de pessoa
	 * @return Tributacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private Tributacao obterTributacao(Pessoa pessoa) throws BancoobException {
		TributacaoDelegate tributacaoDelegate = obterFabricaDelegates().criarTributacaoDelegate();
		return tributacaoDelegate.obterPorPessoa(pessoa.getPessoaCompartilhamento());
	}

	/**
	 * Listar perfis tarifarios.
	 *
	 * @param dto o valor de dto
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<PerfilTarifario> listarPerfisTarifarios(RequisicaoReqDTO dto) throws BancoobException {
		PerfilTarifarioDelegate perfilTarifarioDelegate = obterFabricaDelegates()
				.criarPerfilTarifarioDelegate();
		Pessoa pessoa = (Pessoa) dto.getDados().get(PESSOA);
		TipoPessoa tipoPessoa = pessoa.getTipoPessoa();

		PerfilTarifario filtro = new PerfilTarifario();
		filtro.setPk(new PerfilTarifarioPK());
		filtro.getPk().setIdInstituicao(obterIdInstituicao(dto));
		filtro.setCodTipoPerfilConta(tipoPessoa.getCodTipoPessoa());
		
		// segundo orientação do analista, deve-se passar sempre "1"
		filtro.setCodigoTipoPerfil((short) 1);

		ConsultaDto<PerfilTarifario> consultaDto = new ConsultaDto<PerfilTarifario>();
		consultaDto.setFiltro(filtro);

		return perfilTarifarioDelegate.listar(consultaDto);
	}

	/**
	 * Listar funcionarios.
	 *
	 * @param dto o valor de dto
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<Funcionario> listarFuncionarios(RequisicaoReqDTO dto) throws BancoobException {
		Instituicao instituicao = new Instituicao();
		instituicao.setIdInstituicao(obterIdInstituicao(dto));

		FuncionarioDelegate funcionarioDelegate = obterFabricaDelegates().criarFuncionarioDelegate();
		return funcionarioDelegate.listarGerentesAtivos(instituicao);
	}

	/**
	 * Listar unidades.
	 *
	 * @param dto o valor de dto
	 * @return List
	 */
	private List<UnidadeInstituicaoVO> listarUnidades(RequisicaoReqDTO dto, PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException {
		List<UnidadeInstituicaoVO> unidadesInstituicao = pessoaInstituicaoDelegate.obterListaUnidadesInstituicao(obterIdInstituicao(dto), pessoaCompartilhamento);
		
		List<UnidadeInstituicaoVO> unidades = new ArrayList<UnidadeInstituicaoVO>();
		
		for (UnidadeInstituicaoVO unidadeInstituicao : unidadesInstituicao) {
			if (!unidadeInstituicao.getIdInstituicao().equals(ID_INSTITUICAO_BANCOOB)
					|| (ArrayUtils.contains(UNIDADES_INSTITUICAO_PERMITIDAS, unidadeInstituicao.getIdUnidadeInst()))) {
				UnidadeInstituicaoVO unidadeInstituicaoVO = new UnidadeInstituicaoVO();
				unidadeInstituicaoVO.setDescricao(unidadeInstituicao.getNomeUnidade());
				unidadeInstituicaoVO.setId(unidadeInstituicao.getIdUnidadeInst());
				unidadeInstituicaoVO.setIdInstituicao(unidadeInstituicao.getIdInstituicao());
				
				unidades.add(unidadeInstituicaoVO);
			}
		}
		
		return unidades;
	}

	/**
	 * Listar nucleos.
	 *
	 * @param dto o valor de dto
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<Nucleo> listarNucleos(RequisicaoReqDTO dto) throws BancoobException {
		ConsultaDto<Nucleo> consultaDto = new ConsultaDto<Nucleo>();
		Nucleo nucleo = new Nucleo();
		nucleo.setIdInstituicao(obterIdInstituicao(dto));
		nucleo.setAtivo(Boolean.TRUE);
		consultaDto.setFiltro(nucleo);
		consultaDto.setOrdenacao("descricao");
		return obterFabricaDelegates().criarNucleoDelegate().listar(consultaDto);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected PessoaInstituicao obterEntidade(RequisicaoReqDTO dto) {
		return (PessoaInstituicao) dto.getDados().get(chaveMapa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DadosSelGeralDTO obterDadosSelecao(SelGeralReqDTO dto) throws BancoobException {
		return null;
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
	 * Obter id instituicao.
	 *
	 * @param dto o valor de dto
	 * @return Integer
	 */
	private Integer obterIdInstituicao(RequisicaoReqDTO dto) {
		Integer idInstituicao = ID_INSTITUICAO_BANCOOB;

		Boolean produtosBancoob = (Boolean) dto.getDados().get(EntidadeCadastroFachada.PRODUTOS_BANCOOB);
		if (!produtosBancoob) {
			idInstituicao = Integer.valueOf(obterUsuarioLogado().getIdInstituicao());
		}
		return idInstituicao;
	}
	
	/**
	 * Obter status cop.
	 *
	 * @param dto o valor de dto
	 * @return {@code true}, em caso de sucesso
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private boolean obterStatusCOP(RequisicaoReqDTO dto) throws BancoobException {
		Boolean produtoBancoob = (Boolean) dto.getDados().get(EntidadeCadastroFachada.PRODUTOS_BANCOOB);
		return !pessoaInstituicaoDelegate.obterStatusCOP(produtoBancoob);
	}
	
	/**
	 * Verificar cadastro pessoa.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO verificarCadastroPessoa(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		try {
			PessoaInstituicao pessoaInstituicao = (PessoaInstituicao) dto.getDados().get(chaveMapa);
			obterDelegate().verificarCadastroPessoa(pessoaInstituicao);
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
	 * Recupera o valor de idInstituicaoUsuarioLogado.
	 *
	 * @return o valor de idInstituicaoUsuarioLogado
	 */
	private Integer getIdInstituicaoUsuarioLogado() {
		return Integer.valueOf(obterUsuarioLogado().getIdInstituicao());
	}
	
}