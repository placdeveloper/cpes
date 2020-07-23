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
	 * O m�todo Validar.
	 *
	 * @param entidade o valor de entidade
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void validar(E entidade) throws BancoobException;
}
