package br.com.sicoob.capes.negocio.historico;

import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoAutomaticoPessoa;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoGrupoEconomicoAutomaticoPessoa;

/**
 * Builder para o histórico de HistoricoGrupoEconomicoAutomaticoPessoa
 * 
 * @author valdemar.xavier
 */
public class HistoricoGrupoEconomicoAutomaticoPessoaBuilder
		extends HistoricoBuilder<HistoricoGrupoEconomicoAutomaticoPessoa, GrupoEconomicoAutomaticoPessoa> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoGrupoEconomicoAutomaticoPessoa novaInstanciaHistorico() {
		return new HistoricoGrupoEconomicoAutomaticoPessoa();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoGrupoEconomicoAutomaticoPessoa preencherDadosHistorico(HistoricoGrupoEconomicoAutomaticoPessoa historico,
			GrupoEconomicoAutomaticoPessoa entidadeVigente) {
		historico.setId(null);
		historico.setIdGrupo(entidadeVigente.getGrupoEconomico().getId());
		historico.setIdRelacionamento(entidadeVigente.getRelacionamentoPessoa().getId());
		historico.setIdPessoaCompartilhamento(entidadeVigente.getPessoaCompartilhamento().getId());
		historico.setIdGrupoAutomaticoPessoa(entidadeVigente.getId());
		return historico;
	}

}