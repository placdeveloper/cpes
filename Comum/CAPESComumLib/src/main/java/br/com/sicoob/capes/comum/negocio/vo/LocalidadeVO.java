/*
 * SICOOB
 * 
 * LocalidadeVO.java(br.com.sicoob.capes.comum.negocio.vo.LocalidadeVO)
 */
package br.com.sicoob.capes.comum.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * The Class LocalidadeVO.
 */
public class LocalidadeVO extends BancoobVo {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 845464242661464919L;

	/** O atributo num cep. */
	private String numCep;
	
	/** O atributo id localidade. */
	private Integer idLocalidade;
	
	/** O atributo id localidade pai. */
	private Integer idLocalidadePai;
	
	/** O atributo nome logradouro. */
	private String nomeLogradouro;
	
	/** O atributo desc complemento logradouro. */
	private String descComplementoLogradouro;
	
	/** O atributo nome bairro. */
	private String nomeBairro;
	
	/** O atributo nome localidade. */
	private String nomeLocalidade;
	
	/** O atributo sigla uf. */
	private String siglaUF;
	
	/** O atributo idTipoLogradouro */
	private Integer idTipoLogradouro;
	
	/** O atributo idLogradouro */
	private Integer idLogradouro;

	/**
	 * Recupera desc complemento logradouro.
	 * 
	 * @return desc complemento logradouro
	 */
	public String getDescComplementoLogradouro() {
		return descComplementoLogradouro;
	}

	/**
	 * Recupera id localidade.
	 * 
	 * @return id localidade
	 */
	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	/**
	 * Recupera id localidade pai.
	 * 
	 * @return id localidade pai
	 */
	public Integer getIdLocalidadePai() {
		return idLocalidadePai;
	}

	/**
	 * Recupera nome bairro.
	 * 
	 * @return nome bairro
	 */
	public String getNomeBairro() {
		return nomeBairro;
	}

	/**
	 * Recupera nome localidade.
	 * 
	 * @return nome localidade
	 */
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	/**
	 * Recupera nome logradouro.
	 * 
	 * @return nome logradouro
	 */
	public String getNomeLogradouro() {
		return nomeLogradouro;
	}

	/**
	 * Recupera num cep.
	 * 
	 * @return num cep
	 */
	public String getNumCep() {
		return numCep;
	}

	/**
	 * Recupera sigla uf.
	 * 
	 * @return sigla uf
	 */
	public String getSiglaUF() {
		return siglaUF;
	}

	/**
	 * Preenche desc complemento logradouro.
	 * 
	 * @param descComplementoLogradouro
	 *            o novo desc complemento logradouro
	 */
	public void setDescComplementoLogradouro(String descComplementoLogradouro) {
		this.descComplementoLogradouro = descComplementoLogradouro;
	}

	/**
	 * Preenche id localidade.
	 * 
	 * @param idLocalidade
	 *            o novo id localidade
	 */
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	/**
	 * Preenche id localidade pai.
	 * 
	 * @param idLocalidadePai
	 *            o novo id localidade pai
	 */
	public void setIdLocalidadePai(Integer idLocalidadePai) {
		this.idLocalidadePai = idLocalidadePai;
	}

	/**
	 * Preenche nome bairro.
	 * 
	 * @param nomeBairro
	 *            o novo nome bairro
	 */
	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}

	/**
	 * Preenche nome localidade.
	 * 
	 * @param nomeLocalidade
	 *            o novo nome localidade
	 */
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	/**
	 * Preenche nome logradouro.
	 * 
	 * @param nomeLogradouro
	 *            o novo nome logradouro
	 */
	public void setNomeLogradouro(String nomeLogradouro) {
		this.nomeLogradouro = nomeLogradouro;
	}

	/**
	 * Preenche num cep.
	 * 
	 * @param numCep
	 *            o novo num cep
	 */
	public void setNumCep(String numCep) {
		this.numCep = numCep;
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
	 * @return the idTipoLogradouro
	 */
	public Integer getIdTipoLogradouro() {
		return idTipoLogradouro;
	}

	/**
	 * @param idTipoLogradouro the idTipoLogradouro to set
	 */
	public void setIdTipoLogradouro(Integer idTipoLogradouro) {
		this.idTipoLogradouro = idTipoLogradouro;
	}

	/**
	 * Recupera o atributo idLogradouro
	 * 
	 * @return o idLogradouro
	 */
	public Integer getIdLogradouro() {
		return idLogradouro;
	}

	/**
	 * Preenche o atributo idLogradouro
	 * 
	 * @param idLogradouro
	 *            o idLogradouro
	 */
	public void setIdLogradouro(Integer idLogradouro) {
		this.idLogradouro = idLogradouro;
	}

}