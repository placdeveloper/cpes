/*
 * SICOOB
 * 
 * HistoricoProdutorBuilder.java(br.com.sicoob.capes.negocio.historico.HistoricoProdutorBuilder)
 */
package br.com.sicoob.capes.negocio.historico;

import br.com.sicoob.capes.negocio.entidades.historico.HistoricoProdutor;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtor;

/**
 * @author erico.junior
 * 
 */
public class HistoricoProdutorBuilder extends
		HistoricoBuilder<HistoricoProdutor, Produtor> {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoProdutor novaInstanciaHistorico() {
		return new HistoricoProdutor();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoProdutor preencherDadosHistorico(
			HistoricoProdutor historico, Produtor entidadeVigente) {

		//Atribuindo null no id porque o valor do idPessoa foi atribuido na copia.
		historico.setId(null);		
		return historico;
	}

}
