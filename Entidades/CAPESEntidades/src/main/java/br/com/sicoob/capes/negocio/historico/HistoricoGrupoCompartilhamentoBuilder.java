/*
 * SICOOB
 * 
 * HistoricoGrupoCompartilhamentoBuilder.java(br.com.sicoob.capes.negocio.historico.HistoricoGrupoCompartilhamentoBuilder)
 */
package br.com.sicoob.capes.negocio.historico;

import br.com.sicoob.capes.negocio.entidades.historico.HistoricoGrupoCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoCompartilhamento;

/**
 * The Class HistoricoGrupoCompartilhamentoBuilder.
 */
public class HistoricoGrupoCompartilhamentoBuilder extends
		HistoricoBuilder<HistoricoGrupoCompartilhamento, GrupoCompartilhamento> {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoGrupoCompartilhamento novaInstanciaHistorico() {
		return new HistoricoGrupoCompartilhamento();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoGrupoCompartilhamento preencherDadosHistorico(
			HistoricoGrupoCompartilhamento historico, GrupoCompartilhamento entidadeVigente) {
		
		historico.setIdHistGrupoCompartilhamento(null);
		return historico;
	}

}
