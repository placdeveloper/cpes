/**
 * 
 */
package br.com.sicoob.capes.replicacao.persistencia.dao;

import br.com.sicoob.capes.negocio.entidades.legado.Certidao;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;


/**
 * Dao utilizada para as certidoes.
 * 
 * @author juan.damasceno
 */
public interface CertidaoDAO extends EntidadeReplicavelDaoIF<Certidao> {

	/**
	 * Obter max sequencial por pessoa.
	 *
	 * @param pessoa o valor de pessoa
	 * @return Short
	 */
	Short obterMaxSequencialPorPessoa(Pessoa pessoa);

}