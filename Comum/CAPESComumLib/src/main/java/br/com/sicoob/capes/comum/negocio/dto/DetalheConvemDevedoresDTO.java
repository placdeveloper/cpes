/*
 * SICOOB
 * 
 * DetalheConvemDevedoresDTO.java(br.com.sicoob.capes.comum.negocio.dto.DetalheConvemDevedoresDTO)
 */
package br.com.sicoob.capes.comum.negocio.dto;

/**
 * Builder para as definições de detalhe do Convem Devedores.    
 * @author erico.junior
 */
public class DetalheConvemDevedoresDTO extends DetalheAnotacaoDTO {

	/** Serial UID.*/
	private static final long serialVersionUID = 6277065402263637048L;

	/** Natureza da ação. */
	private String natureza;
	
	/** O Cnpj do credor. */
	private String cnpjCredor;
	
	/** Nome da instituição. */
	private String nomeInstituicao;
	
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
	 * @return the cnpjCredor
	 */
	public String getCnpjCredor() {
		return cnpjCredor;
	}

	/**
	 * @param cnpjCredor the cnpjCredor to set
	 */
	public void setCnpjCredor(String cnpjCredor) {
		this.cnpjCredor = cnpjCredor;
	}

	/**
	 * @return the nomeInstituicao
	 */
	public String getNomeInstituicao() {
		return nomeInstituicao;
	}

	/**
	 * @param nomeInstituicao the nomeInstituicao to set
	 */
	public void setNomeInstituicao(String nomeInstituicao) {
		this.nomeInstituicao = nomeInstituicao;
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
