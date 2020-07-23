/*
 * SICOOB
 * 
 * BancoCafVO.java(br.com.sicoob.capes.comum.negocio.vo.BancoCafVO)
 */
package br.com.sicoob.capes.comum.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * The Class BancoCafVO.
 */
public class BancoCafVO extends BancoobVo {
	
	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 4191985704603555778L;

	/** O atributo num banco. */
	private Short numBanco;
	
	/** O atributo desc banco. */
	private String descBanco;

	/**
	 * Recupera num banco.
	 * 
	 * @return num banco
	 */
	public Short getNumBanco() {
		return this.numBanco;
	}

	/**
	 * Preenche num banco.
	 * 
	 * @param numBanco
	 *            o novo num banco
	 */
	public void setNumBanco(Short numBanco) {
		this.numBanco = numBanco;
	}

	/**
	 * Recupera desc banco.
	 * 
	 * @return desc banco
	 */
	public String getDescBanco() {
		return this.descBanco;
	}

	/**
	 * Preenche desc banco.
	 * 
	 * @param descBanco
	 *            o novo desc banco
	 */
	public void setDescBanco(String descBanco) {
		this.descBanco = descBanco;
	}

}
