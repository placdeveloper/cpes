/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos;

import br.com.sicoob.capes.negocio.entidades.legado.Certidao;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;

/**
 * Servi�o utilizado para replica��o de certidoes.
 * 
 * @author juan.damasceno
 */
public interface CertidaoServico extends EntidadeReplicavelServico<Certidao> {
	
	/**
	 * Obter max sequencial por pessoa.
	 *
	 * @param pessoa o valor de pessoa
	 * @return Short
	 */
	Short obterMaxSequencialPorPessoa(Pessoa pessoa);
}
