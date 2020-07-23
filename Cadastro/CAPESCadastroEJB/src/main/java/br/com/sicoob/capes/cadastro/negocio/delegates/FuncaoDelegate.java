package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.FuncaoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.Funcao;

/**
 * @author lucas.borges
 */
public class FuncaoDelegate extends
		CAPESCadastroCrudDelegate<Funcao, FuncaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected FuncaoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarFuncaoServico();
	}
	
}
