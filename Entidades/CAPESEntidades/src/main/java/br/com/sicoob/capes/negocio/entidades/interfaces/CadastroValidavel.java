/*
 * SICOOB
 * 
 * CadastroValidavel.java(br.com.sicoob.capes.negocio.entidades.interfaces.CadastroValidavel)
 */
package br.com.sicoob.capes.negocio.entidades.interfaces;

import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Identifica e define o comportamento das entidades que atualizam a data da ultima atualizacao cadastral
 */
public interface CadastroValidavel {

	/**
	 * Recupera pessoa compartilhamento.
	 * 
	 * @return pessoa compartilhamento
	 */
	PessoaCompartilhamento getPessoaCompartilhamento();

}