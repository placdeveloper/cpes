/*
 * SICOOB
 * 
 * GrupoCompartilhamentoEnum.java(br.com.sicoob.capes.comum.negocio.enums.GrupoCompartilhamentoEnum)
 */
package br.com.sicoob.capes.comum.negocio.enums;

/**
 * @author erico.junior
 *
 */
public enum GrupoCompartilhamentoEnum {

	/** O atributo SICOOB. */
	SICOOB((short)1, "Sicoob"),
	
	/** O atributo FEDERAL_CRED. */
	FEDERAL_CRED((short)2, "Federal Cred");

	/** O atributo codigo. */
	private Short codigo;
	
	/** O atributo descricao. */
	private String descricao;
	
	/**
	 * Construtor do Enum.
	 * @param codigo 
	 * @param descricao 
	 */
	private GrupoCompartilhamentoEnum(Short codigo, String descricao) {
		this.descricao = descricao;
		this.codigo = codigo;
	}

	/**
	 * @return the codigo
	 */
	public Short getCodigo() {
		return codigo;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}	
}
