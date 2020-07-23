/*
 * SICOOB
 * 
 * TipoFormaConstituicao.java(br.com.sicoob.capes.negocio.entidades.TipoFormaConstituicao)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.bancoob.persistencia.annotations.OrdenacaoPadrao;

/**
 * Entidade que representa o tipo da forma de constituição da empresa.
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name = "TIPOFORMACONSTITUICAO", schema = "CLI")
@OrdenacaoPadrao(colunas = "DESCTIPOFORMACONSTITUICAO", descendente = false)
public class TipoFormaConstituicao extends CAPESEntidade<Short> {

	/** Serial UID. */
	private static final long serialVersionUID = -7781481588474606430L;

	/** O atributo codigo. */
	@Id
	@Column(name = "CODTIPOFORMACONSTITUICAO")
	private Short codigo;

	/** O atributo descricao. */
	@Column(name = "DESCTIPOFORMACONSTITUICAO")
	private String descricao;

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
