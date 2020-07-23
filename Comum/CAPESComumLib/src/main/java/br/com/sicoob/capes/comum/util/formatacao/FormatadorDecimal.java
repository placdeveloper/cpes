/*
 * SICOOB
 * 
 * FormatadorDecimal.java(br.com.sicoob.capes.comum.util.formatacao.FormatadorDecimal)
 */
package br.com.sicoob.capes.comum.util.formatacao;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

import org.apache.commons.lang.StringUtils;

/**
 * Formatador utilizado para formatar tipo numéricos com casas decimais.
 * 
 * Criado em 28/10/2010.
 * 
 * @author rodrigo.chaves
 */
public class FormatadorDecimal extends FormatadorNumerico {

	/** A Constante TAMANHO_GRUPO. */
	protected static final int TAMANHO_GRUPO = 3;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected NumberFormat obterFormatador(String mascara) {

		// Cria os símbolos usados como separadores.
		// Cria o formatador e transforma o número em String.
		DecimalFormat formatador =
				new DecimalFormat(StringUtils.defaultString(mascara), obterSimbolos());
		if (StringUtils.isBlank(mascara)) {
			formatador.setParseBigDecimal(true);
			formatador.setGroupingUsed(true);
			formatador.setGroupingSize(TAMANHO_GRUPO);
			formatador.setDecimalSeparatorAlwaysShown(true);
			formatador.setMinimumIntegerDigits(1);
			formatador.setMinimumFractionDigits(2);
			formatador.setMaximumFractionDigits(2);
		}
		return formatador;
	}

	/**
	 * Obter simbolos.
	 * 
	 * @return decimal format symbols
	 */
	protected DecimalFormatSymbols obterSimbolos() {
		
		DecimalFormatSymbols caracteresFormatacao = new DecimalFormatSymbols();
		caracteresFormatacao.setMonetaryDecimalSeparator(',');
		caracteresFormatacao.setDecimalSeparator(',');
		caracteresFormatacao.setGroupingSeparator('.');
		return caracteresFormatacao;
	}

}