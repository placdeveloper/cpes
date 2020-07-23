/*
 * SICOOB
 * 
 * HistoricoCertidao.java(br.com.sicoob.capes.negocio.entidades.historico.HistoricoCertidao)
 */
package br.com.sicoob.capes.negocio.entidades.historico;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;

import br.com.sicoob.capes.negocio.entidades.CertidaoBase;
import br.com.sicoob.capes.negocio.entidades.interfaces.Historico;

/**
 * Entidade que representa o histórico das certidoes.
 * @author juan.damasceno
 */
@Entity
@Table(name="HISTCERTIDAO", schema="CLI")
@Filters({ @Filter(name = "periodoHistorico"),@Filter(name = "periodoHistoricoAntiga"), @Filter(name = "periodoHistoricoDatasIguais") })
public class HistoricoCertidao extends CertidaoBase implements Historico {

	/** Serial UID.*/
	private static final long serialVersionUID = 7768370401008160219L;

	/** O atributo id historico certidao. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "IDHISTCERTIDAO")
	private Long idHistoricoCertidao;
	
	/** O atributo id certidao. */
	@Column(name = "IDCERTIDAO")
	private Long idCertidao;
	
	/** O atributo data hora fim. */
	@Column(name="DATAHORAFIM")
	private Date dataHoraFim;
	
	/** O atributo id usuario exclusao. */
	@Column(name = "IDUSUARIOEXCL", length = 40)
	private String idUsuarioExclusao;
	
	@Column(name = "IDUSUARIOENVIO", length=40, nullable=true)
	private String idUsuarioEnvio;

	/**
	 * @return the id
	 */
	public Long getId() {
		return getIdHistoricoCertidao();
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		setIdHistoricoCertidao(id);
	}

	/**
	 * @return the idHistoricoCertidao
	 */
	public Long getIdHistoricoCertidao() {
		return idHistoricoCertidao;
	}

	/**
	 * @param idHistoricoCertidao the idHistoricoCertidao to set
	 */
	public void setIdHistoricoCertidao(Long idHistoricoCertidao) {
		this.idHistoricoCertidao = idHistoricoCertidao;
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
	 * @return the idCertidao
	 */
	public Long getIdCertidao() {
		return idCertidao;
	}

	/**
	 * @param idCertidao the idCertidao to set
	 */
	public void setIdCertidao(Long idCertidao) {
		this.idCertidao = idCertidao;
	}

	/** 
	 * {@inheritDoc}
	 */
	public Number getIdEntidadeVigente() {
		return getIdCertidao();
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

	public String getIdUsuarioEnvio() {
		return idUsuarioEnvio;
	}

	public void setIdUsuarioEnvio(String idUsuarioEnvio) {
		this.idUsuarioEnvio = idUsuarioEnvio;
	}
}
