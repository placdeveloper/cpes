/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.TipoFormaConstituicaoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.TipoFormaConstituicao;

/**
 * @author Erico.Junior
 * 
 */
public class TipoFormaConstituicaoDelegate extends
		CAPESCadastroCrudDelegate<TipoFormaConstituicao, TipoFormaConstituicaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoFormaConstituicaoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarTipoFormaConstituicaoServico();
	}

}
