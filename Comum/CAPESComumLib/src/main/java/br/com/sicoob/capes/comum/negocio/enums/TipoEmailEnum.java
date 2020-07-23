/*
 * SICOOB
 * 
 * TipoEmailEnum.java(br.com.sicoob.capes.comum.negocio.enums.TipoEmailEnum)
 */
package br.com.sicoob.capes.comum.negocio.enums;


/**
 * Enum dos tipos de email. São eles:
 *	<pre>
 *	0 - PARTICULAR
 *	1 - COMERCIAL
 *	2 - OUTROS
 *  </pre>
 * 
 * @author Rodrigo.Chaves
 */
public enum TipoEmailEnum {

	/** O atributo PARTICULAR. */
	PARTICULAR,
	
	/** O atributo COMERCIAL. */
	COMERCIAL,
	
	/** O atributo OUTROS. */
	OUTROS;
	
	/**
	 * Recupera codigo.
	 * 
	 * @return codigo
	 */
	public Short getCodigo() {
		return (short) ordinal();
	}
	
}
