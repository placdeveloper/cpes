package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.AutorizacaoDocumentoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.AutorizacaoDocumento;

/**
 * A Classe AutorizacaoDocumentoDelegate.
 */
public class AutorizacaoDocumentoDelegate extends CAPESCadastroCrudDelegate<AutorizacaoDocumento, AutorizacaoDocumentoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AutorizacaoDocumentoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarAutorizacaoDocumentoServico();
	}

}
