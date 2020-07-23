/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.pessoa;

import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaJuridica;

/**
 * @author erico.junior
 *
 */
public class FabricaPessoaJuridica extends FabricaPessoaCompartilhamento {

	/**
	 * {@inheritDoc}
	 */
	public PessoaCompartilhamento criarPessoaCompartilhamento() {
		return new PessoaJuridica();
	}

}
