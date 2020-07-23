/*
 * SICOOB
 * 
 * DetalheCCFDTO.java(br.com.sicoob.capes.comum.negocio.dto.DetalheCCFDTO)
 */
package br.com.sicoob.capes.comum.negocio.dto;

/**
 * DTO que representa o detalhe do CCF.   
 * @author erico.junior
 */
public class DetalheCCFDTO extends DetalheAnotacaoDTO {

	/** Serial UID.*/
	private static final long serialVersionUID = -3738073508878889331L;

	/** Nome do Banco no qual o cheque foi devolvido. */ 
	private String banco;
	
	/** Número da agência referente ao cheque. */
	private Integer agencia;
	
	/** A quantidade de cheques. */
	private Integer quantidadeCheques;
	
	/** Cidade onde está localizada a agência. */
	private String cidade;
	
	/** UF onde fica localizada a agência. */
	private String uf;

	/**
	 * @return the banco
	 */
	public String getBanco() {
		return banco;
	}

	/**
	 * @param banco the banco to set
	 */
	public void setBanco(String banco) {
		this.banco = banco;
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
	 * @return the quantidadeCheques
	 */
	public Integer getQuantidadeCheques() {
		return quantidadeCheques;
	}

	/**
	 * @param quantidadeCheques the quantidadeCheques to set
	 */
	public void setQuantidadeCheques(Integer quantidadeCheques) {
		this.quantidadeCheques = quantidadeCheques;
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
