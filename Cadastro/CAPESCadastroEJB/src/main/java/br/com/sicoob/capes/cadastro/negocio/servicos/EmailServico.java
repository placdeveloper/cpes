/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.vigente.Email;

/**
 * Serviço para manutenção do cadastro de email das pessoas.
 * 
 * @author Erico.Junior
 */
public interface EmailServico extends CAPESCadastroCrudServico<Email> {

	/**
	 * Importa o email para pessoa;
	 * @param email
	 * @return
	 * @throws BancoobException
	 */
	Email importar(Email email) throws BancoobException;
}
