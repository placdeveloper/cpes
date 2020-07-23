/*
 * SICOOB
 * 
 * Produto.java(br.com.sicoob.capes.negocio.entidades.legado.Produto)
 */
package br.com.sicoob.capes.negocio.entidades.legado;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * @author Erico.Junior
 *
 */
@Entity
@Table(name = "Produto")
public class Produto extends CAPESEntidadeLegado<Short> {

	/** Serial UID. */
	private static final long serialVersionUID = -7285842648123013407L;

	/** O atributo id produto. */
	@Id
	@Column(name = "idProduto")
	private Short idProduto;

	/** O atributo descricao. */
	@Column (name = "descProduto")
	private String descricao;

	/** O atributo data atual movimento. */
	@Column(name = "dataAtualMovimento")
	private DateTimeDB dataAtualMovimento;

	/**
	 * @return the idProduto
	 */
	public Short getIdProduto() {
		return idProduto;
	}

	/**
	 * @param idProduto the idProduto to set
	 */
	public void setIdProduto(Short idProduto) {
		this.idProduto = idProduto;
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
	 * @return the dataAtualMovimento
	 */
	public DateTimeDB getDataAtualMovimento() {
		return dataAtualMovimento;
	}

	/**
	 * @param dataAtualMovimento the dataAtualMovimento to set
	 */
	public void setDataAtualMovimento(DateTimeDB dataAtualMovimento) {
		this.dataAtualMovimento = dataAtualMovimento;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Short getIdSQL() {
		return getIdProduto();
	}
}
