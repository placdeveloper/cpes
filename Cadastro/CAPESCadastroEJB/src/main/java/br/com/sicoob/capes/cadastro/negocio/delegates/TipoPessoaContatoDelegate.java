/* 
 * Sicoob
 * TipoEnderecoDelegate.java 
 * Criado em: 05/05/2011
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.TipoPessoaContatoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.TipoPessoaContato;

/**
 * @author diego.rezende
 *
 */
public class TipoPessoaContatoDelegate extends
		CAPESCadastroCrudDominioDelegate<TipoPessoaContato, TipoPessoaContatoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoPessoaContatoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarTipoPessoaContatoServico();
	}

}
