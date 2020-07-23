package br.com.sicoob.capes.negocio.historico;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.bem.BemPessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.bem.historico.HistoricoBemPessoaCompartilhamento;

/**
 * Builder para o histórico de {@link BemPessoaCompartilhamento}.
 * 
 * @author Bruno.Carneiro
 */
public class HistoricoBemPessoaCompartilhamentoBuilder extends HistoricoBuilder<HistoricoBemPessoaCompartilhamento, BemPessoaCompartilhamento> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoBemPessoaCompartilhamento novaInstanciaHistorico() {
		return new HistoricoBemPessoaCompartilhamento();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoBemPessoaCompartilhamento preencherDadosHistorico(HistoricoBemPessoaCompartilhamento historico, BemPessoaCompartilhamento entidadeVigente) {
		historico.setId(null);
		historico.setDataHoraInicio(new DateTimeDB());
		return historico;
	}

}