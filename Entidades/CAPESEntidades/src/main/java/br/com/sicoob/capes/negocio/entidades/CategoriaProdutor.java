/*
 * SICOOB
 * 
 * CategoriaProdutor.java(br.com.sicoob.capes.negocio.entidades.CategoriaProdutor)
 */
package br.com.sicoob.capes.negocio.entidades;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Entidade que representa a tabela responsável por armazenar as categorias de produtor.
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name="CATEGORIAPRODUTOR", schema = "CLI")
public class CategoriaProdutor extends CAPESEntidade<Short> {

	/** Serial UID.*/
	private static final long serialVersionUID = 3082338825641699150L;

	/** O atributo codigo. */
	@Id
	@Column(name = "CODCATEGORIAPRODUTOR")
	private Short codigo;
	
	/** O atributo descricao. */
	@Column(name = "DESCCATEGORIAPRODUTOR")
	private String descricao;

	/** O atributo valor limite. */
	@Column(name = "VALORLIMITERENDAAGROPECUARIA")
	private BigDecimal valorLimite;
	
	/** O atributo ativo. */
	@Column(name = "BOLATIVO")
	private Boolean ativo;

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
	 * Recupera valor limite.
	 * 
	 * @return valor limite
	 */
	public BigDecimal getValorLimite() {
		return valorLimite;
	}

	/**
	 * Preenche valor limite.
	 * 
	 * @param valorLimite
	 *            o novo valor limite
	 */
	public void setValorLimite(BigDecimal valorLimite) {
		this.valorLimite = valorLimite;
	}
	
	/**
	 * Recupera ativo.
	 * 
	 * @return ativo
	 */
	public Boolean getAtivo() {
		return ativo;
	}

	/**
	 * Preenche ativo.
	 * 
	 * @param ativo
	 *            o novo ativo
	 */
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	@Transient
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
