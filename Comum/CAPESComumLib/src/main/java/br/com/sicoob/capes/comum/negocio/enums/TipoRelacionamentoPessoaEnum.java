/*
 * SICOOB
 * 
 * TipoRelacionamentoPessoaEnum.java(br.com.sicoob.capes.comum.negocio.enums.TipoRelacionamentoPessoaEnum)
 */
package br.com.sicoob.capes.comum.negocio.enums;

/**
 * Enum representando os tipos de relacionamento entre pessoas
 *
 * @author diego.rezende
 */
public enum TipoRelacionamentoPessoaEnum {

	/** O atributo ADMINISTRADOR. */
	ADMINISTRADOR((short) 1, "ADMINISTRADOR"),
	
	/** O atributo CONJUGE. */
	CONJUGE((short) 2, "CÔNJUGE"),
	
	/** O atributo CURADOR. */
	CURADOR((short) 3, "CURADOR"),
	
	/** O atributo INVENTARIANTE. */
	INVENTARIANTE((short) 4, "INVENTARIANTE"),
	
	/** O atributo PROCURADOR. */
	PROCURADOR((short) 5, "PROCURADOR"),
	
	/** O atributo REPRESENTATE_LEGAL. */
	REPRESENTATE_LEGAL((short) 6, "REPRESENTATE LEGAL"),
	
	/** O atributo RESPONSAVEL_LEGAL. */
	RESPONSAVEL_LEGAL((short) 7, "RESPONSÁVEL LEGAL"),
	
	/** O atributo SOCIO. */
	SOCIO((short) 8, "SÓCIO"),
	
	/** O atributo SOCIO_ADMINISTRADOR. */
	SOCIO_ADMINISTRADOR((short) 9, "SÓCIO/ADMINISTRADOR"),
	
	/** O atributo TUTOR. */
	TUTOR((short) 10, "TUTOR");
	
	/** O atributo codigo. */
	private Short codigo;
	
	/** O atributo descricao. */
	private String descricao;

	/**
	 * Cria uma nova instância de tipo relacionamento pessoa enum.
	 * 
	 * @param codigo
	 *            the codigo
	 * @param descricao
	 *            the descricao
	 */
	private TipoRelacionamentoPessoaEnum(Short codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	/**
	 * @return o valor de codigo
	 */
	public Short getCodigo() {
		return codigo;
	}

	/**
	 * @return o valor de descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	
}
