/*
 * SICOOB
 * 
 * StringUtils.java(br.com.sicoob.capes.comum.arquivos.util.StringUtils)
 */
package br.com.sicoob.capes.comum.arquivos.util;

import java.util.Arrays;

import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo.AlinhamentoCampo;

/**
 * Utilidades para manipulaÁ„o de objetos {@link java.lang.String} na importaÁ„o/exportaÁ„o de arquivos.
 * 
 * Criado em 21/7/2014.
 * 
 * @author rodrigo.chaves
 */
public final class StringUtils extends org.apache.commons.lang.StringUtils {

	/**
	 * Construtor padr„o privado. AtenÁ„o, essa classe contÈm apenas mÈtodos <code>static</code> utilit·rios e por isso
	 * n„o pode ser instanciada diretamente.
	 */
	private StringUtils() {
	}

	/**
	 * MÈtodo utilit·rio usado para completar um texto com algum caracter especificado ‡ esquerda ou ‡ direita
	 * dependendo do alinhamento do texto desejado.
	 * 
	 * @param original
	 *            - Texto a ser alterado.
	 * @param charComplemento
	 *            - O caracter usado para completar o texto.
	 * @param tamanhoTotal
	 *            - O tamanho total do texto resultante.
	 * @param alinhamento
	 *            - O alinhamento do texto original.
	 * @param cortar
	 *            - Se <code>true</code> indica que caso o tamanho do texto exceda o <code>tamanhoTotal</code> o texto
	 *            original È cortado, caso <code>false</code> retorna o texto inalterado.
	 * @return String - texto modificado (complementado) de acordo com os par‚metros informados.
	 */
	public static String completar(String original, char charComplemento, int tamanhoTotal, AlinhamentoCampo alinhamento, boolean cortar) {
		// Tratar null como vazia.
		original = original == null ? EMPTY : original;
		StringBuilder completa = new StringBuilder(original);

		// Criando o complemento.
		int tamanhoOriginal = original.length();
		int tamanhoComplemento = tamanhoTotal - tamanhoOriginal;
		if (tamanhoComplemento > 0) {
			char[] complementos = new char[tamanhoComplemento];
			Arrays.fill(complementos, charComplemento);

			// Decidindo onde inserir o complemento.
			if (alinhamento == AlinhamentoCampo.DIREITA) {
				completa.insert(0, complementos);
			} else {
				completa.append(complementos);
			}
		} else if (cortar && tamanhoComplemento != 0) {

			// Decidindo onde cortar.
			if (alinhamento == AlinhamentoCampo.DIREITA) {
				completa.delete(0, tamanhoTotal);
			} else {
				completa.delete(tamanhoTotal, tamanhoOriginal);
			}
		}
		return completa.toString();
	}

	/**
	 * MÈtodo utilit·rio usado para completar um texto com algum caracter especificado ‡ esquerda ou ‡ direita
	 * dependendo do alinhamento do texto desejado. Caso o <code>tamanhoTotal</code> seja menor do que o tamanho do
	 * texto original, o texto È retornado inalterado, ou seja, o par‚metro <code>cortar</code> È <code>false</code>.
	 * 
	 * @param original
	 *            - Texto a ser alterado.
	 * @param charComplemento
	 *            - O caracter usado para completar o texto.
	 * @param tamanhoTotal
	 *            - O tamanho total do texto resultante.
	 * @param alinhamento
	 *            - O alinhamento do texto original.
	 * @return String - texto modificado (complementado) de acordo com os par‚metros informados.
	 */
	public static String completar(String original, char charComplemento, int tamanhoTotal, AlinhamentoCampo alinhamento) {
		return completar(original, charComplemento, tamanhoTotal, alinhamento, false);
	}

	/**
	 * MÈtodo respons·vel por remover os acentos de uma determinada palavra.
	 * 
	 * @param palavra
	 *            - a palavra desejada.
	 * @return String - palavra desejada sem acento.
	 */
	public static String removerAcentos(String palavra) {
		String origem = "‡·‚„‰ËÈÍÎÏÌÓÔÚÛÙıˆ˘˙˚¸¿¡¬√ƒ»… ÀÃÕŒ“”‘’÷Ÿ⁄€‹Á«Ò—";
		String destino = "aaaaaeeeeiiiiooooouuuuAAAAAEEEEIIIOOOOOUUUUcCnN";
		for (int i = 0; i < origem.length(); i++) {
			palavra = palavra.replace(origem.charAt(i), destino.charAt(i));
		}
		return palavra;
	}
	
	/**
	 * Remover quebra linha.
	 *
	 * @param texto o valor de texto
	 * @return String
	 */
	public static String removerQuebraLinha(String texto) {
		if(StringUtils.isNotEmpty(texto)){
			texto = texto.trim();
//			texto = texto.replaceAll("(\\r|\\n|\\t)", " ");
			texto = texto.replaceAll("\\s+", " ");
		}
		return texto;
	}

}