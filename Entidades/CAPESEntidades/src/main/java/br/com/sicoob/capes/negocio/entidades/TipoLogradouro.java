/*
 * SICOOB
 * 
 * TipoLogradouro.java(br.com.sicoob.capes.negocio.entidades.TipoLogradouro)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.HashCodeBuilder;

import br.com.bancoob.negocio.entidades.BancoobEmbeddableObject;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;

/**
 * Entidade que representa a localidade. 
 * @author Erico.Junior
 */
@Embeddable
public class TipoLogradouro extends BancoobEmbeddableObject {

	/** Serial UID.*/
	private static final long serialVersionUID = -4034160816255682632L;

	/** O atributo nome. */
	@Transient
	private String nome;
	
	/** O atributo id tipo logradouro. */
	private Integer idTipoLogradouro;
		
	/**
	 * Construtor
	 */
	public TipoLogradouro() {
	}

	/**
	 * Construtor
	 * 
	 * @param idTipoLogradouro
	 * @param nome
	 */
	public TipoLogradouro(Integer idTipoLogradouro, String nome) {
		this.idTipoLogradouro = idTipoLogradouro;
		this.nome = nome;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * @return the idTipoLogradouro
	 */
	public Integer getIdTipoLogradouro() {
		return idTipoLogradouro;
	}

	/**
	 * @param idTipoLogradouro the idTipoLogradouro to set
	 */
	public void setIdTipoLogradouro(Integer idTipoLogradouro) {
		this.idTipoLogradouro = idTipoLogradouro;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		return ReflexaoUtils.equals(this, obj, "idTipoLogradouro");
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}	

}
