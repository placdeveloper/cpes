package br.com.sicoob.capes.comum.arquivos.negocio.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.sicoob.capes.comum.arquivos.negocio.enums.CodificacaoArquivoEnum;
import br.com.sicoob.capes.comum.arquivos.negocio.vo.DadosArquivoVO;

/**
 * <code>Annotation</code> que representa um objeto que agrupa os dados de um arquivo de texto usado
 * para transferência de dados. Contém as informações básicas.
 * 
 * Criado em 21/7/2014.
 * 
 * @author rodrigo.chaves
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Arquivo {

	/**
	 * <code>Enum</code> usado para indicar o tipo do arquivo.<br>
	 * Valores possíveis:
	 * <ul>
	 * <li> {@link #CAMPOS_DELIMITADOS_POR_SEPARADOR}</li>
	 * <li> {@link #CAMPOS_DELIMITADOS_POR_TAMANHO}</li>
	 * </ul>
	 * 
	 * Criado em 22/7/2014
	 * 
	 * @author rodrigo.chaves
	 */
	public enum TipoArquivo {
		CAMPOS_DELIMITADOS_POR_TAMANHO, CAMPOS_DELIMITADOS_POR_SEPARADOR
	}

	/**
	 * A quantidade de caracteres em cada linha do arquivo caso seja fixa. Se informado (maior que
	 * zero) a cada linha lida ocorrerá validação verificando se a quantidade está correta. Caso a
	 * quantidade de caracteres por linha seja indefinida ou variável, utilizar a constante (já é
	 * padrão): {@link DadosArquivoVO #TAMANHO_LINHA_INDEFINIDO}
	 */
	public int caracteresPorLinha() default DadosArquivoVO.TAMANHO_LINHA_INDEFINIDO;

	/**
	 * Indicador se o arquivo possui um número mínimo de caracteres por linha.
	 */
	public boolean caracteresMinimosPorLinha() default false;

	/**
	 * Indicador se o leitor de arquivo deve ignorar o erro ao encontrar um campo com formato
	 * inválido (geralmente numérico ou data) ou um erro deve ser lançado (
	 * {@link br.com.sicoob.capes.comum.arquivos.excecao.CampoArquivoInvalidoException})
	 */
	public boolean ignoraRegistroInvalido() default true;

	/**
	 * Indicador usado na importação que serve para configurar se o serviço de importação deve
	 * manter as referências dos objetos obtidos a partir das linhas no objeto
	 * {@link DadosArquivoVO}. <br />
	 * <strong>Atenção!</strong> Note que, se configurada para <code>true</code>, dependendo da
	 * quantidade de linhas do arquivo, <strong>pode gerar problemas de memória</strong>.
	 */
	public boolean mantemReferencias() default false;

	/**
	 * Indicador usado na importação que serve para configurar se o serviço deve disparar eventos
	 * para cada registro (linha), ou seja, chamar um metodo marcado como "tratador", com retorno
	 * booleano e que possua apenas um argumento de um tipo que implemente
	 * {@code br.com.sicoob.capes.comum.arquivos.RegistroArquivo} encontrada no arquivo.
	 * 
	 * @see br.com.sicoob.capes.comum.arquivos.negocio.annotation.Tratador
	 * @see br.com.sicoob.capes.comum.arquivos.RegistroArquivo
	 */
	public boolean utilizaEventos() default true;

	/**
	 * Indica se os campos (valores) do arquivo são delimitados por tamanho de cada campo (padrão)
	 * ou por um separador específico (CSV).
	 * 
	 * @see #separadorCampos()
	 */
	public TipoArquivo tipoArquivo() default TipoArquivo.CAMPOS_DELIMITADOS_POR_TAMANHO;

	/**
	 * Separador de campos, utilizado somente quando o tipo de arquivo for
	 * {@link TipoArquivo#CAMPOS_DELIMITADOS_POR_SEPARADOR}.
	 */
	public String separadorCampos() default ";";

	/**
	 * A codificacao utilizada na leitura/escrita de arquivos
	 * 
	 * @return a codificacao a ser utilizada
	 * 
	 * @see CodificacaoArquivoEnum
	 */
	public CodificacaoArquivoEnum codificacao() default CodificacaoArquivoEnum.UTF_8;
	
	/**
	 * Indica se componente deve remover a acentuacao ao gerar a linha do
	 * arquivo. <br />
	 * <strong>Atenção!</strong> O default para esse atributo é
	 * <code>false</code></strong>.
	 * 
	 * @return {@code boolean} se deve ou nao remover a acentuacao da linha.
	 */
	boolean removerAcentuacao() default false;
}