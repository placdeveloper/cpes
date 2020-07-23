/*
 * SICOOB
 * 
 * VinculoEmpregaticio.java(br.com.sicoob.capes.negocio.entidades.VinculoEmpregaticio)
 */
package br.com.sicoob.capes.negocio.entidades;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.bancoob.persistencia.annotations.OrdenacaoPadrao;

/**
 * Entidade que representa vinculo empregatício.
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name = "VINCULOEMPREGATICIO", schema = "CLI")
@OrdenacaoPadrao(colunas = "DESCVINCULOEMPREGATICIO", descendente = false)
public class VinculoEmpregaticio extends CAPESEntidade<Short> {

	/** Serial UID. */
	private static final long serialVersionUID = -7781481588474606430L;

	/** O atributo codigo. */
	@Id
	@Column(name = "CODVINCULOEMPREGATICIO")
	private Short codigo;

	/** O atributo descricao. */
	@Column(name = "DESCVINCULOEMPREGATICIO")
	private String descricao;
	
	/** O atributo valorRendaMinimaObrigatoria. */
	@Column(name = "VALORRENDAMINIMAOBRIGATORIA")
	private BigDecimal valorRendaMinimaObrigatoria;

	/**
	 * @return the codigo
	 */
	public Short getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(Short codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            the descricao to set
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
	 * Recupera o valor de valorRendaMinimaObrigatoria.
	 *
	 * @return o valor de valorRendaMinimaObrigatoria
	 */
	public BigDecimal getValorRendaMinimaObrigatoria() {
		return valorRendaMinimaObrigatoria;
	}

	/**
	 * Define o valor de valorRendaMinimaObrigatoria.
	 *
	 * @param valorRendaMinimaObrigatoria o novo valor de valorRendaMinimaObrigatoria
	 */
	public void setValorRendaMinimaObrigatoria(BigDecimal valorRendaMinimaObrigatoria) {
		this.valorRendaMinimaObrigatoria = valorRendaMinimaObrigatoria;
	}

}
