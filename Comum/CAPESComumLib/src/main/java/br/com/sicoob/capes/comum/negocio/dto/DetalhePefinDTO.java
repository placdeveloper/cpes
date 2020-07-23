/*
 * SICOOB
 * 
 * DetalhePefinDTO.java(br.com.sicoob.capes.comum.negocio.dto.DetalhePefinDTO)
 */
package br.com.sicoob.capes.comum.negocio.dto;


/**
 * DTO que representa o detalhe do Pefin.  
 * @author erico.junior
 */
public class DetalhePefinDTO extends DetalheAnotacaoDTO {
	
	/** Serial UID. */
	private static final long serialVersionUID = 5447229081272339715L;

	/** Natureza da a��o. */
	private String natureza;
	
	/** O Cnpj do credor. */
	private String cnpjCredor;
	
	/** Nome da institui��o. */
	private String nomeInstituicao;
	
	/** UF onde est� localizado o cart�rio. */
	private String uf;

	/** Marca��o se � devedor principal ou coobrigado. */
	private String principal;

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

	/**
	 * @return the principal
	 */
	public String getPrincipal() {
		return principal;
	}

	/**
	 * @param principal the principal to set
	 */
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	
}
