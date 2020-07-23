package br.com.sicoob.capes.comum.arquivos.util.conversao;

import org.apache.commons.lang.StringUtils;

import br.com.sicoob.capes.comum.arquivos.excecao.CampoArquivoInvalidoException;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo;

/**
 * Implementa��o padr�o do conversor. 
 * Aten��o essa classe n�o pode ser usada diretamente.
 *
 * Criado em 21/7/2014.
 * @author rodrigo.chaves
 */
public abstract class ConversorAbstrato<T> implements Conversor<T> {

	/**
	 * O tipo do dado que ser� convertido (pode ser uma especializa��o
	 * do tipo gen�rico, por exemplo).
	 */
	protected Class<?> tipoDado;

	/**
	 * Obt�m o valor do atributo <code>tipoDado</code>.
	 * @return Class<?> - O atributo tipoDado
	 */
	public Class<?> getTipoDado() {
		return tipoDado;
	}

	/**
	 * Atribui o valor do atributo <code>tipoDado</code>.
	 * @param tipoDado - O valor a ser atribu�do.
	 */
	public void setTipoDado(Class<?> tipoDado) {
		this.tipoDado = tipoDado;
	}
	
	/**
	 * O m�todo Verificar obrigatoriedade.
	 *
	 * @param valor o valor de valor
	 * @param metadados o valor de metadados
	 * @throws CampoArquivoInvalidoException lan�a a exce��o CampoArquivoInvalidoException
	 */
	protected void verificarObrigatoriedade(String valor, CampoArquivo metadados) throws CampoArquivoInvalidoException {
		if ((metadados.obrigatorio() && StringUtils.isBlank(valor))) {
			throw new CampoArquivoInvalidoException("exception.conversao.obrigatoria");
		}
	}
	
}