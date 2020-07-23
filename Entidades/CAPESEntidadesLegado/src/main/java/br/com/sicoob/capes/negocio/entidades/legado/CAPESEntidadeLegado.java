/*
 * SICOOB
 * 
 * CAPESEntidadeLegado.java(br.com.sicoob.capes.negocio.entidades.legado.CAPESEntidadeLegado)
 */
package br.com.sicoob.capes.negocio.entidades.legado;

import java.io.Serializable;

import br.com.sicoob.capes.negocio.entidades.CAPESEntidadeBase;

/**
 * Classe base para todas as entidades do projeto ReplicacaoClientesBO
 *
 * @author Stefanini IT Solutions
 */
public abstract class CAPESEntidadeLegado<ID extends Serializable> extends CAPESEntidadeBase<ID> {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Recupera o identificador no SQLServer.
	 * @return o identificador no SQLServer.
	 */
	public abstract ID getIdSQL();
}
