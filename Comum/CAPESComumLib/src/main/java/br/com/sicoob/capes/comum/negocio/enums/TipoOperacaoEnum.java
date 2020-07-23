/*
 * SICOOB
 * 
 * TipoOperacaoEnum.java(br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum)
 */
package br.com.sicoob.capes.comum.negocio.enums;

import java.io.Serializable;

/**
 * The Enum TipoOperacaoEnum.
 */
public enum TipoOperacaoEnum implements Serializable {

	/**
	 * Tipo INCLUSÃO
	 */
	I("Inclusão"), 
	
	/**
	 * Tipo ALTERAÇÃO 
	 */
	A("Alteração"),
	
	/**
	 * Tipo EXCLUSÂO 
	 */
	E("Exclusão");

	/** O atributo descricao. */
	private String descricao;

	/**
	 * Cria uma nova instância de tipo operacao enum.
	 * 
	 * @param descricao
	 *            the descricao
	 */
	private TipoOperacaoEnum(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Recupera codigo.
	 * 
	 * @return codigo
	 */
	public Character getCodigo() {
		return name().charAt(0);
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
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return name() + " - " + descricao;
	}
}
