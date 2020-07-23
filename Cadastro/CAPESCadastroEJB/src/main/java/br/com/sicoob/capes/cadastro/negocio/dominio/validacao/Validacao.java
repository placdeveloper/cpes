/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.validacao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;

/**
 * @author erico.junior
 *
 */
public interface Validacao<E extends CAPESEntidade<?>> {
	
	/**
	 * O método Validar.
	 *
	 * @param entidade o valor de entidade
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void validar(E entidade) throws BancoobException;
}
