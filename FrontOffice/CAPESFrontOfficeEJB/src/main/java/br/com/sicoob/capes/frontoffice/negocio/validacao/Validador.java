/*
 * SICOOB
 * 
 * Validador.java(br.com.sicoob.capes.frontoffice.negocio.validacao.Validador)
 */
package br.com.sicoob.capes.frontoffice.negocio.validacao;

import br.com.bancoob.excecao.BancoobException;

/**
 * Define as operações básicas para os validadores.
 * 
 * @author Rodrigo.Chaves
 */
public interface Validador {

	/**
	 * Validar.
	 * 
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	void validar() throws BancoobException;
}
