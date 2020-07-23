/*
 * SICOOB
 * 
 * HistoricoPessoaJuridicaBuilder.java(br.com.sicoob.capes.negocio.historico.HistoricoPessoaJuridicaBuilder)
 */
package br.com.sicoob.capes.negocio.historico;

import br.com.sicoob.capes.negocio.entidades.historico.HistoricoPessoaJuridica;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaJuridica;

/**
 * Builder para o histórico de pessoa jurídica.
 *  
 * @author Erico.Junior
 */
public class HistoricoPessoaJuridicaBuilder extends
		HistoricoPessoaBuilder<HistoricoPessoaJuridica, PessoaJuridica> {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoPessoaJuridica novaInstanciaHistorico() {
		return new HistoricoPessoaJuridica(); 
	}

}
