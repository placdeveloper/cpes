/* 
 * Sicoob
 * TipoPessoaDelegate.java 
 * Criado em: 07/07/2011
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.TipoPessoaServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;

/**
 * 07/07/2011
 * 
 * @author Rodrigo.Chaves
 */
public class TipoPessoaDelegate extends
		CAPESCadastroCrudDelegate<TipoPessoa, TipoPessoaServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoPessoaServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarTipoPessoaServico();
	}

}
