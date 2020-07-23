package br.com.sicoob.capes.comum.arquivos.util.conversao;

import br.com.sicoob.capes.comum.arquivos.excecao.CampoArquivoInvalidoException;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo;

/**
 * Interface comum para os conversores de dados (tipos específicos para
 * <code>String</code> e vice-versa).
 * 
 * Criado em 21/7/2014
 * 
 * @author rodrigo.chaves
 * @param <T>
 *            Classe do objeto que será convertido
 */
public interface Conversor<T> {

	/**
	 * Converte uma {@code String} para o tipo específico informado (genérico)
	 * 
	 * @param valor
	 *            Valor da <code>String</code>
	 * @param metadados
	 *            Configurações da conversão
	 * @return <T> - o valor convertido
	 */
	public T converterDeString(String valor, CampoArquivo metadados)
			throws CampoArquivoInvalidoException;

	/**
	 * Converte objeto do tipo específico informado (genérico) para uma {@code
	 * String}
	 * 
	 * @param valor
	 *            O objeto a ser convertido
	 * @param metadados
	 *            Configurações da conversão
	 * @return String - o valor convertido
	 */
	public String converterParaString(T valor, CampoArquivo metadados);

	/**
	 * Atribui o tipo de dado específico que o conversor usará (pode ser uma
	 * especialização/subclasse do tipo genérico do conversor).
	 * 
	 * @param tipoDado
	 *            O tipo do dado
	 */
	public void setTipoDado(Class<?> tipoDado);

	/**
	 * Obtém o tipo do dado que o conversor usará em suas conversões.
	 * 
	 * @return Class - tipo específico do dado convertido.
	 */
	public Class<?> getTipoDado();
}
