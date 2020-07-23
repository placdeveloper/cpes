/*
 * SICOOB
 * 
 * FormatadorCPF.java(br.com.sicoob.capes.comum.util.formatacao.FormatadorCPF)
 */
package br.com.sicoob.capes.comum.util.formatacao;

/**
 * Formatador utilizado para formatar CPF
 *
 * 04/11/2010
 * @author rodrigo.chaves
 */
public class FormatadorCPF extends FormatadorMascara {

	/** A Constante MASCARA. */
	public static final String MASCARA = "###.###.###-##";

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String obterMascara(String valor) {
		return MASCARA;
	}

}
