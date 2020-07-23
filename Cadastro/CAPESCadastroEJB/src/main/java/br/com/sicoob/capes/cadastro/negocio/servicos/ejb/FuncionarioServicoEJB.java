/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade.AtualizacaoFuncionarioFachada;
import br.com.sicoob.capes.cadastro.negocio.dto.FuncionarioDTO;
import br.com.sicoob.capes.cadastro.negocio.enums.FuncaoEnum;
import br.com.sicoob.capes.cadastro.negocio.excecao.FuncionarioAssociadoClienteHistoricoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.FuncionarioExclusaoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.FuncionarioExisteInstituicaoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.FuncionarioExisteMatriculaException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.FuncionarioServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.FuncionarioServicoRemote;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.PessoaInstituicaoServicoLocal;
import br.com.sicoob.capes.cadastro.persistencia.dao.FuncionarioDAO;
import br.com.sicoob.capes.comum.negocio.vo.UnidadeInstituicaoVO;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.Funcao;
import br.com.sicoob.capes.negocio.entidades.Funcionario;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Nucleo;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao;
import br.com.sicoob.capes.replicacao.negocio.excecao.FuncionarioLegadoExclusaoException;

/**
 * Implementa as operações do serviço de Funcionario.
 * 
 * @author juan.damasceno
 */
@Stateless
@Local( { FuncionarioServicoLocal.class })
@Remote( { FuncionarioServicoRemote.class })
public class FuncionarioServicoEJB extends CAPESCadastroCrudServicoEJB<Funcionario>
		implements FuncionarioServicoRemote, FuncionarioServicoLocal {

	@Inject
	@Default
	private FuncionarioDAO dao;

	/** O atributo delegateSci. */
	private final SCIIntegracaoDelegate delegateSci = CAPESIntegracaoFabricaDelegates
			.getInstance().criarSCIIntegracaoDelegate();
	
	/** O atributo pessoaInstituicaoServico. */
	@EJB(mappedName = "capes/cadastro/PessoaInstituicaoServico")
	private PessoaInstituicaoServicoLocal pessoaInstituicaoServico;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected FuncionarioDAO getDAO() {
		return dao;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Funcionario> listarAtivosPorInstituicao(ConsultaDto<Funcionario> consultaDto) {
		return dao.listarAtivosPorInstituicao(consultaDto);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Funcionario> listarGerentesAtivos(Instituicao instituicao) {

		Funcao funcao = new Funcao();
		funcao.setIdFuncao(FuncaoEnum.GERENTE.getIdTipoFuncao());
		
		Funcionario filtro = new Funcionario();
		filtro.setInstituicao(instituicao);
		filtro.setFuncao(funcao);
		
		ConsultaDto<Funcionario> criterios = new ConsultaDto<Funcionario>();
		criterios.setFiltro(filtro);
		
		return listarAtivosPorInstituicao(criterios);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<Funcionario> listarGerentesAtivos() throws BancoobException {
		Funcao funcao = new Funcao();
		funcao.setIdFuncao(FuncaoEnum.GERENTE.getIdTipoFuncao());
		
		Funcionario filtro = new Funcionario();
		filtro.setFuncao(funcao);
		filtro.setInstituicao(obterIdInstituicaoUsuarioLogado());
		
		ConsultaDto<Funcionario> criterios = new ConsultaDto<Funcionario>();
		criterios.setFiltro(filtro);
		criterios.setOrdemCrescente(true);
		
		return listarAtivosPorInstituicao(criterios);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<Funcionario> listarGerentes(Instituicao instituicao) throws BancoobException {
		
		Funcao funcao = new Funcao();
		funcao.setIdFuncao(FuncaoEnum.GERENTE.getIdTipoFuncao());
		
		Funcionario filtro = new Funcionario();
		filtro.setInstituicao(instituicao);
		filtro.setFuncao(funcao);
		
		ConsultaDto<Funcionario> criterios = new ConsultaDto<Funcionario>();
		criterios.setFiltro(filtro);
		
		return pesquisar(criterios).getResultado();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<Funcionario> listarGerentes() throws BancoobException {
		Funcao funcao = new Funcao();
		funcao.setIdFuncao(FuncaoEnum.GERENTE.getIdTipoFuncao());
		Funcionario filtro = new Funcionario();
		filtro.setInstituicao(obterIdInstituicaoUsuarioLogado());
		filtro.setFuncao(funcao);
		
		ConsultaDto<Funcionario> criterios = new ConsultaDto<Funcionario>();
		criterios.setFiltro(filtro);
		criterios.setOrdemCrescente(true);
		
		return pesquisar(criterios).getResultado();
	}	
	
	/**
	 * Lista os funcinarios da instituição do usuario logado
	 */
	public ConsultaDto<Funcionario> pesquisarPorInstituicao(ConsultaDto<Funcionario> criterios) 
			throws BancoobException {
		
		Funcionario filtro = (Funcionario) criterios.getFiltro();
		if(filtro == null){
			filtro = new Funcionario();
		}
		
		Instituicao instituicaoFiltro = filtro.getInstituicao();
		if(instituicaoFiltro == null){
			instituicaoFiltro = new Instituicao();
		}
		instituicaoFiltro.setIdInstituicao(obterIdInstituicaoUsuarioLogado().getIdInstituicao());
		filtro.setInstituicao(instituicaoFiltro);
		
		ConsultaDto<Funcionario> retorno =  super.pesquisar(criterios);
		List<Funcionario> funcionarios = retorno.getResultado();
		
		if(CollectionUtils.isNotEmpty(funcionarios)){
			
			Integer idInstituicao = obterInstituicaoUsuarioLogado().getIdInstituicao();
			Map<Integer, UnidadeInstituicaoVO> unidades = recuperarUnidadesInstituicao(idInstituicao);
			
			for (Funcionario funcionario : funcionarios) {
				Instituicao instituicao = funcionario.getInstituicao();
				instituicao.setNomeUnidade(recuperarNomeUnidade(unidades, instituicao.getIdUnidadeInst()));
			}
		}
		
		return retorno;
	}
	
	/**
	 * Recuperar nome unidade.
	 *
	 * @param unidades o valor de unidades
	 * @param idUnidadeInst o valor de id unidade inst
	 * @return String
	 */
	private String recuperarNomeUnidade(Map<Integer, UnidadeInstituicaoVO> unidades, Integer idUnidadeInst) {
		String nome = "";
		if(unidades.containsKey(idUnidadeInst)) {
			nome = unidades.get(idUnidadeInst).getDescricao();
		}
		return nome;
	}
	
	/**
	 * Recuperar unidades instituicao.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @return Map
	 */
	private Map<Integer, UnidadeInstituicaoVO> recuperarUnidadesInstituicao(Integer idInstituicao) {
		Map<Integer, UnidadeInstituicaoVO> mapa = new HashMap<Integer, UnidadeInstituicaoVO>();
		List<UnidadeInstituicaoVO> lista = delegateSci.listarUnidadesInstituicao(idInstituicao, false);
		
		if(CollectionUtils.isNotEmpty(lista)) {
			for (UnidadeInstituicaoVO unidade : lista) {
				mapa.put(unidade.getId(), unidade);
			}
		}
		
		return mapa;
	}

	/**
	 * Incluir um {@link Funcionario} na base DB2 e SQL
	 */
	@Override
	public Funcionario incluir(Funcionario funcionario) throws BancoobException {
		if(funcionario.getInstituicao() != null && funcionario.getInstituicao().getIdUnidadeInst() != null){
			funcionario.getInstituicao().setIdInstituicao(obterInstituicaoUsuarioLogado().getIdInstituicao());
		} else {
			funcionario.setInstituicao(obterInstituicaoUsuarioLogado());
		}
		
		// verifica se já existe um funcionário associado a pessoa
		verificarFuncionarioDuplicadoInstituicao(funcionario.getPessoa());
		// verifica se já existe funcionario cadastrado com a mesma matricula na mesma instituição
		verificarMatriculaDuplicadaInstituicao(funcionario);
		
		Funcionario incluido = super.incluir(funcionario);
		
		getReplicacao().incluirFuncionarioLegado(incluido);
		return incluido;
	}

	/**
	 * Excluir o {@link Funcionario} da base DB2 e SQL
	 */
	public void excluir(Funcionario funcionario) throws BancoobException {
		
		validarExcluir(funcionario);
		
		try {
			super.excluir(funcionario.getId());
			getReplicacao().excluirFuncionarioLegado(funcionario);
		}catch (FuncionarioLegadoExclusaoException e) {
			throw new FuncionarioExclusaoException(e);
		}
	}
	
	/**
	 * O método Validar excluir.
	 *
	 * @param funcionario o valor de funcionario
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarExcluir(Funcionario funcionario) throws BancoobException {
		List<PessoaInstituicao> clientes = listarClientes(funcionario);
		
		if(CollectionUtils.isNotEmpty(clientes)) {
			throw new FuncionarioExclusaoException();
		}
		
		if(pessoaInstituicaoServico.verificaFuncionarioAssociadoClienteHistorico(funcionario)){
			throw new FuncionarioAssociadoClienteHistoricoException();
		}
		
	}
	
	/**
	 * Listar clientes.
	 *
	 * @param funcionario o valor de funcionario
	 * @return List
	 */
	private List<PessoaInstituicao> listarClientes(Funcionario funcionario) {
		return pessoaInstituicaoServico.listarPorFuncionarioResponsavel(funcionario);
	}
	
	/**
	 * Altera o {@link Funcionario} da base DB2 e SQL
	 */
	@Override
	public void alterar(Funcionario funcionario) throws BancoobException {
		// verifica se já existe funcionario cadastrado com a mesma matricula na mesma instituição
		verificarMatriculaDuplicadaInstituicao(funcionario);
		super.alterar(funcionario);
		getReplicacao().alterarFuncionarioLegado(funcionario);
	}
	
	/**
	 * Verifica se ja existe um {@link Funcionario} cadastrado para esta pessoa na instituição logada
	 * @param funcionario
	 * @throws BancoobException
	 */
	private void verificarFuncionarioDuplicadoInstituicao(
			Pessoa funcionario) throws BancoobException {
		
		Funcionario filtro = new Funcionario();
		filtro.setInstituicao(obterIdInstituicaoUsuarioLogado());
		filtro.setPessoa(funcionario);
		
		ConsultaDto<Funcionario> criterios = new ConsultaDto<Funcionario>();
		criterios.setFiltro(filtro);
		
		if(super.listar(criterios).size() > 0){
			throw new FuncionarioExisteInstituicaoException();
		}
	}
	/**
	 * Verifica se ja existe um {@link Funcionario} cadastrado na instituição logada com a mesma matricula
	 * @param funcionario
	 * @throws BancoobException
	 */
	private void verificarMatriculaDuplicadaInstituicao(
			Funcionario funcionario) throws BancoobException {
		
		Funcionario filtro = new Funcionario();
		filtro.setInstituicao(obterIdInstituicaoUsuarioLogado());
		filtro.setMatricula(funcionario.getMatricula());

		ConsultaDto<Funcionario> criterios = new ConsultaDto<Funcionario>();
		criterios.setFiltro(filtro);

		List<Funcionario> resultado = super.listar(criterios);
		if(CollectionUtils.isNotEmpty(resultado) 
				&& (funcionario.getIdFuncionario() == null 
				|| !funcionario.getIdFuncionario().equals(resultado.get(0).getIdFuncionario()))){
			throw new FuncionarioExisteMatriculaException();
		}
	}
	
	/**
	 * Obter id instituicao usuario logado.
	 *
	 * @return Instituicao
	 */
	private Instituicao obterIdInstituicaoUsuarioLogado() {
		Instituicao instituicao = new Instituicao();
		instituicao.setIdInstituicao(obterInstituicaoUsuarioLogado().getIdInstituicao());
		return instituicao;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Funcionario> obterGerente(PessoaCompartilhamento pessoaCompartilhamento, Integer idInstituicao) throws BancoobException {
		Funcao funcao = new Funcao();
		funcao.setIdFuncao(FuncaoEnum.GERENTE.getIdTipoFuncao());
		
		Funcionario filtro = new Funcionario();
		Instituicao instituicao = new Instituicao(idInstituicao, null);
		filtro.setInstituicao(instituicao);
		filtro.setFuncao(funcao);
		filtro.setPessoa(pessoaCompartilhamento.getPessoa());
		
		ConsultaDto<Funcionario> criterios = new ConsultaDto<Funcionario>();
		criterios.setFiltro(filtro);
		
		return pesquisar(criterios).getResultado();
	}
	
	/**
	 * @return delegate para replicação de {@link Funcionario}
	 */
	private AtualizacaoFuncionarioFachada getReplicacao() {
		return new AtualizacaoFuncionarioFachada();
	}

	/**
	 * {@inheritDoc}
	 */
	public Long obterQuantidadeFuncionariosPorNucleo(Nucleo nucleo) {
		return getDAO().obterQuantidadeFuncionariosPorNucleo(nucleo);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<FuncionarioDTO> obterListaFuncionarioGerente(Integer idInstituicao) throws BancoobException {
		if (idInstituicao == null) {
			throw new BancoobException("ID instituição não informado.");
		}
		return getDAO().obterListaFuncionarioGerente(idInstituicao);
	}
	
}