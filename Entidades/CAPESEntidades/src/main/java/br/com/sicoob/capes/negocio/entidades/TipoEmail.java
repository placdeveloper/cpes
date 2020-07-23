/*
 * SICOOB
 * 
 * TipoEmail.java(br.com.sicoob.capes.negocio.entidades.TipoEmail)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.sicoob.capes.comum.util.ReflexaoUtils;

/**
 * Classe que representa o tipo de e-mail
 * 
 * 05/05/2011
 * 
 * @author Rodrigo.Chaves
 * 
 */
@Entity
@Table(name = "TIPOEMAIL", schema = "CLI")
public class TipoEmail extends CAPESEntidade<Short> {

	/** Serial UID */
	private static final long serialVersionUID = -1684220150454465103L;
	
	/** O atributo codigo. */
	@Id
	@Column(name = "CODTIPOEMAIL")
	private Short codigo;
	
	/** O atributo descricao. */
	@Column(name = "DESCTIPOEMAIL", length = 60, nullable = false)
	private String descricao;
	
	/** O atributo tipo pessoa. */
	@JoinColumn(name = "IDTIPOPESSOACONTATO", referencedColumnName = "IDTIPOPESSOACONTATO")
	@ManyToOne
	private TipoPessoaContato tipoPessoaContato;

	
	/**
	 * Construtor
	 * 
	 */
	public TipoEmail() {
	}

	/**
	 * Construtor
	 * 
	 * @param codigo
	 */
	public TipoEmail(Short codigo) {
		this.codigo = codigo;
	}

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
	 * @return o valor de tipoPessoaContato
	 */
	public TipoPessoaContato getTipoPessoaContato() {
		return tipoPessoaContato;
	}
	
	/**
	 * @param tipoPessoaContato
	 *            o valor de tipoPessoaContato
	 */
	public void setTipoPessoaContato(TipoPessoaContato tipoPessoaContato) {
		this.tipoPessoaContato = tipoPessoaContato;
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
