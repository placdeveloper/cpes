/*
 * SICOOB
 * 
 * DetalheAnotacaoBacenDTO.java(br.com.sicoob.capes.comum.negocio.dto.DetalheAnotacaoBacenDTO)
 */
package br.com.sicoob.capes.comum.negocio.dto;

import java.math.BigDecimal;

/**
 * @author Erico.Junior
 *
 */
public class DetalheAnotacaoBacenDTO extends DetalheAnotacaoDTO {

	/** Serial UID.*/
	private static final long serialVersionUID = 6108524762277800221L;
	
	/** O atributo data base. */
	private String dataBase;
	
	/** O atributo percentual volume processado. */
	private BigDecimal percentualVolumeProcessado;
	
	/** O atributo percentual documentos processados. */
	private BigDecimal percentualDocumentosProcessados;
	
	/** O atributo quantidade instituicoes. */
	private Integer quantidadeInstituicoes;
	
	/** O atributo quantidade operacoes. */
	private Integer quantidadeOperacoes;
	
	/**
	 * @return the dataBase
	 */
	public String getDataBase() {
		return dataBase;
	}
	/**
	 * @param dataBase the dataBase to set
	 */
	public void setDataBase(String dataBase) {
		this.dataBase = dataBase;
	}
	/**
	 * @return the percentualVolumeProcessado
	 */
	public BigDecimal getPercentualVolumeProcessado() {
		return percentualVolumeProcessado;
	}
	/**
	 * @param percentualVolumeProcessado the percentualVolumeProcessado to set
	 */
	public void setPercentualVolumeProcessado(BigDecimal percentualVolumeProcessado) {
		this.percentualVolumeProcessado = percentualVolumeProcessado;
	}
	/**
	 * @return the percentualDocumentosProcessados
	 */
	public BigDecimal getPercentualDocumentosProcessados() {
		return percentualDocumentosProcessados;
	}
	/**
	 * @param percentualDocumentosProcessados the percentualDocumentosProcessados to set
	 */
	public void setPercentualDocumentosProcessados(BigDecimal percentualDocumentosProcessados) {
		this.percentualDocumentosProcessados = percentualDocumentosProcessados;
	}
	/**
	 * @return the quantidadeInstituicoes
	 */
	public Integer getQuantidadeInstituicoes() {
		return quantidadeInstituicoes;
	}
	/**
	 * @param quantidadeInstituicoes the quantidadeInstituicoes to set
	 */
	public void setQuantidadeInstituicoes(Integer quantidadeInstituicoes) {
		this.quantidadeInstituicoes = quantidadeInstituicoes;
	}
	/**
	 * @return the quantidadeOperacoes
	 */
	public Integer getQuantidadeOperacoes() {
		return quantidadeOperacoes;
	}
	/**
	 * @param quantidadeOperacoes the quantidadeOperacoes to set
	 */
	public void setQuantidadeOperacoes(Integer quantidadeOperacoes) {
		this.quantidadeOperacoes = quantidadeOperacoes;
	}
	
}
