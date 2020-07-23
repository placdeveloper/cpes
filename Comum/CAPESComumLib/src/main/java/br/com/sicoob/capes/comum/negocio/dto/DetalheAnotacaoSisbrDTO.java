/*
 * SICOOB
 * 
 * DetalheAnotacaoSisbrDTO.java(br.com.sicoob.capes.comum.negocio.dto.DetalheAnotacaoSisbrDTO)
 */
package br.com.sicoob.capes.comum.negocio.dto;

import br.com.sicoob.tipos.DateTime;

/**
 * Classe que representa os detalhes das anotações do SISBR.  
 * @author Erico.Junior
 */
public class DetalheAnotacaoSisbrDTO extends DetalheAnotacaoDTO {

	/** Serial UID.*/
	private static final long serialVersionUID = 4918150743374846791L;
	
	/** O atributo numero cooperativa. */
	private String numeroCooperativa;
	
	/** O atributo descricao produto. */
	private String descricaoProduto;
	
	/** O atributo descricao modalidade. */
	private String descricaoModalidade;
	
	/** O atributo contrato. */
	private String contrato;
	
	/** O atributo data vencimento. */
	private DateTime dataVencimento;

	/**
	 * @return the numeroCooperativa
	 */
	public String getNumeroCooperativa() {
		return numeroCooperativa;
	}

	/**
	 * @param numeroCooperativa the numeroCooperativa to set
	 */
	public void setNumeroCooperativa(String numeroCooperativa) {
		this.numeroCooperativa = numeroCooperativa;
	}

	/**
	 * @return the descricaoProduto
	 */
	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	/**
	 * @param descricaoProduto the descricaoProduto to set
	 */
	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	/**
	 * @return the descricaoModalidade
	 */
	public String getDescricaoModalidade() {
		return descricaoModalidade;
	}

	/**
	 * @param descricaoModalidade the descricaoModalidade to set
	 */
	public void setDescricaoModalidade(String descricaoModalidade) {
		this.descricaoModalidade = descricaoModalidade;
	}

	/**
	 * @return the contrato
	 */
	public String getContrato() {
		return contrato;
	}

	/**
	 * @param contrato the contrato to set
	 */
	public void setContrato(String contrato) {
		this.contrato = contrato;
	}

	/**
	 * @return the dataVencimento
	 */
	public DateTime getDataVencimento() {
		return dataVencimento;
	}

	/**
	 * @param dataVencimento the dataVencimento to set
	 */
	public void setDataVencimento(DateTime dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

}
