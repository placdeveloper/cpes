/*
 * SICOOB
 * 
 * HistoricoResponsavelCadastroBuilder.java(br.com.sicoob.capes.negocio.historico.HistoricoResponsavelCadastroBuilder)
 */
package br.com.sicoob.capes.negocio.historico;

import br.com.sicoob.capes.negocio.entidades.historico.HistoricoResponsavelCadastro;
import br.com.sicoob.capes.negocio.entidades.vigente.ResponsavelCadastro;

/**
 * Builder para o histórico de responsáveis pelo cadastro.
 * 
 * @author Erico.Junior
 */
public class HistoricoResponsavelCadastroBuilder extends
		HistoricoBuilder<HistoricoResponsavelCadastro, ResponsavelCadastro> {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoResponsavelCadastro novaInstanciaHistorico() {
		return new HistoricoResponsavelCadastro();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoResponsavelCadastro preencherDadosHistorico(
			HistoricoResponsavelCadastro historico, ResponsavelCadastro entidadeVigente) {
		return historico;
	}

}
