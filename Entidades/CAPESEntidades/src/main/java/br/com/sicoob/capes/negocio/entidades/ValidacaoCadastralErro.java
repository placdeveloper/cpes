/*
 * SICOOB
 * 
 * ValidacaoCadastralErro.java(br.com.sicoob.capes.negocio.entidades.ValidacaoCadastralErro)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * The Class ValidacaoCadastralErro.
 */
@Entity
@Table(schema = "CLI", name = "VALIDACAOCADASTRALERRO")
public class ValidacaoCadastralErro extends CAPESEntidade<Long> {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 4838233915449817580L;

	/** O atributo id erro. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDVALIDACAOCADASTRALERRO")
	private Long idErro;
	
	/** O atributo id pessoa compartilhamento. */
	@ManyToOne(optional = false)
	@JoinColumn(name = "IDPESSOACOMPARTILHAMENTO", nullable = false)
	private ValidacaoCadastral validacaoCadastral;
	
	/** O atributo id instituicao. */
	@Column(name = "IDINSTITUICAO")
	private Integer idInstituicao;
	
	/** O atributo regra. */
	@ManyToOne(optional = false)
	@JoinColumn(name = "CODREGRAVALIDACAOCADASTRAL", nullable = false)
	private ValidacaoCadastralRegra regra;
	
	/** O atributo data. */
	@Column(name = "DATAHORAVALIDACAOCADASTRALERRO")	
	private DateTimeDB data;
	
	/**
	 * Recupera id erro.
	 * 
	 * @return id erro
	 */
	public Long getIdErro() {
	
		return idErro;
	}

	/**
	 * Preenche id erro.
	 * 
	 * @param idErro
	 *            o novo id erro
	 */
	public void setIdErro(Long idErro) {
	
		this.idErro = idErro;
	}

	/**
	 * Recupera validacao cadastral.
	 * 
	 * @return validacao cadastral
	 */
	public ValidacaoCadastral getValidacaoCadastral() {
	
		return validacaoCadastral;
	}

	/**
	 * Preenche validacao cadastral.
	 * 
	 * @param validacaoCadastral
	 *            o novo validacao cadastral
	 */
	public void setValidacaoCadastral(ValidacaoCadastral validacaoCadastral) {
	
		this.validacaoCadastral = validacaoCadastral;
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
	 * Recupera regra.
	 * 
	 * @return regra
	 */
	public ValidacaoCadastralRegra getRegra() {
	
		return regra;
	}

	/**
	 * Preenche regra.
	 * 
	 * @param regra
	 *            o novo regra
	 */
	public void setRegra(ValidacaoCadastralRegra regra) {
	
		this.regra = regra;
	}

	/**
	 * Recupera data.
	 * 
	 * @return data
	 */
	public DateTimeDB getData() {
	
		return data;
	}

	/**
	 * Preenche data.
	 * 
	 * @param data
	 *            o novo data
	 */
	public void setData(DateTimeDB data) {
	
		this.data = data;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Long getId() {

		return getIdErro();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Long id) {

		setIdErro(id);
	}

}
