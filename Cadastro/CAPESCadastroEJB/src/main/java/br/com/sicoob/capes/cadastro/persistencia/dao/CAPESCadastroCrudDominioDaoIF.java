/* 
 * Sicoob
 * CAPESCadastroCrudDominioDaoIF.java 
 * Criado em: 10/05/2011
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;

/**
 * 10/05/2011
 * 
 * @author Rodrigo.Chaves
 * 
 */
public interface CAPESCadastroCrudDominioDaoIF<T extends CAPESEntidade<?>>
		extends CAPESCadastroCrudDaoIF<T> {

	/**
	 * Pesquisar proximo codigo.
	 *
	 * @return Short
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Short pesquisarProximoCodigo() throws BancoobException;

}
