/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos;

import br.com.sicoob.capes.negocio.entidades.legado.Bem;
import br.com.sicoob.capes.negocio.entidades.legado.BemRegistro;

/**
 * Serviço utilizado para os bens.
 * 
 * @author Juan.Damasceno
 */
public interface BemRegistroServico extends EntidadeReplicavelServico<BemRegistro> {

	/**
	 * Obter max sequencial.
	 *
	 * @param bem o valor de bem
	 * @return Short
	 */
	Short obterMaxSequencial(Bem bem);

}