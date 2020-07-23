/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.vigente.Email;

/**
 * Dao para os emails.
 * @author Erico.Junior
 */
public interface EmailDAO extends CAPESCadastroCrudDaoIF<Email> {
	
	/**
	 * Verifica se eh email repetido.
	 *
	 * @param novoEmail o valor de novo email
	 * @return {@code true}, se for email repetido
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public boolean isEmailRepetido(Email novoEmail) throws BancoobException;

}
