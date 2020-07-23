/*
 * SICOOB
 * 
 * UnidadeInstituicaoVO.java(br.com.sicoob.capes.comum.negocio.vo.UnidadeInstituicaoVO)
 */
package br.com.sicoob.capes.comum.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * The Class UnidadeInstituicaoVO.
 */
public class UnidadeInstituicaoVO extends BancoobVo {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -1941809955773724623L;

	/** O atributo id. */
	private Integer id;
	
	/** O atributo descricao. */
	private String descricao;
	
	/** O atributo id instituicao. */
	private Integer idInstituicao;
	
	/** O atributo num cnpj. */
	private String numCNPJ;
	
	/** O atributo sigla unidade. */
	private String siglaUnidade;
	
	/** O atributo nome unidade. */
	private String nomeUnidade;
	
	/** O atributo id unidade inst. */
	private Integer idUnidadeInst;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the idInstituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * @param idInstituicao
	 *            the idInstituicao to set
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	/**
	 * Recupera num cnpj.
	 * 
	 * @return num cnpj
	 */
	public String getNumCNPJ() {
		return numCNPJ;
	}

	/**
	 * Preenche num cnpj.
	 * 
	 * @param numCNPJ
	 *            o novo num cnpj
	 */
	public void setNumCNPJ(String numCNPJ) {
		this.numCNPJ = numCNPJ;
	}

	/**
	 * Recupera sigla unidade.
	 * 
	 * @return sigla unidade
	 */
	public String getSiglaUnidade() {
		return siglaUnidade;
	}

	/**
	 * Preenche sigla unidade.
	 * 
	 * @param siglaUnidade
	 *            o novo sigla unidade
	 */
	public void setSiglaUnidade(String siglaUnidade) {
		this.siglaUnidade = siglaUnidade;
	}

	/**
	 * Recupera nome unidade.
	 * 
	 * @return nome unidade
	 */
	public String getNomeUnidade() {
		return nomeUnidade;
	}

	/**
	 * Preenche nome unidade.
	 * 
	 * @param nomeUnidade
	 *            o novo nome unidade
	 */
	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade;
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
}