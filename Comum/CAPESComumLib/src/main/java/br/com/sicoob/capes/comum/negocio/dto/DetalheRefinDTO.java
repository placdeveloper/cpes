/*
 * SICOOB
 * 
 * DetalheRefinDTO.java(br.com.sicoob.capes.comum.negocio.dto.DetalheRefinDTO)
 */
package br.com.sicoob.capes.comum.negocio.dto;


/**
 * DTO que representa o detalhe do Refin/Financiamentos.  
 * @author erico.junior
 */
public class DetalheRefinDTO extends DetalheAnotacaoDTO {
	
	/** Serial UID. */
	private static final long serialVersionUID = 5447229081272339715L;

	/** Natureza da a��o. */
	private String natureza;
	
	/** O Cnpj do credor. */
	private String cnpjCredor;
	
	/** C�digo do banco ou empresa participante. */
	private Integer codigoEmpresaParticipante;
	
	/** N�mero da ag�ncia referente ao cheque. */
	private Integer agencia;
	
	/** Cidade onde est� localizado o cart�rio. */
	private String cidade;
	
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
	 * @return the agencia
	 */
	public Integer getAgencia() {
		return agencia;
	}

	/**
	 * @param agencia the agencia to set
	 */
	public void setAgencia(Integer agencia) {
		this.agencia = agencia;
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

	/**
	 * @return the codigoEmpresaParticipante
	 */
	public Integer getCodigoEmpresaParticipante() {
		return codigoEmpresaParticipante;
	}

	/**
	 * @param codigoEmpresaParticipante the codigoEmpresaParticipante to set
	 */
	public void setCodigoEmpresaParticipante(Integer codigoEmpresaParticipante) {
		this.codigoEmpresaParticipante = codigoEmpresaParticipante;
	}
	
}
