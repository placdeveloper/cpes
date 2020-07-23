/* 
 * Sicoob
 * CAPESCadastroBOCrudDominioFachada.java 
 * Criado em: 06/05/2011
 */
package br.com.sicoob.capes.cadastro.fachada;

import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;

/**
 * 06/05/2011
 * 
 * @author Rodrigo.Chaves
 */
public abstract class CAPESCadastroBOCrudDominioFachada<T extends CAPESEntidade<?>>
		extends CAPESCadastroBOCrudFachada<T> {

	/**
	 * Construtor
	 * 
	 * @param chaveMapa
	 */
	public CAPESCadastroBOCrudDominioFachada(String chaveMapa) {
		super(chaveMapa);
	}

}
