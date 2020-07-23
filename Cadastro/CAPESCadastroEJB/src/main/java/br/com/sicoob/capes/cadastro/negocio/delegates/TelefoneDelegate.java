/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.TelefoneServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.vigente.Telefone;

/**
 * Delegate para os telefones.
 * 
 * @author Erico.Junior
 */
public class TelefoneDelegate extends CAPESCadastroCrudDelegate<Telefone, TelefoneServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TelefoneServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarTelefoneServico();
	}

	/**
	 * Importa o telefone para pessoa;
	 * @param telefone
	 * @return
	 * @throws BancoobException
	 */
	public Telefone importar(Telefone telefone) throws BancoobException {
		return getServico().importar(telefone);
	}

}
