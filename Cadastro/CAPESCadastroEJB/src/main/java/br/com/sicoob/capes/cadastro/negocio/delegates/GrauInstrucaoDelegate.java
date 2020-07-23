/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.GrauInstrucaoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.GrauInstrucao;

/**
 * @author Erico.Junior
 * 
 */
public class GrauInstrucaoDelegate extends
		CAPESCadastroCrudDelegate<GrauInstrucao, GrauInstrucaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected GrauInstrucaoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarGrauInstrucaoServico();
	}

}
