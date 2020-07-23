/*
 * SICOOB
 * 
 * ConselhoRegional.java(br.com.sicoob.capes.negocio.entidades.ConselhoRegional)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.bancoob.persistencia.annotations.OrdenacaoPadrao;

/**
 * @author Erico.Junior
 *
 */
@Entity
@Table(name = "CONSELHOREGIONAL", schema = "CLI")
@OrdenacaoPadrao(colunas="DESCCONSELHOREGIONAL",descendente=false)
public class ConselhoRegional extends CAPESEntidade<Short> {
	
	/** Serial UID.*/
	private static final long serialVersionUID = 7312786509145375592L;

	/** O atributo codigo. */
	@Id
	@Column(name = "CODCONSELHOREGIONAL")
	private Short codigo;
	
	/** O atributo descricao. */
	@Column(name = "DESCCONSELHOREGIONAL", length = 50, nullable = false)
	private String descricao;

	/**
	 * Recupera codigo.
	 * 
	 * @return codigo
	 */
	public Short getCodigo() {
		return codigo;
	}

	/**
	 * Preenche codigo.
	 * 
	 * @param codigo
	 *            o novo codigo
	 */
	public void setCodigo(Short codigo) {
		this.codigo = codigo;
	}

	/**
	 * Recupera descricao.
	 * 
	 * @return descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Preenche descricao.
	 * 
	 * @param descricao
	 *            o novo descricao
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
