/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.TipoReferenciaServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.TipoReferencia;

/**
 * Business delegate para tipo referencia.  
 * @author juan.damasceno
 */
public class TipoReferenciaDelegate extends
		CAPESCadastroCrudDelegate<TipoReferencia, TipoReferenciaServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoReferenciaServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarTipoReferenciaServico();
	}
}
