/*
 * SICOOB
 * 
 * OrigemInformacao.java(br.com.sicoob.capes.negocio.entidades.OrigemInformacao)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Entidade que representa a tabela responsável por armazenar as origens das 
 * informações (Fontes).
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name="ORIGEMINFO", schema = "CLI")
public class OrigemInformacao extends CAPESEntidade<Short> {

	/** Serial UID. */
	private static final long serialVersionUID = -3463913255209706838L;

	/** O atributo id origem info. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Short idOrigemInfo;
	
	/** O atributo desc origem info. */
	private String descOrigemInfo;
	
	/** O atributo nome origem info. */
	@Column(name = "NOMEORGAOORIGEM")
	private String nomeOrigemInfo;

	/**
	 * Cria uma nova instância de origem informacao.
	 */
	public OrigemInformacao() {
	}
	
	/**
	 * Cria uma nova instância de origem informacao.
	 * 
	 * @param idOrigemInfo
	 *            the id origem info
	 */
	public OrigemInformacao(Short idOrigemInfo) {
		this.idOrigemInfo = idOrigemInfo;
	}



	/**
	 * @return the idOrigemInfo
	 */
	public Short getIdOrigemInfo() {
		return idOrigemInfo;
	}

	/**
	 * @param idOrigemInfo the idOrigemInfo to set
	 */
	public void setIdOrigemInfo(Short idOrigemInfo) {
		this.idOrigemInfo = idOrigemInfo;
	}

	/**
	 * @return the descOrigemInfo
	 */
	public String getDescOrigemInfo() {
		return descOrigemInfo;
	}

	/**
	 * @param descOrigemInfo the descOrigemInfo to set
	 */
	public void setDescOrigemInfo(String descOrigemInfo) {
		this.descOrigemInfo = descOrigemInfo;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	@Transient
	public Short getId() {
		return getIdOrigemInfo();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Short id) {
		setIdOrigemInfo(id);
	}

	/**
	 * Recupera nome origem info.
	 * 
	 * @return nome origem info
	 */
	public String getNomeOrigemInfo() {
		return nomeOrigemInfo;
	}

	/**
	 * Preenche nome origem info.
	 * 
	 * @param nomeOrigemInfo
	 *            o novo nome origem info
	 */
	public void setNomeOrigemInfo(String nomeOrigemInfo) {
		this.nomeOrigemInfo = nomeOrigemInfo;
	}
	
}
