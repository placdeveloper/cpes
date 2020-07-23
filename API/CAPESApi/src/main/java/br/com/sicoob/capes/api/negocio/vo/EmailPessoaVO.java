/*
 * SICOOB
 * 
 * EmailPessoaVO.java(br.com.sicoob.capes.api.negocio.vo.EmailPessoaVO)
 */
package br.com.sicoob.capes.api.negocio.vo;

import java.util.Date;

/**
 * @author Lucas.Borges
 */
public class EmailPessoaVO extends AbstractEntidadeIncluirVO{

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -113033890632677729L;

	// EMAIL PESSOA
	/** O atributo id email. */
	private Long idEmail;

	/** O atributo data hora inicio. */
	private Date dataHoraInicio;
	
	/** O atributo descricao email. */
	private String descricaoEmail;
	
	// TIPO EMAIL
	/** O atributo codigo tipo email. */
	private Short codigoTipoEmail;
	
	/** O atributo descricao tipo email. */
	private String descricaoTipoEmail;
	
	/**
	 * Cria uma nova instância de email pessoa vo.
	 */
	public EmailPessoaVO() {

	}

	/**
	 * Cria uma nova instância de email pessoa vo.
	 * 
	 * @param idEmail
	 *            the id email
	 * @param dataHoraInicio
	 *            the data hora inicio
	 * @param descricaoEmail
	 *            the descricao email
	 * @param codigoTipoEmail
	 *            the codigo tipo email
	 * @param descricaoTipoEmail
	 *            the descricao tipo email
	 */
	public EmailPessoaVO(Long idEmail, Date dataHoraInicio,
			String descricaoEmail, Short codigoTipoEmail,
			String descricaoTipoEmail) {
		this.idEmail = idEmail;
		this.dataHoraInicio = clonar(dataHoraInicio);
		this.descricaoEmail = descricaoEmail;
		this.codigoTipoEmail = codigoTipoEmail;
		this.descricaoTipoEmail = descricaoTipoEmail;
	}

	/**
	 * Recupera id email.
	 * 
	 * @return id email
	 */
	public Long getIdEmail() {
		return idEmail;
	}

	/**
	 * Preenche id email.
	 * 
	 * @param idEmail
	 *            o novo id email
	 */
	public void setIdEmail(Long idEmail) {
		this.idEmail = idEmail;
	}

	/**
	 * Recupera data hora inicio.
	 * 
	 * @return data hora inicio
	 */
	public Date getDataHoraInicio() {
		return clonar(dataHoraInicio);
	}

	/**
	 * Preenche data hora inicio.
	 * 
	 * @param dataHoraInicio
	 *            o novo data hora inicio
	 */
	public void setDataHoraInicio(Date dataHoraInicio) {
		this.dataHoraInicio = clonar(dataHoraInicio);
	}

	/**
	 * Recupera descricao email.
	 * 
	 * @return descricao email
	 */
	public String getDescricaoEmail() {
		return descricaoEmail;
	}

	/**
	 * Preenche descricao email.
	 * 
	 * @param descricaoEmail
	 *            o novo descricao email
	 */
	public void setDescricaoEmail(String descricaoEmail) {
		this.descricaoEmail = descricaoEmail;
	}

	/**
	 * Recupera codigo tipo email.
	 * 
	 * @return codigo tipo email
	 */
	public Short getCodigoTipoEmail() {
		return codigoTipoEmail;
	}

	/**
	 * Preenche codigo tipo email.
	 * 
	 * @param codigoTipoEmail
	 *            o novo codigo tipo email
	 */
	public void setCodigoTipoEmail(Short codigoTipoEmail) {
		this.codigoTipoEmail = codigoTipoEmail;
	}

	/**
	 * Recupera descricao tipo email.
	 * 
	 * @return descricao tipo email
	 */
	public String getDescricaoTipoEmail() {
		return descricaoTipoEmail;
	}

	/**
	 * Preenche descricao tipo email.
	 * 
	 * @param descricaoTipoEmail
	 *            o novo descricao tipo email
	 */
	public void setDescricaoTipoEmail(String descricaoTipoEmail) {
		this.descricaoTipoEmail = descricaoTipoEmail;
	}
}
