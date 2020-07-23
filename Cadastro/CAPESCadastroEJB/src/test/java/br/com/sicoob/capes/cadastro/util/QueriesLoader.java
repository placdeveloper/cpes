package br.com.sicoob.capes.cadastro.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Classe responsável por obter as consultas SQL para carga dos testes.
 * @author diego.rezende
 *
 */
public class QueriesLoader {
	
	/** O atributo inStream. */
	private BufferedReader inStream;
	
	/**
	 * Instancia um novo QueriesLoader.
	 *
	 * @throws IOException lança a exceção IOException
	 */
	public QueriesLoader() throws IOException {
		inStream = new BufferedReader(new FileReader(new File("./src/test/resources/properties/cargaTestesDao.properties")));
	}
	
	/**
	 * Obtém a lista de comandos SQL a executar.
	 * @return
	 * @throws IOException 
	 */
	public Collection<String> getComandosSQL() throws IOException {
		String line = "";
		Collection<String> sqls = new ArrayList<String>();
		while ((line = inStream.readLine()) != null) {
			if (!line.equals("") && line.indexOf('#') == -1){
				sqls.add(line);
			}
		}
		return sqls;
	}

}
