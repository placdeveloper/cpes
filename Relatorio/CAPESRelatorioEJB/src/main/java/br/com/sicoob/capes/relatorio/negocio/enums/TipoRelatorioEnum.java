package br.com.sicoob.capes.relatorio.negocio.enums;

import org.apache.commons.lang.StringUtils;

/**
 * @author Rodrigo.Chaves
 *
 */
public enum TipoRelatorioEnum {
	
	// NAO ALTERAR A ORDEM
	/** O atributo ANALITICO. */
	ANALITICO("Analítico"),
	
	/** O atributo SINTETICO. */
	SINTETICO("Sintético");
	
	/** O atributo descricao. */
	private String descricao;

	/**
	 * @param descricao
	 */
	private TipoRelatorioEnum(String descricao) {
		this.descricao = descricao;
	}
	
	/**
	 * Recupera o valor de codigo.
	 *
	 * @return o valor de codigo
	 */
	public Integer getCodigo() {
		return ordinal();
	}

	/**
     * @return the descricao
     */
    public String getDescricao() {
    	return descricao;
    }

    /**
     * @return o nome da constante somente com a inicial maiuscula
     */
    public String getNome() {
    	return StringUtils.capitalize(name().toLowerCase());
    }
    
    /**
     * Value of.
     *
     * @param codigo o valor de codigo
     * @return TipoRelatorioEnum
     */
    public static TipoRelatorioEnum valueOf(Integer codigo) {
    	return values()[codigo];
    }
    
	/**
	 * Verifica se o codigo recebido eh do tipo {@link #ANALITICO}
	 * 
	 * @param codigo
	 *            o codigo
	 * @return {@code true} caso seja do tipo {@link #ANALITICO}
	 */
    public static boolean isAnalitico(Integer codigo) {
    	return ANALITICO.getCodigo().equals(codigo);
    }
    
	/**
	 * Verifica se o codigo recebido eh do tipo {@link #SINTETICO}
	 * 
	 * @param codigo
	 *            o codigo
	 * @return {@code true} caso seja do tipo {@link #SINTETICO}
	 */
    public static boolean isSintetico(Integer codigo) {
    	return SINTETICO.getCodigo().equals(codigo);
    }
}
