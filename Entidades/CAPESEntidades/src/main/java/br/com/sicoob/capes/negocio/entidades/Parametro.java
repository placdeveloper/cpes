/*
 * SICOOB
 * 
 * TipoEmail.java(br.com.sicoob.capes.negocio.entidades.TipoEmail)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.capes.comum.util.ReflexaoUtils;

/**
 * 
 * @author Paulo.Stoppa
 *
 */
@Entity
@Table(name = "PARAMETROCAPES", schema = "CLI")
public class Parametro extends CAPESEntidade<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6079056723076470780L;

	/**
	 * O atributo codigo.
	 */
	@Id
	@Column(name = "CODPARAMETRO")
	private Integer codigo;

	/**
	 * O atributo descricao.
	 */
	@Column(name = "DESCPARAMETRO")
	private String descricao;

	/**
	 * Atributo valor
	 */
	@Column(name = "DESCVALOR", length = 200, nullable = false)
	private String valor;

	@Column(name = "BOLATIVO")
	private boolean ativo;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public void setId(Integer id) {
		setCodigo(id);
	}

	@Override
	public Integer getId() {
		return getCodigo();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return this.descricao != null ? this.descricao : "";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "codigo");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		return ReflexaoUtils.equals(this, obj, "codigo");
	}

}
