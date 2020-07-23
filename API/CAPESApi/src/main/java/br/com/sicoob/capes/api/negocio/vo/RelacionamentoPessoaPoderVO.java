/*
 * SICOOB
 * 
 * RelacionamentoPessoaPoderVO.java(br.com.sicoob.capes.api.negocio.vo.RelacionamentoPessoaPoderVO)
 */
package br.com.sicoob.capes.api.negocio.vo;

import java.io.Serializable;


/**
 * @author marcelo.onofre
 *
 */
public class RelacionamentoPessoaPoderVO implements Serializable{
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 3412398661316704566L;

	/** O atributo codigo poder. */
	private Short codigoPoder;

	/** O atributo descricao poder. */
	private String descricaoPoder;
	
	/**
	 * Cria uma nova instância de relacionamento pessoa poder vo.
	 */
	public RelacionamentoPessoaPoderVO() {

	}
	
	/**
	 * Cria uma nova instância de relacionamento pessoa poder vo.
	 * 
	 * @param codigoPoder
	 *            the codigo poder
	 * @param descricaoPoder
	 *            the descricao poder
	 */
	public RelacionamentoPessoaPoderVO(Short codigoPoder, String descricaoPoder) {
		this.codigoPoder = codigoPoder;
		this.descricaoPoder = descricaoPoder;		
	}

	/**
	 * Recupera codigo poder.
	 * 
	 * @return codigo poder
	 */
	public Short getCodigoPoder() {
		return codigoPoder;
	}

	/**
	 * Preenche codigo poder.
	 * 
	 * @param codigoPoder
	 *            o novo codigo poder
	 */
	public void setCodigoPoder(Short codigoPoder) {
		this.codigoPoder = codigoPoder;
	}

	/**
	 * Recupera descricao poder.
	 * 
	 * @return descricao poder
	 */
	public String getDescricaoPoder() {
		return descricaoPoder;
	}

	/**
	 * Preenche descricao poder.
	 * 
	 * @param descricaoPoder
	 *            o novo descricao poder
	 */
	public void setDescricaoPoder(String descricaoPoder) {
		this.descricaoPoder = descricaoPoder;
	}
	
}