/*
 * SICOOB
 * 
 * HistoricoPessoaFisicaBuilder.java(br.com.sicoob.capes.negocio.historico.HistoricoPessoaFisicaBuilder)
 */
package br.com.sicoob.capes.negocio.historico;

import br.com.sicoob.capes.negocio.entidades.historico.HistoricoPessoaFisica;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;

/**
 * Builder para o hist�rico de pessoa f�sica.
 *  
 * @author Erico.Junior
 */
public class HistoricoPessoaFisicaBuilder extends
		HistoricoPessoaBuilder<HistoricoPessoaFisica, PessoaFisica> {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoPessoaFisica novaInstanciaHistorico() {
		return new HistoricoPessoaFisica();
	}

}
