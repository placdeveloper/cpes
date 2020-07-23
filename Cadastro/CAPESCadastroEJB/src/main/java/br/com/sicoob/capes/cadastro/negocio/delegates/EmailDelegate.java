/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.EmailServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.vigente.Email;

/**
 * Delegate para os emails.
 * 
 * @author Erico.Junior
 */
public class EmailDelegate extends CAPESCadastroCrudDelegate<Email, EmailServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EmailServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarEmailServico();
	}

	/**
	 * Importa o email para pessoa;
	 * @param email
	 * @return
	 * @throws BancoobException
	 */
	public Email importar(Email email) throws BancoobException {
		return getServico().importar(email);
	}
}
