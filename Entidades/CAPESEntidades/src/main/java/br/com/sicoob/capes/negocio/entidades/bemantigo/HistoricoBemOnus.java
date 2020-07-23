/*
 * SICOOB
 * 
 * HistoricoBemOnus.java(br.com.sicoob.capes.negocio.entidades.historico.HistoricoBemOnus)
 */
package br.com.sicoob.capes.negocio.entidades.bemantigo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import br.com.sicoob.capes.negocio.entidades.interfaces.Historico;


/**
 * The Class HistoricoBemOnus.
 */
@Entity
@Table(name="HISTBEMPESSOAONUS", schema="CLI")
@Filters({ @Filter(name = "periodoHistorico"),@Filter(name = "periodoHistoricoAntiga"), @Filter(name = "periodoHistoricoDatasIguais") })
public class HistoricoBemOnus extends BemOnusBase implements Historico {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** O atributo id historico bem onus. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IDHISTBEMPESSOAONUS")
	private Integer idHistoricoBemOnus;
	
	/** O atributo bem onus. */
	@ManyToOne(optional = true)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name="IDBEMPESSOAONUS")
	private BemOnus bemOnus;
	
	/** O atributo id bem pessoa onus. */
	@Column(name="IDBEMPESSOAONUS", updatable=false, insertable=false)
	private Integer idBemPessoaOnus;
	
	/** O atributo data hora fim. */
	@Column(name="DATAHORAFIM")
	private Date dataHoraFim;
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Integer getId() {
		return idHistoricoBemOnus;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Integer id) {
		idHistoricoBemOnus = id;
	}

	/**
	 * @param bemOnus the bemOnus to set
	 */
	public void setBemOnus(BemOnus bemOnus) {
		this.bemOnus = bemOnus;
	}
	
	/**
	 * @return the bemOnus
	 */
	public BemOnus getBemOnus() {
		return bemOnus;
	}
	
	/**
	 * @return the dataHoraFim
	 */
	public Date getDataHoraFim() {
		return dataHoraFim;
	}

	/**
	 * @param dataHoraFim the dataHoraFim to set
	 */
	public void setDataHoraFim(Date dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
	}

	/**
	 * @return the idHistoricoBemOnus
	 */
	public Integer getIdHistoricoBemOnus() {
		return idHistoricoBemOnus;
	}

	/**
	 * @param idHistoricoBemOnus the idHistoricoBemOnus to set
	 */
	public void setIdHistoricoBemOnus(Integer idHistoricoBemOnus) {
		this.idHistoricoBemOnus = idHistoricoBemOnus;
	}

	/** 
	 * {@inheritDoc}
	 */
	public Serializable getIdEntidadeVigente() {
		return idBemPessoaOnus;
	}

	/**
	 * @return the idBemPessoaOnus
	 */
	public Integer getIdBemPessoaOnus() {
		return idBemPessoaOnus;
	}

	/**
	 * @param idBemPessoaOnus the idBemPessoaOnus to set
	 */
	public void setIdBemPessoaOnus(Integer idBemPessoaOnus) {
		this.idBemPessoaOnus = idBemPessoaOnus;
	}

	/** 
	 * {@inheritDoc}
	 */
	public void setIdUsuarioExclusao(String idUsuarioExclusao) {
		
	}
	
}