/**
 * 
 */
package br.com.sicoob.capes.replicacao.persistencia.dao;

import java.io.Serializable;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.Bem;


/**
 * Dao utilizada para as referências das pessoas.
 * 
 * @author Erico.Junior
 */
public interface BemDAO extends EntidadeReplicavelDaoIF<Bem> {
	
	/**
	 * Método para obter uma entidade a partir do identificador no DB2.
	 * 
	 * @param chave
	 *            A chave da entidade no DB2.
	 * @param numeroCooperativa
	 *            O número da cooperativa.
	 * @return A entidade procurada na instituição informada.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção na alteração.
	 */
	List<Bem> obterPorIdsDB2(Serializable chave, Integer numeroCooperativa) throws BancoobException;

}
