/* 
 * Sicoob
 * TipoRelacionamentoPessoaEnum.java 
 * Criado em: 01/09/2011
 */
package br.com.sicoob.capes.cadastro.negocio.enums;

/**
 * Enum representando os tipos de relacionamento entre pessoas
 *
 * 01/09/2011
 * @author rodrigo.chaves
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
	 * Instancia um novo TipoRelacionamentoPessoaEnum.
	 *
	 * @param codigo o valor de codigo
	 * @param descricao o valor de descricao
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
