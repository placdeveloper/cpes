/*
 * SICOOB
 * 
 * HistoricoEmail.java(br.com.sicoob.capes.negocio.entidades.historico.HistoricoEmail)
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

import br.com.sicoob.capes.negocio.entidades.EmailBase;
import br.com.sicoob.capes.negocio.entidades.interfaces.Historico;

/**
 * Entidade que representa o histórico dos endereços.
 * @author Erico.Junior
 */
@Entity
@Table(name = "HISTEMAILPESSOA", schema = "CLI")
@Filters({ @Filter(name = "periodoHistorico"),@Filter(name = "periodoHistoricoAntiga"), @Filter(name = "periodoHistoricoDatasIguais") })
public class HistoricoEmail extends EmailBase implements Historico {

	/** Serial UID.*/
	private static final long serialVersionUID = 5334416116032192673L;

	/** O atributo id historico email. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "IDHISTEMAILPESSOA")
	private Long idHistoricoEmail;
	
	/** O atributo data hora fim. */
	@Column(name="DATAHORAFIM")
	private Date dataHoraFim;
	
	/** O atributo id email. */
	@Column(name = "IDEMAILPESSOA")
	private Integer idEmail;
	
	/** O atributo id usuario exclusao. */
	@Column(name = "IDUSUARIOEXCL", length = 40)
	private String idUsuarioExclusao;
	
	@Column(name = "IDUSUARIOENVIO", length=40, nullable=true)
	private String idUsuarioEnvio;
	
	
	/**
	 * @return the idHistoricoEmail
	 */
	public Long getIdHistoricoEmail() {
		return idHistoricoEmail;
	}

	/**
	 * @param idHistoricoEmail the idHistoricoEmail to set
	 */
	public void setIdHistoricoEmail(Long idHistoricoEmail) {
		this.idHistoricoEmail = idHistoricoEmail;
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
	 * @return the idEmail
	 */
	public Integer getIdEmail() {
		return idEmail;
	}

	/**
	 * @param idEmail the idEmail to set
	 */
	public void setIdEmail(Integer idEmail) {
		this.idEmail = idEmail;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Long getId() {
		return getIdHistoricoEmail();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Long id) {
		setIdHistoricoEmail(id);
	}

	/** 
	 * {@inheritDoc}
	 */
	public Serializable getIdEntidadeVigente() {
		return idEmail;
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
