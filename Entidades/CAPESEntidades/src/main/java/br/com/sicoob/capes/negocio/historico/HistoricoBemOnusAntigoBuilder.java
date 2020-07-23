/*
 * SICOOB
 * 
 * HistoricoBemOnusBuilder.java(br.com.sicoob.capes.negocio.historico.HistoricoBemOnusBuilder)
 */
package br.com.sicoob.capes.negocio.historico;

import br.com.sicoob.capes.negocio.entidades.bemantigo.BemOnus;
import br.com.sicoob.capes.negocio.entidades.bemantigo.HistoricoBemOnus;

/**
 * The Class HistoricoBemOnusBuilder.
 */
public class HistoricoBemOnusAntigoBuilder extends HistoricoBuilder<HistoricoBemOnus, BemOnus> {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoBemOnus novaInstanciaHistorico() {
		return new HistoricoBemOnus();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoBemOnus preencherDadosHistorico(HistoricoBemOnus historico,
			BemOnus entidadeVigente) {

		historico.setBemOnus(entidadeVigente);
		historico.setId(null);		
		return historico;
	}
}
