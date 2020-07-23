/*
 * SICOOB
 * 
 * HistoricoEmailBuilder.java(br.com.sicoob.capes.negocio.historico.HistoricoEmailBuilder)
 */
package br.com.sicoob.capes.negocio.historico;

import br.com.sicoob.capes.negocio.entidades.historico.HistoricoEmail;
import br.com.sicoob.capes.negocio.entidades.vigente.Email;

/**
 * Builder para o histórico de emails.
 * @author Erico.Junior
 */
public class HistoricoEmailBuilder extends HistoricoBuilder<HistoricoEmail, Email> {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoEmail novaInstanciaHistorico() {
		return new HistoricoEmail();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoEmail preencherDadosHistorico(HistoricoEmail historico,
			Email entidadeVigente) {

		//Atribuindo null no id porque o valor do idEmail foi atribuido na copia.
		historico.setId(null);
		return historico;
	}

}