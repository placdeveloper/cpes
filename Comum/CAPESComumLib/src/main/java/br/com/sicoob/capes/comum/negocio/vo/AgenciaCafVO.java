/*
 * SICOOB
 * 
 * AgenciaCafVO.java(br.com.sicoob.capes.comum.negocio.vo.AgenciaCafVO)
 */
package br.com.sicoob.capes.comum.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * The Class AgenciaCafVO.
 */
public class AgenciaCafVO extends BancoobVo {
	
	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 9042115478709407498L;

	/** O atributo descricao agencia. */
	private String descricaoAgencia;

	/**
	 * Recupera descricao agencia.
	 * 
	 * @return descricao agencia
	 */
	public String getDescricaoAgencia() {
		return this.descricaoAgencia;
	}

	/**
	 * Preenche descricao agencia.
	 * 
	 * @param descricaoAgencia
	 *            o novo descricao agencia
	 */
	public void setDescricaoAgencia(String descricaoAgencia) {
		this.descricaoAgencia = descricaoAgencia;
	}

}
