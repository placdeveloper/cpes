/*
 * SICOOB
 * 
 * AnotacaoSisbr.java(br.com.sicoob.capes.negocio.entidades.AnotacaoSisbr)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * Entidade que representa o detalhe da anotação proveniente do SISBR.
 * @author Erico.Junior
 */
@Entity
@Table(name="ANOTACAOSISBR", schema="CLI")
public class AnotacaoSisbr extends Anotacao {

	/** Serial UID.*/
	private static final long serialVersionUID = 5542449026253583453L;

	/** O atributo id produto. */
	private Integer idProduto;
	
	/** O atributo id modalidade produto. */
	private Integer idModalidadeProduto;
	
	/** O atributo numero contrato. */
	@Column(name = "NUMCONTRATO")
	private String numeroContrato;
	
	/** O atributo data vencimento. */
	private DateTimeDB dataVencimento;
	
	/** O atributo instituicao modalidade produto. */
	@Embedded
	private Instituicao instituicaoModalidadeProduto;	
	
	/**
	 * @return the idProduto
	 */
	public Integer getIdProduto() {
		return idProduto;
	}

	/**
	 * @param idProduto the idProduto to set
	 */
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	/**
	 * @return the idModalidadeProduto
	 */
	public Integer getIdModalidadeProduto() {
		return idModalidadeProduto;
	}

	/**
	 * @param idModalidadeProduto the idModalidadeProduto to set
	 */
	public void setIdModalidadeProduto(Integer idModalidadeProduto) {
		this.idModalidadeProduto = idModalidadeProduto;
	}

	/**
	 * @return the numeroContrato
	 */
	public String getNumeroContrato() {
		return numeroContrato;
	}

	/**
	 * @param numeroContrato the numeroContrato to set
	 */
	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	/**
	 * @return the dataVencimento
	 */
	public DateTimeDB getDataVencimento() {
		return dataVencimento;
	}

	/**
	 * @param dataVencimento the dataVencimento to set
	 */
	public void setDataVencimento(DateTimeDB dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	/**
	 * @return the instituicaoModalidadeProduto
	 */
	public Instituicao getInstituicaoModalidadeProduto() {
		return instituicaoModalidadeProduto;
	}

	/**
	 * @param instituicaoModalidadeProduto the instituicaoModalidadeProduto to set
	 */
	public void setInstituicaoModalidadeProduto(Instituicao instituicaoModalidadeProduto) {
		this.instituicaoModalidadeProduto = instituicaoModalidadeProduto;
	}

}
