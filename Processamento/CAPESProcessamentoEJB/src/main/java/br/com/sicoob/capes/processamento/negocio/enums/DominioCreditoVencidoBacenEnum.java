/**
 * 
 */
package br.com.sicoob.capes.processamento.negocio.enums;

/**
 * Dom�nio do cr�dito vencido na consulta do BACEN.
 * 
 * @author Erico.Junior
 */
public enum DominioCreditoVencidoBacenEnum {

	/** O atributo VENCIDOS_15_30. */
	VENCIDOS_15_30(210, "Cr�ditos vencidos de 15 a 30 dias"), 
	
	/** O atributo VENCIDOS_31_60. */
	VENCIDOS_31_60(220, "Cr�ditos vencidos de 31 a 60 dias"), 
	
	/** O atributo VENCIDOS_61_90. */
	VENCIDOS_61_90(230, "Cr�ditos vencidos de 61 a 90 dias"),

	/** O atributo VENCIDOS_91_120. */
	VENCIDOS_91_120(240, "Cr�ditos vencidos de 91 a 120 dias"), 
	
	/** O atributo VENCIDOS_121_150. */
	VENCIDOS_121_150(245, "Cr�ditos vencidos de 121 a 150 dias"),
	
	/** O atributo VENCIDOS_151_180. */
	VENCIDOS_151_180(250, "Cr�ditos vencidos de 151 a 180 dias"), 
	
	/** O atributo VENCIDOS_181_240. */
	VENCIDOS_181_240(255, "Cr�ditos vencidos de 181 a 240 dias"),
	
	/** O atributo VENCIDOS_241_300. */
	VENCIDOS_241_300(260, "Cr�ditos vencidos de 241 a 300 dias"), 
	
	/** O atributo VENCIDOS_301_360. */
	VENCIDOS_301_360(270, "Cr�ditos vencidos de 301 a 360 dias"),
	
	/** O atributo VENCIDOS_361_540. */
	VENCIDOS_361_540(280, "Cr�ditos vencidos de 361 a 540 dias"),
	
	/** O atributo VENCIDOS_ACIMA_540. */
	VENCIDOS_ACIMA_540(290, "Cr�ditos vencidos acima de 540 dias"),
	
	/** O atributo BAIXADOS_PREJUIZO_ATE_12_MESES. */
	BAIXADOS_PREJUIZO_ATE_12_MESES(310, "Cr�ditos baixados como preju�zo at� 12 meses"),
	
	/** O atributo BAIXADOS_PREJUIZO_12_48_MESES. */
	BAIXADOS_PREJUIZO_12_48_MESES(320, 
			"Cr�ditos baixados como preju�zo h� mais de 12 meses e at� 48 meses"),
	
	/** O atributo BAIXADOS_PREJUIZO_ACIMA_48_MESES. */
	BAIXADOS_PREJUIZO_ACIMA_48_MESES(330, "Cr�ditos baixados como preju�zo h� mais de 42 meses");

	/** O atributo codigo. */
	private Integer codigo;
	
	/** O atributo descricao. */
	private String descricao;

	/**
	 * Construtor do Enum.
	 * @param codigo O c�digo do dom�nio.
	 * @param descricao A descri��o do dom�nio.
	 */
	private DominioCreditoVencidoBacenEnum(Integer codTipoAnotacao, String descricao) {
		this.descricao = descricao;
		this.codigo = codTipoAnotacao;
	}
	
	/**
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	
}
