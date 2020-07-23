/*
 * SICOOB
 * 
 * UnidadeMedida.java(br.com.sicoob.capes.negocio.entidades.UnidadeMedida)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.bancoob.persistencia.annotations.OrdenacaoPadrao;
import br.com.sicoob.capes.negocio.entidades.interfaces.DominioReplicavel;

/**
 * Entidade que representa as unidades de medidas.
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name = "UNIDADEMEDIDA", schema = "CLI")
@OrdenacaoPadrao(colunas="DESCUNIDADEMEDIDA",descendente=false)
public class UnidadeMedida extends CAPESEntidade<Short> implements
		DominioReplicavel {

	/** Serial UID. */
	private static final long serialVersionUID = -6695674493205030296L;

	/** O atributo codigo. */
	@Id
	@Column(name = "CODUNIDADEMEDIDA")
	private Short codigo;

	/** O atributo descricao. */
	@Column(name = "DESCUNIDADEMEDIDA")
	private String descricao;

	/** O atributo sigla. */
	@Column(name = "SIGLAUNIDADEMEDIDA")
	private String sigla;
	
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
