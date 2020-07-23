/*
 * SICOOB
 * 
 * TipoLocalidadeEnum.java(br.com.sicoob.capes.comum.negocio.enums.TipoLocalidadeEnum)
 */
package br.com.sicoob.capes.comum.negocio.enums;

/**
 * @author Juan.Damasceno
 */
public enum TipoLocalidadeEnum {

	/** O atributo MUNICIPIO. */
	MUNICIPIO(1, "MUNICIPIO"),
	
	/** O atributo DISTRITO. */
	DISTRITO(2, "DISTRITO"),
	
	/** O atributo SUBDISTRITO. */
	SUBDISTRITO(3, "SUBDISTRITO"),
	
	/** O atributo OUTRO. */
	OUTRO(4, "OUTRO");

	/** O atributo id tipo localidade. */
	private Integer idTipoLocalidade;
	
	/** O atributo descricao. */
	private String descricao;
	
	/**
	 * Construtor do Enum.
	 * @param idTipoAplicacao O identificador do tipo de aplicação.
	 * @param descricao A descrição do tipo de aplicação.
	 */
	private TipoLocalidadeEnum(Integer idTipoLocalidade, String descricao) {
		this.idTipoLocalidade = idTipoLocalidade;
		this.descricao = descricao;
	}

	/**
	 * @return the idTipoLocalidade
	 */
	public Integer getIdTipoLocalidade() {
		return idTipoLocalidade;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

}
