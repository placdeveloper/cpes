/* 
 * Sicoob
 * TipoPrazoCertidaoDelegate.java 
 * Criado em: 12/07/2011
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.TipoPrazoCertidaoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.TipoPrazoCertidao;

/**
 * 12/07/2011
 * 
 * @author Rodrigo.Chaves
 */
public class TipoPrazoCertidaoDelegate extends
		CAPESCadastroCrudDelegate<TipoPrazoCertidao, TipoPrazoCertidaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoPrazoCertidaoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarTipoPrazoCertidaoServico();
	}

}
