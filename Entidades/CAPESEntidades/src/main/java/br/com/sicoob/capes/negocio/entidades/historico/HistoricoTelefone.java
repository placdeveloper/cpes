/*
 * SICOOB
 * 
 * HistoricoTelefone.java(br.com.sicoob.capes.negocio.entidades.historico.HistoricoTelefone)
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

import br.com.sicoob.capes.negocio.entidades.TelefoneBase;
import br.com.sicoob.capes.negocio.entidades.interfaces.Historico;

/**
 * Entidade que representa o histórico dos telefones.
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name = "HISTTELEFONEPESSOA", schema = "CLI")
@Filters({ @Filter(name = "periodoHistorico"),@Filter(name = "periodoHistoricoAntiga"), @Filter(name = "periodoHistoricoDatasIguais") })
public class HistoricoTelefone extends TelefoneBase implements Historico {

	/** Serial UID. */
	private static final long serialVersionUID = -8511225255994312392L;

	/** O atributo id historico. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDHISTTELEFONEPESSOA")
	private Long idHistorico;

	/** O atributo data hora fim. */
	@Column(name = "DATAHORAFIM")
	private Date dataHoraFim;

	/** O atributo id telefone pessoa. */
	private Integer idTelefonePessoa;
	
	/** O atributo id usuario exclusao. */
	@Column(name = "IDUSUARIOEXCL", length = 40)
	private String idUsuarioExclusao;
	
	@Column(name = "IDUSUARIOENVIO", length=40, nullable=true)
	private String idUsuarioEnvio;

	/**
	 * @return the idHistorico
	 */
	public Long getIdHistorico() {
		return idHistorico;
	}

	/**
	 * @param idHistorico
	 *            the idHistorico to set
	 */
	public void setIdHistorico(Long idHistorico) {
		this.idHistorico = idHistorico;
	}

	/**
	 * @return the dataHoraFim
	 */
	public Date getDataHoraFim() {
		return dataHoraFim;
	}

	/**
	 * @param dataHoraFim
	 *            the dataHoraFim to set
	 */
	public void setDataHoraFim(Date dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
	}

	/**
	 * @return the idTelefonePessoa
	 */
	public Integer getIdTelefonePessoa() {
		return idTelefonePessoa;
	}

	/**
	 * @param idTelefonePessoa the idTelefonePessoa to set
	 */
	public void setIdTelefonePessoa(Integer idTelefonePessoa) {
		this.idTelefonePessoa = idTelefonePessoa;
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
		return idTelefonePessoa;
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
