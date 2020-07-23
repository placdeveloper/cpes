package br.com.sicoob.capes.comum.arquivos.negocio.enums;

/**
 * O Enum CodificacaoArquivoEnum.
 */
public enum CodificacaoArquivoEnum {
	
	/** O atributo ISO_8859_1. */
	ISO_8859_1("ISO-8859-1"),
	
	/** O atributo UTF_8. */
	UTF_8("UTF-8");
	
	/** O atributo codigo. */
	private String codigo;

	/**
     * @param codigo
     */
    private CodificacaoArquivoEnum(String codigo) {
	    this.codigo = codigo;
    }
	
	/**
	 * Recupera o valor de codigo.
	 *
	 * @return o valor de codigo
	 */
	public String getCodigo() {
		return this.codigo;
	}
	
}
