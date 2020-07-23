/*
 * SICOOB
 * 
 * TipoDocumentoVO.java(br.com.sicoob.capes.comum.negocio.vo.TipoDocumentoVO)
 */
package br.com.sicoob.capes.comum.negocio.vo;

import java.io.Serializable;

/**
 * VO que representa o Tipo Documento do GED.
 * @author juan.damasceno
 *
 */
public class TipoDocumentoVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** O atributo id. */
	private Integer id;
	
	/** O atributo nome. */
	private String nome;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
}
