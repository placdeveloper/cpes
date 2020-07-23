/*
 * SICOOB
 * 
 * HistoricoBemImovelBuilder.java(br.com.sicoob.capes.negocio.historico.HistoricoBemImovelBuilder)
 */
package br.com.sicoob.capes.negocio.historico;

import br.com.sicoob.capes.negocio.entidades.bemantigo.BemImovel;
import br.com.sicoob.capes.negocio.entidades.bemantigo.HistoricoBemImovel;

/**
 * The Class HistoricoBemImovelBuilder.
 */
public class HistoricoBemImovelAntigoBuilder extends HistoricoBuilder<HistoricoBemImovel, BemImovel> {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoBemImovel novaInstanciaHistorico() {
		return new HistoricoBemImovel();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoBemImovel preencherDadosHistorico(HistoricoBemImovel historico,
			BemImovel entidadeVigente) {
		historico.setIdBemPessoa(entidadeVigente.getIdBem());
		historico.setIdHistorico(null);
		return historico;
	}
}