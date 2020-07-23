/*
 * SICOOB
 * 
 * EntidadeDominioReplicavel.java(br.com.sicoob.capes.negocio.entidades.legado.EntidadeDominioReplicavel)
 */
package br.com.sicoob.capes.negocio.entidades.legado;

import java.io.Serializable;

/**
 * Superclasse para as entidades que representam domínios replicáveis.
 * @author Erico.Junior
 */
public abstract class EntidadeDominioReplicavel<T extends Serializable> extends
		CAPESEntidadeLegado<T> {

	/** Serial UID.*/
	private static final long serialVersionUID = -6708295143632946262L;

	/**
	 * Recupera descricao.
	 * 
	 * @return descricao
	 */
	public abstract String getDescricao();	
}
