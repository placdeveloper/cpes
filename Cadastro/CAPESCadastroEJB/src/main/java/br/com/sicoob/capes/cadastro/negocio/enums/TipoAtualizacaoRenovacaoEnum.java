/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.enums;

/**
 * Enum para os tipos de RenovacaoCadastral.
 * @author Valdemar.Xavier
 */
public enum TipoAtualizacaoRenovacaoEnum {

	/** O atributo MANUAL. */
	MANUAL((Integer)1, "Manual"),
	
	/** O atributo AUTOMATICO. */
	AUTOMATICO((Integer)2, "Automático");
	

	/** O atributo idTipoAtualizacaoRenovacao. */
	private Integer idTipoAtualizacaoRenovacao;
	
	/** O atributo descricao. */
	private String descricao;

	/**
	 * TipoRenovacaoCadastralEnum
	 * @param idTipoAtualizacaoRenovacao
	 * @param descricao
	 */
	private TipoAtualizacaoRenovacaoEnum(Integer idTipoAtualizacaoRenovacao, String descricao) {
		this.idTipoAtualizacaoRenovacao = idTipoAtualizacaoRenovacao;
		this.descricao = descricao;
	}

	/**
	 * @return the IdTipoAtualizacaoRenovacao
	 */
	public Integer getIdTipoAtualizacaoRenovacao() {
		return idTipoAtualizacaoRenovacao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

}