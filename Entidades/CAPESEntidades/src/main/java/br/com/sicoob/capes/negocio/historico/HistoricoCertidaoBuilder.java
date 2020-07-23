/*
 * SICOOB
 * 
 * HistoricoCertidaoBuilder.java(br.com.sicoob.capes.negocio.historico.HistoricoCertidaoBuilder)
 */
package br.com.sicoob.capes.negocio.historico;

import br.com.sicoob.capes.negocio.entidades.historico.HistoricoCertidao;
import br.com.sicoob.capes.negocio.entidades.vigente.Certidao;

/**
 * Builder para o histórico de certidoes.
 * 
 * @author juan.damasceno
 */
public class HistoricoCertidaoBuilder extends HistoricoBuilder<HistoricoCertidao, Certidao> {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoCertidao novaInstanciaHistorico() {
		return new HistoricoCertidao();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoCertidao preencherDadosHistorico(HistoricoCertidao historico,
			Certidao entidadeVigente) {

		historico.setId(null);
		historico.setIdCertidao(((Certidao) entidadeVigente).getIdCertidao());

		return historico;
	}
}