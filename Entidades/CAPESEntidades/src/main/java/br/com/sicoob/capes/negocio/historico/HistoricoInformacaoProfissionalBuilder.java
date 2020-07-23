/*
 * SICOOB
 * 
 * HistoricoInformacaoProfissionalBuilder.java(br.com.sicoob.capes.negocio.historico.HistoricoInformacaoProfissionalBuilder)
 */
package br.com.sicoob.capes.negocio.historico;

import br.com.sicoob.capes.negocio.entidades.historico.HistoricoInformacaoProfissional;
import br.com.sicoob.capes.negocio.entidades.vigente.InformacaoProfissional;

/**
 * @author Erico.Junior
 * 
 */
public class HistoricoInformacaoProfissionalBuilder
		extends	HistoricoBuilder<HistoricoInformacaoProfissional, InformacaoProfissional> {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoInformacaoProfissional novaInstanciaHistorico() {
		return new HistoricoInformacaoProfissional();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoInformacaoProfissional preencherDadosHistorico(
			HistoricoInformacaoProfissional historico, InformacaoProfissional entidadeVigente) {
		historico.setId(null);
		return historico;
	}

}
