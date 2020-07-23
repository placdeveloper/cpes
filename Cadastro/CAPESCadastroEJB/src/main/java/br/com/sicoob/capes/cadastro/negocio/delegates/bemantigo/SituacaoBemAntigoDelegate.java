/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates.bemantigo;

import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroCrudDelegate;
import br.com.sicoob.capes.cadastro.negocio.servicos.bemantigo.SituacaoBemAntigoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.bemantigo.SituacaoBem;

/**
 * Delegate para as situações dos bens.
 * 
 * @author erico.junior
 */
public class SituacaoBemAntigoDelegate extends
		CAPESCadastroCrudDelegate<SituacaoBem, SituacaoBemAntigoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected SituacaoBemAntigoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarSituacaoBemAntigoServico();
	}

}
