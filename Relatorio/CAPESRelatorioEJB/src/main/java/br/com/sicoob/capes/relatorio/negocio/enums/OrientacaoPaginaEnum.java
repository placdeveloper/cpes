/**
 * 
 */
package br.com.sicoob.capes.relatorio.negocio.enums;

import org.apache.commons.lang.StringUtils;

/**
 * @author Rodrigo.Chaves
 */
public enum OrientacaoPaginaEnum {
	
	/** O atributo RETRATO. */
	RETRATO,
	
	/** O atributo PAISAGEM. */
	PAISAGEM;
	
	/**
	 * @return
	 */
	public String getDescricao() {
		return StringUtils.capitalize(name().toLowerCase());
	}
}
