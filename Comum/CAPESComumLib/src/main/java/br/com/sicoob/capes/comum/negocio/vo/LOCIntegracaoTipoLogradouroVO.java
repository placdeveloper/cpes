/*
 * SICOOB
 * 
 * LOCIntegracaoTipoLogradouroVO.java(br.com.sicoob.capes.comum.negocio.vo.LOCIntegracaoTipoLogradouroVO)
 */
package br.com.sicoob.capes.comum.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * The Class LOCIntegracaoTipoLogradouroVO.
 */
public class LOCIntegracaoTipoLogradouroVO extends BancoobVo {

	/** Serial UID */
	private static final long serialVersionUID = 6771950011787494220L;

	/** O atributo nome. */
	private String nome;
	
	/** O atributo id tipo logradouro. */
	private Integer idTipoLogradouro;

	/**
	 * Cria uma nova instância de LOC integracao tipo logradouro vo.
	 */
	public LOCIntegracaoTipoLogradouroVO() {

	}

	/**
	 * Cria uma nova instância de LOC integracao tipo logradouro vo.
	 * 
	 * @param idTipoLogradouro
	 *            the id tipo logradouro
	 * @param nome
	 *            the nome
	 */
	public LOCIntegracaoTipoLogradouroVO(Integer idTipoLogradouro, String nome) {

		this.idTipoLogradouro = idTipoLogradouro;
		this.nome = nome;
	}

	/**
	 * Recupera nome.
	 * 
	 * @return nome
	 */
	public String getNome() {

		return this.nome;
	}

	/**
	 * Preenche nome.
	 * 
	 * @param nome
	 *            o novo nome
	 */
	public void setNome(String nome) {

		this.nome = nome;
	}

	/**
	 * Recupera id tipo logradouro.
	 * 
	 * @return id tipo logradouro
	 */
	public Integer getIdTipoLogradouro() {

		return this.idTipoLogradouro;
	}

	/**
	 * Preenche id tipo logradouro.
	 * 
	 * @param idTipoLogradouro
	 *            o novo id tipo logradouro
	 */
	public void setIdTipoLogradouro(Integer idTipoLogradouro) {

		this.idTipoLogradouro = idTipoLogradouro;
	}
}
