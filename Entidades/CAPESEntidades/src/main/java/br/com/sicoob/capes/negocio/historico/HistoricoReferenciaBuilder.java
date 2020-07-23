/*
 * SICOOB
 * 
 * HistoricoReferenciaBuilder.java(br.com.sicoob.capes.negocio.historico.HistoricoReferenciaBuilder)
 */
package br.com.sicoob.capes.negocio.historico;

import br.com.sicoob.capes.negocio.entidades.historico.HistoricoReferencia;
import br.com.sicoob.capes.negocio.entidades.vigente.Referencia;

/**
 * Builder para o histórico de referências.
 * 
 * @author Erico.Junior
 */
public class HistoricoReferenciaBuilder extends HistoricoBuilder<HistoricoReferencia, Referencia> {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoReferencia novaInstanciaHistorico() {
		return new HistoricoReferencia();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoReferencia preencherDadosHistorico(HistoricoReferencia historico,
			Referencia entidadeVigente) {
		historico.setId(null);
		return historico;
	}

}
