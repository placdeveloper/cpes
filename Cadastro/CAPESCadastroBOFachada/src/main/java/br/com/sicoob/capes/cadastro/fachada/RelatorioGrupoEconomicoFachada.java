package br.com.sicoob.capes.cadastro.fachada;

import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.FuncionarioDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.NucleoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.ParametroDelegate;
import br.com.sicoob.capes.cadastro.negocio.enums.ParametroEnum;
import br.com.sicoob.capes.cadastro.negocio.vo.ParametroVO;
import br.com.sicoob.capes.comum.negocio.vo.InstituicaoVO;
import br.com.sicoob.capes.comum.negocio.vo.UnidadeInstituicaoVO;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.Funcionario;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Nucleo;

/**
 * A Classe RelatorioGrupoEconomicoFachada.
 */
public class RelatorioGrupoEconomicoFachada extends CAPESCadastroBOFachada {

	/** O atributo delegateNucleo. */
	private NucleoDelegate delegateNucleo = CAPESCadastroFabricaDelegates.getInstance().criarNucleoDelegate();
	
	/** O atributo delegateFuncionario. */
	private FuncionarioDelegate delegateFuncionario = CAPESCadastroFabricaDelegates.getInstance().criarFuncionarioDelegate();
	
	/** O atributo sciIntegracaoDelegate. */
	private SCIIntegracaoDelegate sciIntegracaoDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
	
	private ParametroDelegate parametroDelegate = CAPESCadastroFabricaDelegates.getInstance().criarParametroDelegate();
	
	/** O atributo idInstituicaoSingular. */
	private Integer idInstituicaoSingular;

	/**
	 * Obter definicoes.
	 *
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterDefinicoes() throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		ParametroVO parametro = parametroDelegate.obterParametro(ParametroEnum.SERVICOS_GRUPO_ECONOMICO.getCodigo(), getIdInstituicaoUsuarioLogado());
		ParametroVO parametro2 = parametroDelegate.obterParametro(ParametroEnum.LIMITE_DIAS_HISTORICO_GRUPO_ECONOMICO.getCodigo(), getIdInstituicaoUsuarioLogado());
		retorno.getDados().put("destinoTela", parametro.getValor());
		retorno.getDados().put("diasFiltroHistorico", parametro2 == null ? 0 : Integer.valueOf(parametro2.getValor()));
		return retorno;
	}

	/**
	 * Carregar combos.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO carregarCombos(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		idInstituicaoSingular = (Integer) dto.getDados().get("idInstituicaoSingular");
		if(idInstituicaoSingular == null) {
			idInstituicaoSingular = getIdInstituicaoUsuarioLogado();
		}
		retorno.getDados().put("listaUnidades", consultarUnidadesUnidades());
		retorno.getDados().put("listaGerentes", listarGerentes());
		retorno.getDados().put("listaNucleos", consultarNucleos());

		return retorno;
	}

	/**
	 * Listar gerentes.
	 *
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<Funcionario> listarGerentes() throws BancoobException {
		Instituicao instituicao = new Instituicao();
		instituicao.setIdInstituicao(idInstituicaoSingular);
		return delegateFuncionario.listarGerentes(instituicao);
	}

	/**
	 * Consultar nucleos.
	 *
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<Nucleo> consultarNucleos() throws BancoobException {
		ConsultaDto<Nucleo> criterios = new ConsultaDto<Nucleo>();
		Nucleo filtro = new Nucleo();
		filtro.setIdInstituicao(idInstituicaoSingular);
		criterios.setFiltro(filtro);
		return delegateNucleo.pesquisar(criterios).getResultado();
	}

	/**
	 * Consultar unidades unidades.
	 *
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<Instituicao> consultarUnidadesUnidades() throws BancoobException {
		List<Instituicao> instituicoes = new ArrayList<Instituicao>();
		for (UnidadeInstituicaoVO instituicao_ : sciIntegracaoDelegate.listarUnidadesInstituicao(idInstituicaoSingular, false)) {
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

}