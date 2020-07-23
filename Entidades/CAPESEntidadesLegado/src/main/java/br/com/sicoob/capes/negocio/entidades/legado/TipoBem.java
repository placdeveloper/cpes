/*
 * SICOOB
 * 
 * TipoBem.java(br.com.sicoob.capes.negocio.entidades.legado.TipoBem)
 */
package br.com.sicoob.capes.negocio.entidades.legado;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.capes.comum.util.ReflexaoUtils;

/**
 * Entidade que representa a entidade de tipo de bem.
 * @author erico.junior
 */
@Entity
@Table (name = "TipoBem")
public class TipoBem extends EntidadeDominioReplicavel<Short> {

	/** Serial UID.*/
	private static final long serialVersionUID = 8616438984250179585L;

	/** Identificador do tipo do bem.*/
	@Id
	@Column (name = "IDTipoBem")
	private Short id;
	
	/** Descrição do tipo do bem. */
	@Column (name = "DescTipoBem")
	private String descricao;
	
	/** Código do grupo do bem. */
	@Column (name = "CodGrupoBem")
	private Short grupo;

	/**
	 * @return o valor do atributo descricao.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao o novo valor do atributo descricao.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return o valor do atributo grupo.
	 */
	public Short getGrupo() {
		return grupo;
	}

	/**
	 * @param grupo o novo valor do atributo grupo.
	 */
	public void setGrupo(Short grupo) {
		this.grupo = grupo;
	}

	/**
	 * @return o valor do atributo id.
	 */
	public Short getId() {
		return id;
	}

	/**
	 * @param id o novo valor do atributo id.
	 */
	public void setId(Short id) {
		this.id = id;
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
	public int hashCode() {
		final int prime = 31;
		return prime + ((id == null) ? 0 : id.hashCode());
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		return ReflexaoUtils.equals(this, obj, "id", "descricao", "grupo");
	}	


	
	
}

