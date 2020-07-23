/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.TipoAfastamentoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.TipoAfastamento;

/**
 * @author Erico.Junior
 * 
 */
public class TipoAfastamentoDelegate
		extends	CAPESCadastroCrudDelegate<TipoAfastamento, TipoAfastamentoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoAfastamentoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarTipoAfastamentoServico();
	}

}
