package br.com.sicoob.capes.integracao.negocio.servicos;

import java.util.List;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.vo.LOCIntegracaoLocalidadeVO;
import br.com.sicoob.capes.comum.negocio.vo.LOCIntegracaoTipoLogradouroVO;
import br.com.sicoob.capes.comum.negocio.vo.LOCIntegracaoUFVO;
import br.com.sicoob.capes.comum.negocio.vo.LocalidadeVO;

/**
 * Servico de integracao com o projeto Localidade
 * 
 * 
 * @author Rodrigo.Chaves
 */
public interface LOCIntegracaoServico extends CAPESIntegracaoServico {

	/**
	 * Recupera a localidade a partir da chave informada.
	 * 
	 * @param chave
	 *            A chave da localidade.
	 * @return a localidade a partir da chave informada.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	LOCIntegracaoLocalidadeVO obterLocalidade(Integer chave) throws BancoobException;

	/**
	 * Recupera a Localidade pelo codigo da Receita Federal
	 * 
	 * @param codigoRFB
	 *            o codigo do municipio na base da Receita Federal
	 * @return a localidade ou uma localidade "NÃO ENCONTRADO" com ID 0 (zero)
	 */
	LOCIntegracaoLocalidadeVO obterLocalidadeRFB(Integer codigoRFB) throws BancoobException;

	/**
	 * Recupera a TipoLogradouro pelo nome da Receita Federal
	 * 
	 * @param nome
	 *            o nome do tipo de logradouro na base da Receita Federal
	 * @return o tipo de logradouro ou um tipo de logradouro "OUTROS" com ID 99
	 */
	LOCIntegracaoTipoLogradouroVO obterTipoLogradouroRFB(String nome) throws BancoobException;

	/**
	 * Recupera o TipoLogradouro por {@code id}
	 * 
	 * @param id
	 *            o ID do tipo de logradouro desejado
	 * @return o tipo de logradouro
	 */
	LOCIntegracaoTipoLogradouroVO obterTipoLogradouro(Short id) throws BancoobException;

	/**
	 * Recupera a lista com os tipos de logradouros.
	 * 
	 * @return a lista com os tipos de logradouros.
	 * @throws BancoobException
	 *             casa ocorra alguma exceção.
	 */
	List<LOCIntegracaoTipoLogradouroVO> listarTiposLogradouro() throws BancoobException;

	/**
	 * Lista as UFs
	 * 
	 * @return as UFs
	 * @throws BancoobException
	 *             casa ocorra alguma exceção.
	 */
	List<LOCIntegracaoUFVO> listarUFs() throws BancoobException;

	/**
	 * Pesquisar localidade complemento.
	 * 
	 * @param localidadeVO
	 *            the localidade vo
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	List<LocalidadeVO> pesquisarLocalidadeComplemento(LocalidadeVO localidadeVO) throws BancoobException;

	/**
	 * Pesquisa a localidade por idLogradouro
	 * 
	 * @param idLogradouro
	 * @return
	 * @throws BancoobException
	 */
	LocalidadeVO pesquisarLocalidadePorIdLogradouro(Integer idLogradouro) throws BancoobException;

	/**
	 * Recupera um mapa com os tipos de logradouro, onde a chave é o
	 * identificador do tipo e o valor é a descrição.
	 * 
	 * @return um mapa com os tipos de logradouro, onde a chave é o
	 *         identificador do tipo e o valor é a descrição.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	Map<Integer, String> obterMapaTiposLogradouro() throws BancoobException;

	/**
	 * Recupera o nome da localidade a partir do identificador informado.
	 * 
	 * @param idLocalidade
	 *            O identificador da localidade.
	 * @return o nome da localidade a partir do identificador informado.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção na consulta.
	 */
	String obterNomeLocalidade(Integer idLocalidade) throws BancoobException;

	/**
	 * Obtem a localidade conforme filtro.
	 * @param estadoLocalidade
	 * @param nomeLocalidade
	 * @return
	 */
	List<LocalidadeVO> pesquisarLocalidade(String estadoLocalidade, String nomeLocalidade)throws BancoobException;
	
	/**
	 * Lista todas as localidades cadastradas.
	 * 
	 * @param cooperativa
	 * @return
	 * @throws BancoobException
	 */
	List<LocalidadeVO> listarLocalidade(Integer cooperativa) throws BancoobException;

}
