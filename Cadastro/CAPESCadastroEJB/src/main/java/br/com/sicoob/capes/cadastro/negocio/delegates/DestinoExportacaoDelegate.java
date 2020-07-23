package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.DestinoExportacaoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.DestinoExportacao;

/**
 * A Classe DestinoExportacaoDelegate.
 */
public class DestinoExportacaoDelegate extends CAPESCadastroCrudDelegate<DestinoExportacao, DestinoExportacaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected DestinoExportacaoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarDestinoExportacaoServico();
	}

}