/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.enums;

/**
 * Enum para os tipos de captura.  
 * @author Erico.Junior
 */
public enum TipoCapturaEnum {

	/** O atributo AUTOMATICA. */
	AUTOMATICA((short)1, "Automática"),
	
	/** O atributo SEMI_AUTOMATICA. */
	SEMI_AUTOMATICA((short)2, "Semi-automática"), 
	
	/** O atributo MANUAL. */
	MANUAL((short)3, "Manual");

	/** O atributo codigo. */
	private Short codigo;
	
	/** O atributo descricao. */
	private String descricao;

	/**
	 * Construtor do Enum.
	 * @param codigo O identificador do tipo de captura.
	 * @param descricao A descrição do tipo de captura.
	 */
	private TipoCapturaEnum(Short codigo, String descricao) {
		this.descricao = descricao;
		this.codigo = codigo;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @return the codigo
	 */
	public Short getCodigo() {
		return codigo;
	}

}
