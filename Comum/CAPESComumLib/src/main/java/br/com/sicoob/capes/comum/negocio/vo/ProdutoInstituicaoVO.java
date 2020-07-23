/*
 * SICOOB
 * 
 * ProdutoInstituicaoVO.java(br.com.sicoob.capes.comum.negocio.vo.ProdutoInstituicaoVO)
 */
package br.com.sicoob.capes.comum.negocio.vo;

import java.util.Date;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * The Class ProdutoInstituicaoVO.
 */
public class ProdutoInstituicaoVO extends BancoobVo {
	
	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -5754237938639395182L;

	/** O atributo data atual movimento. */
	private Date dataAtualMovimento;
	
	/** O atributo data proximo movimento. */
	private Date dataProximoMovimento;

	/**
	 * Recupera data atual movimento.
	 * 
	 * @return data atual movimento
	 */
	public Date getDataAtualMovimento() {
		return this.dataAtualMovimento;
	}

	/**
	 * Preenche data atual movimento.
	 * 
	 * @param dataAtualMovimento
	 *            o novo data atual movimento
	 */
	public void setDataAtualMovimento(Date dataAtualMovimento) {
		this.dataAtualMovimento = dataAtualMovimento;
	}

	/**
	 * Recupera data proximo movimento.
	 * 
	 * @return data proximo movimento
	 */
	public Date getDataProximoMovimento() {
		return this.dataProximoMovimento;
	}

	/**
	 * Preenche data proximo movimento.
	 * 
	 * @param dataProximoMovimento
	 *            o novo data proximo movimento
	 */
	public void setDataProximoMovimento(Date dataProximoMovimento) {
		this.dataProximoMovimento = dataProximoMovimento;
	}

}
