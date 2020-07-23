/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.enums;

/**
 * @author juan.damasceno
 *
 */
public enum TipoConsultaEnum {

	/** O atributo ATUAL. */
	ATUAL,
	
	/** O atributo TUDO. */
	TUDO,
	
	/** O atributo PERIODO. */
	PERIODO, 
	
	/** O atributo AUTORIZACAO_FICHA_PREVIA. */
	AUTORIZACAO_FICHA_PREVIA;
	
	/**
	 * Recupera o valor de name.
	 *
	 * @return o valor de name
	 */
	public String getName() {
		return this.name();
	}
}
