/*
 * SICOOB
 * 
 * ModalidadeProdutoVO.java(br.com.sicoob.capes.comum.negocio.vo.ModalidadeProdutoVO)
 */
package br.com.sicoob.capes.comum.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * The Class ModalidadeProdutoVO.
 */
public class ModalidadeProdutoVO extends BancoobVo {
	
	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -7849975499686316495L;

	/** O atributo descricao modalidade produto. */
	private String descricaoModalidadeProduto;

	/**
	 * Recupera descricao modalidade produto.
	 * 
	 * @return descricao modalidade produto
	 */
	public String getDescricaoModalidadeProduto() {
		return this.descricaoModalidadeProduto;
	}

	/**
	 * Preenche descricao modalidade produto.
	 * 
	 * @param descricaoModalidadeProduto
	 *            o novo descricao modalidade produto
	 */
	public void setDescricaoModalidadeProduto(String descricaoModalidadeProduto) {
		this.descricaoModalidadeProduto = descricaoModalidadeProduto;
	}

}
