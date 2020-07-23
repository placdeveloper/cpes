/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.enums;

/**
 * Enum para os tipos de baixas. 
 * @author Erico.Junior
 */
public enum TipoBaixaEnum {

	/** O atributo BAIXA_MANUAL. */
	BAIXA_MANUAL((short)1, "Baixa manual"),
	
	/** O atributo BAIXA_AUTOMATICA_NOVA_CONSULTA. */
	BAIXA_AUTOMATICA_NOVA_CONSULTA((short)2, "Baixa Automática (Nova Consulta)"),
	
	/** O atributo BAIXA_AUTOMATICA_SISBR. */
	BAIXA_AUTOMATICA_SISBR((short)3, "Baixa Automática (SISBR)");

	/** O atributo idTipoBaixa. */
	private Short idTipoBaixa;
	
	/** O atributo descricao. */
	private String descricao;
	
	/**
	 * Construtor do Enum.
	 * 
	 * @param codigo
	 *            O identificador do tipo da baixa.
	 * @param descricao
	 *            A descrição do tipo da baixa.
	 */
	private TipoBaixaEnum(Short idTipoBaixa, String descricao) {
		this.idTipoBaixa = idTipoBaixa;
		this.descricao = descricao;
	}

	/**
	 * @return the idTipoBaixa
	 */
	public Short getIdTipoBaixa() {
		return idTipoBaixa;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

}
