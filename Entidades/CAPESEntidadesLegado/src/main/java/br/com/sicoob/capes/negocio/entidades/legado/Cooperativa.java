/*
 * SICOOB
 * 
 * Cooperativa.java(br.com.sicoob.capes.negocio.entidades.legado.Cooperativa)
 */
package br.com.sicoob.capes.negocio.entidades.legado;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.sicoob.capes.negocio.entidades.legado.pk.CooperativaPK;

/**
 * The Class Cooperativa.
 */
@Entity
@Table(name = "COOPERATIVA")
public class Cooperativa extends CAPESEntidadeLegado<CooperativaPK> {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 9220101405700979712L;
	
	/** O atributo id. */
	@EmbeddedId
	private CooperativaPK id;
	
	/** O atributo id instituicao. */
	private Integer idInstituicao;
	
	/** O atributo id unidade inst. */
	private Integer idUnidadeInst;
	
	/** O atributo nome cooperativa. */
	@Column(name = "DescNomeCoop")
	private String nomeCooperativa;
	
	/**
	 * Recupera id.
	 * 
	 * @return id
	 */
	public CooperativaPK getId() {
		return id;
	}

	/**
	 * Preenche id.
	 * 
	 * @param id
	 *            o novo id
	 */
	public void setId(CooperativaPK id) {
		this.id = id;
	}

	/**
	 * Recupera id instituicao.
	 * 
	 * @return id instituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * Preenche id instituicao.
	 * 
	 * @param idInstituicao
	 *            o novo id instituicao
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	/**
	 * Recupera id unidade inst.
	 * 
	 * @return id unidade inst
	 */
	public Integer getIdUnidadeInst() {
		return idUnidadeInst;
	}

	/**
	 * Preenche id unidade inst.
	 * 
	 * @param idUnidadeInst
	 *            o novo id unidade inst
	 */
	public void setIdUnidadeInst(Integer idUnidadeInst) {
		this.idUnidadeInst = idUnidadeInst;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public CooperativaPK getIdSQL() {
		return getId();
	}

	/**
	 * @return the nomeCooperativa
	 */
	public String getNomeCooperativa() {
		return nomeCooperativa;
	}

	/**
	 * @param nomeCooperativa the nomeCooperativa to set
	 */
	public void setNomeCooperativa(String nomeCooperativa) {
		this.nomeCooperativa = nomeCooperativa;
	}

}
