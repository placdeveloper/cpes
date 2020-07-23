// 26/12/2013 - 13:56:02
package br.com.sicoob.capes.cadastro.util;


/**
 * @author Rodrigo.Chaves
 */
public interface CAPESCadastroConstantes {

	/**
	 * A Interface Persistencia.
	 */
	interface Persistencia {

		/** O atributo ARQUIVO_QUERIES. */
		String ARQUIVO_QUERIES = "capes.cadastro.queries.xml";
		
		/** O atributo ARQUIVO_QUERIES_FICHA_CADASTRAL. */
		String ARQUIVO_QUERIES_FICHA_CADASTRAL = "capes.fichacadastral.queries.xml";
		
		/** O atributo ARQUIVO_QUERIES_MENSAGEM_REPLICACAO. */
		String ARQUIVO_QUERIES_MENSAGEM_REPLICACAO = "capes.mensagem.replicacao.queries.xml";
		
		/** O atributo COMANDOS_CADASTRO_UNICO_CLIENTE_FICHA_CADASTRAL. */
		String COMANDOS_CADASTRO_UNICO_CLIENTE_FICHA_CADASTRAL = "COMANDOS_CAPES_FICHA_CADASTRAL";
		
		/** O atributo COMANDOS_CADASTRO_UNICO_CLIENTE_MENSAGEM_REPLICACAO. */
		String COMANDOS_CADASTRO_UNICO_CLIENTE_MENSAGEM_REPLICACAO = "COMANDOS_CAPES_MENSAGEM_REPLICACAO";
		
		/** O atributo NOME_COLECAO_COMANDOS. */
		String NOME_COLECAO_COMANDOS = "COMANDOS_CAPES_CADASTRO";
	}
}
