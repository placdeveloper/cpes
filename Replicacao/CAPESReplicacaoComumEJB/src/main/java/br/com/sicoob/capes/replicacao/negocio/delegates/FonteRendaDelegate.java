/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.sicoob.capes.negocio.entidades.legado.FonteRenda;
import br.com.sicoob.capes.replicacao.negocio.servicos.FonteRendaServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.locator.CAPESReplicacaoComumServiceLocator;

/**
 * Delegate para as fontes de rendas das pessoas.
 * 
 * @author Erico.Junior
 */
public class FonteRendaDelegate extends EntidadeReplicavelDelegate<FonteRenda, FonteRendaServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected FonteRendaServico localizarServico() {
		return CAPESReplicacaoComumServiceLocator.getInstance().localizarFonteRendaServico();
	}
}
