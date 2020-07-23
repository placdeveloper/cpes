package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.EnderecoFiscalServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.EnderecoFiscal;

/**
 * A Classe EnderecoFiscalDelegate.
 */
public class EnderecoFiscalDelegate extends
CAPESCadastroCrudDelegate<EnderecoFiscal, EnderecoFiscalServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EnderecoFiscalServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarEnderecoFiscalServico();
	}

}
