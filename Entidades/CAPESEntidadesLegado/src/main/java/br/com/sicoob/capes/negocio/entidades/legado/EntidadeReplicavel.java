/*
 * SICOOB
 * 
 * EntidadeReplicavel.java(br.com.sicoob.capes.negocio.entidades.legado.EntidadeReplicavel)
 */
package br.com.sicoob.capes.negocio.entidades.legado;

import java.io.Serializable;

import br.com.sicoob.capes.negocio.entidades.legado.interfaces.Replicavel;

/**
 * Superclasse para as entidades que serão replicadas no legado.
 * 
 * @author erico.junior
 */
public abstract class EntidadeReplicavel<T extends Serializable> extends
		CAPESEntidadeLegado<T> implements Replicavel {

	/** Serial UID. */
	private static final long serialVersionUID = -246816544394431041L;

}
