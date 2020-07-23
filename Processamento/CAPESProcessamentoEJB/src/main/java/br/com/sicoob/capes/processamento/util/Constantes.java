/**
 * 
 */
package br.com.sicoob.capes.processamento.util;

/**
 * @author Erico.Junior
 *
 */
public interface Constantes {

	/**
	 * A Interface Persistencia.
	 */
	interface Persistencia {
		
		/** O atributo NOME_COLECAO_COMANDOS. */
		String NOME_COLECAO_COMANDOS = "comandos-capes.processamento";

		/** O atributo ARQUIVO_QUERIES. */
		String ARQUIVO_QUERIES = "capes.processamento.queries.xml";
		
		/** O atributo DATASOURCE. */
		String DATASOURCE = "jdbc/BancoobDS";
		
	}
}
