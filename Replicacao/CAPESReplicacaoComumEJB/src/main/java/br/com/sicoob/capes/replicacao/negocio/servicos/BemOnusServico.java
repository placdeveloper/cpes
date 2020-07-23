/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos;

import br.com.sicoob.capes.negocio.entidades.legado.BemOnus;

/**
 * Serviço utilizado para replicação de bem ônus.
 * 
 * @author juan.damasceno
 */
public interface BemOnusServico extends EntidadeReplicavelServico<BemOnus> {
	
	/**
	 * Obter max sequencial por pessoa.
	 *
	 * @param bem o valor de bem
	 * @return Short
	 */
	Short obterMaxSequencialPorPessoa(br.com.sicoob.capes.negocio.entidades.legado.Bem bem);
}