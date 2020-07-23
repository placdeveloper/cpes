/*
 * SICOOB
 * 
 * TipoReferencia.java(br.com.sicoob.capes.negocio.entidades.TipoReferencia)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.sicoob.capes.negocio.entidades.interfaces.DominioReplicavelLista;

/**
 * Entidade que representa a tabela responsável por armazenar os tipos de referências.
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name="TIPOREFERENCIAPESSOA", schema = "CLI")
public class TipoReferencia extends CAPESEntidade<Short> 
	implements DominioReplicavelLista{

	/** Serial UID. */
	private static final long serialVersionUID = -4682119026472814354L;

	/** O atributo codigo. */
	@Id
	@Column(name = "CODTIPOREFERENCIAPESSOA")
	private Short codigo;

	/** O atributo descricao. */
	@Column(name = "DESCTIPOREFERENCIAPESSOA")
	private String descricao;

	/**
	 * @return the codigo
	 */
	public Short getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
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
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	@Transient
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

	/** 
	 * {@inheritDoc}
	 */
	public String getCodigoListaItem() {
		return String.valueOf(getCodigo());
	}
}
