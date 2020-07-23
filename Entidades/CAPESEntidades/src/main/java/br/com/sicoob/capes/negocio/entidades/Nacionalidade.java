/*
 * SICOOB
 * 
 * Nacionalidade.java(br.com.sicoob.capes.negocio.entidades.Nacionalidade)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.capes.negocio.entidades.interfaces.DominioReplicavelLista;

/**
 * Entidade que representa a nacionalidade.
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name = "NACIONALIDADE", schema = "CLI")
public class Nacionalidade extends CAPESEntidade<Short> implements DominioReplicavelLista {

	/** Serial UID. */
	private static final long serialVersionUID = -5616162942302340875L;

	/** O atributo codigo. */
	@Id
	@Column(name = "CODNACIONALIDADE")
	private Short codigo;

	/** O atributo descricao. */
	@Column(name = "DESCNACIONALIDADE")
	private String descricao;

	/**
	 * @return the codigo
	 */
	public Short getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(Short codigo) {
		this.codigo = codigo;
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
	@Override
	public Short getId() {
		return getCodigo();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Short id) {
		setCodigo(id);
	}

	/** 
	 * {@inheritDoc}
	 */
	public String getCodigoListaItem() {
		return String.valueOf(getCodigo());
	}

}
