/*
 * SICOOB
 * 
 * FormatadorNumerico.java(br.com.sicoob.capes.comum.util.formatacao.FormatadorNumerico)
 */
package br.com.sicoob.capes.comum.util.formatacao;

import java.text.NumberFormat;
import java.util.Locale;

import br.com.bancoob.excecao.BancoobException;

/**
 * Formatador utilizado para formatar tipo numéricos.
 * 
 * Criado em 28/10/2010.
 *
 * @author rodrigo.chaves
 */
public class FormatadorNumerico extends FormatadorBase<Number> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String doFormat(Number valor, String mascara) throws BancoobException {
		return obterFormatador(mascara).format(valor);
	}

	/**
	 * Obtém o formatador que será utilizado para realizar a formatação.
	 *
	 * @param mascara
	 *            o valor de mascara
	 * @return o formatador
	 */
	protected NumberFormat obterFormatador(String mascara) {
		return NumberFormat.getInstance(new Locale("pt", "BR"));
	}

}