/*
 * SICOOB
 * 
 * FormatadorBase.java(br.com.sicoob.capes.comum.util.formatacao.FormatadorBase)
 */
package br.com.sicoob.capes.comum.util.formatacao;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;

/**
 * The Class FormatadorBase.
 * 
 * @param <T>
 *            the generic type
 */
public abstract class FormatadorBase<T> implements Formatador<T> {

	/** 
	 * {@inheritDoc}
	 */
	public String formatar(T valor, String mascara) throws BancoobException {
		String formatado = StringUtils.EMPTY;
		if (valor != null) {
			formatado = doFormat(valor, mascara);
		}
		return formatado;
	}

	/**
	 * Do format.
	 * 
	 * @param valor
	 *            the valor
	 * @param mascara
	 *            the mascara
	 * @return string
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	protected abstract String doFormat(T valor, String mascara) throws BancoobException;

}
