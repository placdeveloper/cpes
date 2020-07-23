package br.com.sicoob.capes.api.inclusao.negocio.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;

/**
 * A Classe PessoaJuridicaDTO.
 * 
 * @author bruno.carneiro
 */
public class PessoaJuridicaDTO extends PessoaDTO {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 3024716985213472768L;

	/** O atributo dataConstituicao. */
	private Date dataConstituicao;

	/** O atributo valorCapitalSocial. */
	private BigDecimal valorCapitalSocial = BigDecimal.ZERO;

	/** O atributo inscricaoEstadual. */
	private String inscricaoEstadual;

	/** O atributo codigoEsferaAdministrativa. */
	private Short codigoEsferaAdministrativa;

	/** O atributo codigoFormaConstituicao. */
	private Short codigoFormaConstituicao;

	/** O atributo numeroRegistroJuntaComercial. */
	private String numeroRegistroJuntaComercial;

	/** O atributo dataRegistroJuntaComercial. */
	private Date dataRegistroJuntaComercial;

	/** O atributo numeroUltimaAlteracaoContratoSocial. */
	private String numeroUltimaAlteracaoContratoSocial;

	/** O atributo dataUltimaAlteracaoContratoSocial. */
	private Date dataUltimaAlteracaoContratoSocial;

	/** O atributo numeroRegistroRepresentacao. */
	private String numeroRegistroRepresentacao;

	/** O atributo dataRegistroRepresentacao. */
	private Date dataRegistroRepresentacao;

	/** O atributo contratoSocial. */
	private String contratoSocial;

	/** O atributo inscricaoMunicipal. */
	private String inscricaoMunicipal;

	/**
	 * Recupera o valor de dataConstituicao.
	 * 
	 * @return o valor de dataConstituicao
	 */
	public Date getDataConstituicao() {
		return dataConstituicao;
	}

	/**
	 * Define o valor de dataConstituicao.
	 * 
	 * @param dataConstituicao
	 *            o novo valor de dataConstituicao
	 */
	public void setDataConstituicao(Date dataConstituicao) {
		this.dataConstituicao = dataConstituicao;
	}

	/**
	 * Recupera o valor de valorCapitalSocial.
	 * 
	 * @return o valor de valorCapitalSocial
	 */
	public BigDecimal getValorCapitalSocial() {
		return valorCapitalSocial;
	}

	/**
	 * Define o valor de valorCapitalSocial.
	 * 
	 * @param valorCapitalSocial
	 *            o novo valor de valorCapitalSocial
	 */
	public void setValorCapitalSocial(BigDecimal valorCapitalSocial) {
		this.valorCapitalSocial = valorCapitalSocial;
	}

	/**
	 * Recupera o valor de inscricaoEstadual.
	 * 
	 * @return o valor de inscricaoEstadual
	 */
	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	/**
	 * Define o valor de inscricaoEstadual.
	 * 
	 * @param inscricaoEstadual
	 *            o novo valor de inscricaoEstadual
	 */
	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	/**
	 * Recupera o valor de codigoEsferaAdministrativa.
	 * 
	 * @return o valor de codigoEsferaAdministrativa
	 */
	public Short getCodigoEsferaAdministrativa() {
		return codigoEsferaAdministrativa;
	}

	/**
	 * Define o valor de codigoEsferaAdministrativa.
	 * 
	 * @param codigoEsferaAdministrativa
	 *            o novo valor de codigoEsferaAdministrativa
	 */
	public void setCodigoEsferaAdministrativa(Short codigoEsferaAdministrativa) {
		this.codigoEsferaAdministrativa = codigoEsferaAdministrativa;
	}

	/**
	 * Recupera o valor de codigoFormaConstituicao.
	 * 
	 * @return o valor de codigoFormaConstituicao
	 */
	public Short getCodigoFormaConstituicao() {
		return codigoFormaConstituicao;
	}

	/**
	 * Define o valor de codigoFormaConstituicao.
	 * 
	 * @param codigoFormaConstituicao
	 *            o novo valor de codigoFormaConstituicao
	 */
	public void setCodigoFormaConstituicao(Short codigoFormaConstituicao) {
		this.codigoFormaConstituicao = codigoFormaConstituicao;
	}

	/**
	 * Recupera o valor de numeroRegistroJuntaComercial.
	 * 
	 * @return o valor de numeroRegistroJuntaComercial
	 */
	public String getNumeroRegistroJuntaComercial() {
		return numeroRegistroJuntaComercial;
	}

	/**
	 * Define o valor de numeroRegistroJuntaComercial.
	 * 
	 * @param numeroRegistroJuntaComercial
	 *            o novo valor de numeroRegistroJuntaComercial
	 */
	public void setNumeroRegistroJuntaComercial(String numeroRegistroJuntaComercial) {
		this.numeroRegistroJuntaComercial = numeroRegistroJuntaComercial;
	}

	/**
	 * Recupera o valor de dataRegistroJuntaComercial.
	 * 
	 * @return o valor de dataRegistroJuntaComercial
	 */
	public Date getDataRegistroJuntaComercial() {
		return dataRegistroJuntaComercial;
	}

	/**
	 * Define o valor de dataRegistroJuntaComercial.
	 * 
	 * @param dataRegistroJuntaComercial
	 *            o novo valor de dataRegistroJuntaComercial
	 */
	public void setDataRegistroJuntaComercial(Date dataRegistroJuntaComercial) {
		this.dataRegistroJuntaComercial = dataRegistroJuntaComercial;
	}

	/**
	 * Recupera o valor de numeroUltimaAlteracaoContratoSocial.
	 * 
	 * @return o valor de numeroUltimaAlteracaoContratoSocial
	 */
	public String getNumeroUltimaAlteracaoContratoSocial() {
		return numeroUltimaAlteracaoContratoSocial;
	}

	/**
	 * Define o valor de numeroUltimaAlteracaoContratoSocial.
	 * 
	 * @param numeroUltimaAlteracaoContratoSocial
	 *            o novo valor de numeroUltimaAlteracaoContratoSocial
	 */
	public void setNumeroUltimaAlteracaoContratoSocial(String numeroUltimaAlteracaoContratoSocial) {
		this.numeroUltimaAlteracaoContratoSocial = numeroUltimaAlteracaoContratoSocial;
	}

	/**
	 * Recupera o valor de dataUltimaAlteracaoContratoSocial.
	 * 
	 * @return o valor de dataUltimaAlteracaoContratoSocial
	 */
	public Date getDataUltimaAlteracaoContratoSocial() {
		return dataUltimaAlteracaoContratoSocial;
	}

	/**
	 * Define o valor de dataUltimaAlteracaoContratoSocial.
	 * 
	 * @param dataUltimaAlteracaoContratoSocial
	 *            o novo valor de dataUltimaAlteracaoContratoSocial
	 */
	public void setDataUltimaAlteracaoContratoSocial(Date dataUltimaAlteracaoContratoSocial) {
		this.dataUltimaAlteracaoContratoSocial = dataUltimaAlteracaoContratoSocial;
	}

	/**
	 * Recupera o valor de numeroRegistroRepresentacao.
	 * 
	 * @return o valor de numeroRegistroRepresentacao
	 */
	public String getNumeroRegistroRepresentacao() {
		return numeroRegistroRepresentacao;
	}

	/**
	 * Define o valor de numeroRegistroRepresentacao.
	 * 
	 * @param numeroRegistroRepresentacao
	 *            o novo valor de numeroRegistroRepresentacao
	 */
	public void setNumeroRegistroRepresentacao(String numeroRegistroRepresentacao) {
		this.numeroRegistroRepresentacao = numeroRegistroRepresentacao;
	}

	/**
	 * Recupera o valor de dataRegistroRepresentacao.
	 * 
	 * @return o valor de dataRegistroRepresentacao
	 */
	public Date getDataRegistroRepresentacao() {
		return dataRegistroRepresentacao;
	}

	/**
	 * Define o valor de dataRegistroRepresentacao.
	 * 
	 * @param dataRegistroRepresentacao
	 *            o novo valor de dataRegistroRepresentacao
	 */
	public void setDataRegistroRepresentacao(Date dataRegistroRepresentacao) {
		this.dataRegistroRepresentacao = dataRegistroRepresentacao;
	}

	/**
	 * Recupera o valor de contratoSocial.
	 * 
	 * @return o valor de contratoSocial
	 */
	public String getContratoSocial() {
		return contratoSocial;
	}

	/**
	 * Define o valor de contratoSocial.
	 * 
	 * @param contratoSocial
	 *            o novo valor de contratoSocial
	 */
	public void setContratoSocial(String contratoSocial) {
		this.contratoSocial = contratoSocial;
	}

	/**
	 * Recupera o valor de inscricaoMunicipal.
	 * 
	 * @return o valor de inscricaoMunicipal
	 */
	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}

	/**
	 * Define o valor de inscricaoMunicipal.
	 * 
	 * @param inscricaoMunicipal
	 *            o novo valor de inscricaoMunicipal
	 */
	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}
	
	@Override
	public Short getCodigoTipoPessoa() {
		return TipoPessoaEnum.PESSOA_JURIDICA.getCodigo();
	}

}