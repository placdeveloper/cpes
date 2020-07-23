package br.com.sicoob.capes.replicacao.persistencia.dao;

import br.com.sicoob.capes.negocio.entidades.legado.Bem;
import br.com.sicoob.capes.negocio.entidades.legado.BemPosse;


/**
 * Interface que define os metodos de persistencia.
 * 
 * @author Juan.Damasceno
 *
 */
public interface BemPosseDAO extends EntidadeReplicavelDaoIF<BemPosse> {

	/**
	 * Obter max sequencial.
	 *
	 * @param bem o valor de bem
	 * @return Short
	 */
	Short obterMaxSequencial(Bem bem);
}