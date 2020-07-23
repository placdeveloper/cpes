/* 
 * Sicoob
 * TipoCertidaoDelegate.java 
 * Criado em: 12/07/2011
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.TipoCertidaoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.TipoCertidao;

/**
 * 12/07/2011
 * 
 * @author Rodrigo.Chaves
 */
public class TipoCertidaoDelegate extends
		CAPESCadastroCrudDominioDelegate<TipoCertidao, TipoCertidaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoCertidaoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarTipoCertidaoServico();
	}

}
