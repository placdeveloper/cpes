/* 
 * Sicoob
 * SubTipoCertidaoDelegate.java 
 * Criado em: 12/07/2011
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.SubTipoCertidaoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.SubTipoCertidao;

/**
 * 12/07/2011
 * 
 * @author Rodrigo.Chaves
 */
public class SubTipoCertidaoDelegate
		extends
		CAPESCadastroCrudDelegate<SubTipoCertidao, SubTipoCertidaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected SubTipoCertidaoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarSubTipoCertidaoServico();
	}

}
