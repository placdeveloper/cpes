/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.enums;

/**
 * Enum para os tipos de bem. 
 * @author Juan.Damasceno
 */
public enum TipoBemEnum {

	/** O atributo MOVEL. */
	MOVEL((short)0, "Móvel"),
	
	/** O atributo IMOVEL. */
	IMOVEL((short)1, "Imóvel"),
	
	/** O atributo OUTROS_BENS. */
	OUTROS_BENS((short)2, "Outros bens"),
	
	/** O atributo SEMOVENTES. */
	SEMOVENTES((short)3, "Semoventes");

	/** O atributo idTipoBem. */
	private Short idTipoBem;
	
	/** O atributo descricao. */
	private String descricao;

	/**
	 * TipoBemEnum
	 * @param idTipoBem
	 * @param descricao
	 */
	private TipoBemEnum(Short idTipoBem, String descricao) {
		this.idTipoBem = idTipoBem;
		this.descricao = descricao;
	}

	/**
	 * @return the idTipoBem
	 */
	public Short getIdTipoBem() {
		return idTipoBem;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

}