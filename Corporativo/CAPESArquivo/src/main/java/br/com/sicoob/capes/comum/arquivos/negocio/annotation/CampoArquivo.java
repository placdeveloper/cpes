package br.com.sicoob.capes.comum.arquivos.negocio.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.sicoob.capes.comum.arquivos.util.StringUtils;

/**
 * Essa <code>Annotation</code> representa um campo na linha de texto de um
 * arquivo de transferência de dados. É usada para mapear o atributo de uma
 * classe para o valor correspondente na linha de texto.
 * 
 * Criado em 21/7/2014
 * 
 * @author rodrigo.chaves
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CampoArquivo {

	/**
	 * <code>Enum</code> usado para indicar o alinhamento do dado de um
	 * determinado campo de um arquivo. Valores possíveis: <code>ESQUERDA</code>
	 * (complemento fica a direita) ou <code>DIREITA</code> (complemento fica a
	 * esquerda).
	 */
	public enum AlinhamentoCampo {
		ESQUERDA, DIREITA
	}

	/**
	 * Posição inicial (começa em 0) do dados na linha.
	 */
	public int inicio() default -1;

	/**
	 * Tamanho do dado na linha.
	 */
	public int tamanho() default -1;

	/**
	 * Indicador se o dado é obrigatório ou não. O padrão é <code>true</code>.
	 */
	public boolean obrigatorio() default false;

	/**
	 * Formato da data (usado quando o campo for do tipo {@link java.util.Date}
	 * ). p.s.: ignorado em outros tipos de dado.
	 * 
	 * @see java.text.SimpleDateFormat
	 */
	public String formatoData() default StringUtils.EMPTY;

	/**
	 * Caracter que será usado para complementar o tamanho do campo caso o dado
	 * ocupe menos do que o tamanho especificado.
	 */
	public char complemento() default ' ';

	/**
	 * Determina o alinhamento do dado: esquerda (padrão) ou direita.
	 * 
	 * @see AlinhamentoCampo
	 */
	public AlinhamentoCampo alinhamento() default AlinhamentoCampo.ESQUERDA;

	/**
	 * Indica a precisão decimal (quantidade de casas) de um tipo numérico. Essa
	 * informação é muito importantes em números decimais.
	 */
	public int precisaoDecimal() default 0;

	/**
	 * Caracter usado para separar a parte inteira da decimal de um número.
	 * <strong>Atenção!</strong> Esse atributo deve ser informado somente se o
	 * valor contido no arquivo vier com o separador decimal.
	 */
	public String caracterSeparadorDecimal() default StringUtils.EMPTY;

	/**
	 * Caracter usado para separar os grupos (milhares) de um número.
	 * <strong>Atenção!</strong> Esse atributo deve ser informado somente se o
	 * valor contido no arquivo vier com caracteres agrupadores (milhares,
	 * milhões, etc).
	 */
	public String caracterAgrupador() default StringUtils.EMPTY;

	/**
	 * Indica a ordem que o campo aparece no caso de um arquivo CSV. Atenção,
	 * essa propriedade só é considerada em casos de arquivos CSV, em outros
	 * casos (arquivo que os campos são delimitados por tamanho) essa
	 * propriedade é desconsiderada.
	 */
	public int ordemCampo() default -1;

	/**
	 * Caracter usado para atribuir o tamanho do grupo (milhares) de um número.
	 */
	public int tamanhoAgrupador() default 3;

	/**
	 * String usada para representar os valores {@code true} e {@link Boolean#TRUE}
	 */
	public String descBooleanTrue() default "1";

	/**
	 * String usada para representar os valores {@code false} e {@link Boolean#FALSE}
	 */
	public String descBooleanFalse() default "0";
	
	/**
	 * Quando o campo for nulo, preenche com espacos ao inves de utilizar o complemento.
	 * Ex: tipo de dados de valor, que o complemento é '0'. 
	 * <strong>Utilizado somente na exportacao</strong>
	 */
	public boolean vazioSeNulo() default false;

}