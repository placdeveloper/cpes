// 21/03/2013 - 12:28:32
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.CnaeFiscalServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.CnaeFiscal;

/**
 * @author Rodrigo.Chaves
 */
public class CnaeFiscalDelegate extends
		CAPESCadastroCrudDelegate<CnaeFiscal, CnaeFiscalServico> {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected CnaeFiscalServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarCnaeFiscalServico();
	}

}
