/*
 * SICOOB
 * 
 * DominioVO.java(br.com.sicoob.capes.api.negocio.vo.DominioVO)
 */
package br.com.sicoob.capes.api.negocio.vo;

import java.io.Serializable;


/**
 * @author Marcelo.Onofre
 *
 */
public class DominioVO implements Serializable {

	/** Serial UID.*/
	private static final long serialVersionUID = -7784561146464864737L;

	/** O atributo codigo. */
	private Short codigo;

	/** O atributo descricao. */
	private String descricao;
	
	/**
	 * Cria uma nova instância de dominio vo.
	 */
	public DominioVO(){
		
	}

	/**
	 * Cria uma nova instância de dominio vo.
	 * 
	 * @param codigo
	 *            the codigo
	 * @param descricao
	 *            the descricao
	 */
	public DominioVO(Short codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

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
	
	
}
