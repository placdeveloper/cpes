/*
 * SICOOB
 * 
 * HistoricoTelefoneBuilder.java(br.com.sicoob.capes.negocio.historico.HistoricoTelefoneBuilder)
 */
package br.com.sicoob.capes.negocio.historico;

import br.com.sicoob.capes.negocio.entidades.historico.HistoricoTelefone;
import br.com.sicoob.capes.negocio.entidades.vigente.Telefone;

/**
 * Builder para o histórico de telefone.
 * @author Erico.Junior
 */
public class HistoricoTelefoneBuilder extends HistoricoBuilder<HistoricoTelefone, Telefone> {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoTelefone novaInstanciaHistorico() {
		return new HistoricoTelefone();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoTelefone preencherDadosHistorico(HistoricoTelefone historico,
			Telefone entidadeVigente) {

		//Atribuindo null no id porque o valor do idTelefone foi atribuido na copia.
		historico.setId(null);
		return historico;
	}

}
