/**
 * 
 */
package br.com.sicoob.capes.replicacao.persistencia.dao;

import java.io.Serializable;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.EntidadeReplicavel;

/**
 * Interface para os DAOs de entidades replic�veis.
 * 
 * @author erico.junior
 */
public interface EntidadeReplicavelDaoIF<T extends EntidadeReplicavel<? extends Serializable>>
		extends CAPESReplicacaoComumCrudDaoIF<T> {

	/**
	 * M�todo para obter uma entidade a partir do identificador no DB2.
	 * 
	 * @param chave
	 *            A chave da entidade no DB2.
	 * @param numeroCooperativa
	 *            O n�mero da cooperativa.
	 * @return A entidade procurada na institui��o informada.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o na altera��o.
	 */
	T obterPorIdDB2(Serializable chave, Integer numeroCooperativa) throws BancoobException;
	
	/**
	 * M�todo para obter uma lista de entidade a partir do identificador no DB2.
	 * 
	 * @param chave
	 *            A chave da entidade no DB2.
	 * @param numeroCooperativa
	 *            O n�mero da cooperativa.
	 * @return A a lista de entidade procurada na institui��o informada.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o na altera��o.
	 */
	List<T> obterPorIdsDB2(Serializable chave, Integer numeroCooperativa) throws BancoobException;
	
}
