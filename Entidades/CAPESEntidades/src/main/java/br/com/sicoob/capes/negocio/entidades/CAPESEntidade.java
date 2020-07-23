/*
 * SICOOB
 * 
 * CAPESEntidade.java(br.com.sicoob.capes.negocio.entidades.CAPESEntidade)
 */
package br.com.sicoob.capes.negocio.entidades;

import java.io.Serializable;

/**
 * Classe base para todas as entidades do projeto CadastroUnicoClientesComum
 * 
 * @author Stefanini IT Solutions
 */
public abstract class CAPESEntidade<ID extends Serializable> extends CAPESEntidadeBase<ID> {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Recupera o id.
	 * 
	 * @return id
	 */
	public abstract ID getId();

	/**
	 * Preenche o id.
	 * 
	 * @param id
	 *            o novo id
	 */
	public abstract void setId(ID id);
}