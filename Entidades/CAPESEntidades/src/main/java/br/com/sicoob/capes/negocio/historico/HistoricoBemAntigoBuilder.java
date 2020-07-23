/*
 * SICOOB
 * 
 * HistoricoBemBuilder.java(br.com.sicoob.capes.negocio.historico.HistoricoBemBuilder)
 */
package br.com.sicoob.capes.negocio.historico;

import br.com.sicoob.capes.negocio.entidades.bemantigo.Bem;
import br.com.sicoob.capes.negocio.entidades.bemantigo.HistoricoBem;

/**
 * The Class HistoricoBemBuilder.
 */
public class HistoricoBemAntigoBuilder extends HistoricoBuilder<HistoricoBem, Bem> {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoBem novaInstanciaHistorico() {
		return new HistoricoBem();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoBem preencherDadosHistorico(HistoricoBem historico, Bem entidadeVigente) {
		historico.setIdBemPessoa(entidadeVigente.getIdBem());
		historico.setId(null);
		return historico;
	}
}