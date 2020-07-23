/*
 * SICOOB
 * 
 * HistoricoReferencia.java(br.com.sicoob.capes.negocio.entidades.historico.HistoricoReferencia)
 */
package br.com.sicoob.capes.negocio.entidades.historico;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;

import br.com.sicoob.capes.negocio.entidades.ReferenciaBase;
import br.com.sicoob.capes.negocio.entidades.interfaces.Historico;

/**
 * The Class HistoricoReferencia.
 */
@Entity
@Table(name="HISTREFERENCIAPESSOA", schema="CLI")
@Inheritance(strategy=InheritanceType.JOINED)
@Filters({ @Filter(name = "periodoHistorico"),@Filter(name = "periodoHistoricoAntiga"), @Filter(name = "periodoHistoricoDatasIguais") })
public class HistoricoReferencia extends ReferenciaBase implements Historico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** O atributo id historico. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IDHISTREFERENCIAPESSOA")
	private Long idHistorico;
	
	/** O atributo id referencia pessoa. */
	@Column(name="IDREFERENCIAPESSOA")
	private Integer idReferenciaPessoa;
	
	/** O atributo data hora fim. */
	@Column(name="DATAHORAFIM")
	private Date dataHoraFim;
	
	/** O atributo id usuario exclusao. */
	@Column(name = "IDUSUARIOEXCL", length = 40)
	private String idUsuarioExclusao;
	
	@Column(name = "IDUSUARIOENVIO", length=40, nullable=true)
	private String idUsuarioEnvio;

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
	 * @return the idReferenciaPessoa
	 */
	public Integer getIdReferenciaPessoa() {
		return idReferenciaPessoa;
	}

	/**
	 * @param idReferenciaPessoa the idReferenciaPessoa to set
	 */
	public void setIdReferenciaPessoa(Integer idReferenciaPessoa) {
		this.idReferenciaPessoa = idReferenciaPessoa;
	}

	/**
	 * @return the idHistorico
	 */
	public Long getIdHistorico() {
		return idHistorico;
	}

	/**
	 * @param idHistorico the idHistorico to set
	 */
	public void setIdHistorico(Long idHistorico) {
		this.idHistorico = idHistorico;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Long getId() {
		return getIdHistorico();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Long id) {
		setIdHistorico(id);
	}

	/** 
	 * {@inheritDoc}
	 */
	public Serializable getIdEntidadeVigente() {
		return idReferenciaPessoa;
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