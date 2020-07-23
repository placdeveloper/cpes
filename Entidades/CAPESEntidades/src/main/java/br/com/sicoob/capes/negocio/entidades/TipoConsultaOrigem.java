/*
 * SICOOB
 * 
 * TipoConsultaOrigem.java(br.com.sicoob.capes.negocio.entidades.TipoConsultaOrigem)
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
 * The Class TipoConsultaOrigem.
 */
@Entity
@Table(name = "TIPOCONSULTAORIGEM", schema = "CLI")
public class TipoConsultaOrigem extends CAPESEntidade<Integer> {
	
	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -1887555985331974397L;

	/** A Constante RFB_FISICA. */	public static final Integer RFB_FISICA = 2;
	
	/** A Constante RFB_JURIDICA. */
	public static final Integer RFB_JURIDICA = 3;
	
	/** O atributo id tipo consulta origem. */
	@Id
	@Column(name = "CODTIPOCONSULTAORIGEM")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTipoConsultaOrigem;

	/** O atributo nome tipo consulta origem. */
	@Column(name = "NOMETIPOCONSULTAORIGEM")
	private String nomeTipoConsultaOrigem;

	/** O atributo descricao tipo consulta origem. */
	@Column(name = "DESCTIPOCONSULTAORIGEM")
	private String descricaoTipoConsultaOrigem;

	/** O atributo origem informacao. */
	@ManyToOne
	@JoinColumn(name = "IDORIGEMINFO", referencedColumnName = "IDORIGEMINFO")
	private OrigemInformacao origemInformacao;
	
	/**
	 * Cria uma nova instância de tipo consulta origem.
	 */
	public TipoConsultaOrigem() {
		super();
	}

	/**
	 * Cria uma nova instância de tipo consulta origem.
	 * 
	 * @param idTipoConsultaOrigem
	 *            the id tipo consulta origem
	 */
	public TipoConsultaOrigem(Integer idTipoConsultaOrigem) {

		this.idTipoConsultaOrigem = idTipoConsultaOrigem;
	}

	/**
	 * Recupera id tipo consulta origem.
	 * 
	 * @return id tipo consulta origem
	 */
	public Integer getIdTipoConsultaOrigem() {
		return idTipoConsultaOrigem;
	}

	/**
	 * Preenche id tipo consulta origem.
	 * 
	 * @param idTipoConsultaOrigem
	 *            o novo id tipo consulta origem
	 */
	public void setIdTipoConsultaOrigem(Integer idTipoConsultaOrigem) {
		this.idTipoConsultaOrigem = idTipoConsultaOrigem;
	}

	/**
	 * Recupera nome tipo consulta origem.
	 * 
	 * @return nome tipo consulta origem
	 */
	public String getNomeTipoConsultaOrigem() {
		return nomeTipoConsultaOrigem;
	}

	/**
	 * Preenche nome tipo consulta origem.
	 * 
	 * @param nomeTipoConsultaOrigem
	 *            o novo nome tipo consulta origem
	 */
	public void setNomeTipoConsultaOrigem(String nomeTipoConsultaOrigem) {
		this.nomeTipoConsultaOrigem = nomeTipoConsultaOrigem;
	}

	/**
	 * Recupera descricao tipo consulta origem.
	 * 
	 * @return descricao tipo consulta origem
	 */
	public String getDescricaoTipoConsultaOrigem() {
		return descricaoTipoConsultaOrigem;
	}

	/**
	 * Preenche descricao tipo consulta origem.
	 * 
	 * @param descricaoTipoConsultaOrigem
	 *            o novo descricao tipo consulta origem
	 */
	public void setDescricaoTipoConsultaOrigem(String descricaoTipoConsultaOrigem) {
		this.descricaoTipoConsultaOrigem = descricaoTipoConsultaOrigem;
	}

	/**
	 * Recupera origem informacao.
	 * 
	 * @return origem informacao
	 */
	public OrigemInformacao getOrigemInformacao() {
		return origemInformacao;
	}

	/**
	 * Preenche origem informacao.
	 * 
	 * @param origemInformacao
	 *            o novo origem informacao
	 */
	public void setOrigemInformacao(OrigemInformacao origemInformacao) {
		this.origemInformacao = origemInformacao;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Integer getId() {
		return getIdTipoConsultaOrigem();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Integer id) {
		setIdTipoConsultaOrigem(id);
	}

}