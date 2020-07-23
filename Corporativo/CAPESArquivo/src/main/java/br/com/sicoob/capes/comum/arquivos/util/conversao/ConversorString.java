package br.com.sicoob.capes.comum.arquivos.util.conversao;

import org.apache.commons.lang.StringUtils;

import br.com.sicoob.capes.comum.arquivos.excecao.CampoArquivoInvalidoException;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo;

/**
 * Conversor de <code>String</code>. Esse conversor não precisa realizar
 * conversão, apenas retorna o dado originalmente informado.
 *
 * Criado em 22/7/2014
 * @author rodrigo.chaves
 */
public class ConversorString extends ConversorAbstrato<String> {

	/**
	 * {@inheritDoc}
	 */
	public String converterDeString(String valor, CampoArquivo metadados) throws CampoArquivoInvalidoException {
		verificarObrigatoriedade(valor, metadados);
		return valor != null ? valor.trim() : null;
	}

	/**
	 * {@inheritDoc}
	 */
	public String converterParaString(String valor, CampoArquivo metadados) {
		return valor == null ? StringUtils.EMPTY : br.com.sicoob.capes.comum.arquivos.util.StringUtils.removerQuebraLinha(valor);
	}

}