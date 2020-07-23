/* 
 * Sicoob
 * TipoObjetoCertidaoDelegate.java 
 * Criado em: 12/07/2011
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.TipoObjetoCertidaoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.TipoObjetoCertidao;

/**
 * 12/07/2011
 * 
 * @author Rodrigo.Chaves
 */
public class TipoObjetoCertidaoDelegate
		extends
		CAPESCadastroCrudDelegate<TipoObjetoCertidao, TipoObjetoCertidaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoObjetoCertidaoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarTipoObjetoCertidaoServico();
	}

}
