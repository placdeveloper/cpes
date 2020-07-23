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
	 * M�todo para incluir um objeto.
	 * 
	 * @param objeto
	 *            o objeto a ser inclu�do.
	 * @param numeroCooperativa
	 *            O n�mero da cooperativa.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o na inclus�o.
	 */
	T incluir(T objeto, Integer numeroCooperativa) throws BancoobException;

	/**
	 * M�todo para excluir um objeto.
	 * 
	 * @param chave
	 *            A chave da entidade.
	 * @param numeroCooperativa
	 *            O n�mero da cooperativa.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o na exclus�o.
	 */
	void excluir(Serializable chave, Integer numeroCooperativa) throws BancoobException;

	/**
	 * M�todo para alterar um objeto.
	 * 
	 * @param objeto
	 *            o objeto a ser alterado.
	 * @param numeroCooperativa
	 *            O n�mero da cooperativa.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o na altera��o.
	 */
	void alterar(T objeto, Integer numeroCooperativa) throws BancoobException;

	/**
	 * M�todo para obter uma entidade.
	 * 
	 * @param chave
	 *            A chave da entidade.
	 * @param numeroCooperativa
	 *            O n�mero da cooperativa.
	 * @return A entidade procurada na cooperativa informada.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o na altera��o.
	 */
	T obter(Serializable chave, Integer numeroCooperativa) throws BancoobException;
	
	/**
	 * M�todo para listar 
	 * @param numeroCooperativa
	 *            O n�mero da cooperativa.
	 * @return A lista de todas as entidade procuradas na cooperativa informada.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o na altera��o.
	 */
	List<T> listar(Integer numeroCooperativa) throws BancoobException;

	/**
	 * M�todo para listar 
	 * @param criterios
	 * 			  Os crit�rios para o filtro.
	 * @param numeroCooperativa
	 *            O n�mero da cooperativa.
	 * @return A lista de todas as entidade procuradas na cooperativa informada.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o na altera��o.
	 */
	List<T> listar(ConsultaDto<T> criterios, Integer numeroCooperativa) throws BancoobException;
}