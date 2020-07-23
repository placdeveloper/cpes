/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos;

import br.com.sicoob.capes.negocio.entidades.legado.Bem;
import br.com.sicoob.capes.negocio.entidades.legado.BemPosse;

/**
 * Serviço utilizado para os bens.
 * 
 * @author juan.damasceno
 */
public interface BemPosseServico extends EntidadeReplicavelServico<BemPosse> {

	/**
	 * Obter max sequencial.
	 *
	 * @param bemSQL o valor de bem sql
	 * @return Short
	 */
	Short obterMaxSequencial(Bem bemSQL);

}