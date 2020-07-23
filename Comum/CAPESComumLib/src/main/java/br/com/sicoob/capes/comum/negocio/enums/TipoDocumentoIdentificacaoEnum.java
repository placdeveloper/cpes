/*
 * SICOOB
 * 
 * TipoDocumentoIdentificacaoEnum.java(br.com.sicoob.capes.comum.negocio.enums.TipoDocumentoIdentificacaoEnum)
 */
package br.com.sicoob.capes.comum.negocio.enums;

/**
 * @author Erico.Junior
 *
 */
public enum TipoDocumentoIdentificacaoEnum {

	/** O atributo CARTEIRA_IDENTIDADE. */
	CARTEIRA_IDENTIDADE((short)1, "CARTEIRA DE IDENTIDADE"),
	
	/** O atributo RIC. */
	RIC((short)2, "REGISTRO ÚNICO DE IDENTIDADE CIVIL RIC"), 
	
	/** O atributo CARTEIRA_IDENTIDADE_PROFISSIONAL. */
	CARTEIRA_IDENTIDADE_PROFISSIONAL((short)3, "CARTEIRA DE IDENTIDADE PROFISSIONAL"),
	
	/** O atributo CARTEIRA_IDENTIDADE_MILITAR. */
	CARTEIRA_IDENTIDADE_MILITAR((short)4, "CARTEIRA DE IDENTIDADE MILITAR"),
	
	/** O atributo CNH. */
	CNH((short)5, "CARTEIRA NACIONAL DE HABILITAÇÃO - CNH"),
	
	/** O atributo PASSAPORTE. */
	PASSAPORTE((short)6, "PASSAPORTE"),
	
	/** O atributo CTPS. */
	CTPS((short)7, "CARTEIRA DE TRABALHO E PREVIDÊNCIA SOCIAL - CTPS"),
	
	/** O atributo RNE. */
	RNE((short)8, "REGISTRO NACIONAL DE ESTRANGEIRO - RNE"),
	
	/** O atributo CERTIDaO_NASCIMENTO. */
	CERTIDaO_NASCIMENTO((short)9, "CERTIDÃO DE NASCIMENTO");

	/** O atributo codigo. */
	private Short codigo;
	
	/** O atributo descricao. */
	private String descricao;
	
	/**
	 * Cria uma nova instância de tipo documento identificacao enum.
	 * 
	 * @param codigo
	 *            the codigo
	 * @param descricao
	 *            the descricao
	 */
	private TipoDocumentoIdentificacaoEnum(Short codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	/**
	 * @return the codigo
	 */
	public Short getCodigo() {
		return codigo;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	
}
