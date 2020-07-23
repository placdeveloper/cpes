/*
 * SICOOB
 * 
 * TipoPosseBem.java(br.com.sicoob.capes.negocio.entidades.bemantigo.TipoPosseBem)
 */
package br.com.sicoob.capes.negocio.entidades.bemantigo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.bancoob.persistencia.annotations.OrdenacaoPadrao;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.interfaces.DominioReplicavelLista;

/**
 * Entidade que representa a tabela dos tipos de posse de bens.
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name = "TIPOPOSSEBEM", schema = "CLI")
@OrdenacaoPadrao(colunas="DESCTIPOPOSSEBEM",descendente=false)
public class TipoPosseBem extends CAPESEntidade<Short> implements
		DominioReplicavelLista {

	/** Serial UID. */
	private static final long serialVersionUID = 3555817805491524520L;

	/** O atributo codigo. */
	@Id
	@Column(name = "CODTIPOPOSSEBEM")
	private Short codigo;

	/** O atributo descricao. */
	@Column(name = "DESCTIPOPOSSEBEM")
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