/*
 * SICOOB
 * 
 * DetalheFalenciaDTO.java(br.com.sicoob.capes.comum.negocio.dto.DetalheFalenciaDTO)
 */
package br.com.sicoob.capes.comum.negocio.dto;

/**
 * DTO que representa o detalhe de Falência. 
 * @author erico.junior
 */
public class DetalheFalenciaDTO extends DetalheAnotacaoDTO {

	/** Serial UID.*/
	private static final long serialVersionUID = -130720229459913628L;

	/** Natureza da ação. */
	private String natureza;
	
	/** Número da vara civil. */
	private Integer varaCivil;
	
	/** Cidade onde está localizado o cartório. */
	private String cidade;
	
	/** UF onde está localizado o cartório. */
	private String uf;

	/**
	 * @return the natureza
	 */
	public String getNatureza() {
		return natureza;
	}

	/**
	 * @param natureza the natureza to set
	 */
	public void setNatureza(String natureza) {
		this.natureza = natureza;
	}

	/**
	 * @return the varaCivil
	 */
	public Integer getVaraCivil() {
		return varaCivil;
	}

	/**
	 * @param varaCivil the varaCivil to set
	 */
	public void setVaraCivil(Integer varaCivil) {
		this.varaCivil = varaCivil;
	}

	/**
	 * @return the cidade
	 */
	public String getCidade() {
		return cidade;
	}

	/**
	 * @param cidade the cidade to set
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	/**
	 * @return the uf
	 */
	public String getUf() {
		return uf;
	}

	/**
	 * @param uf the uf to set
	 */
	public void setUf(String uf) {
		this.uf = uf;
	}

	
}
