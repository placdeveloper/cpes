/*
 * SICOOB
 * 
 * HistoricoPessoaInstituicao.java(br.com.sicoob.capes.negocio.entidades.historico.HistoricoPessoaInstituicao)
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

import br.com.sicoob.capes.negocio.entidades.PessoaInstituicaoBase;
import br.com.sicoob.capes.negocio.entidades.interfaces.Historico;

/**
 * Entidade que representa o histórico das pessoa instituicao.
 * @author juan.damasceno
 */
@Entity
@Table(name="HISTPESSOAINSTITUICAO", schema="CLI")
@Filters({ @Filter(name = "periodoHistorico"),@Filter(name = "periodoHistoricoAntiga"), @Filter(name = "periodoHistoricoDatasIguais") })
public class HistoricoPessoaInstituicao extends PessoaInstituicaoBase implements Historico {

	/** Serial UID.*/
	private static final long serialVersionUID = 7768370401008160219L;

	/** O atributo id historico. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "IDHISTPESSOAINSTITUICAO")
	private Integer idHistorico;
	
	/** O atributo data hora fim. */
	@Column(name="DATAHORAFIM")
	private Date dataHoraFim;
	
	/** O atributo id pessoa instituicao. */
	private Integer idPessoaInstituicao;
	
	/** O atributo id usuario exclusao. */
	@Column(name = "IDUSUARIOEXCL", length = 40)
	private String idUsuarioExclusao;
	
	/**
	 * @return the idPessoaInstituicao
	 */
	public Integer getIdPessoaInstituicao() {
		return idPessoaInstituicao;
	}

	/**
	 * @param idPessoaInstituicao the idPessoaInstituicao to set
	 */
	public void setIdPessoaInstituicao(Integer idPessoaInstituicao) {
		this.idPessoaInstituicao = idPessoaInstituicao;
	}

	/**
	 * @return the idHistorico
	 */
	public Integer getIdHistorico() {
		return idHistorico;
	}

	/**
	 * @param idHistorico the idHistorico to set
	 */
	public void setIdHistorico(Integer idHistorico) {
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
	public Integer getId() {
		return getIdHistorico();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Integer id) {
		setIdHistorico(id);
	}

	/** 
	 * {@inheritDoc}
	 */
	public Serializable getIdEntidadeVigente() {
		return getIdPessoaInstituicao();
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
}
