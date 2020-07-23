/*
 * SICOOB
 * 
 * AnotacaoSisbrDTO.java(br.com.sicoob.capes.comum.negocio.dto.AnotacaoSisbrDTO)
 */
package br.com.sicoob.capes.comum.negocio.dto;

import java.math.BigDecimal;
import java.util.Date;



/**
 * DTO utilizado para representar as informações de anotações provenientes do
 * SISBR.
 * 
 * @author Erico.Junior
 */
public class AnotacaoSisbrDTO extends AnotacaoDTO {

	/** Serial UID. */
	private static final long serialVersionUID = 7774323587954266752L;

	/** O atributo num pessoa. */
	private Integer numPessoa;
	
	/** O atributo id produto. */
	private Integer idProduto;
	
	/** O atributo id modalidade produto. */
	private Integer idModalidadeProduto;
	
	/** O atributo numero contrato. */
	private String numeroContrato;
	
	/** O atributo codigo tipo anotacao. */
	private Integer codigoTipoAnotacao;
	
	/** O atributo data ocorrencia. */
	private Date dataOcorrencia;
	
	/** O atributo data vencimento. */
	private Date dataVencimento;
	
	/** O atributo valor. */
	private BigDecimal valor;

	/**
	 * @return the numPessoa
	 */
	public Integer getNumPessoa() {
		return numPessoa;
	}

	/**
	 * @param numPessoa
	 *            the numPessoa to set
	 */
	public void setNumPessoa(Integer numPessoa) {
		this.numPessoa = numPessoa;
	}

	/**
	 * @return the idProduto
	 */
	public Integer getIdProduto() {
		return idProduto;
	}

	/**
	 * @param idProduto
	 *            the idProduto to set
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
	 * @param idModalidadeProduto
	 *            the idModalidadeProduto to set
	 */
	public void setIdModalidadeProduto(Integer idModalidadeProduto) {
		this.idModalidadeProduto = idModalidadeProduto;
	}
	
	/**
	 * @return the codigoTipoAnotacao
	 */
	public Integer getCodigoTipoAnotacao() {
		return codigoTipoAnotacao;
	}

	/**
	 * @param codigoTipoAnotacao
	 *            the codigoTipoAnotacao to set
	 */
	public void setCodigoTipoAnotacao(Integer codigoTipoAnotacao) {
		this.codigoTipoAnotacao = codigoTipoAnotacao;
	}
	
	/**
	 * @return the dataOcorrencia
	 */
	public Date getDataOcorrencia() {
		return dataOcorrencia;
	}

	/**
	 * @param dataOcorrencia
	 *            the dataOcorrencia to set
	 */
	public void setDataOcorrencia(Date dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}

	/**
	 * @return the numeroContrato
	 */
	public String getNumeroContrato() {
		return numeroContrato;
	}

	/**
	 * @param numeroContrato
	 *            the numeroContrato to set
	 */
	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	/**
	 * @return the dataVencimento
	 */
	public Date getDataVencimento() {
		return dataVencimento;
	}

	/**
	 * @param dataVencimento
	 *            the dataVencimento to set
	 */
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	
	/**
	 * @return the valor
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * @param valor
	 *            the valor to set
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}