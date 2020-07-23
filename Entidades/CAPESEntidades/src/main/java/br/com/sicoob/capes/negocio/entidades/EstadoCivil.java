/*
 * SICOOB
 * 
 * EstadoCivil.java(br.com.sicoob.capes.negocio.entidades.EstadoCivil)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.bancoob.persistencia.annotations.OrdenacaoPadrao;

/**
 * Entidade que representa estado civil.
 * @author Erico.Junior
 */
@Entity
@Table(name="ESTADOCIVIL", schema = "CLI")
@OrdenacaoPadrao(colunas = "DESCESTADOCIVIL", descendente = false)
public class EstadoCivil extends CAPESEntidade<Short> {

	/** Serial UID.*/
	private static final long serialVersionUID = -7781481588474606430L;

	/** O atributo codigo. */
	@Id
	@Column(name = "CODESTADOCIVIL")
	private Short codigo;

	/** O atributo descricao. */
	@Column(name = "DESCESTADOCIVIL")
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
	
	public EstadoCivil(){
		
	}
	
	public EstadoCivil(Short id){
		this.codigo = id;
	}
	
}
