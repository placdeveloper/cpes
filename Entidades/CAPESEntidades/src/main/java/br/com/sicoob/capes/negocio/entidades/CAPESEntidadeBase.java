/*
 * SICOOB
 * 
 * CAPESEntidadeBase.java(br.com.sicoob.capes.negocio.entidades.CAPESEntidadeBase)
 */
package br.com.sicoob.capes.negocio.entidades;

import java.io.Serializable;

import br.com.bancoob.negocio.entidades.BancoobEntidade;

/**
 * The Class CAPESEntidadeBase.
 * 
 * @param <ID>
 *            the generic type
 */
public abstract class CAPESEntidadeBase<ID extends Serializable> extends BancoobEntidade{

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 2450698067790255640L;

}
