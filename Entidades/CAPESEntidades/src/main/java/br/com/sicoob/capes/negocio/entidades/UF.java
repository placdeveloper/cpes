/*
 * SICOOB
 * 
 * UF.java(br.com.sicoob.capes.negocio.entidades.UF)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import br.com.bancoob.negocio.entidades.BancoobEmbeddableObject;

/**
 * @author Erico.Junior
 *
 */
@Embeddable
public class UF extends BancoobEmbeddableObject {

	/** Serial UID.*/
	private static final long serialVersionUID = 5325494631066340727L;

	/** O atributo sigla uf. */
	@Transient
	private String siglaUF;
	
	/** O atributo nome uf. */
	@Transient
	private String nomeUF;
	
	/** O atributo id uf. */
	@Transient
	private Integer idUF;

	/**
	 * @return the siglaUF
	 */
	public String getSiglaUF() {
		return siglaUF;
	}

	/**
	 * @param siglaUF the siglaUF to set
	 */
	public void setSiglaUF(String siglaUF) {
		this.siglaUF = siglaUF;
	}

	/**
	 * @return the nomeUF
	 */
	public String getNomeUF() {
		return nomeUF;
	}

	/**
	 * @param nomeUF the nomeUF to set
	 */
	public void setNomeUF(String nomeUF) {
		this.nomeUF = nomeUF;
	}

	/**
	 * @return the idUF
	 */
	public Integer getIdUF() {
		return idUF;
	}

	/**
	 * @param idUF the idUF to set
	 */
	public void setIdUF(Integer idUF) {
		this.idUF = idUF;
	}
}
