/*
 * SICOOB
 * 
 * ReferenciaBancariaBase.java(br.com.sicoob.capes.negocio.entidades.ReferenciaBancariaBase)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import br.com.sicoob.capes.negocio.entidades.vigente.Referencia;

/**
 * The Class ReferenciaBancariaBase.
 */
@MappedSuperclass
public class ReferenciaBancariaBase extends Referencia {
	
	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 1584429753824439315L;
	
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