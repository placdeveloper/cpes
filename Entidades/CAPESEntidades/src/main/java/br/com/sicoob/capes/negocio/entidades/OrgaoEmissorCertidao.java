/*
 * SICOOB
 * 
 * OrgaoEmissorCertidao.java(br.com.sicoob.capes.negocio.entidades.OrgaoEmissorCertidao)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.bancoob.persistencia.annotations.OrdenacaoPadrao;

/**
 * Classe que representa o órgão emissor de certidões
 * 
 * 12/07/2011
 * 
 * @author Rodrigo.Chaves
 */
@Entity
@Table(name = "ORGAOEMISSORCERTIDAO", schema = "CLI")
@OrdenacaoPadrao(colunas = "DESCORGAOEMISSORCERTIDAO", descendente = false)
public class OrgaoEmissorCertidao extends CAPESEntidade<Short> {

	/** Serial UID */
	private static final long serialVersionUID = 6863493825294087436L;

	/** O atributo codigo. */
	@Id
	@Column(name = "CODORGAOEMISSORCERTIDAO")
	private Short codigo;

	/** O atributo descricao. */
	@Column(name = "DESCORGAOEMISSORCERTIDAO", length = 200, nullable = false)
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
