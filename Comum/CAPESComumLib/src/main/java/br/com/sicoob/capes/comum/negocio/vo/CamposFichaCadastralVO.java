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
public class CamposFichaCadastralVO extends BancoobVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** O atributo relacionamentos. */
	private Boolean relacionamentos;
	
	/** O atributo enderecos. */
	private Boolean enderecos;
	
	/** O atributo telefones. */
	private Boolean telefones;
	
	/** O atributo emails. */
	private Boolean emails;
	
	/** O atributo referencias. */
	private Boolean referencias;
	
	/** O atributo bens. */
	private Boolean bens;
	
	/** O atributo bens. */
	private Boolean bensNovo;
	
	/** O atributo certidoes. */
	private Boolean certidoes;
	
	/** O atributo produtores. */
	private Boolean produtores;
	
	/** O atributo fontes de renda. */
	private Boolean fontesDeRenda;
	
	/** O atributo autorizacao. */
	private Boolean autorizacao;
	
	/** O atributo atual. */
	private Boolean atual;
	
	/** O atributo posicao. */
	private Boolean posicao;
	
	/** O atributo data. */
	private Date data;
	
	/** O atributo dataFim. */
	private Date dataFim;
	
	/** O atributo historico. */
	private Boolean historico;
	
	/** O atributo historico. */
	private Boolean atualCompleto;
	
	/** O atributo posicao. */
	private Boolean historicoEm;
	
	/** O atributo emBranco. */
	private Boolean emBranco;
	
	/** O atributo tipo pessoa. */
	private Integer tipoPessoa;
	
	/** O atributo qtd relacionamentos. */
	private Integer qtdRelacionamentos;
	
	/** O atributo qtd enderecos. */
	private Integer qtdEnderecos;
	
	/** O atributo qtd telefones. */
	private Integer qtdTelefones;
	
	/** O atributo qtd emails. */
	private Integer qtdEmails;
	
	/** O atributo qtd referencias. */
	private Integer qtdReferencias;
	
	/** O atributo qtd bens. */
	private Integer qtdBens;
	
	/** O atributo qtd bens onus. */
	private Integer qtdBensOnus;
	
	/** O atributo qtd bens registro. */
	private Integer qtdBensRegistro;
	
	/** O atributo qtd bens posse. */
	private Integer qtdBensPosse;
	
	/** O atributo qtd certidoes. */
	private Integer qtdCertidoes;
	
	/** O atributo qtd produtores. */
	private Integer qtdProdutores;
	
	/** O atributo qtd produtividades. */
	private Integer qtdProdutividades;
	
	/** O atributo qtd fontes renda. */
	private Integer qtdFontesRenda;
	
	/** O atributo qtd bens imoveis. */
	private Integer qtdBensImoveis;
	
	/**
	 * Recupera relacionamentos.
	 * 
	 * @return relacionamentos
	 */
	public Boolean getRelacionamentos() {
		return relacionamentos;
	}
	
	/**
	 * Preenche relacionamentos.
	 * 
	 * @param relacionamentos
	 *            o novo relacionamentos
	 */
	public void setRelacionamentos(Boolean relacionamentos) {
		this.relacionamentos = relacionamentos;
	}
	
	/**
	 * Recupera enderecos.
	 * 
	 * @return enderecos
	 */
	public Boolean getEnderecos() {
		return enderecos;
	}
	
	/**
	 * Preenche enderecos.
	 * 
	 * @param enderecos
	 *            o novo enderecos
	 */
	public void setEnderecos(Boolean enderecos) {
		this.enderecos = enderecos;
	}
	
	/**
	 * Recupera telefones.
	 * 
	 * @return telefones
	 */
	public Boolean getTelefones() {
		return telefones;
	}
	
	/**
	 * Preenche telefones.
	 * 
	 * @param telefones
	 *            o novo telefones
	 */
	public void setTelefones(Boolean telefones) {
		this.telefones = telefones;
	}
	
	/**
	 * Recupera emails.
	 * 
	 * @return emails
	 */
	public Boolean getEmails() {
		return emails;
	}
	
	/**
	 * Preenche emails.
	 * 
	 * @param emails
	 *            o novo emails
	 */
	public void setEmails(Boolean emails) {
		this.emails = emails;
	}
	
	/**
	 * Recupera referencias.
	 * 
	 * @return referencias
	 */
	public Boolean getReferencias() {
		return referencias;
	}
	
	/**
	 * Preenche referencias.
	 * 
	 * @param referencias
	 *            o novo referencias
	 */
	public void setReferencias(Boolean referencias) {
		this.referencias = referencias;
	}
	
	/**
	 * Recupera bens.
	 * 
	 * @return bens
	 */
	public Boolean getBens() {
		return bens;
	}
	
	/**
	 * Preenche bens.
	 * 
	 * @param bens
	 *            o novo bens
	 */
	public void setBens(Boolean bens) {
		this.bens = bens;
	}
	
	/**
	 * Recupera certidoes.
	 * 
	 * @return certidoes
	 */
	public Boolean getCertidoes() {
		return certidoes;
	}
	
	/**
	 * Preenche certidoes.
	 * 
	 * @param certidoes
	 *            o novo certidoes
	 */
	public void setCertidoes(Boolean certidoes) {
		this.certidoes = certidoes;
	}
	
	/**
	 * Recupera produtores.
	 * 
	 * @return produtores
	 */
	public Boolean getProdutores() {
		return produtores;
	}
	
	/**
	 * Preenche produtores.
	 * 
	 * @param produtores
	 *            o novo produtores
	 */
	public void setProdutores(Boolean produtores) {
		this.produtores = produtores;
	}
	
	/**
	 * Recupera fontes de renda.
	 * 
	 * @return fontes de renda
	 */
	public Boolean getFontesDeRenda() {
		return fontesDeRenda;
	}
	
	/**
	 * Preenche fontes de renda.
	 * 
	 * @param fontesDeRenda
	 *            o novo fontes de renda
	 */
	public void setFontesDeRenda(Boolean fontesDeRenda) {
		this.fontesDeRenda = fontesDeRenda;
	}
	
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
	 * Recupera posicao.
	 * 
	 * @return posicao
	 */
	public Boolean getPosicao() {
		return posicao;
	}
	
	/**
	 * Preenche posicao.
	 * 
	 * @param posicao
	 *            o novo posicao
	 */
	public void setPosicao(Boolean posicao) {
		this.posicao = posicao;
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
	 * Recupera dataFim.
	 * 
	 * @return data
	 */
	public Date getDataFim() {
		return dataFim;
	}

	/**
	 * Preenche dataFim.
	 * 
	 * @param dataFim
	 *            o novo dataFim
	 */
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
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
	
	/**
	 * Recupera historico.
	 * 
	 * @return historico
	 */
	public Boolean getHistorico() {
		return historico;
	}
	
	/**
	 * Preenche historico.
	 * 
	 * @param historico
	 *            o novo historico
	 */
	public void setHistorico(Boolean historico) {
		this.historico = historico;
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
	
	/**
	 * Recupera qtd relacionamentos.
	 * 
	 * @return qtd relacionamentos
	 */
	public Integer getQtdRelacionamentos() {
		return qtdRelacionamentos;
	}
	
	/**
	 * Preenche qtd relacionamentos.
	 * 
	 * @param qtdRelacionamentos
	 *            o novo qtd relacionamentos
	 */
	public void setQtdRelacionamentos(Integer qtdRelacionamentos) {
		this.qtdRelacionamentos = qtdRelacionamentos;
	}
	
	/**
	 * Recupera qtd enderecos.
	 * 
	 * @return qtd enderecos
	 */
	public Integer getQtdEnderecos() {
		return qtdEnderecos;
	}
	
	/**
	 * Preenche qtd enderecos.
	 * 
	 * @param qtdEnderecos
	 *            o novo qtd enderecos
	 */
	public void setQtdEnderecos(Integer qtdEnderecos) {
		this.qtdEnderecos = qtdEnderecos;
	}
	
	/**
	 * Recupera qtd telefones.
	 * 
	 * @return qtd telefones
	 */
	public Integer getQtdTelefones() {
		return qtdTelefones;
	}
	
	/**
	 * Preenche qtd telefones.
	 * 
	 * @param qtdTelefones
	 *            o novo qtd telefones
	 */
	public void setQtdTelefones(Integer qtdTelefones) {
		this.qtdTelefones = qtdTelefones;
	}
	
	/**
	 * Recupera qtd emails.
	 * 
	 * @return qtd emails
	 */
	public Integer getQtdEmails() {
		return qtdEmails;
	}
	
	/**
	 * Preenche qtd emails.
	 * 
	 * @param qtdEmails
	 *            o novo qtd emails
	 */
	public void setQtdEmails(Integer qtdEmails) {
		this.qtdEmails = qtdEmails;
	}
	
	/**
	 * Recupera qtd referencias.
	 * 
	 * @return qtd referencias
	 */
	public Integer getQtdReferencias() {
		return qtdReferencias;
	}
	
	/**
	 * Preenche qtd referencias.
	 * 
	 * @param qtdReferencias
	 *            o novo qtd referencias
	 */
	public void setQtdReferencias(Integer qtdReferencias) {
		this.qtdReferencias = qtdReferencias;
	}
	
	/**
	 * Recupera qtd bens.
	 * 
	 * @return qtd bens
	 */
	public Integer getQtdBens() {
		return qtdBens;
	}
	
	/**
	 * Preenche qtd bens.
	 * 
	 * @param qtdBens
	 *            o novo qtd bens
	 */
	public void setQtdBens(Integer qtdBens) {
		this.qtdBens = qtdBens;
	}
	
	/**
	 * Recupera qtd bens onus.
	 * 
	 * @return qtd bens onus
	 */
	public Integer getQtdBensOnus() {
		return qtdBensOnus;
	}
	
	/**
	 * Preenche qtd bens onus.
	 * 
	 * @param qtdBensOnus
	 *            o novo qtd bens onus
	 */
	public void setQtdBensOnus(Integer qtdBensOnus) {
		this.qtdBensOnus = qtdBensOnus;
	}
	
	/**
	 * Recupera qtd bens registro.
	 * 
	 * @return qtd bens registro
	 */
	public Integer getQtdBensRegistro() {
		return qtdBensRegistro;
	}
	
	/**
	 * Preenche qtd bens registro.
	 * 
	 * @param qtdBensRegistro
	 *            o novo qtd bens registro
	 */
	public void setQtdBensRegistro(Integer qtdBensRegistro) {
		this.qtdBensRegistro = qtdBensRegistro;
	}
	
	/**
	 * Recupera qtd bens posse.
	 * 
	 * @return qtd bens posse
	 */
	public Integer getQtdBensPosse() {
		return qtdBensPosse;
	}
	
	/**
	 * Preenche qtd bens posse.
	 * 
	 * @param qtdBensPosse
	 *            o novo qtd bens posse
	 */
	public void setQtdBensPosse(Integer qtdBensPosse) {
		this.qtdBensPosse = qtdBensPosse;
	}
	
	/**
	 * Recupera qtd certidoes.
	 * 
	 * @return qtd certidoes
	 */
	public Integer getQtdCertidoes() {
		return qtdCertidoes;
	}
	
	/**
	 * Preenche qtd certidoes.
	 * 
	 * @param qtdCertidoes
	 *            o novo qtd certidoes
	 */
	public void setQtdCertidoes(Integer qtdCertidoes) {
		this.qtdCertidoes = qtdCertidoes;
	}
	
	/**
	 * Recupera qtd produtores.
	 * 
	 * @return qtd produtores
	 */
	public Integer getQtdProdutores() {
		return qtdProdutores;
	}
	
	/**
	 * Preenche qtd produtores.
	 * 
	 * @param qtdProdutores
	 *            o novo qtd produtores
	 */
	public void setQtdProdutores(Integer qtdProdutores) {
		this.qtdProdutores = qtdProdutores;
	}
	
	/**
	 * Recupera qtd produtividades.
	 * 
	 * @return qtd produtividades
	 */
	public Integer getQtdProdutividades() {
		return qtdProdutividades;
	}
	
	/**
	 * Preenche qtd produtividades.
	 * 
	 * @param qtdProdutividades
	 *            o novo qtd produtividades
	 */
	public void setQtdProdutividades(Integer qtdProdutividades) {
		this.qtdProdutividades = qtdProdutividades;
	}
	
	/**
	 * Recupera qtd fontes renda.
	 * 
	 * @return qtd fontes renda
	 */
	public Integer getQtdFontesRenda() {
		return qtdFontesRenda;
	}
	
	/**
	 * Preenche qtd fontes renda.
	 * 
	 * @param qtdFontesRenda
	 *            o novo qtd fontes renda
	 */
	public void setQtdFontesRenda(Integer qtdFontesRenda) {
		this.qtdFontesRenda = qtdFontesRenda;
	}
	
	/**
	 * Recupera qtd bens imoveis.
	 * 
	 * @return qtd bens imoveis
	 */
	public Integer getQtdBensImoveis() {
		return qtdBensImoveis;
	}
	
	/**
	 * Preenche qtd bens imoveis.
	 * 
	 * @param qtdBensImoveis
	 *            o novo qtd bens imoveis
	 */
	public void setQtdBensImoveis(Integer qtdBensImoveis) {
		this.qtdBensImoveis = qtdBensImoveis;
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

	public Boolean getEmBranco() {
		return emBranco;
	}

	public void setEmBranco(Boolean emBranco) {
		this.emBranco = emBranco;
	}

	public Boolean getBensNovo() {
		return bensNovo;
	}

	public void setBensNovo(Boolean bensNovo) {
		this.bensNovo = bensNovo;
	}
}