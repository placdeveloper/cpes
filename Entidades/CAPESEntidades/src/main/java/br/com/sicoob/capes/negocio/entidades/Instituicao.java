/*
 * SICOOB
 * 
 * Instituicao.java(br.com.sicoob.capes.negocio.entidades.Instituicao)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.HashCodeBuilder;

import br.com.bancoob.negocio.entidades.BancoobEmbeddableObject;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;

/**
 * Entidade que representa a institução.
 * @author Erico.Junior
 *
 */
@Embeddable
public class Instituicao extends BancoobEmbeddableObject {

	/** Serial UID.*/
	private static final long serialVersionUID = 4343643575462680926L;

	/** O atributo nome. */
	@Transient
	private String nome;

	/** O atributo nome unidade. */
	@Transient
	private String nomeUnidade;
	
	/** O atributo numero. */
	@Transient
	private String numero;
	
	/** O atributo id instituicao. */
	private Integer idInstituicao;
	
	/** O atributo id unidade inst. */
	private Integer idUnidadeInst;
	
	/**
	 * Cria uma nova instância de instituicao.
	 */
	public Instituicao() {
	}
	
	/**
	 * Cria uma nova instância de instituicao.
	 * 
	 * @param idInstituicao
	 *            the id instituicao
	 * @param idUnidadeInst
	 *            the id unidade inst
	 */
	public Instituicao(Integer idInstituicao, Integer idUnidadeInst) {
		this.idInstituicao = idInstituicao;
		this.idUnidadeInst = idUnidadeInst;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		return ReflexaoUtils.equals(this, obj, "idInstituicao");
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
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
	 * @return the idInstituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * @param idInstituicao the idInstituicao to set
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	/**
	 * @return the idUnidadeInst
	 */
	public Integer getIdUnidadeInst() {
		return idUnidadeInst;
	}

	/**
	 * @param idUnidadeInst the idUnidadeInst to set
	 */
	public void setIdUnidadeInst(Integer idUnidadeInst) {
		this.idUnidadeInst = idUnidadeInst;
	}	

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the nomeUnidade
	 */
	public String getNomeUnidade() {
		return nomeUnidade;
	}

	/**
	 * @param nomeUnidade the nomeUnidade to set
	 */
	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade;
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		
		return getClass().getSimpleName() + ": idInstituicao(" + getIdInstituicao()
				+ "); idUnidadeInst(" + getIdUnidadeInst() + ")";
	}
	
}
