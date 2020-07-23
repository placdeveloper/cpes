/*
 * SICOOB
 * 
 * TipoBem.java(br.com.sicoob.capes.negocio.entidades.bemantigo.TipoBem)
 */
package br.com.sicoob.capes.negocio.entidades.bemantigo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.bancoob.persistencia.annotations.OrdenacaoPadrao;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.interfaces.DominioReplicavel;

/**
 * Entidade que representa a tabela com os tipos dos bens.
 * @author Erico.Junior
 */
@Entity
@Table(name = "TIPOBEM", schema = "CLI")
@OrdenacaoPadrao(colunas="DESCTIPOBEM",descendente=false)
public class TipoBem extends CAPESEntidade<Short> implements DominioReplicavel {

	/** Serial UID. */
	private static final long serialVersionUID = -1049179923314498287L;

	/** O atributo codigo. */
	@Id
	@Column(name = "CODTIPOBEM")
	private Short codigo;

	/** O atributo descricao. */
	@Column(name = "DESCTIPOBEM")
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

}
