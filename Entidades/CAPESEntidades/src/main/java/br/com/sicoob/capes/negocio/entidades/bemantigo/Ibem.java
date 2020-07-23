/*
 * SICOOB
 * 
 * Ibem.java(br.com.sicoob.capes.negocio.entidades.interfaces.Ibem)
 */
package br.com.sicoob.capes.negocio.entidades.bemantigo;

import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * @author Erico.Junior
 *
 */
public interface Ibem {

	/**
	 * Recupera bem.
	 * 
	 * @return bem
	 */
	Bem getBem();
	
	/**
	 * Preenche bem.
	 * 
	 * @param bem
	 *            o novo bem
	 */
	void setBem(Bem bem);
	
	/**
	 * Recupera data hora inicio.
	 * 
	 * @return data hora inicio
	 */
	DateTimeDB getDataHoraInicio();
	
	/**
	 * Preenche data hora inicio.
	 * 
	 * @param dataHoraInicio
	 *            o novo data hora inicio
	 */
	void setDataHoraInicio(DateTimeDB dataHoraInicio);
}
