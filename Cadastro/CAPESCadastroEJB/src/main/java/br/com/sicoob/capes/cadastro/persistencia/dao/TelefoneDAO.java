/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.vigente.Telefone;

/**
 * Dao para os telefones
 * @author Erico.Junior
 */
public interface TelefoneDAO extends CAPESCadastroCrudDaoIF<Telefone> {
	
	/**
	 * Verifica se eh telefone repetido.
	 *
	 * @param novoTel o valor de novo tel
	 * @return {@code true}, se for telefone repetido
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public boolean isTelefoneRepetido(Telefone novoTel) throws BancoobException;
}
