/*
 * SICOOB
 * 
 * AnotacaoDTO.java(br.com.sicoob.capes.comum.negocio.dto.AnotacaoDTO)
 */
package br.com.sicoob.capes.comum.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * The Class AnotacaoDTO.
 */
public class AnotacaoDTO extends BancoobDto {
	
	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 101346651345343609L;
	
	/** O atributo id instituicao. */
	private Integer idInstituicao;
	
	/** O atributo id unidade inst. */
	private Integer idUnidadeInst;
	
	/** O atributo observacao. */
	private String observacao;

	/**
	 * Recupera id instituicao.
	 * 
	 * @return id instituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * Preenche id instituicao.
	 * 
	 * @param idInstituicao
	 *            o novo id instituicao
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	/**
	 * Recupera id unidade inst.
	 * 
	 * @return id unidade inst
	 */
	public Integer getIdUnidadeInst() {
		return idUnidadeInst;
	}

	/**
	 * Preenche id unidade inst.
	 * 
	 * @param idUnidadeInst
	 *            o novo id unidade inst
	 */
	public void setIdUnidadeInst(Integer idUnidadeInst) {
		this.idUnidadeInst = idUnidadeInst;
	}

	/**
	 * Recupera observacao.
	 * 
	 * @return observacao
	 */
	public String getObservacao() {
		return observacao;
	}

	/**
	 * Preenche observacao.
	 * 
	 * @param observacao
	 *            o novo observacao
	 */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}