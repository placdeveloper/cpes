package br.com.sicoob.capes.cadastro.fachada;

import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.api.negocio.vo.NucleoVO;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.CategoriaProdutorDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CentraisSingularesDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.FuncionarioDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.GrupoEconomicoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.NucleoDelegate;
import br.com.sicoob.capes.cadastro.negocio.dto.FuncionarioDTO;
import br.com.sicoob.capes.cadastro.negocio.vo.UnidadeVO;
import br.com.sicoob.capes.comum.negocio.vo.LocalidadeVO;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.LOCIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.CategoriaProdutor;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomico;
import br.com.sicoob.capes.negocio.entidades.Nucleo;

@RemoteService
public class RelatorioClienteProdutoFachada extends CAPESCadastroBOFachada {
	
	private static final String TODOS = "TODOS";
	
	private CentraisSingularesDelegate centraisSingularesDelegate = 
			CAPESCadastroFabricaDelegates.getInstance().criarCentraisSingularesDelegate();
	
	private Integer idInstituicao;
	private Integer cooperativa;
	
	/**
	 * Obter definicoes.
	 *
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterDefinicoes() throws BancoobException {
		
		RetornoDTO retornoDTO = new RetornoDTO();
		UsuarioBancoob usuario = obterUsuarioLogado();
		idInstituicao = Integer.valueOf(usuario.getIdInstituicao());
		cooperativa = Integer.valueOf(usuario.getCooperativa());
		
		retornoDTO.getDados().put("listaPac", listarPACs());
		retornoDTO.getDados().put("listaNucleo", listarNucleo());
		//retornoDTO.getDados().put("listaGrupoEconomico", listarGrupoEconomico());
		retornoDTO.getDados().put("listaFuncionarioGerente", listarGerente());
		retornoDTO.getDados().put("listaCategoriaProdutor", listarCategoria());
		retornoDTO.getDados().put("listaMunicipio", listarMunicipio());
		retornoDTO.getDados().put("numCooperativa", cooperativa);
		
		return retornoDTO;
	}

	private List<UnidadeVO> listarPACs() throws BancoobException {
		List<UnidadeVO> listaPacs = new ArrayList<UnidadeVO>();
		List<UnidadeVO> pacs = centraisSingularesDelegate.obterListaPacs(cooperativa);
		UnidadeVO pacTodos = new UnidadeVO();
		
		pacTodos.setDescricao(TODOS);
		listaPacs.add(pacTodos);
		
		for (UnidadeVO unidade : pacs) {
			UnidadeVO pac = new UnidadeVO();
			pac.setCodigo(unidade.getCodigo());
			pac.setDescricao(unidade.getCodigo() + " - " + unidade.getDescricao());
			listaPacs.add(pac);
		}
		return listaPacs;
	}

	private List<LocalidadeVO> listarMunicipio() throws BancoobException {
		LOCIntegracaoDelegate delegateLocalidade = 
				CAPESIntegracaoFabricaDelegates.getInstance().criarLOCIntegracaoDelegate();
		
		List<LocalidadeVO> municipios = delegateLocalidade.listarLocalidade(cooperativa);
		
		List<LocalidadeVO> listaMunicipio = new ArrayList<LocalidadeVO>();
		LocalidadeVO municipioTodos = new LocalidadeVO();
		
		municipioTodos.setNomeLocalidade(TODOS);
		listaMunicipio.add(municipioTodos);
		
		for (LocalidadeVO unidade : municipios) {
			LocalidadeVO municipio = new LocalidadeVO();
			municipio.setNomeLocalidade(unidade.getSiglaUF() + " - " + unidade.getNomeLocalidade());
			municipio.setIdLocalidade(unidade.getIdLocalidade());
			listaMunicipio.add(municipio);
		}
		return listaMunicipio;
	}

	private List<FuncionarioDTO> listarGerente() throws BancoobException {

		FuncionarioDelegate delegateFuncionario = CAPESCadastroFabricaDelegates.getInstance()
				.criarFuncionarioDelegate();

		List<FuncionarioDTO> gerentes = delegateFuncionario.obterListaFuncionarioGerente(idInstituicao);

		List<FuncionarioDTO> listaGerentes = new ArrayList<FuncionarioDTO>();
		FuncionarioDTO gerenteTodos = new FuncionarioDTO();

		gerenteTodos.setNomePessoaLegado(TODOS);
		listaGerentes.add(gerenteTodos);

		for (FuncionarioDTO funcionario : gerentes) {
			FuncionarioDTO gerente = new FuncionarioDTO();
			gerente.setIdPessoaLegado(funcionario.getIdPessoaLegado());
			gerente.setNomePessoaLegado(funcionario.getNomePessoaLegado());
			listaGerentes.add(gerente);
		}
		return listaGerentes;

	}

	private List<GrupoEconomico> listarGrupoEconomico() throws BancoobException {

		GrupoEconomicoDelegate grupoEconomicoDelegate = CAPESCadastroFabricaDelegates.getInstance()
				.criarGrupoEconomicoDelegate();

		List<GrupoEconomico> gruposEconomicos = grupoEconomicoDelegate.obterListaGrupoEconomico(idInstituicao);

		List<GrupoEconomico> listaGruposEconomicos = new ArrayList<GrupoEconomico>();
		GrupoEconomico grupoEconomicoTodo = new GrupoEconomico();

		grupoEconomicoTodo.setDescricao(TODOS);
		listaGruposEconomicos.add(grupoEconomicoTodo);

		for (GrupoEconomico grupoEconomico : gruposEconomicos) {
			GrupoEconomico grupoEconomicoVO = new GrupoEconomico();
			grupoEconomicoVO.setIdGrupoEconomico(grupoEconomico.getIdGrupoEconomico());
			grupoEconomicoVO.setDescricao(grupoEconomico.getDescricao());
			listaGruposEconomicos.add(grupoEconomicoVO);
		}
		return listaGruposEconomicos;

	}

	private List<CategoriaProdutor> listarCategoria() throws BancoobException {

		CategoriaProdutorDelegate categoriaProdutorDelegate = CAPESCadastroFabricaDelegates.getInstance()
				.criarCategoriaProdutorDelegate();

		List<CategoriaProdutor> categorias = categoriaProdutorDelegate.listar();

		List<CategoriaProdutor> listaCategorias = new ArrayList<CategoriaProdutor>();
		CategoriaProdutor categoriaTodos = new CategoriaProdutor();

		categoriaTodos.setDescricao(TODOS);
		listaCategorias.add(categoriaTodos);

		for (CategoriaProdutor categoria : categorias) {
			CategoriaProdutor categoriaProdutor = new CategoriaProdutor();
			categoriaProdutor.setId(categoria.getId());
			categoriaProdutor.setDescricao(categoria.getDescricao());
			listaCategorias.add(categoriaProdutor);
		}
		return listaCategorias;

	}

	private List<NucleoVO> listarNucleo() throws BancoobException {

		List<NucleoVO> listaNucleos = new ArrayList<NucleoVO>();
		List<Nucleo> nucleos = obterNucloes();
		NucleoVO nucleoTodos = new NucleoVO();

		nucleoTodos.setDescricao(TODOS);
		listaNucleos.add(nucleoTodos);
		
		for (Nucleo nucleo : nucleos) {
			NucleoVO nucleoVO = new NucleoVO();
			nucleoVO.setId(nucleo.getId());
			nucleoVO.setDescricao(nucleo.getDescricao());
			listaNucleos.add(nucleoVO);
		}
		return listaNucleos;
	}

	private List<Nucleo> obterNucloes() throws BancoobException {
		NucleoDelegate delegateNucleo = CAPESCadastroFabricaDelegates.getInstance().criarNucleoDelegate();
		ConsultaDto<Nucleo> criterios = new ConsultaDto<Nucleo>();
		Nucleo filtro = new Nucleo();
		filtro.setIdInstituicao(idInstituicao);
		criterios.setFiltro(filtro);
		return delegateNucleo.pesquisar(criterios).getResultado();
	}
}