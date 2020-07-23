/* 
 * Sicoob
 * TipoAbrangenciaCertidaoDelegate.java 
 * Criado em: 12/07/2011
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.TipoAbrangenciaCertidaoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.TipoAbrangenciaCertidao;

/**
 * 12/07/2011
 * 
 * @author Rodrigo.Chaves
 */
public class TipoAbrangenciaCertidaoDelegate
		extends
		CAPESCadastroCrudDelegate<TipoAbrangenciaCertidao, TipoAbrangenciaCertidaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoAbrangenciaCertidaoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarTipoAbrangenciaCertidaoServico();
	}

}
