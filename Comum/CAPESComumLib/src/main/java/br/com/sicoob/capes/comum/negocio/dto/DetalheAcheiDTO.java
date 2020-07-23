/*
 * SICOOB
 * 
 * DetalheAcheiDTO.java(br.com.sicoob.capes.comum.negocio.dto.DetalheAcheiDTO)
 */
package br.com.sicoob.capes.comum.negocio.dto;

/**
 * DTO que representa o detalhe do Achei.
 * @author Erico.Junior
 */
public class DetalheAcheiDTO extends DetalheAnotacaoDTO {

	/** Serial UID. */
	private static final long serialVersionUID = 6524935659106871945L;

	/** Nome do Banco no qual o cheque foi devolvido. */ 
	private String banco;
	
	/** Número da agência referente ao cheque. */
	private Integer agencia;
	
	/** Número da conta corrente. */
	private String contaCorrente;
	
	/** Número do cheque. */
	private String cheque;
	
	/** Alínea de devolução do cheque. */
	private String alinea;
	
	/** Cidade onde está localizada a agência. */
	private String cidade;
	
	/** UF onde fica localizada a agência. */
	private String uf;

	/**
	 * @return the contaCorrente
	 */
	public String getContaCorrente() {
		return contaCorrente;
	}

	/**
	 * @param contaCorrente the contaCorrente to set
	 */
	public void setContaCorrente(String contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

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
	 * @return the cheque
	 */
	public String getCheque() {
		return cheque;
	}

	/**
	 * @param cheque the cheque to set
	 */
	public void setCheque(String cheque) {
		this.cheque = cheque;
	}

	/**
	 * @return the alinea
	 */
	public String getAlinea() {
		return alinea;
	}

	/**
	 * @param alinea the alinea to set
	 */
	public void setAlinea(String alinea) {
		this.alinea = alinea;
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
