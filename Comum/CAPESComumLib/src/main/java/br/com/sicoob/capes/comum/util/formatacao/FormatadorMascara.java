/*
 * SICOOB
 * 
 * FormatadorMascara.java(br.com.sicoob.capes.comum.util.formatacao.FormatadorMascara)
 */
package br.com.sicoob.capes.comum.util.formatacao;

import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;

/**
 * Formatador utilizado para formatar valores a partir de uma m�scara
 * 
 * 29/10/2010
 * 
 * @see MaskFormatter
 * @author Rodrigo.Chaves
 */
public class FormatadorMascara extends FormatadorBase<String> {

	/**
	 * {@inheritDoc}
	 */
	protected String doFormat(String valor, String mascara) throws BancoobException {

		String formatado = null;
		try {
			MaskFormatter formatter =
					new MaskFormatter(StringUtils.defaultString(mascara, obterMascara(valor)));
			JFormattedTextField field = new JFormattedTextField(formatter);
			field.setText(valor.toString());
			formatado = field.getText();
		} catch (ParseException e) {
			formatado = valor.toString();
		}
		return formatado;
	}

	/**
	 * Recupera a m�scara que ser� usada para realizar a formata��o
	 * @param valor TODO
	 * 
	 * @return a m�scara
	 */
	protected String obterMascara(String valor) {
		return null;
	}

}