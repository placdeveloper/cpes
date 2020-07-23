package br.com.sicoob.capes.cadastro.fachada;

import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.FuncaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.FuncionarioDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.NucleoDelegate;
import br.com.sicoob.capes.comum.negocio.vo.InstituicaoVO;
import br.com.sicoob.capes.comum.negocio.vo.UnidadeInstituicaoVO;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.Funcionario;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Nucleo;

/**
 * A Classe FuncionarioFachada.
 */
@RemoteService
public class FuncionarioFachada extends CAPESCadastroBOCrudFachada<Funcionario> {

	/** O atributo delegateFuncionario. */
	private final FuncionarioDelegate delegateFuncionario = CAPESCadastroFabricaDelegates
			.getInstance().criarFuncionarioDelegate();
	
	/** O atributo delegateNucleo. */
	private final NucleoDelegate delegateNucleo = CAPESCadastroFabricaDelegates
			.getInstance().criarNucleoDelegate();
	
	/** O atributo delegateFuncao. */
	private final FuncaoDelegate delegateFuncao = CAPESCadastroFabricaDelegates
			.getInstance().criarFuncaoDelegate();
	
	/** O atributo sciIntegracaoDelegate. */
	private final SCIIntegracaoDelegate sciIntegracaoDelegate = CAPESIntegracaoFabricaDelegates
			.getInstance().criarSCIIntegracaoDelegate();

	/**
	 * Instancia um novo FuncionarioFachada.
	 */
	public FuncionarioFachada() {
		super("funcionario");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected FuncionarioDelegate obterDelegate() {
		return this.delegateFuncionario;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Funcionario obterEntidade(RequisicaoReqDTO dto) {
		return (Funcionario) dto.getDados().get(this.chaveMapa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		retorno.getDados().put("funcoes", delegateFuncao.listar());
		retorno.getDados().put("pacs", getPACs());
		retorno.getDados().put("nucleos", getNucleosAtivos());
		return retorno;
	}
	
	public List<Nucleo> getNucleosAtivos() throws BancoobException{
		ConsultaDto<Nucleo> consultaDto = new ConsultaDto<Nucleo>();
		return delegateNucleo.pesquisarPorInstituicaoAtivos(consultaDto).getResultado();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DadosSelGeralDTO obterDadosSelecao(SelGeralReqDTO dto) throws BancoobException {
		ConsultaDto<Funcionario> consultaDto = montarConsultaDto(dto, Funcionario.class);
		ConsultaDto<Funcionario> resposta = obterDelegate().pesquisarPorInstituicao(consultaDto);
		return montarResultado(resposta);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO excluirDados(RequisicaoReqDTO dto) throws BancoobException {
		Funcionario entidade = obterEntidade(dto);
		obterDelegate().excluir(entidade);
		return obterMapRetorno(this.chaveMapa, entidade);
	}
	
	/**
	 * Recupera o valor de PACs.
	 *
	 * @return o valor de PACs
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<Instituicao> getPACs() throws BancoobException {
		List<Instituicao> instituicoes = new ArrayList<Instituicao>();
		for (UnidadeInstituicaoVO unidade : getUnidadesInstituicaoUsuarioLogado()) {
			InstituicaoVO instituicaoSCI = sciIntegracaoDelegate.obterInstituicaoPorId(unidade.getIdInstituicao());
			Instituicao instituicao = new Instituicao();
			instituicao.setNomeUnidade(unidade.getNomeUnidade());
			instituicao.setNome(instituicaoSCI.getNome());
			instituicao.setNumero(String.valueOf(instituicaoSCI.getNumero()));
			instituicao.setIdInstituicao(instituicaoSCI.getIdInstituicao());
			instituicao.setIdUnidadeInst(unidade.getIdUnidadeInst());
			instituicoes.add(instituicao);        
		}
		return instituicoes;
	}

	/**
	 * Recupera o valor de unidadesInstituicaoUsuarioLogado.
	 *
	 * @return o valor de unidadesInstituicaoUsuarioLogado
	 */
	private List<UnidadeInstituicaoVO> getUnidadesInstituicaoUsuarioLogado() {
		return sciIntegracaoDelegate.listarUnidadesInstituicao(Integer.valueOf(obterUsuarioLogado().getIdInstituicao()), false);
	}
}