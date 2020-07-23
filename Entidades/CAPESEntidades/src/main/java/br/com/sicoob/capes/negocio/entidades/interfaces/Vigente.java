/*
 * SICOOB
 * 
 * Vigente.java(br.com.sicoob.capes.negocio.entidades.interfaces.Vigente)
 */
package br.com.sicoob.capes.negocio.entidades.interfaces;

import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * @author Erico.Junior
 */
public interface Vigente {

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
	
	/**
	 * Recupera gravar historico.
	 * 
	 * @return gravar historico
	 */
	Boolean getGravarHistorico();
	
	/**
	 * Preenche gravar historico.
	 * 
	 * @param gravarHistorico
	 *            o novo gravar historico
	 */
	void setGravarHistorico(Boolean gravarHistorico);
}
