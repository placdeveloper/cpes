/*
 * SICOOB
 * 
 * FormatadorCNPJ.java(br.com.sicoob.capes.comum.util.formatacao.FormatadorCNPJ)
 */
package br.com.sicoob.capes.comum.util.formatacao;

/**
 * Formatador utilizado para formatar CNPJ
 *
 * 04/11/2010
 * @author rodrigo.chaves
 * 
 */
public class FormatadorCNPJ extends FormatadorMascara {

	/** A Constante MASCARA. */
	public static final String MASCARA = "##.###.###/####-##";

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String obterMascara(String valor) {
		return MASCARA;
	}

}
