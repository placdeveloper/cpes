/*
 * SICOOB
 * 
 * FormatadorCpfCnpj.java(br.com.sicoob.capes.comum.util.formatacao.FormatadorCpfCnpj)
 */
package br.com.sicoob.capes.comum.util.formatacao;


/**
 * @author Rodrigo.Chaves
 */
public class FormatadorCpfCnpj extends FormatadorMascara {
	
	 /** A Constante TAMANHO_CPF. */
 	private static final int TAMANHO_CPF = 11;

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected String obterMascara(String valor) {
		
		String mascara = FormatadorCPF.MASCARA;
		if (valor.length() > TAMANHO_CPF) {
			mascara = FormatadorCNPJ.MASCARA;
		}
		return mascara;
	}

}
