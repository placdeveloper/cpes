/*
 * SICOOB
 * 
 * CertidaoPessoaVO.java(br.com.sicoob.capes.api.negocio.vo.CertidaoPessoaVO)
 */
package br.com.sicoob.capes.api.negocio.vo;

import java.util.Date;

/**
 * @author Lucas.Borges
 */
public class CertidaoPessoaVO extends AbstractPessoaVO{

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 3325627723868774025L;

	// CERTIDAO PESSOA
	/** O atributo id certidao. */
	private Long idCertidao;

	/** O atributo data hora inicio. */
	private Date dataHoraInicio;
	
	/** O atributo numero. */
	private String numero;
	
	/** O atributo numero crea. */
	private String numeroCrea;

	/** O atributo observacao. */
	private String observacao;
	
	/** O atributo data emissao. */
	private Date dataEmissao;
	
	/** O atributo data vencimento. */
	private Date dataVencimento;
	
	// TIPO CERTIDAO
	/** O atributo codigo tipo certidao. */
	private Short codigoTipoCertidao;
	
	/** O atributo sigla tipo certidao. */
	private String siglaTipoCertidao;
	
	/** O atributo nome tipo certidao. */
	private String nomeTipoCertidao;
	
	// SUB TIPO CERTIDAO
	/** O atributo codigo sub tipo certidao. */
	private Short codigoSubTipoCertidao;
	
	/** O atributo descricao sub tipo certidao. */
	private String descricaoSubTipoCertidao;
	
	/**
	 * Cria uma nova instância de certidao pessoa vo.
	 */
	public CertidaoPessoaVO() {

	}
	
	/**
	 * Cria uma nova instância de certidao pessoa vo.
	 * 
	 * @param idCertidao
	 *            the id certidao
	 * @param dataHoraInicio
	 *            the data hora inicio
	 * @param numero
	 *            the numero
	 * @param numeroCrea
	 *            the numero crea
	 * @param observacao
	 *            the observacao
	 * @param dataEmissao
	 *            the data emissao
	 * @param dataVencimento
	 *            the data vencimento
	 * @param codigoTipoCertidao
	 *            the codigo tipo certidao
	 * @param siglaTipoCertidao
	 *            the sigla tipo certidao
	 * @param nomeTipoCertidao
	 *            the nome tipo certidao
	 */
	public CertidaoPessoaVO(Long idCertidao, Date dataHoraInicio, String numero, 
			String numeroCrea, String observacao, Date dataEmissao, Date dataVencimento, 
			Short codigoTipoCertidao, String siglaTipoCertidao, String nomeTipoCertidao, 
			Short codigoSubTipoCertidao, String descricaoSubTipoCertidao) {
		this.idCertidao = idCertidao;
		this.dataHoraInicio = dataHoraInicio;
		this.numero = numero;
		this.numeroCrea = numeroCrea;
		this.observacao = observacao;
		this.dataEmissao = dataEmissao;
		this.dataVencimento = dataVencimento;
		this.codigoTipoCertidao = codigoTipoCertidao;
		this.siglaTipoCertidao = siglaTipoCertidao;
		this.nomeTipoCertidao = nomeTipoCertidao;
		this.codigoSubTipoCertidao = codigoSubTipoCertidao;
		this.descricaoSubTipoCertidao = descricaoSubTipoCertidao;
	}

	/**
	 * Recupera id certidao.
	 * 
	 * @return id certidao
	 */
	public Long getIdCertidao() {
		return idCertidao;
	}

	/**
	 * Preenche id certidao.
	 * 
	 * @param idCertidao
	 *            o novo id certidao
	 */
	public void setIdCertidao(Long idCertidao) {
		this.idCertidao = idCertidao;
	}

	/**
	 * Recupera data hora inicio.
	 * 
	 * @return data hora inicio
	 */
	public Date getDataHoraInicio() {
		return dataHoraInicio;
	}

	/**
	 * Preenche data hora inicio.
	 * 
	 * @param dataHoraInicio
	 *            o novo data hora inicio
	 */
	public void setDataHoraInicio(Date dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

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

	/**
	 * Recupera data emissao.
	 * 
	 * @return data emissao
	 */
	public Date getDataEmissao() {
		return dataEmissao;
	}

	/**
	 * Preenche data emissao.
	 * 
	 * @param dataEmissao
	 *            o novo data emissao
	 */
	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	/**
	 * Recupera data vencimento.
	 * 
	 * @return data vencimento
	 */
	public Date getDataVencimento() {
		return dataVencimento;
	}

	/**
	 * Preenche data vencimento.
	 * 
	 * @param dataVencimento
	 *            o novo data vencimento
	 */
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	/**
	 * Recupera numero crea.
	 * 
	 * @return numero crea
	 */
	public String getNumeroCrea() {
		return numeroCrea;
	}

	/**
	 * Preenche numero crea.
	 * 
	 * @param numeroCrea
	 *            o novo numero crea
	 */
	public void setNumeroCrea(String numeroCrea) {
		this.numeroCrea = numeroCrea;
	}

	/**
	 * Recupera codigo tipo certidao.
	 * 
	 * @return codigo tipo certidao
	 */
	public Short getCodigoTipoCertidao() {
		return codigoTipoCertidao;
	}

	/**
	 * Preenche codigo tipo certidao.
	 * 
	 * @param codigoTipoCertidao
	 *            o novo codigo tipo certidao
	 */
	public void setCodigoTipoCertidao(Short codigoTipoCertidao) {
		this.codigoTipoCertidao = codigoTipoCertidao;
	}

	/**
	 * Recupera sigla tipo certidao.
	 * 
	 * @return sigla tipo certidao
	 */
	public String getSiglaTipoCertidao() {
		return siglaTipoCertidao;
	}

	/**
	 * Preenche sigla tipo certidao.
	 * 
	 * @param siglaTipoCertidao
	 *            o novo sigla tipo certidao
	 */
	public void setSiglaTipoCertidao(String siglaTipoCertidao) {
		this.siglaTipoCertidao = siglaTipoCertidao;
	}

	/**
	 * Recupera nome tipo certidao.
	 * 
	 * @return nome tipo certidao
	 */
	public String getNomeTipoCertidao() {
		return nomeTipoCertidao;
	}

	/**
	 * Preenche nome tipo certidao.
	 * 
	 * @param nomeTipoCertidao
	 *            o novo nome tipo certidao
	 */
	public void setNomeTipoCertidao(String nomeTipoCertidao) {
		this.nomeTipoCertidao = nomeTipoCertidao;
	}

	/**
	 * Recupera o valor de codigoSubTipoCertidao.
	 *
	 * @return o valor de codigoSubTipoCertidao
	 */
	public Short getCodigoSubTipoCertidao() {
		return codigoSubTipoCertidao;
	}

	/**
	 * Define o valor de codigoSubTipoCertidao.
	 *
	 * @param codigoSubTipoCertidao o novo valor de codigoSubTipoCertidao
	 */
	public void setCodigoSubTipoCertidao(Short codigoSubTipoCertidao) {
		this.codigoSubTipoCertidao = codigoSubTipoCertidao;
	}

	/**
	 * Recupera o valor de descricaoSubTipoCertidao.
	 *
	 * @return o valor de descricaoSubTipoCertidao
	 */
	public String getDescricaoSubTipoCertidao() {
		return descricaoSubTipoCertidao;
	}

	/**
	 * Define o valor de descricaoSubTipoCertidao.
	 *
	 * @param descricaoSubTipoCertidao o novo valor de descricaoSubTipoCertidao
	 */
	public void setDescricaoSubTipoCertidao(String descricaoSubTipoCertidao) {
		this.descricaoSubTipoCertidao = descricaoSubTipoCertidao;
	}
	
}
