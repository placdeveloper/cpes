/*
 * SICOOB
 * 
 * ListaItem.java(br.com.sicoob.capes.negocio.entidades.legado.ListaItem)
 */
package br.com.sicoob.capes.negocio.entidades.legado;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.legado.pk.ListaItemPK;

/**
 * Classe que mapeia um item da lista.
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name = "ListaItem")
public class ListaItem extends EntidadeDominioReplicavel<ListaItemPK> {

	/** Serial UID. */
	private static final long serialVersionUID = 5213697421831105958L;

	/** Chave primária do item da lista. */
	@EmbeddedId
	private ListaItemPK pk;

	/** O atributo descricao. */
	@Column(name = "DescListaItem")
	private String descricao;

	/**
	 * @return the pk
	 */
	public ListaItemPK getPk() {
		return pk;
	}

	/**
	 * @param pk
	 *            the pk to set
	 */
	public void setPk(ListaItemPK pk) {
		this.pk = pk;
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
	public ListaItemPK getIdSQL() {
		return getPk();
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		return ReflexaoUtils.equals(this, obj, "descricao", "pk");
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "pk");
	}

	
}
