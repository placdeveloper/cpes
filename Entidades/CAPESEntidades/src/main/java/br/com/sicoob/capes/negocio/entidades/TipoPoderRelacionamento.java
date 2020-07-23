/*
 * SICOOB
 * 
 * TipoPoderRelacionamento.java(br.com.sicoob.capes.negocio.entidades.TipoPoderRelacionamento)
 */
package br.com.sicoob.capes.negocio.entidades;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import br.com.bancoob.persistencia.annotations.OrdenacaoPadrao;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;

/**
 * Entidade que representa os tipo de poder dos relacionamentos
 *
 * 29/08/2011
 * @author rodrigo.chaves
 */
@Entity
@Table(schema = "CLI", name = "TIPOPODERRELACIONAMENTO")
@OrdenacaoPadrao(colunas = "DESCTIPOPODERRELACIONAMENTO", descendente = false)
public class TipoPoderRelacionamento extends CAPESEntidade<Short> {

	/** Serial UID */
	private static final long serialVersionUID = -2911530960230681869L;
	
	/** O atributo codigo. */
	@Id
	@Column(name = "CODTIPOPODERRELACIONAMENTO")
	private Short codigo;
	
	/** O atributo descricao. */
	@Column(name = "DESCTIPOPODERRELACIONAMENTO", length = 200, nullable = false)
	private String descricao;
	
	/** O atributo relacionamento pessoa. */
	@ManyToMany(mappedBy = "poderes", fetch = FetchType.LAZY)
	private Set<RelacionamentoPessoa> relacionamentoPessoa;
	
	/**
	 * @return o valor de codigo
	 */
	public Short getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo o valor de codigo
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
	 * @param descricao o valor de descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	/**
	 * Recupera relacionamento pessoa.
	 * 
	 * @return relacionamento pessoa
	 */
	public Set<RelacionamentoPessoa> getRelacionamentoPessoa() {
		return relacionamentoPessoa;
	}

	/**
	 * Preenche relacionamento pessoa.
	 * 
	 * @param relacionamentoPessoa
	 *            o novo relacionamento pessoa
	 */
	public void setRelacionamentoPessoa(Set<RelacionamentoPessoa> relacionamentoPessoa) {
		this.relacionamentoPessoa = relacionamentoPessoa;
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
