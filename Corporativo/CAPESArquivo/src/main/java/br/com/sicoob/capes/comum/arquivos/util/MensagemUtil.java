/*
 * SICOOB
 * 
 * MensagemUtil.java(br.com.sicoob.capes.comum.arquivos.util.MensagemUtil)
 */
package br.com.sicoob.capes.comum.arquivos.util;

import java.util.ResourceBundle;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.sicoob.capes.comum.arquivos.infraestrutura.mensagens.CAPESArquivosResourceBundle;

/**
 * The Class MensagemUtil.
 */
public class MensagemUtil {
	
	/**
	 * Cria uma nova instância de mensagem util.
	 */
	private MensagemUtil() {
	}
	
	/**
	 * Retorna a String representada pela chave informada.
	 * 
	 * @param chave
	 *            a chave.
	 * @param parametros
	 *            os parametros usados.
	 * @return a string.
	 */
	public static String getString(String chave, Object... parametros) {

		return getString(chave, recuperarResourceBundle(), parametros);
	}

	/**
	 * Retorna a String representada pela chave informada.
	 * 
	 * @param chave
	 *            a chave.
	 * @param parametros
	 *            os parametros usados.
	 * @return a string.
	 */
	public static String getString(String chave, ResourceBundle bundle, Object... parametros) {

		String mensagem = bundle.getString(chave);
		return substituirParametros(mensagem, parametros);
	}

	/**
	 * Substitui os parametros de uma mensagem pelos valores correspondentes.
	 * 
	 * @param mensagem
	 *            A mensagem cujos parametros devem ser substituidos.
	 * @param parametros
	 *            Os valores dos parametros.
	 * @return A mensagem com os parametros devidamente substituidos.
	 */
	public static String substituirParametros(String mensagem, Object... parametros) {

		int contador = 1;
		String tmpMensagem = mensagem;
		if (parametros != null) {
			for (Object parametro : parametros) {
				String expressao = "\\{" + contador++ + "\\}";
				tmpMensagem = mensagem.replaceAll(expressao, (parametro == null) ? null : parametro.toString()
						.replaceAll("\\\\", "\\\\\\\\").replaceAll("\\$", "\\\\\\$"));
			}
		}
		return tmpMensagem;
	}

	/**
	 * Recupera o ResourceBundle a ser usado.
	 * 
	 * @return o ResourceBundle a ser usado.
	 */
	private static BancoobResourceBundle recuperarResourceBundle() {

		return CAPESArquivosResourceBundle.getInstance();
	}

}
