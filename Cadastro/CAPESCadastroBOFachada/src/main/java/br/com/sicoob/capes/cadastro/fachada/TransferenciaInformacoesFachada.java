package br.com.sicoob.capes.cadastro.fachada;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.beanutils.BeanComparator;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.FuncionarioDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.GrupoEconomicoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.GrupoEconomicoPessoaDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.NucleoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaInstituicaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.SolicitacaoTransferenciaInformacoesDelegate;
import br.com.sicoob.capes.comum.negocio.vo.InstituicaoVO;
import br.com.sicoob.capes.comum.negocio.vo.UnidadeInstituicaoVO;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.Funcionario;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomico;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Nucleo;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoEconomicoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao;

/**
 * A Classe TransferenciaInformacoesFachada.
 */
@RemoteService
public class TransferenciaInformacoesFachada extends CAPESCadastroBOFachada {

	/** A constante UNIDADE_ORIGEM. */
	private static final String UNIDADE_ORIGEM = "unidadeOrigem";
	
	/** A constante TOTAL. */
	private static final String TOTAL = "total";
	
	/** A constante GERENTE. */
	private static final int GERENTE = 0;
	
	/** A constante UNIDADE. */
	private static final int UNIDADE = 1;
	
	/** A constante GRUPO. */
	private static final int GRUPO = 2;
	
	/** A constante NUCLEO. */
	private static final int NUCLEO = 3;
	
	/** A constante CPFCNPJ. */
	private static final int CPFCNPJ = 4;

	/** O atributo delegateFuncionario. */
	private final FuncionarioDelegate delegateFuncionario = CAPESCadastroFabricaDelegates
			.getInstance().criarFuncionarioDelegate();
	
	/** O atributo sciIntegracaoDelegate. */
	private final SCIIntegracaoDelegate sciIntegracaoDelegate = CAPESIntegracaoFabricaDelegates
			.getInstance().criarSCIIntegracaoDelegate();
	
	/** O atributo delegateGrupoEconomico. */
	private final GrupoEconomicoDelegate delegateGrupoEconomico = CAPESCadastroFabricaDelegates
			.getInstance().criarGrupoEconomicoDelegate();

	/** O atributo delegateGrupoEconomicoPessoaDelegate. */
	private final GrupoEconomicoPessoaDelegate delegateGrupoEconomicoPessoaDelegate = CAPESCadastroFabricaDelegates
			.getInstance().criarGrupoEconomicoPessoaDelegate();
	
	/** O atributo delegateNucleo. */
	private final NucleoDelegate delegateNucleo = CAPESCadastroFabricaDelegates
			.getInstance().criarNucleoDelegate();

	/** O atributo delegatePessoaInstituicao. */
	private final PessoaInstituicaoDelegate delegatePessoaInstituicao = CAPESCadastroFabricaDelegates
			.getInstance().criarPessoaInstituicaoDelegate();

	/** O atributo delegateTransferenciaInformacoes. */
	private final SolicitacaoTransferenciaInformacoesDelegate delegateTransferenciaInformacoes = CAPESCadastroFabricaDelegates
			.getInstance().criarSolicitacaoTransferenciaInformacoesDelegate();
	
	/**
	 * Obter definicoes.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		switch ((Integer)dto.getDados().get("tipo")) {
			case GERENTE: 
				retorno.getDados().put("unidades", getUnidades());
				retorno.getDados().put("unidadesAtivas", getUnidadesAtivas());
				retorno.getDados().put("unidadesSemPADigital", getUnidadesSemPADigital());
				retorno.getDados().put("gerentes", ordenarListaGerentes(delegateFuncionario.listarGerentes()));
				retorno.getDados().put("gerentesAtivos", ordenarListaGerentes(delegateFuncionario.listarGerentesAtivos()));
				break;
			case UNIDADE: 
				retorno.getDados().put("unidades", getUnidades());
				retorno.getDados().put("unidadesAtivas", getUnidadesAtivas());
				retorno.getDados().put("unidadesSemPADigital", getUnidadesSemPADigital());
				retorno.getDados().put("origem",  dto.getDados().get("origem"));
				retorno.getDados().put("gerentesAtivos", ordenarListaGerentes(delegateFuncionario.listarGerentesAtivos()));
				break;
			case GRUPO: 
				retorno.getDados().put("grupos", delegateGrupoEconomico.pesquisar(obterFiltroConsultaGrupoEconomico()).getResultado());
				break;
			case NUCLEO:
				retorno.getDados().put("nucleos", delegateNucleo.pesquisarPorInstituicaoAtivos(new ConsultaDto<Nucleo>()).getResultado());
				break;
			case CPFCNPJ: 
				retorno.getDados().put("unidades", getUnidades());
				retorno.getDados().put("unidadesAtivas", getUnidadesAtivas());
				retorno.getDados().put("unidadesSemPADigital", getUnidadesSemPADigital());
				retorno.getDados().put("gerentesAtivos", ordenarListaGerentes(delegateFuncionario.listarGerentesAtivos()));
				retorno.getDados().put("nucleos", delegateNucleo.pesquisarPorInstituicaoAtivos(new ConsultaDto<Nucleo>()).getResultado());
				break;
		}
		return retorno;
	}

	/**
	 * Ordenar lista gerentes.
	 *
	 * @param listarGerentes o valor de listar gerentes
	 * @return List
	 */
	private List<Funcionario> ordenarListaGerentes(List<Funcionario> listarGerentes) {
		Collections.sort(listarGerentes, new BeanComparator<Funcionario>("pessoaCompartilhamento.nomePessoa"));
		return listarGerentes;
	}

	/**
	 * Verifica a quantidade de informações a serem atualizadas
	 */
	public RetornoDTO verificarTransferencias(RequisicaoReqDTO dto) throws BancoobException {
		final Instituicao instituicao;
		RetornoDTO retorno = new RetornoDTO();
		switch ((Integer) dto.getDados().get("tipo")) {
		case GERENTE:
			instituicao = (Instituicao) dto.getDados().get(UNIDADE_ORIGEM);
			retorno.getDados().put(TOTAL, delegatePessoaInstituicao.pesquisarNumeroRegistros(montarConsulta(instituicao.getIdInstituicao(), instituicao.getIdUnidadeInst(), (Funcionario) dto.getDados().get("gerenteOrigem"), null)));
			break;
		case UNIDADE:
			instituicao = (Instituicao) dto.getDados().get(UNIDADE_ORIGEM);
			retorno.getDados().put(TOTAL, delegatePessoaInstituicao.pesquisarNumeroRegistros(montarConsulta(instituicao.getIdInstituicao(), instituicao.getIdUnidadeInst(), null, null)));
			break;
		case GRUPO:
			Integer idGrupoOrigem = (Integer) dto.getDados().get("idGrupoOrigem");
			retorno.getDados().put(TOTAL, delegateGrupoEconomicoPessoaDelegate.pesquisarNumeroRegistros(idGrupoOrigem));
			break;
		case NUCLEO:
			retorno.getDados().put(TOTAL, delegatePessoaInstituicao.pesquisarNumeroRegistros(montarConsulta(getIdInstituicaoUsuarioLogado(), null, null, (Nucleo) dto.getDados().get("nucleoOrigem"))));
			break;
		}
		return retorno;
	}
	/**
	 * Executa a transferência de informações
	 */
	public RetornoDTO transferirInformacoes(RequisicaoReqDTO dto) throws BancoobException {
		Instituicao instituicao;
		final ConsultaDto<PessoaInstituicao> consulta;
		switch ((Integer) dto.getDados().get("tipo")) {
		case GERENTE:
		case UNIDADE:
			instituicao = (Instituicao) dto.getDados().get(UNIDADE_ORIGEM);
			consulta = montarConsulta(instituicao.getIdInstituicao(), instituicao.getIdUnidadeInst(), (Funcionario) dto.getDados().get("gerenteOrigem"), null);
			instituicao = (Instituicao) dto.getDados().get("unidadeDestino");
			delegateTransferenciaInformacoes.transferirInformacoesUnidade(consulta, instituicao.getIdUnidadeInst(), (Funcionario) dto.getDados().get("gerenteDestino"));
			break;
		case GRUPO:
			Integer idGrupoOrigem = (Integer) dto.getDados().get("idGrupoOrigem");
			Integer idGrupoDestino = (Integer) dto.getDados().get("idGrupoDestino");
			delegateTransferenciaInformacoes.transferirInformacoesGrupo(montarConsulta(idGrupoOrigem), montarGrupoEconomico(idGrupoDestino));
			break;
		case NUCLEO:
			consulta = montarConsulta(getIdInstituicaoUsuarioLogado(), null, null, (Nucleo) dto.getDados().get("nucleoOrigem"));
			delegateTransferenciaInformacoes.transferirInformacoesNucleo(consulta, (Nucleo) dto.getDados().get("nucleoDestino"));
			break;
		case CPFCNPJ:
			instituicao = (Instituicao) dto.getDados().get("unidadeDestino");
			delegateTransferenciaInformacoes.transferirInformacoesCpfCnpj((String) dto.getDados().get("cpfCnpj"), instituicao.getIdUnidadeInst(), (Funcionario) dto.getDados().get("gerenteDestino"), (Nucleo) dto.getDados().get("nucleoDestino"));
			break;
		}
		return new RetornoDTO();
	}
	/**
	 * Monta o filtro de {@link PessoaInstituicao}
	 * @param idInstituicao
	 * @param idUnidadeInst
	 * @param gerente
	 * @param nucleo
	 * @return
	 */
	private ConsultaDto<PessoaInstituicao> montarConsulta(Integer idInstituicao, Integer idUnidadeInst, Funcionario gerente, Nucleo nucleo){
		ConsultaDto<PessoaInstituicao> criterios = new ConsultaDto<PessoaInstituicao>();
		PessoaInstituicao filtro = new PessoaInstituicao();
		criterios.setFiltro(filtro);
		filtro.setIdInstituicao(idInstituicao);
		filtro.setIdUnidadeInst(idUnidadeInst);
		filtro.setFuncionario(gerente);
		filtro.setNucleo(nucleo);
		return criterios;
	}
	/**
	 * Monta o filtro de {@link GrupoEconomicoPessoa}
	 * @param grupoOrigem
	 * @return
	 */
	private ConsultaDto<GrupoEconomicoPessoa> montarConsulta(Integer idGrupoOrigem){
		ConsultaDto<GrupoEconomicoPessoa> criterios = new ConsultaDto<GrupoEconomicoPessoa>();
		GrupoEconomico grupoOrigem = new GrupoEconomico();
		grupoOrigem.setIdGrupoEconomico(idGrupoOrigem);
		GrupoEconomicoPessoa filtro = new GrupoEconomicoPessoa();
		filtro.setGrupoEconomico(grupoOrigem);
		criterios.setFiltro(filtro);
		return criterios;
	}
	
	private ConsultaDto<GrupoEconomico> obterFiltroConsultaGrupoEconomico() {
		ConsultaDto<GrupoEconomico> criterios = new ConsultaDto<GrupoEconomico>();
		GrupoEconomico filtro = new GrupoEconomico();
		filtro.setIdInstituicao(getIdInstituicaoUsuarioLogado());
		criterios.setFiltro(filtro);
		return criterios;
	}
	
	private GrupoEconomico montarGrupoEconomico(Integer idGrupoDestino) {
		GrupoEconomico retorno = new GrupoEconomico();
		retorno.setIdGrupoEconomico(idGrupoDestino);
		return retorno;
	}
	
	/**
	 * Recupera o valor de unidades.
	 *
	 * @return o valor de unidades
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<Instituicao> getUnidades() throws BancoobException {
		List<Instituicao> instituicoes = new ArrayList<Instituicao>();
		for (UnidadeInstituicaoVO instituicao_ : sciIntegracaoDelegate.listarUnidadesInstituicao(getIdInstituicaoUsuarioLogado(), false)) {
			Instituicao instituicao = new Instituicao();
			instituicao.setNomeUnidade(instituicao_.getNomeUnidade());
			instituicao.setIdUnidadeInst(instituicao_.getIdUnidadeInst());
			
			InstituicaoVO vo = sciIntegracaoDelegate.obterInstituicaoPorId(instituicao_.getIdInstituicao());
			instituicao.setNome(vo.getNome());
			instituicao.setNumero(String.valueOf(vo.getNumero()));
			instituicao.setIdInstituicao(vo.getIdInstituicao());
			
			instituicoes.add(instituicao);
		}
		return instituicoes;
	}
	
	/**
	 * Recupera as unidades ativas.
	 *
	 * @return o valor de unidades
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<Instituicao> getUnidadesAtivas() throws BancoobException {
		List<Instituicao> instituicoes = new ArrayList<Instituicao>();
		for (UnidadeInstituicaoVO instituicao_ : sciIntegracaoDelegate.listarUnidadesInstituicao(getIdInstituicaoUsuarioLogado(), Boolean.TRUE)) {
			Instituicao instituicao = new Instituicao();
			instituicao.setNomeUnidade(instituicao_.getNomeUnidade());
			instituicao.setIdUnidadeInst(instituicao_.getIdUnidadeInst());
			
			InstituicaoVO vo = sciIntegracaoDelegate.obterInstituicaoPorId(instituicao_.getIdInstituicao());
			instituicao.setNome(vo.getNome());
			instituicao.setNumero(String.valueOf(vo.getNumero()));
			instituicao.setIdInstituicao(vo.getIdInstituicao());
			
			instituicoes.add(instituicao);
		}
		return instituicoes;
	}
	
	/**
	 * Recupera o valor de unidades.
	 *
	 * @return o valor de unidades
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<Instituicao> getUnidadesSemPADigital() throws BancoobException {
		List<Instituicao> instituicoes = new ArrayList<Instituicao>();
		for (UnidadeInstituicaoVO instituicao_ : sciIntegracaoDelegate.listarUnidadesInstituicaoSemPADigital(getIdInstituicaoUsuarioLogado(), false)) {
			Instituicao instituicao = new Instituicao();
			instituicao.setNomeUnidade(instituicao_.getNomeUnidade());
			instituicao.setIdUnidadeInst(instituicao_.getIdUnidadeInst());
			
			InstituicaoVO vo = sciIntegracaoDelegate.obterInstituicaoPorId(instituicao_.getIdInstituicao());
			instituicao.setNome(vo.getNome());
			instituicao.setNumero(String.valueOf(vo.getNumero()));
			instituicao.setIdInstituicao(vo.getIdInstituicao());
			
			instituicoes.add(instituicao);
		}
		return instituicoes;
	}

	/**
	 * Recupera o valor de idInstituicaoUsuarioLogado.
	 *
	 * @return o valor de idInstituicaoUsuarioLogado
	 */
	private Integer getIdInstituicaoUsuarioLogado() {
		return Integer.valueOf(obterUsuarioLogado().getIdInstituicao());
	}
	
	/**
	 * Obter status clientes.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterStatusClientes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		retorno.getDados().put("statusCliente", delegatePessoaInstituicao.obterStatusTransferenciaCliente());
		return retorno;
	}
	
	/**
	 * Obter status grupo economico.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterStatusGrupoEconomico(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		retorno.getDados().put("statusGrupoEconomico", delegateGrupoEconomico.obterStatusTransferenciaGrupoEconomico());
		return retorno;
	}
	
}