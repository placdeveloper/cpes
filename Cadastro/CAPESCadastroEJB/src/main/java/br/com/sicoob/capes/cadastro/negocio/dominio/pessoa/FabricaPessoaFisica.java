/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.pessoa;

import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;

/**
 * @author erico.junior
 *
 */
public class FabricaPessoaFisica extends FabricaPessoaCompartilhamento {

	/**
	 * {@inheritDoc}
	 */
	public PessoaCompartilhamento criarPessoaCompartilhamento() {
		return new PessoaFisica();
	}

}
