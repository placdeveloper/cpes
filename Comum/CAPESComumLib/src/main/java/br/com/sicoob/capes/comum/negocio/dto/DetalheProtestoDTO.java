/*
 * SICOOB
 * 
 * DetalheProtestoDTO.java(br.com.sicoob.capes.comum.negocio.dto.DetalheProtestoDTO)
 */
package br.com.sicoob.capes.comum.negocio.dto;

/**
 * DTO que representa o detalhe do protesto.
 * @author Erico.Junior
 */
public class DetalheProtestoDTO extends DetalheAnotacaoDTO {

	/** Serial UID. */
	private static final long serialVersionUID = -8641771328926355656L;

	/** Natureza do protesto. */
	private String natureza;
	
	/** Identificação do cartório no qual o título encontra-se protestado. */ 
	private Integer cartorio;

	/** Cidade onde está localizado o cartório. */
	private String cidade;
	
	/** UF onde está localizado o cartório. */
	private String uf;
	
	/** Status sub-judice da divida. */
	private String subJudice;

	/**
	 * @return the cartorio
	 */
	public Integer getCartorio() {
		return cartorio;
	}

	/**
	 * @param cartorio the cartorio to set
	 */
	public void setCartorio(Integer cartorio) {
		this.cartorio = cartorio;
	}

	/**
	 * @return the subJudice
	 */
	public String getSubJudice() {
		return subJudice;
	}

	/**
	 * @param subJudice the subJudice to set
	 */
	public void setSubJudice(String subJudice) {
		this.subJudice = subJudice;
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

}
