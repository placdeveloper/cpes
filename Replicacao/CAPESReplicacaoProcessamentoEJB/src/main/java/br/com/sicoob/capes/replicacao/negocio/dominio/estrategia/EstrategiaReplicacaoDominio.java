/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.dominio.estrategia;

import br.com.bancoob.excecao.BancoobException;

/**
 * Interface para as estrat�gias de replica��o de dom�nio no SQL (legado).
 * @author Erico.Junior
 */
public interface EstrategiaReplicacaoDominio {

	/**
	 * O m�todo Replicar.
	 *
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void replicar() throws BancoobException; 
}
