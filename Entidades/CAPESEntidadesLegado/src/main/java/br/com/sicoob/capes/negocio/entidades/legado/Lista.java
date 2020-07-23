/*
 * SICOOB
 * 
 * Lista.java(br.com.sicoob.capes.negocio.entidades.legado.Lista)
 */
package br.com.sicoob.capes.negocio.entidades.legado;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.sicoob.capes.comum.util.ReflexaoUtils;

/**
 * Entidade que representa uma lista.
 * @author Erico.Junior
 */
@Entity
@Table (name="Lista")
public class Lista extends CAPESEntidadeLegado<Integer> {

	/** Serial UID. */
	private static final long serialVersionUID = -6176395267748643255L;

	/** Identificador da lista. */
	@Id
	private Integer idLista;

	/** O atributo descricao. */
	@Column (name = "DescLista")
	private String descricao;

	/** O atributo cod tipo ident. */
	@Column (name = "CodTipoIdent")
	private Integer codTipoIdent;

	/** O atributo id produto. */
	@Column (name = "IDProduto")
	private Integer idProduto;
	
	/** O atributo bol adm bancoob. */
	@Column (name = "BolAdmBancoob")
	private Boolean bolAdmBancoob = Boolean.FALSE;
	
	/** Coleção dos itens de uma lista. */
	@OneToMany (mappedBy = "pk.lista", targetEntity = ListaItem.class)
	private List<ListaItem> itens;

	/**
	 * @return the idLista
	 */
	public Integer getIdLista() {
		return idLista;
	}

	/**
	 * @param idLista the idLista to set
	 */
	public void setIdLista(Integer idLista) {
		this.idLista = idLista;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the codTipoIdent
	 */
	public Integer getCodTipoIdent() {
		return codTipoIdent;
	}

	/**
	 * @param codTipoIdent the codTipoIdent to set
	 */
	public void setCodTipoIdent(Integer codTipoIdent) {
		this.codTipoIdent = codTipoIdent;
	}

	/**
	 * @return the idProduto
	 */
	public Integer getIdProduto() {
		return idProduto;
	}

	/**
	 * @param idProduto the idProduto to set
	 */
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	/**
	 * @return the bolAdmBancoob
	 */
	public Boolean getBolAdmBancoob() {
		return bolAdmBancoob;
	}

	/**
	 * @param bolAdmBancoob the bolAdmBancoob to set
	 */
	public void setBolAdmBancoob(Boolean bolAdmBancoob) {
		this.bolAdmBancoob = bolAdmBancoob;
	}

	/**
	 * @return the itens
	 */
	public List<ListaItem> getItens() {
		return itens;
	}

	/**
	 * @param itens the itens to set
	 */
	public void setItens(List<ListaItem> itens) {
		this.itens = itens;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Integer getIdSQL() {
		return getIdLista();
	}
	
	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		return ReflexaoUtils.equals(this, obj, "idLista");
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "idLista");
	}	

}
