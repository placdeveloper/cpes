/*
 * SICOOB
 * 
 * MapaTipoAnotacao.java(br.com.sicoob.capes.negocio.entidades.MapaTipoAnotacao)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The Class MapaTipoAnotacao.
 */
@Entity
@Table(name = "MAPATIPOANOTACAO", schema = "CLI")
public class MapaTipoAnotacao extends CAPESEntidade<Integer> {
	
	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -4020846212760725825L;

	/** O atributo id mapa tipo anotacao. */
	@Id
	@Column(name = "IDMAPATIPOANOTACAO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMapaTipoAnotacao;

	/** O atributo tipo anotacao. */
	@ManyToOne
	@JoinColumn(name = "CODTIPOANOTACAO", referencedColumnName = "CODTIPOANOTACAO", nullable = false)
	private TipoAnotacao tipoAnotacao;

	/** O atributo tipo consulta origem. */
	@ManyToOne
	@JoinColumn(name = "CODTIPOCONSULTAORIGEM", referencedColumnName = "CODTIPOCONSULTAORIGEM", nullable = false)
	private TipoConsultaOrigem tipoConsultaOrigem;
	
	/** O atributo codigo tipo origem informacao. */
	@Column(name = "CODTIPOORIGEMINFO", nullable = false)
	private String codigoTipoOrigemInformacao;

	/**
	 * Recupera id mapa tipo anotacao.
	 * 
	 * @return id mapa tipo anotacao
	 */
	public Integer getIdMapaTipoAnotacao() {
		return idMapaTipoAnotacao;
	}

	/**
	 * Preenche id mapa tipo anotacao.
	 * 
	 * @param idMapaTipoAnotacao
	 *            o novo id mapa tipo anotacao
	 */
	public void setIdMapaTipoAnotacao(Integer idMapaTipoAnotacao) {
		this.idMapaTipoAnotacao = idMapaTipoAnotacao;
	}

	/**
	 * Recupera tipo anotacao.
	 * 
	 * @return tipo anotacao
	 */
	public TipoAnotacao getTipoAnotacao() {
		return tipoAnotacao;
	}

	/**
	 * Preenche tipo anotacao.
	 * 
	 * @param tipoAnotacao
	 *            o novo tipo anotacao
	 */
	public void setTipoAnotacao(TipoAnotacao tipoAnotacao) {
		this.tipoAnotacao = tipoAnotacao;
	}

	/**
	 * Recupera tipo consulta origem.
	 * 
	 * @return tipo consulta origem
	 */
	public TipoConsultaOrigem getTipoConsultaOrigem() {
		return tipoConsultaOrigem;
	}

	/**
	 * Preenche tipo consulta origem.
	 * 
	 * @param tipoConsultaOrigem
	 *            o novo tipo consulta origem
	 */
	public void setTipoConsultaOrigem(TipoConsultaOrigem tipoConsultaOrigem) {
		this.tipoConsultaOrigem = tipoConsultaOrigem;
	}

	/**
	 * Recupera codigo tipo origem informacao.
	 * 
	 * @return codigo tipo origem informacao
	 */
	public String getCodigoTipoOrigemInformacao() {
		return codigoTipoOrigemInformacao;
	}

	/**
	 * Preenche codigo tipo origem informacao.
	 * 
	 * @param codigoTipoOrigemInformacao
	 *            o novo codigo tipo origem informacao
	 */
	public void setCodigoTipoOrigemInformacao(String codigoTipoOrigemInformacao) {
		this.codigoTipoOrigemInformacao = codigoTipoOrigemInformacao;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Integer getId() {
		return getIdMapaTipoAnotacao();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Integer id) {
		setIdMapaTipoAnotacao(id);
	}

}
