/*
 * SICOOB
 * 
 * HistoricoProdutividadeBuilder.java(br.com.sicoob.capes.negocio.historico.HistoricoProdutividadeBuilder)
 */
package br.com.sicoob.capes.negocio.historico;

import br.com.sicoob.capes.negocio.entidades.historico.HistoricoProdutividade;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtividade;

/**
 * @author Erico.Junior
 * 
 */
public class HistoricoProdutividadeBuilder extends
		HistoricoBuilder<HistoricoProdutividade, Produtividade> {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoProdutividade novaInstanciaHistorico() {
		return new HistoricoProdutividade();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoProdutividade preencherDadosHistorico(
			HistoricoProdutividade historico, Produtividade entidadeVigente) {

		// Atribuindo null no id porque o valor do idPessoa foi atribuido na
		// copia.
		historico.setId(null);
		historico.setIdPessoaCompartilhamento(entidadeVigente.getProdutor().getId());
		return historico;
	}

}
