package br.com.sicoob.capes.relatorio.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * A Classe RelatorioDeclaracaoPropositoDTO.
 */
public class RelatorioDeclaracaoPropositoDTO extends BancoobDto {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -2483452951147821357L;

	/** O atributo contaPoupanca. */
	private Boolean contaPoupanca;
	
	/** O atributo contaCorrente. */
	private Boolean contaCorrente;
	
	/** O atributo contaSalario. */
	private Boolean contaSalario;
	
	/** O atributo chequeEspecial. */
	private Boolean chequeEspecial;
	
	/** O atributo emprestimoFinanciamento. */
	private Boolean emprestimoFinanciamento;
	
	/** O atributo investimento. */
	private Boolean investimento;
	
	/** O atributo cartoes. */
	private Boolean cartoes;
	
	/** O atributo seguros. */
	private Boolean seguros;
	
	/** O atributo consorcio. */
	private Boolean consorcio;
	
	/** O atributo previdenciaPrivada. */
	private Boolean previdenciaPrivada;

	/**
	 * Recupera o valor de contaPoupanca.
	 *
	 * @return o valor de contaPoupanca
	 */
	public Boolean getContaPoupanca() {
		return contaPoupanca;
	}

	/**
	 * Define o valor de contaPoupanca.
	 *
	 * @param contaPoupanca o novo valor de contaPoupanca
	 */
	public void setContaPoupanca(Boolean contaPoupanca) {
		this.contaPoupanca = contaPoupanca;
	}

	/**
	 * Recupera o valor de contaCorrente.
	 *
	 * @return o valor de contaCorrente
	 */
	public Boolean getContaCorrente() {
		return contaCorrente;
	}

	/**
	 * Define o valor de contaCorrente.
	 *
	 * @param contaCorrente o novo valor de contaCorrente
	 */
	public void setContaCorrente(Boolean contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

	/**
	 * Recupera o valor de contaSalario.
	 *
	 * @return o valor de contaSalario
	 */
	public Boolean getContaSalario() {
		return contaSalario;
	}

	/**
	 * Define o valor de contaSalario.
	 *
	 * @param contaSalario o novo valor de contaSalario
	 */
	public void setContaSalario(Boolean contaSalario) {
		this.contaSalario = contaSalario;
	}

	/**
	 * Recupera o valor de chequeEspecial.
	 *
	 * @return o valor de chequeEspecial
	 */
	public Boolean getChequeEspecial() {
		return chequeEspecial;
	}

	/**
	 * Define o valor de chequeEspecial.
	 *
	 * @param chequeEspecial o novo valor de chequeEspecial
	 */
	public void setChequeEspecial(Boolean chequeEspecial) {
		this.chequeEspecial = chequeEspecial;
	}

	/**
	 * Recupera o valor de emprestimoFinanciamento.
	 *
	 * @return o valor de emprestimoFinanciamento
	 */
	public Boolean getEmprestimoFinanciamento() {
		return emprestimoFinanciamento;
	}

	/**
	 * Define o valor de emprestimoFinanciamento.
	 *
	 * @param emprestimoFinanciamento o novo valor de emprestimoFinanciamento
	 */
	public void setEmprestimoFinanciamento(Boolean emprestimoFinanciamento) {
		this.emprestimoFinanciamento = emprestimoFinanciamento;
	}

	/**
	 * Recupera o valor de investimento.
	 *
	 * @return o valor de investimento
	 */
	public Boolean getInvestimento() {
		return investimento;
	}

	/**
	 * Define o valor de investimento.
	 *
	 * @param investimento o novo valor de investimento
	 */
	public void setInvestimento(Boolean investimento) {
		this.investimento = investimento;
	}

	/**
	 * Recupera o valor de cartoes.
	 *
	 * @return o valor de cartoes
	 */
	public Boolean getCartoes() {
		return cartoes;
	}

	/**
	 * Define o valor de cartoes.
	 *
	 * @param cartoes o novo valor de cartoes
	 */
	public void setCartoes(Boolean cartoes) {
		this.cartoes = cartoes;
	}

	/**
	 * Recupera o valor de seguros.
	 *
	 * @return o valor de seguros
	 */
	public Boolean getSeguros() {
		return seguros;
	}

	/**
	 * Define o valor de seguros.
	 *
	 * @param seguros o novo valor de seguros
	 */
	public void setSeguros(Boolean seguros) {
		this.seguros = seguros;
	}

	/**
	 * Recupera o valor de consorcio.
	 *
	 * @return o valor de consorcio
	 */
	public Boolean getConsorcio() {
		return consorcio;
	}

	/**
	 * Define o valor de consorcio.
	 *
	 * @param consorcio o novo valor de consorcio
	 */
	public void setConsorcio(Boolean consorcio) {
		this.consorcio = consorcio;
	}

	/**
	 * Recupera o valor de previdenciaPrivada.
	 *
	 * @return o valor de previdenciaPrivada
	 */
	public Boolean getPrevidenciaPrivada() {
		return previdenciaPrivada;
	}

	/**
	 * Define o valor de previdenciaPrivada.
	 *
	 * @param previdenciaPrivada o novo valor de previdenciaPrivada
	 */
	public void setPrevidenciaPrivada(Boolean previdenciaPrivada) {
		this.previdenciaPrivada = previdenciaPrivada;
	}

}