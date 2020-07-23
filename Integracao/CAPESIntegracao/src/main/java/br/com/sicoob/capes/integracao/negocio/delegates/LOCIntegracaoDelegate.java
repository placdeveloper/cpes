package br.com.sicoob.capes.integracao.negocio.delegates;

import java.util.List;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.vo.LOCIntegracaoLocalidadeVO;
import br.com.sicoob.capes.comum.negocio.vo.LOCIntegracaoTipoLogradouroVO;
import br.com.sicoob.capes.comum.negocio.vo.LOCIntegracaoUFVO;
import br.com.sicoob.capes.comum.negocio.vo.LocalidadeVO;
import br.com.sicoob.capes.integracao.negocio.servicos.LOCIntegracaoServico;
import br.com.sicoob.capes.integracao.negocio.servicos.locator.CAPESIntegracaoServiceLocator;

/**
 * 
 * @author Rodrigo.Chaves
 */
public class LOCIntegracaoDelegate extends CAPESIntegracaoDelegate<LOCIntegracaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected LOCIntegracaoServico localizarServico() {
		return CAPESIntegracaoServiceLocator.getInstance().localizarLOCIntegracaoServico();
	}

	/**
	 * Recupera a localidade a partir da chave informada.
	 * 
	 * @param chave
	 *            A chave da localidade.
	 * @return a localidade a partir da chave informada.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	public LOCIntegracaoLocalidadeVO obterLocalidade(Integer chave) throws BancoobException {
		return getServico().obterLocalidade(chave);
	}

	/**
	 * Recupera a Localidade pelo codigo da Receita Federal
	 * 
	 * @param codigoRFB
	 *            o codigo do municipio na base da Receita Federal
	 * @return a localidade ou uma localidade "NÃO ENCONTRADO" com ID 0 (zero)
	 */
	public LOCIntegracaoLocalidadeVO obterLocalidadeRFB(Integer codigoRFB) throws BancoobException {
		return getServico().obterLocalidadeRFB(codigoRFB);
	}

	/**
	 * Recupera a TipoLogradouro pelo nome da Receita Federal
	 * 
	 * @param nome
	 *            o nome do tipo de logradouro na base da Receita Federal
	 * @return o tipo de logradouro ou um tipo de logradouro "OUTROS" com ID 99
	 */
	public LOCIntegracaoTipoLogradouroVO obterTipoLogradouroRFB(String nome) throws BancoobException {
		return getServico().obterTipoLogradouroRFB(nome);
	}

	/**
	 * Recupera o TipoLogradouro por {@code id}
	 * 
	 * @param id
	 *            o ID do tipo de logradouro desejado
	 * @return o tipo de logradouro
	 * @see LOCIntegracaoServico#obterTipoLogradouro(Short)
	 */
	public LOCIntegracaoTipoLogradouroVO obterTipoLogradouro(Short id) throws BancoobException {
		return getServico().obterTipoLogradouro(id);
	}

	/**
	 * Recupera a lista com os tipos de logradouros.
	 * 
	 * @return a lista com os tipos de logradouros.
	 * @throws BancoobException
	 *             casa ocorra alguma exceção.
	 */
	public List<LOCIntegracaoTipoLogradouroVO> listarTiposLogradouro() throws BancoobException {
		return getServico().listarTiposLogradouro();
	}

	/**
	 * Lista as UFs
	 * 
	 * @return as UFs
	 * @throws BancoobException
	 *             casa ocorra alguma exceção.
	 */
	public List<LOCIntegracaoUFVO> listarUFs() throws BancoobException {
		return getServico().listarUFs();
	}

	/**
	 * Pesquisar Localidade integração.
	 * 
	 * @param localidadeVO
	 * @return
	 * @throws BancoobException
	 */
	public List<LocalidadeVO> pesquisarLocalidadeComplemento(LocalidadeVO localidadeVO) throws BancoobException {
		return getServico().pesquisarLocalidadeComplemento(localidadeVO);
	}

	/**
	 * Pesquisa a localidade por idLogradouro
	 * 
	 * @param idLogradouro
	 * @return
	 * @throws BancoobException
	 */
	public LocalidadeVO pesquisarLocalidadePorIdLogradouro(Integer idLogradouro) throws BancoobException {
		return getServico().pesquisarLocalidadePorIdLogradouro(idLogradouro);
	}

	/**
	 * Recupera um mapa com os tipos de logradouro, onde a chave é o
	 * identificador do tipo e o valor é a descrição.
	 * 
	 * @return um mapa com os tipos de logradouro, onde a chave é o
	 *         identificador do tipo e o valor é a descrição.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	public Map<Integer, String> obterMapaTiposLogradouro() throws BancoobException {
		return getServico().obterMapaTiposLogradouro();
	}

	/**
	 * Recupera o nome da localidade a partir do identificador informado.
	 * 
	 * @param idLocalidade
	 *            O identificador da localidade.
	 * @return o nome da localidade a partir do identificador informado.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção na consulta.
	 */
	public String obterNomeLocalidade(Integer idLocalidade) throws BancoobException {
		return getServico().obterNomeLocalidade(idLocalidade);
	}

	/**
	 * Obtem a localidade conforme filtro.
	 * @param estadoLocalidade
	 * @param nomeLocalidade
	 * @return
	 * @throws BancoobException 
	 */
	public List<LocalidadeVO> pesquisarLocalidade(String estadoLocalidade, String nomeLocalidade) throws BancoobException  {
		return getServico().pesquisarLocalidade(estadoLocalidade, nomeLocalidade);
	}
	
	/**
	 * Lista todas as localidades cadastradas.
	 * 
	 * @param cooperativa
	 * @return
	 * @throws BancoobException
	 */
	public List<LocalidadeVO> listarLocalidade(Integer cooperativa) throws BancoobException {
		return getServico().listarLocalidade(cooperativa);
	}

}