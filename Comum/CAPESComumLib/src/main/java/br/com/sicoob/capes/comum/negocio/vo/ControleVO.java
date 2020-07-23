/*
 * SICOOB
 * 
 * ControleVO.java(br.com.sicoob.capes.comum.negocio.vo.ControleVO)
 */
package br.com.sicoob.capes.comum.negocio.vo;

import java.io.Serializable;

/**
 * TODO javadoc.
 *
 * @author Rodrigo.Chaves
 */
public class ControleVO implements Serializable {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 868870964081381634L;

	/** O atributo codigo. */
	private Integer codigo;

	/** O atributo descricao. */
	private String descricao;

	/**
	 * Construtor.
	 */
	public ControleVO() {
	}

	/**
	 * Construtor.
	 *
	 * @param codigo
	 *            o valor de codigo
	 * @param descricao
	 *            o valor de descricao
	 */
	public ControleVO(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
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
	 * Recupera codigo.
	 * 
	 * @return codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * Preenche codigo.
	 * 
	 * @param codigo
	 *            o novo codigo
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName() + "#" + getCodigo() + "=" + getDescricao();
	}
}
