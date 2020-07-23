/*
 * SICOOB
 * 
 * FormatadorMonetario.java(br.com.sicoob.capes.comum.util.formatacao.FormatadorMonetario)
 */
package br.com.sicoob.capes.comum.util.formatacao;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Currency;

/**
 * The Class FormatadorMonetario.
 */
public class FormatadorMonetario extends FormatadorDecimal {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected NumberFormat obterFormatador(String mascara) {
		return (DecimalFormat) super.obterFormatador(null);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected DecimalFormatSymbols obterSimbolos() {
		
		DecimalFormatSymbols simbolos = super.obterSimbolos();
		simbolos.setCurrency(Currency.getInstance("BRL"));
		return simbolos;
	}

}
