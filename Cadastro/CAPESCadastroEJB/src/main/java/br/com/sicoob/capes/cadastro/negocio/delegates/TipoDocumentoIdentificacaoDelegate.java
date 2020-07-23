/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.TipoDocumentoIdentificacaoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.TipoDocumentoIdentificacao;

/**
 * @author Erico.Junior
 * 
 */
public class TipoDocumentoIdentificacaoDelegate extends	CAPESCadastroCrudDelegate
		<TipoDocumentoIdentificacao, TipoDocumentoIdentificacaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoDocumentoIdentificacaoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarTipoDocumentoIdentificacaoServico();
	}

}
