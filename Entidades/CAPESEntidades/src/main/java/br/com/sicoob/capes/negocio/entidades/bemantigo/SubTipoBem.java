/*
 * SICOOB
 * 
 * SubTipoBem.java(br.com.sicoob.capes.negocio.entidades.bemantigo.SubTipoBem)
 */
package br.com.sicoob.capes.negocio.entidades.bemantigo;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.bancoob.persistencia.annotations.OrdenacaoPadrao;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.UnidadeMedida;
import br.com.sicoob.capes.negocio.entidades.interfaces.DominioReplicavel;
import flexjson.JSON;

/**
 * Entidade que representa a tabela com os subtipos dos bens.
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name = "SUBTIPOBEM", schema = "CLI")
@OrdenacaoPadrao(colunas="CODSUBTIPOBEM",descendente=false)
public class SubTipoBem extends CAPESEntidade<Short> implements
		DominioReplicavel {

	/** Serial UID. */
	private static final long serialVersionUID = 1653007788509613940L;

	/** A constante CODIGO_SUBTIPO_SEM_PATRIMONIO. */
	public static final Short CODIGO_SUBTIPO_SEM_PATRIMONIO = (short) 16;
	
	/** O atributo codigo. */
	@Id
	@Column(name = "CODSUBTIPOBEM")
	private Short codigo;

	/** O atributo descricao. */
	@Column(name = "DESCSUBTIPOBEM")
	private String descricao;

	/** O atributo tipo bem. */
	@JoinColumn(name = "CODTIPOBEM", referencedColumnName = "CODTIPOBEM")
	@ManyToOne
	private TipoBem tipoBem;
	
	/** O atributo cod tipo bem. */
	@Column(name="CODTIPOBEM", insertable=false, updatable=false)
	private Short codTipoBem;
	
	/** O atributo cadastra registro. */
	@Column(name = "BOLCADASTRAREGISTRO")
	private Boolean cadastraRegistro = Boolean.FALSE;

	/** O atributo cadastra posse. */
	@Column(name = "BOLCADASTRAPOSSE")
	private Boolean cadastraPosse = Boolean.FALSE;
	
	/** O atributo subtipos. */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "SUBTIPOBEMUNIDADEMEDIDA", schema = "CLI",
			joinColumns = { @JoinColumn(name = "CODSUBTIPOBEM") }, 
			inverseJoinColumns = { @JoinColumn(name = "CODUNIDADEMEDIDA") })
	private Set<UnidadeMedida> unidadesMedida;
	
	/**
	 * @return the codigo
	 */
	public Short getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(Short codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the tipoBem
	 */
	public TipoBem getTipoBem() {
		return tipoBem;
	}

	/**
	 * @param tipoBem
	 *            the tipoBem to set
	 */
	public void setTipoBem(TipoBem tipoBem) {
		this.tipoBem = tipoBem;
	}

	/**
	 * @return the cadastraRegistro
	 */
	public Boolean getCadastraRegistro() {
		return cadastraRegistro;
	}

	/**
	 * @param cadastraRegistro
	 *            the cadastraRegistro to set
	 */
	public void setCadastraRegistro(Boolean cadastraRegistro) {
		this.cadastraRegistro = cadastraRegistro;
	}

	/**
	 * @return the cadastraPosse
	 */
	public Boolean getCadastraPosse() {
		return cadastraPosse;
	}

	/**
	 * @param cadastraPosse
	 *            the cadastraPosse to set
	 */
	public void setCadastraPosse(Boolean cadastraPosse) {
		this.cadastraPosse = cadastraPosse;
	}

	/**
	 * @return the codTipoBem
	 */
	public Short getCodTipoBem() {
		return codTipoBem;
	}

	/**
	 * @param codTipoBem the codTipoBem to set
	 */
	public void setCodTipoBem(Short codTipoBem) {
		this.codTipoBem = codTipoBem;
	}
	
	/**
	 * @return the subtipos
	 */
	@JSON(include=false)
	public Set<UnidadeMedida> getUnidadesMedida() {
		return unidadesMedida;
	}

	/**
	 * @param subtipos the subtipos to set
	 */
	public void setUnidadesMedida(Set<UnidadeMedida> unidadesMedida) {
		this.unidadesMedida = unidadesMedida;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Short getId() {
		return getCodigo();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Short id) {
		setCodigo(id);
	}

}
