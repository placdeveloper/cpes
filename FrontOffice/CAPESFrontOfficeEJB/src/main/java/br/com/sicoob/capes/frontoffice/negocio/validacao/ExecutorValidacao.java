/*
 * SICOOB
 * 
 * ExecutorValidacao.java(br.com.sicoob.capes.frontoffice.negocio.validacao.ExecutorValidacao)
 */
package br.com.sicoob.capes.frontoffice.negocio.validacao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import br.com.bancoob.excecao.BancoobException;

/**
 * The Class ExecutorValidacao.
 */
public class ExecutorValidacao {

	/** O atributo validadores. */
	private List<Validador> validadores = new ArrayList<Validador>();

	/**
	 * Cria uma nova instância de executor validacao.
	 * 
	 * @param validadores
	 *            the validadores
	 */
	public ExecutorValidacao(Validador... validadores) {

		if (validadores != null) {
			CollectionUtils.addAll(this.validadores, validadores);
		}
	}

	/**
	 * Validar.
	 * 
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public void validar() throws BancoobException {

		for (Validador validador : validadores) {
			validador.validar();
		}
	}

	/**
	 * Adicionar validadores.
	 * 
	 * @param validadores
	 *            the validadores
	 */
	public void adicionarValidadores(Validador... validadores) {

		if (validadores != null) {
			CollectionUtils.addAll(this.validadores, validadores);
		}
	}

	/**
	 * Adicionar validadores.
	 * 
	 * @param validadores
	 *            the validadores
	 */
	public void adicionarValidadores(List<Validador> validadores) {

		if (validadores != null) {
			this.validadores.addAll(validadores);
		}
	}
}
