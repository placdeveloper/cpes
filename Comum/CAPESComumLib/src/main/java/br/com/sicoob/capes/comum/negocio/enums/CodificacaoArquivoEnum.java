package br.com.sicoob.capes.comum.negocio.enums;

/**
 * O Enum CodificacaoArquivoEnum.
 *
 * @author Bruno.Carneiro
 */
public enum CodificacaoArquivoEnum {

	/** O atributo ASCII. */
	ASCII("US-ASCII"),

	/** O atributo CP1252. */
	CP1252("Cp1252"),

	/** O atributo ISO_8859_1. */
	ISO_8859_1("ISO-8859-1"),

	/** O atributo UTF_8. */
	UTF_8("UTF-8");

	/** O atributo codigo. */
	private String codigo;

	/**
	 * Instancia um novo CodificacaoArquivoEnum.
	 *
	 * @param codigo
	 *            o valor de codigo
	 */
	private CodificacaoArquivoEnum(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Recupera o valor de codigo.
	 *
	 * @return codigo
	 */
	public String getCodigo() {
		return this.codigo;
	}

	/**
	 * Obtém o enum a partir do código.
	 *
	 * @param codigo
	 *            o valor de codigo
	 * @return CodificacaoArquivoEnum
	 */
	public static CodificacaoArquivoEnum obterPorCodigo(String codigo) {
		for (CodificacaoArquivoEnum codificacao : values()) {
			if (codificacao.codigo.equals(codigo)) {
				return codificacao;
			}
		}
		return null;
	}

}