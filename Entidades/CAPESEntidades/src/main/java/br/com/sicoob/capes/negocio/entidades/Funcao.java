/*
 * SICOOB
 * 
 * Funcao.java(br.com.sicoob.capes.negocio.entidades.Funcao)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.bancoob.persistencia.annotations.OrdenacaoPadrao;

/**
 * Classe responsavel por armazenar os dados de Funcao. 
 * @author juan.damasceno
 *
 */
@Entity
@Table(name="FUNCAO",schema="CLI")
@OrdenacaoPadrao(colunas = "descricao", descendente = false)
public class Funcao extends CAPESEntidade<Short> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** O atributo id funcao. */
	@Id
	private Short idFuncao;
	
	/** O atributo descricao. */
	@Column(name="DESCFUNCAO")
	private String descricao;
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Short getId() {
		return idFuncao;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Short id) {
		idFuncao = id;
	}

	/**
	 * @return the idFuncao
	 */
	public Short getIdFuncao() {
		return idFuncao;
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
	 * @param idFuncao the idFuncao to set
	 */
	public void setIdFuncao(Short idFuncao) {
		this.idFuncao = idFuncao;
	}
}
