/* 
 * Sicoob
 * CAPESCadastroBOCrudFachada.java 
 * Criado em: 06/05/2011
 */
package br.com.sicoob.capes.cadastro.fachada;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;
import javax.persistence.QueryTimeoutException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroCrudDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.excecao.CAPESCadastroNegocioException;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;

/**
 * Fachada que disponibiliza os serviços de Crud da entidade
 * 
 * 06/05/2011
 * 
 * @author Rodrigo.Chaves
 */
public abstract class CAPESCadastroBOCrudFachada<T extends CAPESEntidade<?>> extends CAPESCadastroBOFachada {

	/** A constante PESSOA. */
	protected static final String PESSOA = "pessoa";
	
	/** O atributo chaveMapa. */
	protected String chaveMapa;

	/**
	 * Construtor
	 * 
	 * @param chaveMapa
	 */
	public CAPESCadastroBOCrudFachada(String chaveMapa) {
		this.chaveMapa = chaveMapa;
	}

	/**
	 * Recupera entidade do dto.
	 * 
	 * @param dto
	 *            instancia de RequisicaoDTO.
	 * @throws BancoobException
	 *             Caso alguma exceção ocorra.
	 */
	protected abstract T obterEntidade(RequisicaoReqDTO dto);

	/**
	 * Recupera o delegate
	 * 
	 * @return o delegate
	 */
	protected abstract CAPESCadastroCrudDelegate<T, ?> obterDelegate();

	/**
	 * Lista a entidade usando os dados do dto como filtro.
	 * 
	 * @param dto
	 *            instancia de RequisicaoDTO que será usado como filtro da pesquisa.
	 * @return o retorno da consulta.
	 * @throws BancoobException
	 *             Caso alguma exceção ocorra.
	 */
	protected List<T> listar(RequisicaoReqDTO dto) throws BancoobException {
		try {
			return obterDelegate().listar();
		} catch (QueryTimeoutException e) {
			throw new CAPESCadastroNegocioException(
					String.format(Constantes.Negocio.MENSAGEM_FACHADA_QUERY_TIMEOUT));
		} catch (PersistenceException e) {
			throw new CAPESCadastroNegocioException(
					String.format(Constantes.Negocio.MENSAGEM_FACHADA_COULD_NOT_EXECUTE_QUERY));
		} catch (Exception e) {//nosonar
			throw new CAPESCadastroNegocioException(e.getMessage());
		}
	}

	/**
	 * Inclui os dados.
	 * 
	 * @param dto
	 *            uma instancia de RequisicaoDTO.
	 * @return uma instancia de RetornoDTO.
	 * @throws BancoobException
	 *             Caso alguma exceção ocorra.
	 */
	public RetornoDTO incluirDados(RequisicaoReqDTO dto) throws BancoobException {
		T entidade = obterEntidade(dto);
		obterDelegate().incluir(entidade);
		return obterMapRetorno(this.chaveMapa, entidade);
	}

	/**
	 * Altera os dados.
	 * 
	 * @param dto
	 *            uma instancia de RequisicaoDTO.
	 * @return uma instancia de RetornoDTO.
	 * @throws BancoobException
	 *             Caso alguma exceção ocorra.
	 */
	public RetornoDTO alterarDados(RequisicaoReqDTO dto) throws BancoobException {
		T entidade = obterEntidade(dto);
		validarEntidade(entidade);
		obterDelegate().alterar(entidade);
		return obterMapRetorno(this.chaveMapa, entidade);
	}

	/**
	 * Recupera a entidade.
	 * 
	 * @param dto
	 *            uma instancia de RequisicaoDTO contendo a chave da entidade que será recuperada.
	 * @return uma instancia de RetornoDTO.
	 * @throws BancoobException
	 *             Caso alguma exceção ocorra.
	 */
	public RetornoDTO obterDados(RequisicaoReqDTO dto) throws BancoobException {
		T entidade = obterEntidade(dto);
		T entidadePersistente = consultarEntidade(entidade);
		return obterMapRetorno(this.chaveMapa, entidadePersistente);
	}

	/**
	 * Consulta a entidade. 
	 * @param entidade que será consultada.
	 * @return uma instancia de RetornoDTO.
	 * @throws BancoobException Caso alguma exceção ocorra.
	 */
	protected T consultarEntidade(T entidade) throws BancoobException {
		validarEntidade(entidade);
		try {
			return obterDelegate().obter(entidade.getId());
		} catch (QueryTimeoutException e) {
			throw new CAPESCadastroNegocioException(
					String.format(Constantes.Negocio.MENSAGEM_FACHADA_QUERY_TIMEOUT));
		} catch (PersistenceException e) {
			throw new CAPESCadastroNegocioException(
					String.format(Constantes.Negocio.MENSAGEM_FACHADA_COULD_NOT_EXECUTE_QUERY));
		} catch (Exception e) {//nosonar
			throw new CAPESCadastroNegocioException(e.getMessage());
		}
	}

	/**
	 * Exclui a entidade passada como parametro.
	 * 
	 * @param dto
	 *            uma instancia de RequisicaoDTO.
	 * @return uma instancia de RetornoDTO.
	 * @throws BancoobException
	 *             Caso alguma exceção ocorra.
	 */
	public RetornoDTO excluirDados(RequisicaoReqDTO dto) throws BancoobException {
		T entidade = obterEntidade(dto);
		obterDelegate().excluir(entidade);
		return obterMapRetorno(this.chaveMapa, entidade);
	}

	/**
	 * Recupera os dados que serão utilizados na tela de seleção.
	 * 
	 * @return uma instancia de RetornoDTO.
	 * @throws BancoobException
	 *             Caso alguma exceção ocorra.
	 */
	@SuppressWarnings("unchecked")
	public DadosSelGeralDTO obterDadosSelecao(RequisicaoReqDTO dto) throws BancoobException {
		List<T> listaEntidades = listar(dto);
		DadosSelGeralDTO resultadoDto = new DadosSelGeralDTO();
		resultadoDto.getDados().put(NOME_PADRAO_LISTA, listaEntidades);
		return resultadoDto;
	}


	/**
	 * Recupera os dados que serão utilizados na tela de seleção.
	 * 
	 * @return uma instancia de {@link SelGeralReqDTO}.
	 * @throws BancoobException
	 *             Caso alguma exceção ocorra.
	 */
	public DadosSelGeralDTO obterDadosSelecao(SelGeralReqDTO dto) throws BancoobException {
		ConsultaDto<T> consultaDto = new ConsultaDto<T>();
		popularConsultaDto(consultaDto, dto);
		ConsultaDto<?> retornoConsultaDto = null;
		try {
			retornoConsultaDto = obterDelegate().pesquisar(consultaDto);
		} catch (QueryTimeoutException e) {
			throw new CAPESCadastroNegocioException(
					String.format(Constantes.Negocio.MENSAGEM_FACHADA_QUERY_TIMEOUT));
		} catch (PersistenceException e) {
			throw new CAPESCadastroNegocioException(
					String.format(Constantes.Negocio.MENSAGEM_FACHADA_COULD_NOT_EXECUTE_QUERY));
		} catch (Exception e) {//nosonar
			throw new CAPESCadastroNegocioException(e.getMessage());
		}
		return montarResultado(retornoConsultaDto);
	}

	/**
	 * Obtém as definições da fachada.
	 * 
	 * @return uma instancia de RetornoDTO.
	 * @throws BancoobException
	 *             Caso alguma exceção ocorra.
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		return new RetornoDTO();
	}

	/**
	 * Cria o mapa que será retornado pela Fachada.
	 * 
	 * @param chave
	 *            a chave da entidade que será retornado pelo mapa.
	 * @param entidade
	 *            a entidade que será retornada.
	 * @return o mapa que será retornado pela Fachada.
	 */
	protected RetornoDTO obterMapRetorno(String chave, T entidade) {
		RetornoDTO retornoDTO = new RetornoDTO();
		Map<String, Object> dados = new HashMap<String, Object>();
		dados.put(chave, entidade);
		retornoDTO.setDados(dados);
		return retornoDTO;
	}

	/**
	 * Obter fabrica delegates.
	 *
	 * @return CAPESCadastroFabricaDelegates
	 */
	protected CAPESCadastroFabricaDelegates obterFabricaDelegates() {
		return CAPESCadastroFabricaDelegates.getInstance();
	}
	
	/**
	 * Método padrão do componente de pesquisar por código.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@SuppressWarnings("unchecked")
	public DadosSelGeralDTO pesquisarEntidadeComponente(RequisicaoReqDTO dto) throws BancoobException {
		try {
			Class<T> classeGenerica = (Class<T>) ReflexaoUtils.obterParametroGenerico(getClass());
			T entidade = ReflexaoUtils.construirObjetoPorClasse(classeGenerica);
			preencherFiltroPesquisaComponente(entidade, dto);

			ConsultaDto<T> criterios = new ConsultaDto<T>();
			criterios.setTamanhoPagina(TAMANHO_PAGINA_PADRAO);
			criterios.setPagina(Integer.valueOf(dto.getDados().get("pagina").toString()) - DESLOCAMENTO_NUMERO_PAGINA);
			criterios.setFiltro(entidade);

			ConsultaDto<?> resultado = realizarConsultaEntidadeComponente(entidade, criterios);

			return montarResultado(resultado);
			
		} catch (QueryTimeoutException e) {
			throw new CAPESCadastroNegocioException(
					String.format(Constantes.Negocio.MENSAGEM_FACHADA_QUERY_TIMEOUT));
		} catch (PersistenceException e) {
			throw new CAPESCadastroNegocioException(
					String.format(Constantes.Negocio.MENSAGEM_FACHADA_COULD_NOT_EXECUTE_QUERY));
		} catch (Exception e) {//nosonar
			throw new NegocioException("O valor informado para pesquisa \u00e9 inv\u00E1lido.", e);
		}
	}
	
	/**
	 * Preenche o filtro para a pesquisa do componente.
	 *
	 * @param entidade o valor de entidade
	 * @param dto o valor de dto
	 * @return T
	 */
	protected void preencherFiltroPesquisaComponente(T entidade, RequisicaoReqDTO dto){
		
	}
	
	/**
	 * Realiza a consulta do componente.
	 * @param entidade
	 * @param criterios
	 * @return
	 * @throws BancoobException
	 */
	@SuppressWarnings("unchecked")
	protected ConsultaDto<?> realizarConsultaEntidadeComponente(T entidade, ConsultaDto<T> criterios) throws BancoobException {
		CAPESCadastroCrudDelegate<CAPESEntidade<?>, ?> delegate = obterFabricaDelegates().criarDelegate(entidade.getClass());
		ConsultaDto<?> resultado = delegate.pesquisar((ConsultaDto<CAPESEntidade<?>>) criterios);
		return resultado;
	}

}