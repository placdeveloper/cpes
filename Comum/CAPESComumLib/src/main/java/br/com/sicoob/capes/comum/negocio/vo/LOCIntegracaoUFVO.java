/*
 * SICOOB
 * 
 * LOCIntegracaoUFVO.java(br.com.sicoob.capes.comum.negocio.vo.LOCIntegracaoUFVO)
 */
package br.com.sicoob.capes.comum.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * The Class LOCIntegracaoUFVO.
 */
public class LOCIntegracaoUFVO extends BancoobVo{
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -2672255254189613880L;

	/** O atributo sigla uf. */
	private String siglaUF;
	
	/** O atributo nome uf. */
	private String nomeUF;
	
	/** O atributo id uf. */
	private Integer idUF;

	/**
	 * Cria uma nova instância de LOC integracao ufvo.
	 */
	public LOCIntegracaoUFVO() {

	}

	/**
	 * Recupera sigla uf.
	 * 
	 * @return sigla uf
	 */
	public String getSiglaUF() {

		return this.siglaUF;
	}

	/**
	 * Preenche sigla uf.
	 * 
	 * @param siglaUF
	 *            o novo sigla uf
	 */
	public void setSiglaUF(String siglaUF) {

		this.siglaUF = siglaUF;
	}

	/**
	 * Recupera nome uf.
	 * 
	 * @return nome uf
	 */
	public String getNomeUF() {

		return this.nomeUF;
	}

	/**
	 * Preenche nome uf.
	 * 
	 * @param nomeUF
	 *            o novo nome uf
	 */
	public void setNomeUF(String nomeUF) {

		this.nomeUF = nomeUF;
	}

	/**
	 * Recupera id uf.
	 * 
	 * @return id uf
	 */
	public Integer getIdUF() {

		return this.idUF;
	}

	/**
	 * Preenche id uf.
	 * 
	 * @param idUF
	 *            o novo id uf
	 */
	public void setIdUF(Integer idUF) {

		this.idUF = idUF;
	}
}