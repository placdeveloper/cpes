/* 
 * Sicoob
 * RegistroRelacionamentoDelegate.java 
 * Criado em: 05/10/2011
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.RegistroRelacionamentoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.vigente.RegistroRelacionamento;

/**
 * Delegate para {@link RegistroRelacionamento}
 * 
 * 05/10/2011
 * @author Rodrigo.Chaves
 */
public class RegistroRelacionamentoDelegate
		extends
		EntidadeCadastroDelegate<RegistroRelacionamento, RegistroRelacionamentoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RegistroRelacionamentoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarRegistroRelacionamentoServico();
	}

}
