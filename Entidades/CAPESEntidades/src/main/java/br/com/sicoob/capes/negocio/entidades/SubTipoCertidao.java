/*
 * SICOOB
 * 
 * SubTipoCertidao.java(br.com.sicoob.capes.negocio.entidades.SubTipoCertidao)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.bancoob.persistencia.annotations.OrdenacaoPadrao;

/**
 * Classe que representa o subtipo de certidão
 * 
 * 12/07/2011
 * 
 * @author Rodrigo.Chaves
 */
@Entity
@Table(name = "SUBTIPOCERTIDAO", schema = "CLI")
@OrdenacaoPadrao(colunas = "DESCSUBTIPOCERTIDAO", descendente = false)
public class SubTipoCertidao extends CAPESEntidade<Short> {

	/** Serial UID */
	private static final long serialVersionUID = 5881437301988896545L;

	/** O atributo codigo. */
	@Id
	@Column(name = "CODSUBTIPOCERTIDAO")
	private Short codigo;
	
	/** O atributo descricao. */
	@Column(name = "DESCSUBTIPOCERTIDAO", length=200, nullable = false)
	private String descricao;

	/**
	 * @return o valor de codigo
	 */
	public Short getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            o valor de codigo
	 */
	public void setCodigo(Short codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return o valor de descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            o valor de descricao
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
