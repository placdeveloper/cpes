package br.com.sicoob.capes.negocio.historico;

import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoNovo;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoGrupoEconomicoNovo;

/**
 * 
 * @author Paulo.Stoppa
 *
 */
public class HistoricoGrupoEconomicoNovoBuilder extends HistoricoBuilder<HistoricoGrupoEconomicoNovo, GrupoEconomicoNovo> {


	/**
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoGrupoEconomicoNovo novaInstanciaHistorico() {
		return new HistoricoGrupoEconomicoNovo();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoGrupoEconomicoNovo preencherDadosHistorico(HistoricoGrupoEconomicoNovo historico, GrupoEconomicoNovo entidadeVigente) {
		historico.setId(null);
		historico.setIdGrupo(entidadeVigente.getId());
		historico.setCodTipoGrupoEconomico(entidadeVigente.getTipo().getCodigo());
		return historico;
	}

}