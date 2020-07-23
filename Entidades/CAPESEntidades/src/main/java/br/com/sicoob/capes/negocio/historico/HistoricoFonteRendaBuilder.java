/*
 * SICOOB
 * 
 * HistoricoFonteRendaBuilder.java(br.com.sicoob.capes.negocio.historico.HistoricoFonteRendaBuilder)
 */
package br.com.sicoob.capes.negocio.historico;

import br.com.sicoob.capes.negocio.entidades.historico.HistoricoFonteRenda;
import br.com.sicoob.capes.negocio.entidades.vigente.FonteRenda;

/**
 * Builder para o histórico de fonte de renda.
 * 
 * @author Erico.Junior
 */
public class HistoricoFonteRendaBuilder extends HistoricoBuilder<HistoricoFonteRenda, FonteRenda> {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoFonteRenda novaInstanciaHistorico() {
		return new HistoricoFonteRenda();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoFonteRenda preencherDadosHistorico(
			HistoricoFonteRenda historico, FonteRenda entidadeVigente) {
		return historico;
	}
}
