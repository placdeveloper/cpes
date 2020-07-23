/*
 * SICOOB
 * 
 * TelefonePessoaVO.java(br.com.sicoob.capes.api.negocio.vo.TelefonePessoaVO)
 */
package br.com.sicoob.capes.api.negocio.vo;

import java.util.Date;

/**
 * @author Lucas.Borges
 */
public class TelefonePessoaVO extends AbstractEntidadeIncluirVO {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -7776861798389233953L;

	// TELEFONE PESSOA
	/** O atributo id telefone. */
	private Long idTelefone;

	/** O atributo data hora inicio. */
	private Date dataHoraInicio;
	
	/** O atributo ddd. */
	private String ddd;

	/** O atributo telefone. */
	private String telefone;

	/** O atributo ramal. */
	private String ramal;	
	
	// TIPO TELEFONE
	/** O atributo codigo tipo telefone. */
	private Short codigoTipoTelefone;
	
	/** O atributo descricao tipo telefone. */
	private String descricaoTipoTelefone;

	/** O atributo descricao observação. */
	private String descObservacao;

	/**
	 * Cria uma nova instância de telefone pessoa vo.
	 */
	public TelefonePessoaVO() {

	}

	/**
	 * Cria uma nova instância de telefone pessoa vo.
	 * 
	 * @param idTelefone
	 *            the id telefone
	 * @param dataHoraInicio
	 *            the data hora inicio
	 * @param ddd
	 *            the ddd
	 * @param telefone
	 *            the telefone
	 * @param ramal
	 *            the ramal
	 * @param codigoTipoTelefone
	 *            the codigo tipo telefone
	 * @param descricaoTipoTelefone
	 *            the descricao tipo telefone
	 */
	public TelefonePessoaVO(Long idTelefone, Date dataHoraInicio, String ddd,
			String telefone, String ramal, Short codigoTipoTelefone,
			String descricaoTipoTelefone) {
		this.idTelefone = idTelefone;
		this.dataHoraInicio = clonar(dataHoraInicio);
		this.ddd = ddd;
		this.telefone = telefone;
		this.ramal = ramal;
		this.codigoTipoTelefone = codigoTipoTelefone;
		this.descricaoTipoTelefone = descricaoTipoTelefone;
	}

	/**
	 * Recupera id telefone.
	 * 
	 * @return id telefone
	 */
	public Long getIdTelefone() {
		return idTelefone;
	}

	/**
	 * Preenche id telefone.
	 * 
	 * @param idTelefone
	 *            o novo id telefone
	 */
	public void setIdTelefone(Long idTelefone) {
		this.idTelefone = idTelefone;
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
	 * Recupera ddd.
	 * 
	 * @return ddd
	 */
	public String getDdd() {
		return ddd;
	}

	/**
	 * Preenche ddd.
	 * 
	 * @param ddd
	 *            o novo ddd
	 */
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	/**
	 * Recupera telefone.
	 * 
	 * @return telefone
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * Preenche telefone.
	 * 
	 * @param telefone
	 *            o novo telefone
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * Recupera ramal.
	 * 
	 * @return ramal
	 */
	public String getRamal() {
		return ramal;
	}

	/**
	 * Preenche ramal.
	 * 
	 * @param ramal
	 *            o novo ramal
	 */
	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	/**
	 * Recupera codigo tipo telefone.
	 * 
	 * @return codigo tipo telefone
	 */
	public Short getCodigoTipoTelefone() {
		return codigoTipoTelefone;
	}

	/**
	 * Preenche codigo tipo telefone.
	 * 
	 * @param codigoTipoTelefone
	 *            o novo codigo tipo telefone
	 */
	public void setCodigoTipoTelefone(Short codigoTipoTelefone) {
		this.codigoTipoTelefone = codigoTipoTelefone;
	}

	/**
	 * Recupera descricao tipo telefone.
	 * 
	 * @return descricao tipo telefone
	 */
	public String getDescricaoTipoTelefone() {
		return descricaoTipoTelefone;
	}

	/**
	 * Preenche descricao tipo telefone.
	 * 
	 * @param descricaoTipoTelefone
	 *            o novo descricao tipo telefone
	 */
	public void setDescricaoTipoTelefone(String descricaoTipoTelefone) {
		this.descricaoTipoTelefone = descricaoTipoTelefone;
	}

	public String getDescObservacao() {
		return descObservacao;
	}

	public void setDescObservacao(String descObservacao) {
		this.descObservacao = descObservacao;
	}
	
}
