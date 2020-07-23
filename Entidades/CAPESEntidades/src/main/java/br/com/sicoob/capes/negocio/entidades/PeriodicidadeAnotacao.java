/*
 * SICOOB
 * 
 * PeriodicidadeAnotacao.java(br.com.sicoob.capes.negocio.entidades.PeriodicidadeAnotacao)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Entidade que representa a tabela responsável por armazenar as periodicidades
 * das anotações.
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name = "PERIODICIDADEANOTACAO", schema = "CLI")
public class PeriodicidadeAnotacao extends CAPESEntidade<Short> {

	/** Serial UID. */
	private static final long serialVersionUID = -3485322631490559069L;

	/** O atributo id periodicidade anotacao. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Short idPeriodicidadeAnotacao;

	/** O atributo desc periodicidade anotacao. */
	@Column(name = "DESCPERIODICIDADE")
	private String descPeriodicidadeAnotacao;

	/**
	 * @return the idPeriodicidadeAnotacao
	 */
	public Short getIdPeriodicidadeAnotacao() {
		return idPeriodicidadeAnotacao;
	}

	/**
	 * @param idPeriodicidadeAnotacao
	 *            the idPeriodicidadeAnotacao to set
	 */
	public void setIdPeriodicidadeAnotacao(Short idPeriodicidadeAnotacao) {
		this.idPeriodicidadeAnotacao = idPeriodicidadeAnotacao;
	}

	/**
	 * @return the descPeriodicidadeAnotacao
	 */
	public String getDescPeriodicidadeAnotacao() {
		return descPeriodicidadeAnotacao;
	}

	/**
	 * @param descPeriodicidadeAnotacao
	 *            the descPeriodicidadeAnotacao to set
	 */
	public void setDescPeriodicidadeAnotacao(String descPeriodicidadeAnotacao) {
		this.descPeriodicidadeAnotacao = descPeriodicidadeAnotacao;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	@Transient
	public Short getId() {
		return getIdPeriodicidadeAnotacao();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Short id) {
		setIdPeriodicidadeAnotacao(id);
	}

}
