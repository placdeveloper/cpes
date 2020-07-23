/*
 * SICOOB
 * 
 * DominioReplicavelLista.java(br.com.sicoob.capes.negocio.entidades.interfaces.DominioReplicavelLista)
 */
package br.com.sicoob.capes.negocio.entidades.interfaces;

/**
 * Interface que define as entidade que são replicáveis para lista no SQL (legado).
 * @author Erico.Junior
 */
public interface DominioReplicavelLista extends DominioReplicavel{

	/**
	 * Recupera codigo lista item.
	 * 
	 * @return codigo lista item
	 */
	String getCodigoListaItem();
}
