/*
 * SICOOB
 * 
 * HistoricoTributacao.java(br.com.sicoob.capes.negocio.entidades.historico.HistoricoTributacao)
 */
package br.com.sicoob.capes.negocio.entidades.historico;

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

import br.com.sicoob.capes.negocio.entidades.TributacaoBase;
import br.com.sicoob.capes.negocio.entidades.interfaces.Historico;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Entidade que representa o histórico das certidoes.
 * @author juan.damasceno
 */
@Entity
@Table(name="HISTPESSOATRIBUTACAO", schema="CLI")
@Filters({ @Filter(name = "periodoHistorico"),@Filter(name = "periodoHistoricoAntiga"), @Filter(name = "periodoHistoricoDatasIguais") })
public class HistoricoTributacao extends TributacaoBase implements Historico {

	/** Serial UID.*/
	private static final long serialVersionUID = 7768370401008160219L;

	/** O atributo id historico. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "IDHISTPESSOATRIBUTACAO")
	private Long idHistorico;
	
	/** O atributo data hora fim. */
	@Column(name="DATAHORAFIM")
	private Date dataHoraFim;
	
	/** O atributo pessoa compartilhamento. */
	@ManyToOne
	@JoinColumn(name="IDPESSOACOMPARTILHAMENTO")
	private PessoaCompartilhamento pessoaCompartilhamento;

	/** O atributo id pessoa compartilhamento. */
	@Column(updatable = false, insertable = false)
	private Long idPessoaCompartilhamento;
	
	/** O atributo id usuario exclusao. */
	@Column(name = "IDUSUARIOEXCL", length = 40)
	private String idUsuarioExclusao;
	
	/** 
	 * {@inheritDoc}
	 */
	public PessoaCompartilhamento getPessoaCompartilhamento() {
		return pessoaCompartilhamento;
	}

	/**
	 * Preenche pessoa compartilhamento.
	 * 
	 * @param pessoaCompartilhamento
	 *            o novo pessoa compartilhamento
	 */
	public void setPessoaCompartilhamento(
			PessoaCompartilhamento pessoaCompartilhamento) {
		this.pessoaCompartilhamento = pessoaCompartilhamento;
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
		return getPessoaCompartilhamento().getIdPessoaCompartilhamento();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Long getIdPessoaCompartilhamento() {
		return idPessoaCompartilhamento;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setIdPessoaCompartilhamento(Long idPessoaCompartilhamento) {
		this.idPessoaCompartilhamento = idPessoaCompartilhamento;
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
	public void setIdUsuarioExclusao(String idUsuario) {
		this.idUsuarioExclusao = idUsuario;
	}
	
}
