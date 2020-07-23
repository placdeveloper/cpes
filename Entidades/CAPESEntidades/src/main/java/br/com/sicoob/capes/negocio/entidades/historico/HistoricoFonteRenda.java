/*
 * SICOOB
 * 
 * HistoricoFonteRenda.java(br.com.sicoob.capes.negocio.entidades.historico.HistoricoFonteRenda)
 */
package br.com.sicoob.capes.negocio.entidades.historico;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;

import br.com.sicoob.capes.negocio.entidades.FonteRendaBase;
import br.com.sicoob.capes.negocio.entidades.interfaces.Historico;

/**
 * The Class HistoricoFonteRenda.
 */
@Entity
@Table(name="HISTFONTERENDA", schema="CLI")
@Filters({ @Filter(name = "periodoHistorico"), @Filter(name = "periodoHistoricoDatasIguais") })
public class HistoricoFonteRenda extends FonteRendaBase implements Historico {

	/** Serial UID.*/
	private static final long serialVersionUID = 3080940409950837878L;

	/** O atributo id fonte renda. */
	private Long idFonteRenda;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDHISTFONTERENDA")
	private Long idHistorico;
	
	/** O atributo data hora fim. */
	@Column(name="DATAHORAFIM")
	private Date dataHoraFim;
	
	/** O atributo id usuario exclusao. */
	@Column(name = "IDUSUARIOEXCL", length = 40)
	private String idUsuarioExclusao;
	
	@Column(name = "IDUSUARIOENVIO", length=40, nullable=true)
	private String idUsuarioEnvio;

	/**
	 * @return the idFonteRenda
	 */
	public Long getIdFonteRenda() {
		return idFonteRenda;
	}

	/**
	 * @param idFonteRenda the idFonteRenda to set
	 */
	public void setIdFonteRenda(Long idFonteRenda) {
		this.idFonteRenda = idFonteRenda;
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
	@Override
	public Long getId() {
		return getIdFonteRenda();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Long id) {
		setIdFonteRenda(id);
	}

	/** 
	 * {@inheritDoc}
	 */
	public Serializable getIdEntidadeVigente() {
		return idFonteRenda;
	}

	/**
	 * Recupera id usuario exclusao.
	 * 
	 * @return id usuario exclusao
	 */
	public String getIdUsuarioExclusao() {
		return idUsuarioExclusao;
	}

	/** 
	 * {@inheritDoc}
	 */
	public void setIdUsuarioExclusao(String idUsuarioExclusao) {
		this.idUsuarioExclusao = idUsuarioExclusao;
	}

	/**
	 * Recupera idHistorico.
	 * 
	 * @return idHistorico
	 */
	public Long getIdHistorico() {
		return idHistorico;
	}

	/** 
	 * {@inheritDoc}
	 */
	public void setIdHistorico(Long idHistorico) {
		this.idHistorico = idHistorico;
	}

	public String getIdUsuarioEnvio() {
		return idUsuarioEnvio;
	}

	public void setIdUsuarioEnvio(String idUsuarioEnvio) {
		this.idUsuarioEnvio = idUsuarioEnvio;
	}
	
}
