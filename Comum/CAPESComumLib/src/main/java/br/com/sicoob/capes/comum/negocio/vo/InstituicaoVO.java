/*
 * SICOOB
 * 
 * InstituicaoVO.java(br.com.sicoob.capes.comum.negocio.vo.InstituicaoVO)
 */
package br.com.sicoob.capes.comum.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * @author Erico.Junior
 *
 */
public class InstituicaoVO extends BancoobVo {

	/** Serial UID.*/
	private static final long serialVersionUID = 7627604204638589445L;

	/** O atributo id instituicao. */
	private Integer idInstituicao;
	
	/** O atributo numero. */
	private Integer numero;
	
	/** O atributo nome. */
	private String nome;
	
	/** O atributo sigla instituicao. */
	private String siglaInstituicao;
	
	/** O atributo codigo tipo instituicao. */
	private Integer codigoTipoInstituicao;
	
	/** O atributo codigo situacao inst. */
	private Integer codigoSituacaoInst;
	
	/** O atributo cnpj. */
	private String cnpj;
	
	/**
	 * @return the idInstituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}
	/**
	 * @param idInstituicao the idInstituicao to set
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	/**
	 * @return the numero
	 */
	public Integer getNumero() {
		return numero;
	}
	/**
	 * @param numero the numero to set
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
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
	
	/**
	 * Recupera sigla instituicao.
	 * 
	 * @return sigla instituicao
	 */
	public String getSiglaInstituicao() {
		return siglaInstituicao;
	}
	
	/**
	 * Preenche sigla instituicao.
	 * 
	 * @param siglaInstituicao
	 *            o novo sigla instituicao
	 */
	public void setSiglaInstituicao(String siglaInstituicao) {
		this.siglaInstituicao = siglaInstituicao;
	}
	
	/**
	 * Recupera codigo tipo instituicao.
	 * 
	 * @return codigo tipo instituicao
	 */
	public Integer getCodigoTipoInstituicao() {
		return codigoTipoInstituicao;
	}
	
	/**
	 * Preenche codigo tipo instituicao.
	 * 
	 * @param codigoTipoInstituicao
	 *            o novo codigo tipo instituicao
	 */
	public void setCodigoTipoInstituicao(Integer codigoTipoInstituicao) {
		this.codigoTipoInstituicao = codigoTipoInstituicao;
	}
	
	/**
	 * Recupera codigo situacao inst.
	 * 
	 * @return codigo situacao inst
	 */
	public Integer getCodigoSituacaoInst() {
		return codigoSituacaoInst;
	}
	
	/**
	 * Preenche codigo situacao inst.
	 * 
	 * @param codigoSituacaoInst
	 *            o novo codigo situacao inst
	 */
	public void setCodigoSituacaoInst(Integer codigoSituacaoInst) {
		this.codigoSituacaoInst = codigoSituacaoInst;
	}

	/**
	 * Recupera cnpj da instituicao
	 * @return cnpj
	 */
	public String getCnpj() {
		return cnpj;
	}

	/**
	 * Preenche cnpj da instituicao.
	 * @param cnpj
	 */
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

}