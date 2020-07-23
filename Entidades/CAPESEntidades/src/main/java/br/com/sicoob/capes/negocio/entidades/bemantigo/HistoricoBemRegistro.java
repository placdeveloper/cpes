/*
 * SICOOB
 * 
 * HistoricoBemRegistro.java(br.com.sicoob.capes.negocio.entidades.historico.HistoricoBemRegistro)
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
 * The Class HistoricoBemRegistro.
 */
@Entity
@Table(name="HISTBEMPESSOAREGISTRO", schema="CLI")
@Filters({ @Filter(name = "periodoHistorico"),@Filter(name = "periodoHistoricoAntiga"), @Filter(name = "periodoHistoricoDatasIguais") })
public class HistoricoBemRegistro extends BemRegistroBase implements Historico {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** O atributo id historico bem registro. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IDHISTBEMPESSOAREGISTRO")
	private Integer idHistoricoBemRegistro;
	
	/** O atributo bem registro. */
	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name="IDBEMPESSOAREGISTRO")
	private BemRegistro bemRegistro;
	
	/** O atributo id bem pessoa registro. */
	@Column(name="IDBEMPESSOAREGISTRO", updatable=false, insertable=false)
	private Integer idBemPessoaRegistro;
	
	/** O atributo data hora fim. */
	@Column(name="DATAHORAFIM")
	private Date dataHoraFim;
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Integer getId() {
		return getIdHistoricoBemRegistro();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Integer id) {
		setIdHistoricoBemRegistro(id);
	}

	/**
	 * @return the idHistoricoBemRegistro
	 */
	public Integer getIdHistoricoBemRegistro() {
		return idHistoricoBemRegistro;
	}

	/**
	 * @param idHistoricoBemRegistro the idHistoricoBemRegistro to set
	 */
	public void setIdHistoricoBemRegistro(Integer idHistoricoBemRegistro) {
		this.idHistoricoBemRegistro = idHistoricoBemRegistro;
	}

	/**
	 * @return the bemRegistro
	 */
	public BemRegistro getBemRegistro() {
		return bemRegistro;
	}

	/**
	 * @param bemRegistro the bemRegistro to set
	 */
	public void setBemRegistro(BemRegistro bemRegistro) {
		this.bemRegistro = bemRegistro;
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
		return idBemPessoaRegistro;
	}

	/**
	 * @return the idBemPessoaRegistro
	 */
	public Integer getIdBemPessoaRegistro() {
		return idBemPessoaRegistro;
	}

	/**
	 * @param idBemPessoaRegistro the idBemPessoaRegistro to set
	 */
	public void setIdBemPessoaRegistro(Integer idBemPessoaRegistro) {
		this.idBemPessoaRegistro = idBemPessoaRegistro;
	}

	/** 
	 * {@inheritDoc}
	 */
	public void setIdUsuarioExclusao(String idUsuario) {
		
	}

}