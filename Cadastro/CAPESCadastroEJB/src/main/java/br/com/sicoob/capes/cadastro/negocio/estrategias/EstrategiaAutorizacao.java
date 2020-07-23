// 24/02/2013 - 12:05:24
package br.com.sicoob.capes.cadastro.negocio.estrategias;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;

/**
 * Define as responsabilidades das estrat�gias de autoriza��o
 * 
 * @author Rodrigo.Chaves
 */
public interface EstrategiaAutorizacao {

	/**
	 * Executa as opera��es relativas � sua responsabilidade
	 * 
	 * @param autorizacao
	 *            a autoriza��o que deve ser manipulada
	 */
	void executar(Autorizacao autorizacao) throws BancoobException;

}