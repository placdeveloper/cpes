/*
 * SICOOB
 * 
 * TipoEndereco.java(br.com.sicoob.capes.negocio.entidades.TipoEndereco)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.sicoob.capes.negocio.entidades.interfaces.DominioReplicavelLista;

/**
 * Classe que representa o tipo de endereço
 * 
 * 05/05/2011
 * 
 * @author Rodrigo.Chaves
 * 
 */
@Entity
@Table(name = "TIPOENDERECO", schema = "CLI")
public class TipoEndereco extends CAPESEntidade<Short> 
		implements DominioReplicavelLista {

	/** Serial UID */
	private static final long serialVersionUID = 7930643401299344359L;
	
	/** O atributo codigo. */
	@Id
	@Column(name = "CODTIPOENDERECO")
	private Short codigo;
	
	/** O atributo descricao. */
	@Column(length = 60, name = "DESCTIPOENDERECO", nullable = false)
	private String descricao;
	
	/** O atributo tipo pessoa. */
	@JoinColumn(name = "IDTIPOPESSOACONTATO", referencedColumnName = "IDTIPOPESSOACONTATO")
	@ManyToOne
	private TipoPessoaContato tipoPessoaContato;
	
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

	/**
	 * {@inheritDoc}
	 */
	public String getCodigoListaItem() {
		return String.valueOf(getCodigo());
	}

	/**
	 * @return the tipoPessoaContato
	 */
	public TipoPessoaContato getTipoPessoaContato() {
		return tipoPessoaContato;
	}

	/**
	 * @param tipoPessoaContato the tipoPessoaContato to set
	 */
	public void setTipoPessoaContato(TipoPessoaContato tipoPessoaContato) {
		this.tipoPessoaContato = tipoPessoaContato;
	}

}
