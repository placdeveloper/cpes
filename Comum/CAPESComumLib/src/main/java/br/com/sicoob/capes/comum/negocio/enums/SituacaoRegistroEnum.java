/*
 * SICOOB
 * 
 * SituacaoRegistroEnum.java(br.com.sicoob.capes.comum.negocio.enums.SituacaoRegistroEnum)
 */
package br.com.sicoob.capes.comum.negocio.enums;

import java.io.Serializable;

/**
 * Enum utilizado para representar a situação atual do registro integrado ao
 * GED/GFT.
 */
public enum SituacaoRegistroEnum implements Serializable {

	/** O atributo VIGENTE. */
	VIGENTE, // Os que não possuem registro na tabela Autorizacao
	
	/** O atributo BLOQUEADO. */
	BLOQUEADO, 
	
	/** O atributo EM_AUTORIZACAO. */
	EM_AUTORIZACAO, 
	
	/** O atributo DEVOLVIDO. */
	DEVOLVIDO, 
	
	/** O atributo A_ENCAMINHAR. */
	A_ENCAMINHAR;

	/**
	 * Value of.
	 * 
	 * @param codigo
	 *            the codigo
	 * @return situacao registro enum
	 */
	public static SituacaoRegistroEnum valueOf(Short codigo) {
		for (SituacaoRegistroEnum enumm : SituacaoRegistroEnum.values()) {
			if (enumm.ordinal() == codigo.intValue()) {
				return enumm;
			}
		}
		return null;
	}
	
	/**
	 * Recupera o valor de codigo.
	 *
	 * @return o valor de codigo
	 */
	public Short getCodigo() {
		return Integer.valueOf(ordinal()).shortValue();
	}
}