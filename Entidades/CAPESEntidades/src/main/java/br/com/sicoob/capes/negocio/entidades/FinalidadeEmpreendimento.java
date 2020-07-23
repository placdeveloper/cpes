/*
 * SICOOB
 * 
 * FinalidadeEmpreendimento.java(br.com.sicoob.capes.negocio.entidades.FinalidadeEmpreendimento)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.bancoob.persistencia.annotations.OrdenacaoPadrao;

/**
 * Entidade que representa a finalidade do empreendimento.
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name = "FINALIDADEEMPREENDIMENTO", schema = "CLI")
@OrdenacaoPadrao(colunas = "DESCFINALIDADEEMPREENDIMENTO", descendente = false)
public class FinalidadeEmpreendimento extends CAPESEntidade<Short> {

	/** Serial UID. */
	private static final long serialVersionUID = -6290761332131363408L;

	/** O atributo codigo. */
	@Id
	@Column(name = "CODFINALIDADEEMPREENDIMENTO")
	private Short codigo;

	/** O atributo descricao. */
	@Column(name = "DESCFINALIDADEEMPREENDIMENTO")
	private String descricao;

	/**
	 * Recupera codigo.
	 * 
	 * @return codigo
	 */
	public Short getCodigo() {
		return codigo;
	}

	/**
	 * Preenche codigo.
	 * 
	 * @param codigo
	 *            o novo codigo
	 */
	public void setCodigo(Short codigo) {
		this.codigo = codigo;
	}

	/**
	 * Recupera descricao.
	 * 
	 * @return descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Preenche descricao.
	 * 
	 * @param descricao
	 *            o novo descricao
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
	public void setId(Short identificador) {
		setCodigo(identificador);
	}

}
