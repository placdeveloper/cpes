/*
 * SICOOB
 * 
 * HistoricoTributacaoBuilder.java(br.com.sicoob.capes.negocio.historico.HistoricoTributacaoBuilder)
 */
package br.com.sicoob.capes.negocio.historico;

import br.com.sicoob.capes.negocio.entidades.historico.HistoricoTributacao;
import br.com.sicoob.capes.negocio.entidades.vigente.Tributacao;

/**
 * The Class HistoricoTributacaoBuilder.
 */
public class HistoricoTributacaoBuilder extends HistoricoBuilder<HistoricoTributacao, Tributacao> {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoTributacao novaInstanciaHistorico() {
		return new HistoricoTributacao();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoTributacao preencherDadosHistorico(HistoricoTributacao historico,
			Tributacao entidadeVigente) {
		historico.setId(null);
		return historico;
	}
}