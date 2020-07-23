package br.com.sicoob.capes.negocio.historico;

import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoManualPessoa;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoGrupoEconomicoManualPessoa;

/**
 * Builder para o histórico de HistoricoGrupoEconomicoManualPessoa
 * 
 * @author valdemar.xavier
 */
public class HistoricoGrupoEconomicoManualPessoaBuilder extends HistoricoBuilder<HistoricoGrupoEconomicoManualPessoa, GrupoEconomicoManualPessoa> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoGrupoEconomicoManualPessoa novaInstanciaHistorico() {
		return new HistoricoGrupoEconomicoManualPessoa();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoGrupoEconomicoManualPessoa preencherDadosHistorico(HistoricoGrupoEconomicoManualPessoa historico,
			GrupoEconomicoManualPessoa entidadeVigente) {
		historico.setId(null);
		historico.setIdGrupo(entidadeVigente.getGrupoEconomico().getId());
		historico.setIdPessoaCompartilhamento(entidadeVigente.getPessoaCompartilhamento().getId());
		historico.setIdGrupoManualPessoa(entidadeVigente.getId());
		return historico;
	}

}