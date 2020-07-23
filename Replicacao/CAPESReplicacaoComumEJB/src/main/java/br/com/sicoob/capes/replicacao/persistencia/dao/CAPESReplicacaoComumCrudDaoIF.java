package br.com.sicoob.capes.replicacao.persistencia.dao;

import java.io.Serializable;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.dao.BancoobCrudDaoIF;
import br.com.sicoob.capes.negocio.entidades.legado.CAPESEntidadeLegado;

/**
 * @author Stefanini IT Solutions
 */
public interface CAPESReplicacaoComumCrudDaoIF<T extends CAPESEntidadeLegado<?>> extends
		BancoobCrudDaoIF<T> {

	/**
	 * Método para incluir um objeto.
	 * 
	 * @param objeto
	 *            o objeto a ser incluído.
	 * @param numeroCooperativa
	 *            O número da cooperativa.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção na inclusão.
	 */
	T incluir(T objeto, Integer numeroCooperativa) throws BancoobException;

	/**
	 * Método para excluir um objeto.
	 * 
	 * @param chave
	 *            A chave da entidade.
	 * @param numeroCooperativa
	 *            O número da cooperativa.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção na exclusão.
	 */
	void excluir(Serializable chave, Integer numeroCooperativa) throws BancoobException;

	/**
	 * Método para alterar um objeto.
	 * 
	 * @param objeto
	 *            o objeto a ser alterado.
	 * @param numeroCooperativa
	 *            O número da cooperativa.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção na alteração.
	 */
	void alterar(T objeto, Integer numeroCooperativa) throws BancoobException;

	/**
	 * Método para obter uma entidade.
	 * 
	 * @param chave
	 *            A chave da entidade.
	 * @param numeroCooperativa
	 *            O número da cooperativa.
	 * @return A entidade procurada na cooperativa informada.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção na alteração.
	 */
	T obter(Serializable chave, Integer numeroCooperativa) throws BancoobException;
	
	/**
	 * Método para listar 
	 * @param numeroCooperativa
	 *            O número da cooperativa.
	 * @return A lista de todas as entidade procuradas na cooperativa informada.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção na alteração.
	 */
	List<T> listar(Integer numeroCooperativa) throws BancoobException;

	/**
	 * Método para listar 
	 * @param criterios
	 * 			  Os critérios para o filtro.
	 * @param numeroCooperativa
	 *            O número da cooperativa.
	 * @return A lista de todas as entidade procuradas na cooperativa informada.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção na alteração.
	 */
	List<T> listar(ConsultaDto<T> criterios, Integer numeroCooperativa) throws BancoobException;
}