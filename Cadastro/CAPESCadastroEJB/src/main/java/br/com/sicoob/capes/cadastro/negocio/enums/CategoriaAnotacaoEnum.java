/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.enums;

/**
 * Enum para categoria da anotação.
 * @author Erico.Junior
 */
public enum CategoriaAnotacaoEnum {

	/** O atributo INFORMATIVA. */
	INFORMATIVA((short)1, "Informativa"),
	
	/** O atributo IMPEDITIVA_RELATIVA. */
	IMPEDITIVA_RELATIVA((short)2, "Impeditiva relativa"),
	
	/** O atributo IMPEDITIVA_ABSOLUTA. */
	IMPEDITIVA_ABSOLUTA((short)3, "Impeditiva absoluta");

	/** O atributo idCategoriaAnotacao. */
	private Short idCategoriaAnotacao;
	
	/** O atributo descricao. */
	private String descricao;
	
	/**
	 * Construtor da Enum.
	 * @param idCategoriaAnotacao O identificador da categoria.
	 * @param descricao A descrição da categoria.
	 */
	private CategoriaAnotacaoEnum(Short idCategoriaAnotacao, String descricao) {
		this.descricao = descricao;
		this.idCategoriaAnotacao = idCategoriaAnotacao;
	}

	/**
	 * @return the idCategoriaAnotacao
	 */
	public Short getIdCategoriaAnotacao() {
		return idCategoriaAnotacao;
	}


	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

}
