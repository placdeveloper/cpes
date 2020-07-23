/*
 * SICOOB
 * 
 * HistoricoGrupoEconomicoPessoaBuilder.java(br.com.sicoob.capes.negocio.historico.HistoricoGrupoEconomicoPessoaBuilder)
 */
package br.com.sicoob.capes.negocio.historico;

import br.com.sicoob.capes.negocio.entidades.historico.HistoricoGrupoEconomicoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoEconomicoPessoa;

/**
 * 09/02/2012
 * @author Rodrigo.Chaves
 */
public class HistoricoGrupoEconomicoPessoaBuilder extends
		HistoricoBuilder<HistoricoGrupoEconomicoPessoa, GrupoEconomicoPessoa> {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoGrupoEconomicoPessoa novaInstanciaHistorico() {
		return new HistoricoGrupoEconomicoPessoa();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoGrupoEconomicoPessoa preencherDadosHistorico(
			HistoricoGrupoEconomicoPessoa historico, GrupoEconomicoPessoa vigente) {
		
		historico.setId(null);
		return historico;
	}


}
