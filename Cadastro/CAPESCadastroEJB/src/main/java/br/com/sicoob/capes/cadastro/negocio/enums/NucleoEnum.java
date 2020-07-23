/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.enums;

/**
 * @author Erico.Junior
 *
 */
public enum NucleoEnum {

	/** O atributo NENHUM. */
	NENHUM(0, "Nenhum");

	/** O atributo numNucleo. */
	private Integer numNucleo;
	
	/** O atributo descricao. */
	private String descricao;
	
	/**
	 * Instancia um novo NucleoEnum.
	 *
	 * @param numNucleo o valor de num nucleo
	 * @param descricao o valor de descricao
	 */
	private NucleoEnum(Integer numNucleo, String descricao) {
		this.numNucleo = numNucleo;
		this.descricao = descricao;
	}

	/**
	 * @return the numNucleo
	 */
	public Integer getNumNucleo() {
		return numNucleo;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	
}