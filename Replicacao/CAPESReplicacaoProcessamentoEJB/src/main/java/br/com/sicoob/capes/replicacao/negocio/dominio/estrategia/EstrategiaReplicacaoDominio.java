/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.dominio.estrategia;

import br.com.bancoob.excecao.BancoobException;

/**
 * Interface para as estratégias de replicação de domínio no SQL (legado).
 * @author Erico.Junior
 */
public interface EstrategiaReplicacaoDominio {

	/**
	 * O método Replicar.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void replicar() throws BancoobException; 
}
