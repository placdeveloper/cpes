/*
 * SICOOB
 * 
 * ExcecaoUtils.java(br.com.sicoob.capes.comum.util.ExcecaoUtils)
 */
package br.com.sicoob.capes.comum.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;

/**
 * The Class ExcecaoUtils.
 */
public final class ExcecaoUtils {

	// construtor privado para evitar instâncias de classe utilitária
	/**
	 * Cria uma nova instância de excecao utils.
	 */
	private ExcecaoUtils() {
	}
	
	/**
	 * Extrair stack trace.
	 * 
	 * @param excecao
	 *            the excecao
	 * @return string
	 */
	public static String extrairStackTrace(Throwable excecao) {

		String stackTrace = null;
		if (excecao != null) {
			StringWriter writer = new StringWriter();
			PrintWriter print = null;
			try {
				print = new PrintWriter(writer);
				excecao.printStackTrace(print);
			} finally {
				IOUtils.closeQuietly(writer);
				IOUtils.closeQuietly(print);
			}
			stackTrace = writer.toString().replaceAll("\r\n", "\n");
		}
		return stackTrace;
	}
	
}
