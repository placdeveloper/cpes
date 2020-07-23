/*
 * SICOOB
 * 
 * GrauInstrucao.java(br.com.sicoob.capes.negocio.entidades.GrauInstrucao)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidade que representa grau de instrução.
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name = "GRAUINSTRUCAO", schema = "CLI")
public class GrauInstrucao extends CAPESEntidade<Short> {

	/** Serial UID. */
	private static final long serialVersionUID = 8098466773064380059L;

	/** O atributo codigo. */
	@Id
	@Column(name = "CODGRAUINSTRUCAO")
	private Short codigo;

	/** O atributo descricao. */
	@Column(name = "DESCGRAUINSTRUCAO")
	private String descricao;

	/** O atributo codigo grau instrucao bndes. */
	@Column(name = "CODGRAUINSTRUCAOBNDES")
	private Integer codigoGrauInstrucaoBNDES;
	
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
	 * @return the codigoGrauInstrucaoBNDES
	 */
	public Integer getCodigoGrauInstrucaoBNDES() {
		return codigoGrauInstrucaoBNDES;
	}

	/**
	 * @param codigoGrauInstrucaoBNDES the codigoGrauInstrucaoBNDES to set
	 */
	public void setCodigoGrauInstrucaoBNDES(Integer codigoGrauInstrucaoBNDES) {
		this.codigoGrauInstrucaoBNDES = codigoGrauInstrucaoBNDES;
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
