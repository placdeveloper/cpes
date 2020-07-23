package br.com.sicoob.capes.comum.arquivos.util.conversao;

import br.com.sicoob.capes.comum.arquivos.excecao.CampoArquivoInvalidoException;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo;
import br.com.sicoob.capes.comum.arquivos.util.StringUtils;

/**
 * Conversor utilizado para converter <code>Character</code> para
 * <code>String</code> e vice-versa.
 *
 * Criado em 22/7/2014
 * @author rodrigo.chaves
 */
public class ConversorCaracter extends ConversorAbstrato<Character> {
	
	/**
	 * {@inheritDoc}
	 */
	public Character converterDeString(String valor, CampoArquivo metadados) throws CampoArquivoInvalidoException {
		verificarObrigatoriedade(valor, metadados);
		
		Character c = null;
		if (valor != null) {
			c = ' ';
			if (valor.length() > 0) {
				c = valor.charAt(0);
			}
		}
		return c;
	}

	/**
	 * {@inheritDoc}
	 */
	public String converterParaString(Character valor, CampoArquivo metadados) {
		return valor != null ? valor.toString() : StringUtils.EMPTY;
	}

}