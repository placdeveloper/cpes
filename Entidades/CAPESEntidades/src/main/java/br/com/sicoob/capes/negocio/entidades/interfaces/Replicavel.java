/*
 * SICOOB
 * 
 * Replicavel.java(br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel)
 */
package br.com.sicoob.capes.negocio.entidades.interfaces;

import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Interface para as entidades que precisam ser replicadas no legado.
 * @author Erico.Junior
 */
public interface Replicavel {

	/**
	 * Recupera pessoa compartilhamento.
	 * 
	 * @return pessoa compartilhamento
	 */
	PessoaCompartilhamento getPessoaCompartilhamento();
}
