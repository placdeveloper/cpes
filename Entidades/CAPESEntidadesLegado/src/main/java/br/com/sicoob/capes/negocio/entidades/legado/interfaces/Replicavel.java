/*
 * SICOOB
 * 
 * Replicavel.java(br.com.sicoob.capes.negocio.entidades.legado.interfaces.Replicavel)
 */
package br.com.sicoob.capes.negocio.entidades.legado.interfaces;

import java.io.Serializable;

/**
 * Interface para as entidades replicáveis do cadastro do cliente
 * @author erico.junior
 */
public interface Replicavel {

	/**
	 * Recupera o identificador no DB2.
	 * 
	 * @return o identificador no DB2.
	 */
	Serializable getIdDB2();
	
	/**
	 * Preenche id d b2.
	 * 
	 * @param idDB2
	 *            o novo id d b2
	 */
	void setIdDB2(Serializable idDB2);
	
	/**
	 * Preenche id sql.
	 * 
	 * @param idSQL
	 *            o novo id sql
	 */
	void setIdSQL(Serializable idSQL);
}
