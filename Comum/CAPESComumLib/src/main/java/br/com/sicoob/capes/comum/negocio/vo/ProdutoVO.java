/*
 * SICOOB
 * 
 * ProdutoVO.java(br.com.sicoob.capes.comum.negocio.vo.ProdutoVO)
 */
package br.com.sicoob.capes.comum.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * The Class ProdutoVO.
 */
public class ProdutoVO extends BancoobVo{
	
	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -6498944613396379059L;

	/** O atributo descricao produto. */
	private String descricaoProduto;

	/**
	 * Recupera descricao produto.
	 * 
	 * @return descricao produto
	 */
	public String getDescricaoProduto() {
		return this.descricaoProduto;
	}

	/**
	 * Preenche descricao produto.
	 * 
	 * @param descricaoProduto
	 *            o novo descricao produto
	 */
	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

}
