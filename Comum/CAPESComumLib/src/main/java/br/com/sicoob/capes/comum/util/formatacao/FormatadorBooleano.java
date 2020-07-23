/*
 * SICOOB
 * 
 * FormatadorBooleano.java(br.com.sicoob.capes.comum.util.formatacao.FormatadorBooleano)
 */
package br.com.sicoob.capes.comum.util.formatacao;

import br.com.bancoob.excecao.BancoobException;

/**
 * The Class FormatadorBooleano.
 */
public class FormatadorBooleano extends FormatadorBase<Boolean> {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected String doFormat(Boolean valor, String mascara) throws BancoobException {
		
		return valor ? "Sim" : "Não";
	}

}
