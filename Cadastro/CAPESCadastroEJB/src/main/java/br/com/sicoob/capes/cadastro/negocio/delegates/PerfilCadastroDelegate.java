/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.PerfilCadastroServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.PerfilCadastro;

/**
 * Business delegate para o perfil do cadastro.
 * 
 * @author Isaac.Pessoa
 */
public class PerfilCadastroDelegate extends
		CAPESCadastroCrudDelegate<PerfilCadastro, PerfilCadastroServico> {

	@Override
	protected PerfilCadastroServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarPerfilCadastroServico();
	}

}