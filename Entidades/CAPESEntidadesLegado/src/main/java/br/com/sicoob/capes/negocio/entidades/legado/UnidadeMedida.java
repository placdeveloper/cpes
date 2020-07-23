/*
 * SICOOB
 * 
 * UnidadeMedida.java(br.com.sicoob.capes.negocio.entidades.legado.UnidadeMedida)
 */
package br.com.sicoob.capes.negocio.entidades.legado;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.capes.comum.util.ReflexaoUtils;

/**
 * Entidade que representa a tabela de unidade de medida.
 * 
 * @author erico.junior
 */
@Entity
@Table(name = "UnidadeMedida")
public class UnidadeMedida extends EntidadeDominioReplicavel<Short> {

	/** Serial UID. */
	private static final long serialVersionUID = -6061976072236403446L;

	/** Identificador da unidade de medida. */
	@Id
	@Column(name = "IDUnidadeMedida")
	private Short id;

	/** Sigla da unidade de medida. */
	@Column(name = "siglaUnidadeMedida")
	private String sigla;

	/** Descrição da unidade de medida. */
	@Column(name = "descUnidadeMedida")
	private String descricao;

	/**
	 * @return the id
	 */
	public Short getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Short id) {
		this.id = id;
	}

	/**
	 * @return the sigla
	 */
	public String getSigla() {
		return sigla;
	}

	/**
	 * @param sigla
	 *            the sigla to set
	 */
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Short getIdSQL() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		return ReflexaoUtils.equals(this, obj, "id", "sigla", "descricao");
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		return prime + ((id == null) ? 0 : id.hashCode());
	}

}
