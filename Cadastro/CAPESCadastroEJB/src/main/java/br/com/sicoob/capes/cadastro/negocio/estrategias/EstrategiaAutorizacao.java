// 24/02/2013 - 12:05:24
package br.com.sicoob.capes.cadastro.negocio.estrategias;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;

/**
 * Define as responsabilidades das estratégias de autorização
 * 
 * @author Rodrigo.Chaves
 */
public interface EstrategiaAutorizacao {

	/**
	 * Executa as operações relativas à sua responsabilidade
	 * 
	 * @param autorizacao
	 *            a autorização que deve ser manipulada
	 */
	void executar(Autorizacao autorizacao) throws BancoobException;

}