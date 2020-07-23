/*
 * SICOOB
 * 
 * FormatadorPadrao.java(br.com.sicoob.capes.comum.util.formatacao.FormatadorPadrao)
 */
package br.com.sicoob.capes.comum.util.formatacao;

import br.com.bancoob.excecao.BancoobException;


/**
 * Implementação padrão do formatador.
 * 
 * Criado em 28/10/2010.
 * 
 * @author rodrigo.chaves
 */
public class FormatadorPadrao extends FormatadorBase<Object> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String doFormat(Object valor, String mascara) throws BancoobException {
		String retorno = null;
		if (valor != null) {
			retorno = valor.toString();
		}
		return retorno;
	}

}