/*
 * SICOOB
 * 
 * FormatadorTelefone.java(br.com.sicoob.capes.comum.util.formatacao.FormatadorTelefone)
 */
package br.com.sicoob.capes.comum.util.formatacao;

import org.apache.commons.lang.StringUtils;

/**
 * Formatador utilizado para formatar números de telefone.
 * 
 * 29/10/2010
 * 
 * @author Rodrigo.Chaves
 * 
 */
public class FormatadorTelefone extends FormatadorMascara {

	/** A Constante MASCARA. */
	public static final String MASCARA = "####-####";
	
	/** A Constante MASCARA_NONO_DIGITO. */
	public static final String MASCARA_NONO_DIGITO = "#" + MASCARA;
	
	/** A Constante MASCARA_0800. */
	public static final String MASCARA_0800 = "#### ### ####";

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String obterMascara(String valor) {
		String mascara = StringUtils.EMPTY;
		if (valor.length() == 8) {
			mascara = MASCARA;
		} else if (valor.length() == 9) {
			mascara = MASCARA_NONO_DIGITO;
		} else if (valor.length() == 11) {
			mascara = MASCARA_0800;
		} else {
			  StringBuffer buffer = new StringBuffer();
			  for (int i = 0; i < valor.length(); i++) {
				  buffer.append("#");
			  }
			  mascara = buffer.toString();
		}
		return mascara;
	}
}