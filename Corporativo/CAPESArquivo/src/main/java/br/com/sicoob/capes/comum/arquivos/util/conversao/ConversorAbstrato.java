package br.com.sicoob.capes.comum.arquivos.util.conversao;

import org.apache.commons.lang.StringUtils;

import br.com.sicoob.capes.comum.arquivos.excecao.CampoArquivoInvalidoException;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo;

/**
 * Implementação padrão do conversor. 
 * Atenção essa classe não pode ser usada diretamente.
 *
 * Criado em 21/7/2014.
 * @author rodrigo.chaves
 */
public abstract class ConversorAbstrato<T> implements Conversor<T> {

	/**
	 * O tipo do dado que será convertido (pode ser uma especialização
	 * do tipo genérico, por exemplo).
	 */
	protected Class<?> tipoDado;

	/**
	 * Obtém o valor do atributo <code>tipoDado</code>.
	 * @return Class<?> - O atributo tipoDado
	 */
	public Class<?> getTipoDado() {
		return tipoDado;
	}

	/**
	 * Atribui o valor do atributo <code>tipoDado</code>.
	 * @param tipoDado - O valor a ser atribuído.
	 */
	public void setTipoDado(Class<?> tipoDado) {
		this.tipoDado = tipoDado;
	}
	
	/**
	 * O método Verificar obrigatoriedade.
	 *
	 * @param valor o valor de valor
	 * @param metadados o valor de metadados
	 * @throws CampoArquivoInvalidoException lança a exceção CampoArquivoInvalidoException
	 */
	protected void verificarObrigatoriedade(String valor, CampoArquivo metadados) throws CampoArquivoInvalidoException {
		if ((metadados.obrigatorio() && StringUtils.isBlank(valor))) {
			throw new CampoArquivoInvalidoException("exception.conversao.obrigatoria");
		}
	}
	
}