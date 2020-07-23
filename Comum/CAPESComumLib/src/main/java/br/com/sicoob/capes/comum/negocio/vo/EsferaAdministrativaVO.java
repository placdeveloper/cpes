/*
 * SICOOB
 * 
 * EsferaAdministrativaVO.java(br.com.sicoob.capes.comum.negocio.vo.EsferaAdministrativaVO)
 */
package br.com.sicoob.capes.comum.negocio.vo;

import java.io.Serializable;

/**
 * VO que representa a esfera administrativa.
 * @author Erico.Junior
 */
public class EsferaAdministrativaVO implements Serializable {

	/** Serial UID.*/
	private static final long serialVersionUID = -3706767627784492600L;

	/** O atributo codigo. */
	private Short codigo;
	
	/** O atributo descricao. */
	private String descricao;

	/**
	 * @return the codigo
	 */
	public Short getCodigo() {
		return this.codigo;
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
		return this.descricao;
	}
	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
