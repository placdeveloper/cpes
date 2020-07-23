/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.CategoriaAnotacaoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.CategoriaAnotacao;

/**
 * Business delegate para as categorias das anotações.
 * 
 * @author Erico.Junior
 */
public class CategoriaAnotacaoDelegate extends
		CAPESCadastroCrudDelegate<CategoriaAnotacao, CategoriaAnotacaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CategoriaAnotacaoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarCategoriaAnotacaoServico();
	}

}
