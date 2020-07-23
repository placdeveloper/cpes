package br.com.sicoob.capes.comum.arquivos.util.conversao;

import br.com.sicoob.capes.comum.arquivos.excecao.CampoArquivoInvalidoException;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo;

/**
 * Interface comum para os conversores de dados (tipos espec�ficos para
 * <code>String</code> e vice-versa).
 * 
 * Criado em 21/7/2014
 * 
 * @author rodrigo.chaves
 * @param <T>
 *            Classe do objeto que ser� convertido
 */
public interface Conversor<T> {

	/**
	 * Converte uma {@code String} para o tipo espec�fico informado (gen�rico)
	 * 
	 * @param valor
	 *            Valor da <code>String</code>
	 * @param metadados
	 *            Configura��es da convers�o
	 * @return <T> - o valor convertido
	 */
	public T converterDeString(String valor, CampoArquivo metadados)
			throws CampoArquivoInvalidoException;

	/**
	 * Converte objeto do tipo espec�fico informado (gen�rico) para uma {@code
	 * String}
	 * 
	 * @param valor
	 *            O objeto a ser convertido
	 * @param metadados
	 *            Configura��es da convers�o
	 * @return String - o valor convertido
	 */
	public String converterParaString(T valor, CampoArquivo metadados);

	/**
	 * Atribui o tipo de dado espec�fico que o conversor usar� (pode ser uma
	 * especializa��o/subclasse do tipo gen�rico do conversor).
	 * 
	 * @param tipoDado
	 *            O tipo do dado
	 */
	public void setTipoDado(Class<?> tipoDado);

	/**
	 * Obt�m o tipo do dado que o conversor usar� em suas convers�es.
	 * 
	 * @return Class - tipo espec�fico do dado convertido.
	 */
	public Class<?> getTipoDado();
}
