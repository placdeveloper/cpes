/*
 * SICOOB
 * 
 * HistoricoBemRegistroBuilder.java(br.com.sicoob.capes.negocio.historico.HistoricoBemRegistroBuilder)
 */
package br.com.sicoob.capes.negocio.historico;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemRegistro;
import br.com.sicoob.capes.negocio.entidades.bemantigo.HistoricoBemRegistro;

/**
 * 
 * @author juan.damasceno
 *
 */
public class HistoricoBemRegistroAntigoBuilder extends HistoricoBuilder<HistoricoBemRegistro, BemRegistro> {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoBemRegistro novaInstanciaHistorico() {
		return new HistoricoBemRegistro();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoBemRegistro preencherDadosHistorico(HistoricoBemRegistro historico,
			BemRegistro entidadeVigente) {
		historico.setIdHistoricoBemRegistro(null);
		historico.setBemRegistro(entidadeVigente);
		
		if(historico.getDataHoraInicio() == null && entidadeVigente.getDataHoraInicio() == null) {
			historico.setDataHoraInicio(new DateTimeDB());
		}
		
		return historico;
	}
}