/*
 * SICOOB
 * 
 * HistoricoReferenciaBancariaBuilder.java(br.com.sicoob.capes.negocio.historico.HistoricoReferenciaBancariaBuilder)
 */
package br.com.sicoob.capes.negocio.historico;

import br.com.sicoob.capes.negocio.entidades.ReferenciaBancaria;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoReferenciaBancaria;

/**
 * Builder para o histórico de referências.
 * 
 * @author Erico.Junior
 */
public class HistoricoReferenciaBancariaBuilder extends
		HistoricoBuilder<HistoricoReferenciaBancaria, ReferenciaBancaria> {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoReferenciaBancaria novaInstanciaHistorico() {
		return new HistoricoReferenciaBancaria();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoReferenciaBancaria preencherDadosHistorico(
			HistoricoReferenciaBancaria historico, ReferenciaBancaria entidadeVigente) {
		historico.setId(null);
		return historico;
	}
}