/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.TipoCapturaServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.TipoCaptura;

/**
 * Business delegate para os tipos de captura.
 * 
 * @author Erico.Junior
 */
public class TipoCapturaDelegate extends
		CAPESCadastroCrudDelegate<TipoCaptura, TipoCapturaServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoCapturaServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarTipoCapturaServico();
	}

}
