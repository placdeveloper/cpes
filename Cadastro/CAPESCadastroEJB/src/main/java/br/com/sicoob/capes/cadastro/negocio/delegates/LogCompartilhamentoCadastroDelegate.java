/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.LogCompartilhamentoCadastroServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.LogCompartilhamentoCadastro;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * @author Erico.Junior
 * 
 */
public class LogCompartilhamentoCadastroDelegate
		extends	CAPESCadastroCrudDelegate<LogCompartilhamentoCadastro, LogCompartilhamentoCadastroServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected LogCompartilhamentoCadastroServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarLogCompartilhamentoCadastroServico();
	}
	
	/**
	 * Altera o registro de log de compartilhamento para "Sim".
	 * @param pessoa A pessoa que terá o seu cadastro compartilhado.
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	public void compartilhar(PessoaCompartilhamento pessoa) throws BancoobException{ 
		getServico().compartilhar(pessoa);
	}
}
