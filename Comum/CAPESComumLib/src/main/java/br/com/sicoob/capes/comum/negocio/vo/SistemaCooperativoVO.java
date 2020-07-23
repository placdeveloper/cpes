/*
 * SICOOB
 * 
 * SistemaCooperativoVO.java(br.com.sicoob.capes.comum.negocio.vo.SistemaCooperativoVO)
 */
package br.com.sicoob.capes.comum.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * The Class SistemaCooperativoVO.
 */
public class SistemaCooperativoVO extends BancoobVo {
	
	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -1409961274251739114L;
	
	/** O atributo id sistema cooperativo. */
	private Integer idSistemaCooperativo;
	
	/** O atributo numCooperativa. */
	private Integer numCooperativa;

	/**
	 * Recupera id sistema cooperativo.
	 * 
	 * @return id sistema cooperativo
	 */
	public Integer getIdSistemaCooperativo() {
		return idSistemaCooperativo;
	}

	/**
	 * Preenche id sistema cooperativo.
	 * 
	 * @param idSistemaCooperativo
	 *            o novo id sistema cooperativo
	 */
	public void setIdSistemaCooperativo(Integer idSistemaCooperativo) {
		this.idSistemaCooperativo = idSistemaCooperativo;
	}

	/**
	 * Recupera o valor de numCooperativa.
	 *
	 * @return o valor de numCooperativa
	 */
	public Integer getNumCooperativa() {
		return numCooperativa;
	}

	/**
	 * Define o valor de numCooperativa.
	 *
	 * @param numCooperativa o novo valor de numCooperativa
	 */
	public void setNumCooperativa(Integer numCooperativa) {
		this.numCooperativa = numCooperativa;
	}

}
