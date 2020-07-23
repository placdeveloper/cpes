/*
 * SICOOB
 * 
 * GrupoCompartilhamentoVO.java(br.com.sicoob.capes.comum.negocio.vo.GrupoCompartilhamentoVO)
 */
package br.com.sicoob.capes.comum.negocio.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class GrupoCompartilhamentoVO.
 */
public class GrupoCompartilhamentoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** O atributo uf. */
	private String uf;
	
	/** O atributo num cnpj. */
	private String numCNPJ;
	
	/** O atributo id grupo compartilhamento. */
	private Integer idGrupoCompartilhamento;	
	
	/** O atributo data hora inicial. */
	private Date dataHoraInicial;
	
	/** O atributo descricao. */
	private String descricao;
	
	/** O atributo codigo. */
	private Short codigo;
	
	/** O atributo selecionado. */
	private Boolean selecionado=false;
	
	//Instituicao
	/** O atributo id instituicao. */
	private Integer idInstituicao;
	
	/** O atributo nome instituicao. */
	private String nomeInstituicao;
	
	/** O atributo sigla instituicao. */
	private String siglaInstituicao;	
	
	/** O atributo desc instituicao. */
	private String descInstituicao;
	
	/** O atributo numero. */
	private String numero;
	
	/** O atributo integracao srf. */
	private Boolean integracaoSrf = false;
	
	/**
	 * Recupera numero.
	 * 
	 * @return numero
	 */
	public String getNumero() {
		return numero;
	}
	
	/**
	 * Preenche numero.
	 * 
	 * @param numero
	 *            o novo numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	/**
	 * Recupera desc instituicao.
	 * 
	 * @return desc instituicao
	 */
	public String getDescInstituicao() {
		return descInstituicao;
	}
	
	/**
	 * Preenche desc instituicao.
	 * 
	 * @param descInstituicao
	 *            o novo desc instituicao
	 */
	public void setDescInstituicao(String descInstituicao) {
		this.descInstituicao = descInstituicao;
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
	 * Recupera nome instituicao.
	 * 
	 * @return nome instituicao
	 */
	public String getNomeInstituicao() {
		return nomeInstituicao;
	}
	
	/**
	 * Preenche nome instituicao.
	 * 
	 * @param nomeInstituicao
	 *            o novo nome instituicao
	 */
	public void setNomeInstituicao(String nomeInstituicao) {
		this.nomeInstituicao = nomeInstituicao;
	}
	
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
	 * Recupera codigo.
	 * 
	 * @return codigo
	 */
	public Short getCodigo() {
		return codigo;
	}
	
	/**
	 * Preenche codigo.
	 * 
	 * @param codCompartilhamentoCadastro
	 *            o novo codigo
	 */
	public void setCodigo(
			Short codCompartilhamentoCadastro) {
		this.codigo = codCompartilhamentoCadastro;
	}
	
	/**
	 * Recupera descricao.
	 * 
	 * @return descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	
	/**
	 * Preenche descricao.
	 * 
	 * @param descCompartilhamentoCadastro
	 *            o novo descricao
	 */
	public void setDescricao(
			String descCompartilhamentoCadastro) {
		this.descricao = descCompartilhamentoCadastro;
	}
	
	/**
	 * Recupera data hora inicial.
	 * 
	 * @return data hora inicial
	 */
	public Date getDataHoraInicial() {
		return dataHoraInicial;
	}
	
	/**
	 * Preenche data hora inicial.
	 * 
	 * @param dataHoraInicial
	 *            o novo data hora inicial
	 */
	public void setDataHoraInicial(Date dataHoraInicial) {
		this.dataHoraInicial = dataHoraInicial;
	}
	
	/**
	 * Recupera id grupo compartilhamento.
	 * 
	 * @return id grupo compartilhamento
	 */
	public Integer getIdGrupoCompartilhamento() {
		return idGrupoCompartilhamento;
	}
	
	/**
	 * Preenche id grupo compartilhamento.
	 * 
	 * @param idGrupoCompartilhamento
	 *            o novo id grupo compartilhamento
	 */
	public void setIdGrupoCompartilhamento(Integer idGrupoCompartilhamento) {
		this.idGrupoCompartilhamento = idGrupoCompartilhamento;
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
	 * Recupera uf.
	 * 
	 * @return uf
	 */
	public String getUf() {
		return uf;
	}
	
	/**
	 * Preenche uf.
	 * 
	 * @param uf
	 *            o novo uf
	 */
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	/**
	 * Recupera selecionado.
	 * 
	 * @return selecionado
	 */
	public Boolean getSelecionado() {
		return selecionado;
	}
	
	/**
	 * Preenche selecionado.
	 * 
	 * @param selecionado
	 *            o novo selecionado
	 */
	public void setSelecionado(Boolean selecionado) {
		this.selecionado = selecionado;
	}
	
	/**
	 * Recupera integracao srf.
	 * 
	 * @return integracao srf
	 */
	public Boolean getIntegracaoSrf() {
		return integracaoSrf;
	}
	
	/**
	 * Preenche integracao srf.
	 * 
	 * @param integracaoSrf
	 *            o novo integracao srf
	 */
	public void setIntegracaoSrf(Boolean integracaoSrf) {
		this.integracaoSrf = integracaoSrf;
	}
	
}
