/*
 * SICOOB
 * 
 * AtividadeEconomica.java(br.com.sicoob.capes.negocio.entidades.AtividadeEconomica)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.bancoob.persistencia.annotations.OrdenacaoPadrao;

/**
 * Entidade que representa as atividades econômicas.
 * @author Erico.Junior
 */
@Entity
@Table(name="ATIVIDADEECONOMICA", schema = "CLI")
@OrdenacaoPadrao(colunas = "DESCATIVIDADEECONOMICA", descendente = false)
public class AtividadeEconomica extends CAPESEntidade<Short> {

	/** Serial UID.*/
	private static final long serialVersionUID = -4044319034663172113L;

	/** O atributo codigo. */
	@Id
	@Column(name = "CODATIVIDADEECONOMICA")
	private Short codigo;
	
	/** O atributo descricao. */
	@Column(name = "DESCATIVIDADEECONOMICA")
	private String descricao;

	/**
	 * @return the codigo
	 */
	public Short getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
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
	 * @param descricao the descricao to set
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
