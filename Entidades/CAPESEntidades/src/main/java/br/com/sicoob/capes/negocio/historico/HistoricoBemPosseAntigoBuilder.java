/*
 * SICOOB
 * 
 * HistoricoBemPosseBuilder.java(br.com.sicoob.capes.negocio.historico.HistoricoBemPosseBuilder)
 */
package br.com.sicoob.capes.negocio.historico;

import br.com.sicoob.capes.negocio.entidades.bemantigo.BemPosse;
import br.com.sicoob.capes.negocio.entidades.bemantigo.HistoricoBemPosse;

/**
 * The Class HistoricoBemPosseBuilder.
 */
public class HistoricoBemPosseAntigoBuilder extends HistoricoBuilder<HistoricoBemPosse, BemPosse> {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoBemPosse novaInstanciaHistorico() {
		return new HistoricoBemPosse();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoBemPosse preencherDadosHistorico(HistoricoBemPosse historico,
			BemPosse entidadeVigente) {
		historico.setId(null);
		historico.setBemPosse(entidadeVigente);
		return historico;
	}
}
