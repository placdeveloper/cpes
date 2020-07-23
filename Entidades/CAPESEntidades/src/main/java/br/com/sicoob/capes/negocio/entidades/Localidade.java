/*
 * SICOOB
 * 
 * Localidade.java(br.com.sicoob.capes.negocio.entidades.Localidade)
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
public class Localidade extends BancoobEmbeddableObject {

	/** Serial UID.*/
	private static final long serialVersionUID = 5279289690070391437L;

	/** O atributo id localidade. */
	private Integer idLocalidade;

	/** O atributo nome. */
	@Transient
	private String nome;
	
	/** O atributo nome limpo. */
	@Transient
	private String nomeLimpo;
	
	/** O atributo numero cep. */
	@Transient
	private String numeroCep;
	
	/** O atributo codigo ibge. */
	@Transient
	private String codigoIBGE;

	/** O atributo uf. */
	@Transient
	private UF uf;

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
	 * Recupera nome limpo.
	 * 
	 * @return nome limpo
	 */
	public String getNomeLimpo() {
		return nomeLimpo;
	}

	/**
	 * Preenche nome limpo.
	 * 
	 * @param nomeLimpo
	 *            o novo nome limpo
	 */
	public void setNomeLimpo(String nomeLimpo) {
		this.nomeLimpo = nomeLimpo;
	}

	/**
	 * @return the idLocalidade
	 */
	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	/**
	 * @param idLocalidade the idLocalidade to set
	 */
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}	
	
	/**
	 * Recupera numero cep.
	 * 
	 * @return numero cep
	 */
	public String getNumeroCep() {
		return numeroCep;
	}

	/**
	 * Preenche numero cep.
	 * 
	 * @param numeroCep
	 *            o novo numero cep
	 */
	public void setNumeroCep(String numeroCep) {
		this.numeroCep = numeroCep;
	}	
	
	/**
	 * Recupera codigo ibge.
	 * 
	 * @return codigo ibge
	 */
	public String getCodigoIBGE() {
		return codigoIBGE;
	}

	/**
	 * Preenche codigo ibge.
	 * 
	 * @param codigoIBGE
	 *            o novo codigo ibge
	 */
	public void setCodigoIBGE(String codigoIBGE) {
		this.codigoIBGE = codigoIBGE;
	}

	/**
	 * @return the uf
	 */
	public UF getUf() {
		return uf;
	}

	/**
	 * @param uf the uf to set
	 */
	public void setUf(UF uf) {
		this.uf = uf;
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		return ReflexaoUtils.equals(this, obj, "idLocalidade");
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}
