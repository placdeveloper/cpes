/*
 * SICOOB
 * 
 * HistoricoEnderecoBuilder.java(br.com.sicoob.capes.negocio.historico.HistoricoEnderecoBuilder)
 */
package br.com.sicoob.capes.negocio.historico;

import br.com.sicoob.capes.negocio.entidades.historico.HistoricoEndereco;
import br.com.sicoob.capes.negocio.entidades.vigente.Endereco;

/**
 * Builder para os historicos dos endereços.
 * @author Erico.Junior
 */
public class HistoricoEnderecoBuilder extends HistoricoBuilder<HistoricoEndereco, Endereco> {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoEndereco novaInstanciaHistorico() {
		return new HistoricoEndereco();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoEndereco preencherDadosHistorico(HistoricoEndereco historico,
			Endereco entidadeVigente) {

		//Atribuindo null no id porque o valor do idEndereco foi atribuido na copia.
		historico.setId(null);
		return historico;
	}

}
