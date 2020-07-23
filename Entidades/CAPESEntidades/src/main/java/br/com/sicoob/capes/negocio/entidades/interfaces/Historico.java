/*
 * SICOOB
 * 
 * Historico.java(br.com.sicoob.capes.negocio.entidades.interfaces.Historico)
 */
package br.com.sicoob.capes.negocio.entidades.interfaces;

import java.io.Serializable;
import java.util.Date;

import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * @author Erico.Junior
 */
public interface Historico {
	
	/**
	 * Recupera id entidade vigente.
	 * 
	 * @return id entidade vigente
	 */
	Serializable getIdEntidadeVigente();
	
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
	 * Recupera data hora fim.
	 * 
	 * @return data hora fim
	 */
	Date getDataHoraFim();
	
	/**
	 * Preenche data hora fim.
	 * 
	 * @param dataHoraInicio
	 *            o novo data hora fim
	 */
	void setDataHoraFim(Date dataHoraInicio);
	
	/**
	 * Preenche id usuario exclusao.
	 * 
	 * @param idUsuario
	 *            o novo id usuario exclusao
	 */
	void setIdUsuarioExclusao(String idUsuario);
	
}
