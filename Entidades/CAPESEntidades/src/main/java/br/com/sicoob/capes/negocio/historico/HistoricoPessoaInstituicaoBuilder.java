/*
 * SICOOB
 * 
 * HistoricoPessoaInstituicaoBuilder.java(br.com.sicoob.capes.negocio.historico.HistoricoPessoaInstituicaoBuilder)
 */
package br.com.sicoob.capes.negocio.historico;

import br.com.sicoob.capes.negocio.entidades.historico.HistoricoPessoaInstituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao;

/**
 * The Class HistoricoPessoaInstituicaoBuilder.
 */
public class HistoricoPessoaInstituicaoBuilder extends HistoricoBuilder<HistoricoPessoaInstituicao, PessoaInstituicao> {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoPessoaInstituicao novaInstanciaHistorico() {
		return new HistoricoPessoaInstituicao();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoPessoaInstituicao preencherDadosHistorico(HistoricoPessoaInstituicao historico,
			PessoaInstituicao entidadeVigente) {
		historico.setId(null);
		historico.setGruposEconomicos(null);
		return historico;
	}
}