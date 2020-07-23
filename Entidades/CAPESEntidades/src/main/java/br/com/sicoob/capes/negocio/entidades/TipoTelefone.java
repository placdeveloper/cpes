/*
 * SICOOB
 * 
 * TipoTelefone.java(br.com.sicoob.capes.negocio.entidades.TipoTelefone)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Classe que representa o tipo de telefone
 * 
 * 05/05/2011
 * 
 * @author Rodrigo.Chaves
 * 
 */
@Entity
@Table(name = "TIPOTELEFONE", schema = "CLI")
public class TipoTelefone extends CAPESEntidade<Short> {

	/** Serial UID */
	private static final long serialVersionUID = 6805630234372396143L;
	
	/** O atributo codigo. */
	@Id
	@Column(name = "CODTIPOTELEFONE")
	private Short codigo;
	
	/** O atributo descricao. */
	@Column(name = "DESCTIPOTELEFONE", length = 60, nullable = false)
	private String descricao;
	
	/** O atributo dddObrigatorio. */
	@Column(name = "BOLDDDOBRIGATORIO")
	private Boolean dddObrigatorio = Boolean.FALSE;
	
	@Column(name = "NUMMINDIGITOS")
	private Short numMinDigitos;
	
	/** O atributo tipo pessoa. */
	@JoinColumn(name = "IDTIPOPESSOACONTATO", referencedColumnName = "IDTIPOPESSOACONTATO")
	@ManyToOne
	private TipoPessoaContato tipoPessoaContato;
	
	/** O atributo ativo. */
	@Column(name="BOLATIVO")
	private Boolean ativo;

	/**
	 * Cria uma nova instância de tipo telefone.
	 */
	public TipoTelefone() {
	}

	/**
	 * Cria uma nova instância de tipo telefone.
	 * 
	 * @param codigo
	 *            the codigo
	 */
	public TipoTelefone(Short codigo) {
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
	 * Recupera o valor de dddObrigatorio.
	 *
	 * @return o valor de dddObrigatorio
	 */
	public Boolean getDddObrigatorio() {
		return dddObrigatorio;
	}

	/**
	 * Define o valor de dddObrigatorio.
	 *
	 * @param dddObrigatorio o novo valor de dddObrigatorio
	 */
	public void setDddObrigatorio(Boolean dddObrigatorio) {
		this.dddObrigatorio = dddObrigatorio;
	}
	
	/**
	 * @return the numMinDigitos
	 */
	public Short getNumMinDigitos() {
		return numMinDigitos;
	}

	/**
	 * @param numMinDigitos the numMinDigitos to set
	 */
	public void setNumMinDigitos(Short numMinDigitos) {
		this.numMinDigitos = numMinDigitos;
	}

	/**
	 * @return the ativo
	 */
	public Boolean getAtivo() {
		return ativo;
	}

	/**
	 * @param ativo the ativo to set
	 */
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}
