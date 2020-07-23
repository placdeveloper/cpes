/*
 * SICOOB
 * 
 * DetalheAnotacaoInternaDTO.java(br.com.sicoob.capes.comum.negocio.dto.DetalheAnotacaoInternaDTO)
 */
package br.com.sicoob.capes.comum.negocio.dto;


/**
 * Classe que representa os detalhes das anotações internas. 
 * @author Erico.Junior
 */
public class DetalheAnotacaoInternaDTO extends DetalheAnotacaoDTO {

	/** Serial UID.*/
	private static final long serialVersionUID = 2175490968444524642L;
	
	/** O atributo quantidade. */
	private Short quantidade;
	
	/** O atributo id instituicao. */
	private Integer idInstituicao;
	
	/** O atributo id unidade inst. */
	private Integer idUnidadeInst;
	
	/** O atributo responsavel. */
	private String responsavel;
	
	/** O atributo observacao. */
	private String observacao;
	
	/** O atributo numero. */
	private String numero;
	
	/**
	 * @return the quantidade
	 */
	public Short getQuantidade() {
		return quantidade;
	}
	/**
	 * @param quantidade the quantidade to set
	 */
	public void setQuantidade(Short quantidade) {
		this.quantidade = quantidade;
	}
	/**
	 * @return the idInstituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}
	/**
	 * @param idInstituicao the idInstituicao to set
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	/**
	 * @return the idUnidadeInst
	 */
	public Integer getIdUnidadeInst() {
		return idUnidadeInst;
	}
	/**
	 * @param idUnidadeInst the idUnidadeInst to set
	 */
	public void setIdUnidadeInst(Integer idUnidadeInst) {
		this.idUnidadeInst = idUnidadeInst;
	}
	/**
	 * @return the responsavel
	 */
	public String getResponsavel() {
		return responsavel;
	}
	/**
	 * @param responsavel the responsavel to set
	 */
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
	/**
	 * @return the observacao
	 */
	public String getObservacao() {
		return observacao;
	}
	/**
	 * @param observacao the observacao to set
	 */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}
	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}
		
}
