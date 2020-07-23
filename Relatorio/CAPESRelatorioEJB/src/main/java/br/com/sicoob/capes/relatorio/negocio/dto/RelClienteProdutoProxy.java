package br.com.sicoob.capes.relatorio.negocio.dto;

import java.io.Serializable;
import java.util.Date;

public class RelClienteProdutoProxy implements Serializable {

	private static final long serialVersionUID = -3323627185128137233L;

	private Integer codCliente;
	private String nomeRazao;
	private String tipoPessoa;
	private String contaCapital;
	private String contaCorrente;
	private String aplicação;
	private String poupanca;
	private String operacaoCredito;
	private String cartaoCredito;
	private String debitoAutomatico;

	public Integer getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Integer codCliente) {
		this.codCliente = codCliente;
	}

	public String getNomeRazao() {
		return nomeRazao;
	}

	public void setNomeRazao(String nomeRazao) {
		this.nomeRazao = nomeRazao;
	}

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getContaCapital() {
		return contaCapital;
	}

	public void setContaCapital(String contaCapital) {
		this.contaCapital = contaCapital;
	}

	public String getContaCorrente() {
		return contaCorrente;
	}

	public void setContaCorrente(String contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

	public String getAplicação() {
		return aplicação;
	}

	public void setAplicação(String aplicação) {
		this.aplicação = aplicação;
	}

	public String getPoupanca() {
		return poupanca;
	}

	public void setPoupanca(String poupanca) {
		this.poupanca = poupanca;
	}

	public String getOperacaoCredito() {
		return operacaoCredito;
	}

	public void setOperacaoCredito(String operacaoCredito) {
		this.operacaoCredito = operacaoCredito;
	}

	public String getCartaoCredito() {
		return cartaoCredito;
	}

	public void setCartaoCredito(String cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}

	public String getDebitoAutomatico() {
		return debitoAutomatico;
	}

	public void setDebitoAutomatico(String debitoAutomatico) {
		this.debitoAutomatico = debitoAutomatico;
	}

}
