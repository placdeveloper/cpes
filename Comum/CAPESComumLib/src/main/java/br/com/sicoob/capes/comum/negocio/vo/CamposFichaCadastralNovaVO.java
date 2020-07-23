/*
 * SICOOB
 * 
 * CamposFichaCadastralVO.java(br.com.sicoob.capes.comum.negocio.vo.CamposFichaCadastralVO)
 */
package br.com.sicoob.capes.comum.negocio.vo;

import java.io.Serializable;
import java.util.Date;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * The Class CamposFichaCadastralVO.
 */
public class CamposFichaCadastralNovaVO extends BancoobVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** O atributo atual. */
	private Boolean atual;
	
	/** O atributo data. */
	private Date data;
	
	/** O atributo historico. */
	private Boolean atualCompleto;
	
	/** O atributo posicao. */
	private Boolean historicoEm;
	
	/** O atributo emBranco. */
	private Boolean emBranco;
	
	/** O atributo autorizacao. */
	private Boolean autorizacao;
	
	private Boolean bens;
	
	private Boolean certidoes;
	
	private Boolean emails;
	
	private Boolean enderecos;
	
	private Boolean produtores;
	
	private Boolean fontesDeRenda;
	
	private Boolean referencias;
	
	private Boolean relacionamentos;
	
	private Boolean telefones;
	
	/** O atributo tipo pessoa. */
	private Integer tipoPessoa;
	
	/**
	 * Recupera atual.
	 * 
	 * @return atual
	 */
	public Boolean getAtual() {
		return atual;
	}
	
	/**
	 * Preenche atual.
	 * 
	 * @param atual
	 *            o novo atual
	 */
	public void setAtual(Boolean atual) {
		this.atual = atual;
	}
	
	/**
	 * Recupera data.
	 * 
	 * @return data
	 */
	public Date getData() {
		return data;
	}
	
	/**
	 * Preenche data.
	 * 
	 * @param data
	 *            o novo data
	 */
	public void setData(Date data) {
		this.data = data;
	}

	public Boolean getAtualCompleto() {
		return atualCompleto;
	}

	public void setAtualCompleto(Boolean atualCompleto) {
		this.atualCompleto = atualCompleto;
	}
	
	public Boolean getHistoricoEm() {
		return historicoEm;
	}

	public void setHistoricoEm(Boolean historicoEm) {
		this.historicoEm = historicoEm;
	}
	
	/**
	 * Recupera emBranco.
	 * 
	 * @return emBranco
	 */
	public Boolean getEmBranco() {
		return emBranco;
	}
	
	/**
	 * Preenche emBranco.
	 * 
	 * @param emBranco
	 *            o novo emBranco.
	 */
	public void setEmBranco(Boolean emBranco) {
		this.emBranco = emBranco;
	}
	
	/**
	 * Recupera autorizacao.
	 * 
	 * @return autorizacao
	 */
	public Boolean getAutorizacao() {
		return autorizacao;
	}
	
	/**
	 * Preenche autorizacao.
	 * 
	 * @param autorizacao
	 *            o novo autorizacao
	 */
	public void setAutorizacao(Boolean autorizacao) {
		this.autorizacao = autorizacao;
	}

	public Boolean getBens() {
		return bens;
	}

	public void setBens(Boolean bens) {
		this.bens = bens;
	}

	public Boolean getCertidoes() {
		return certidoes;
	}

	public void setCertidoes(Boolean certidoes) {
		this.certidoes = certidoes;
	}

	public Boolean getEmails() {
		return emails;
	}

	public void setEmails(Boolean emails) {
		this.emails = emails;
	}

	public Boolean getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Boolean enderecos) {
		this.enderecos = enderecos;
	}

	public Boolean getProdutores() {
		return produtores;
	}

	public void setProdutores(Boolean produtores) {
		this.produtores = produtores;
	}

	public Boolean getFontesDeRenda() {
		return fontesDeRenda;
	}

	public void setFontesDeRenda(Boolean fontesDeRenda) {
		this.fontesDeRenda = fontesDeRenda;
	}

	public Boolean getReferencias() {
		return referencias;
	}

	public void setReferencias(Boolean referencias) {
		this.referencias = referencias;
	}

	public Boolean getRelacionamentos() {
		return relacionamentos;
	}

	public void setRelacionamentos(Boolean relacionamentos) {
		this.relacionamentos = relacionamentos;
	}

	public Boolean getTelefones() {
		return telefones;
	}

	public void setTelefones(Boolean telefones) {
		this.telefones = telefones;
	}
	
	/**
	 * Recupera tipo pessoa.
	 * 
	 * @return tipo pessoa
	 */
	public Integer getTipoPessoa() {
		return tipoPessoa;
	}
	
	/**
	 * Preenche tipo pessoa.
	 * 
	 * @param tipoPessoa
	 *            o novo tipo pessoa
	 */
	public void setTipoPessoa(Integer tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}
	
}