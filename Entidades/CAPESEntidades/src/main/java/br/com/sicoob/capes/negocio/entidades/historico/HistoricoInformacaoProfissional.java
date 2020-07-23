/*
 * SICOOB
 * 
 * HistoricoInformacaoProfissional.java(br.com.sicoob.capes.negocio.entidades.historico.HistoricoInformacaoProfissional)
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

import br.com.sicoob.capes.negocio.entidades.InformacaoProfissionalBase;
import br.com.sicoob.capes.negocio.entidades.interfaces.Historico;

/**
 * @author Erico.Junior
 *
 */
@Entity
@Table(name = "HISTINFORMACAOPROFISSIONAL", schema = "CLI")
public class HistoricoInformacaoProfissional extends InformacaoProfissionalBase implements Historico {

	/** Serial UID.*/
	private static final long serialVersionUID = 4225385452390652853L;

	/** O atributo id historico. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "IDHISTINFORMACAOPROFISSIONAL")
	private Integer idHistorico;
	
	/** O atributo id informacao. */
	@Column(name="IDINFORMACAOPROFISSIONAL")
	private Integer idInformacao;

	/** O atributo data hora fim. */
	@Column(name="DATAHORAFIM")
	private Date dataHoraFim;
	
	/** O atributo id usuario exclusao. */
	@Column(name = "IDUSUARIOEXCL", length = 40)
	private String idUsuarioExclusao;

	/**
	 * Recupera id historico.
	 * 
	 * @return id historico
	 */
	public Integer getIdHistorico() {
		return idHistorico;
	}

	/**
	 * Preenche id historico.
	 * 
	 * @param idHistorico
	 *            o novo id historico
	 */
	public void setIdHistorico(Integer idHistorico) {
		this.idHistorico = idHistorico;
	}

	/**
	 * Recupera id informacao.
	 * 
	 * @return id informacao
	 */
	public Integer getIdInformacao() {
		return idInformacao;
	}

	/**
	 * Preenche id informacao.
	 * 
	 * @param idInformacao
	 *            o novo id informacao
	 */
	public void setIdInformacao(Integer idInformacao) {
		this.idInformacao = idInformacao;
	}

	/** 
	 * {@inheritDoc}
	 */
	public Date getDataHoraFim() {
		return dataHoraFim;
	}

	/** 
	 * {@inheritDoc}
	 */
	public void setDataHoraFim(Date dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
	}

	/** 
	 * {@inheritDoc}
	 */
	public Serializable getIdEntidadeVigente() {
		return getIdInformacao();
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
