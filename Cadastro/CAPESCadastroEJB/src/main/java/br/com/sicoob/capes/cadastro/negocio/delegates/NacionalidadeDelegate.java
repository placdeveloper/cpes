/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.NacionalidadeServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.Nacionalidade;

/**
 * @author Erico.Junior
 * 
 */
public class NacionalidadeDelegate
		extends	CAPESCadastroCrudDominioDelegate<Nacionalidade, NacionalidadeServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected NacionalidadeServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarNacionalidadeServico();
	}

}