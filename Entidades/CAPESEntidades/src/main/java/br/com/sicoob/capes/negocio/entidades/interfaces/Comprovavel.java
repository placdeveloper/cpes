/*
 * SICOOB
 * 
 * Comprovavel.java(br.com.sicoob.capes.negocio.entidades.interfaces.Comprovavel)
 */
package br.com.sicoob.capes.negocio.entidades.interfaces;

import java.util.Set;

import br.com.sicoob.capes.negocio.entidades.DocumentoComprobatorio;

/**
 * The Interface Comprovavel.
 */
public interface Comprovavel extends Aprovavel {

	/**
	 * Recupera documentos comprobatorios.
	 * 
	 * @return documentos comprobatorios
	 */
	Set<DocumentoComprobatorio> getDocumentosComprobatorios();
	
	/**
	 * Preenche documentos comprobatorios.
	 * 
	 * @param documentosComprobatorios
	 *            o novo documentos comprobatorios
	 */
	void setDocumentosComprobatorios(Set<DocumentoComprobatorio> documentosComprobatorios);
}
