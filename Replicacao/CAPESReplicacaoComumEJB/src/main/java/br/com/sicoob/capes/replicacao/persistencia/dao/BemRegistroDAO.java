package br.com.sicoob.capes.replicacao.persistencia.dao;

import br.com.sicoob.capes.negocio.entidades.legado.Bem;
import br.com.sicoob.capes.negocio.entidades.legado.BemRegistro;


/**
 * Interface que define os metodos de persistencia de bem registro.
 * 
 * @author Juan.Damasceno
 *
 */
public interface BemRegistroDAO extends EntidadeReplicavelDaoIF<BemRegistro> {

	/**
	 * Obter max sequencial.
	 *
	 * @param bem o valor de bem
	 * @return Short
	 */
	Short obterMaxSequencial(Bem bem);
}