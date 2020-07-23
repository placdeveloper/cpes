/*
 * SICOOB
 * 
 * FormatadorLocalizacaoImovel.java(br.com.sicoob.capes.comum.util.formatacao.FormatadorLocalizacaoImovel)
 */
package br.com.sicoob.capes.comum.util.formatacao;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;

/**
 * @author Marcelo.Onofre
 */
public class FormatadorLocalizacaoImovel extends FormatadorBase<String> {


	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected String doFormat(String valor, String mascara) throws BancoobException {
		String retorno = null;
		if(StringUtils.isNotEmpty(valor)){
			if(valor.equals("U")){
				retorno = "URBANO";
			}else if(valor.equals("R")){
				retorno = "RURAL";
			}
		}
		
		return retorno;
	}

}
