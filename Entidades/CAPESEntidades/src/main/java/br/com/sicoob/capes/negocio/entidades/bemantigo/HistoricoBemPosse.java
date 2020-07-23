/*
 * SICOOB
 * 
 * HistoricoBemPosse.java(br.com.sicoob.capes.negocio.entidades.historico.HistoricoBemPosse)
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
 * The Class HistoricoBemPosse.
 */
@Entity
@Table(name="HISTBEMPESSOAPOSSE", schema="CLI")
@Filters({ @Filter(name = "periodoHistorico"),@Filter(name = "periodoHistoricoAntiga"), @Filter(name = "periodoHistoricoDatasIguais") })
public class HistoricoBemPosse extends BemPosseBase implements Historico {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** O atributo id historico bem posse. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IDHISTBEMPESSOAPOSSE")
	private Integer idHistoricoBemPosse;
	
	/** O atributo bem posse. */
	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name="IDBEMPESSOAPOSSE")
	private BemPosse bemPosse;
	
	/** O atributo id bem pessoa posse. */
	@Column(name="IDBEMPESSOAPOSSE", updatable=false, insertable=false)
	private Integer idBemPessoaPosse;	
	
	/** O atributo data hora fim. */
	@Column(name="DATAHORAFIM")
	private Date dataHoraFim;
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Integer getId() {
		return this.idHistoricoBemPosse;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Integer id) {
		this.idHistoricoBemPosse = id;
	}

	/**
	 * @return the bemPosse
	 */
	public BemPosse getBemPosse() {
		return bemPosse;
	}

	/**
	 * @param bemPosse the bemPosse to set
	 */
	public void setBemPosse(BemPosse bemPosse) {
		this.bemPosse = bemPosse;
	}

	/**
	 * @return the idHistoricoBemPosse
	 */
	public Integer getIdHistoricoBemPosse() {
		return idHistoricoBemPosse;
	}

	/**
	 * @param idHistoricoBemPosse the idHistoricoBemPosse to set
	 */
	public void setIdHistoricoBemPosse(Integer idHistoricoBemPosse) {
		this.idHistoricoBemPosse = idHistoricoBemPosse;
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
	 * {@inheritDoc}
	 */
	public Serializable getIdEntidadeVigente() {
		return idBemPessoaPosse;
	}

	/**
	 * @return the idBemPessoaPosse
	 */
	public Integer getIdBemPessoaPosse() {
		return idBemPessoaPosse;
	}

	/**
	 * @param idBemPessoaPosse the idBemPessoaPosse to set
	 */
	public void setIdBemPessoaPosse(Integer idBemPessoaPosse) {
		this.idBemPessoaPosse = idBemPessoaPosse;
	}

	/** 
	 * {@inheritDoc}
	 */
	public void setIdUsuarioExclusao(String idUsuarioExclusao) {
		
	}
	
}