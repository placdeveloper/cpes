package br.com.sicoob.capes.replicacao.persistencia.dao;

import br.com.sicoob.capes.negocio.entidades.legado.Bem;
import br.com.sicoob.capes.negocio.entidades.legado.BemOnus;


/**
 * DAO que define os metodos de persistencia de BemOnus.
 * @author juan.damasceno
 *
 */
public interface BemOnusDAO extends EntidadeReplicavelDaoIF<BemOnus>{
	
	/**
	 * Obter max sequencial por pessoa.
	 *
	 * @param bem o valor de bem
	 * @return Short
	 */
	Short obterMaxSequencialPorPessoa(Bem bem);
}