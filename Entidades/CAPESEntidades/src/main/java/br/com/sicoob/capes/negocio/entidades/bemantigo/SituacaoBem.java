/*
 * SICOOB
 * 
 * SituacaoBem.java(br.com.sicoob.capes.negocio.entidades.bemantigo.SituacaoBem)
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
import javax.persistence.Table;

import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.interfaces.DominioReplicavelLista;
import flexjson.JSON;

/**
 * Entidade que representa a tabela com as situações dos bens.
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name = "SITUACAOBEMPESSOA", schema = "CLI")
public class SituacaoBem extends CAPESEntidade<Short> implements
		DominioReplicavelLista {

	/** Serial UID. */
	private static final long serialVersionUID = 8385979760699207199L;
	
	/** A constante CODIGO_SITUACAO_LIVRE. */
	public static final Short CODIGO_SITUACAO_LIVRE = (short) 1;

	/** O atributo codigo. */
	@Id
	@Column(name = "CODSITUACAOBEMPESSOA")
	private Short codigo;

	/** O atributo descricao. */
	@Column(name = "DESCSITUACAOBEMPESSOA")
	private String descricao;

	/** O atributo cadastra onus. */
	@Column(name = "BOLCADASTRAONUS")
	private Boolean cadastraOnus = Boolean.FALSE;

	/** O atributo subtipos. */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "SITUACAOSUBTIPOBEM", schema = "CLI",
			joinColumns = { @JoinColumn(name = "CODSITUACAOBEMPESSOA") }, 
			inverseJoinColumns = { @JoinColumn(name = "CODSUBTIPOBEM") })
	private Set<SubTipoBem> subtipos;

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
	 * @return the cadastraOnus
	 */
	public Boolean getCadastraOnus() {
		return cadastraOnus;
	}

	/**
	 * @param cadastraOnus
	 *            the cadastraOnus to set
	 */
	public void setCadastraOnus(Boolean cadastraOnus) {
		this.cadastraOnus = cadastraOnus;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Short getId() {
		return getCodigo();
	}

	/**
	 * @return the subtipos
	 */
	@JSON(include=false)
	public Set<SubTipoBem> getSubtipos() {
		return subtipos;
	}

	/**
	 * @param subtipos
	 *            the subtipos to set
	 */
	public void setSubtipos(Set<SubTipoBem> subtipos) {
		this.subtipos = subtipos;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Short id) {
		setCodigo(id);
	}

	/** 
	 * {@inheritDoc}
	 */
	public String getCodigoListaItem() {
		return String.valueOf(getCodigo());
	}

}
