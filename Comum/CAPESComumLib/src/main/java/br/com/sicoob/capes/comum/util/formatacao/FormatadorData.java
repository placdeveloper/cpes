/*
 * SICOOB
 * 
 * FormatadorData.java(br.com.sicoob.capes.comum.util.formatacao.FormatadorData)
 */
package br.com.sicoob.capes.comum.util.formatacao;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.bancoob.excecao.BancoobException;

/**
 * The Class FormatadorData.
 */
public class FormatadorData extends FormatadorBase<Date> {

	/** 
	 * {@inheritDoc}
	 */
	protected String doFormat(Date valor, String mascara) throws BancoobException {
		SimpleDateFormat sdf = new SimpleDateFormat(mascara);
		sdf.setLenient(false);
		return sdf.format((Date) valor);
	}
}
