/*
 * SICOOB
 * 
 * HistoricoReferenciaBancaria.java(br.com.sicoob.capes.negocio.entidades.historico.HistoricoReferenciaBancaria)
 */
package br.com.sicoob.capes.negocio.entidades.historico;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;


/**
 * The Class HistoricoReferenciaBancaria.
 */
@Entity
@Table(name="HISTREFERENCIABANCARIAPESSOA", schema="CLI")
@Filters({ @Filter(name = "periodoHistorico"),@Filter(name = "periodoHistoricoAntiga"), @Filter(name = "periodoHistoricoDatasIguais") })
public class HistoricoReferenciaBancaria extends HistoricoReferencia {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** O atributo numero banco. */
	@Column(name="NUMBANCO")
	private String numeroBanco;
	
	/** O atributo numero agencia. */
	@Column(name="NUMAGENCIA")
	private String numeroAgencia;
	
	/** O atributo numero conta. */
	@Column(name="NUMCONTACORRENTE")
	private String numeroConta;
	
	/**
	 * @return the numeroBanco
	 */
	public String getNumeroBanco() {
		return numeroBanco;
	}
	/**
	 * @param numeroBanco the numeroBanco to set
	 */
	public void setNumeroBanco(String numeroBanco) {
		this.numeroBanco = numeroBanco;
	}
	/**
	 * @return the numeroAgencia
	 */
	public String getNumeroAgencia() {
		return numeroAgencia;
	}
	/**
	 * @param numeroAgencia the numeroAgencia to set
	 */
	public void setNumeroAgencia(String numeroAgencia) {
		this.numeroAgencia = numeroAgencia;
	}
	/**
	 * @return the numeroConta
	 */
	public String getNumeroConta() {
		return numeroConta;
	}
	/**
	 * @param numeroConta the numeroConta to set
	 */
	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}
}