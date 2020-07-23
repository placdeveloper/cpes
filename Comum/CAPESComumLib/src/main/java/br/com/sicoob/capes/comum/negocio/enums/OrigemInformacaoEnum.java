/*
 * SICOOB
 * 
 * OrigemInformacaoEnum.java(br.com.sicoob.capes.comum.negocio.enums.OrigemInformacaoEnum)
 */
package br.com.sicoob.capes.comum.negocio.enums;

/**
 * Enum para as origens das informa��es. 
 * @author Erico.Junior
 */
public enum OrigemInformacaoEnum {

	/** O atributo BACEN. */
	BACEN((short)1, "Bacen"),
	
	/** O atributo INTERNA. */
	INTERNA((short)2, "Interna"),
	
	/** O atributo RECEITA. */
	RECEITA((short)3, "Receita"),
	
	/** O atributo SERASA. */
	SERASA((short)4, "Serasa"),
	
	/** O atributo SISBR. */
	SISBR((short)5, "Sisbr"),
	
	/** O atributo BOA_VISTA. */
	BOA_VISTA((short)6, "Boa Vista Servi�os"),
	
	/** O atributo ETICA_ONLINE. */
	ETICA_ONLINE((short)7, "�tica On-Line"),
	
	/** O atributo AML_CONSULTING. */
	AML_CONSULTING((short)8, "AML Consulting"),
	
	/** O atributo SPC. */
	SPC((short)9, "SPC Brasil");

	/** O atributo id origem informacao. */
	private Short idOrigemInformacao;
	
	/** O atributo descricao. */
	private String descricao;

	/**
	 * Construtor do Enum.
	 * @param idOrigemInformacao O identificador da origem da informa��o.
	 * @param descricao A descri��o da origem da informa��o.
	 */
	private OrigemInformacaoEnum(Short idOrigemInformacao, String descricao) {
		
		this.descricao = descricao;
		this.idOrigemInformacao = idOrigemInformacao;
	}

	/**
	 * @return the idOrigemInformacao
	 */
	public Short getIdOrigemInformacao() {
		return idOrigemInformacao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

}
