/*
 * SICOOB
 * 
 * HistoricoPessoaBuilder.java(br.com.sicoob.capes.negocio.historico.HistoricoPessoaBuilder)
 */
package br.com.sicoob.capes.negocio.historico;

import br.com.sicoob.capes.negocio.entidades.historico.HistoricoPessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Builder para o histórico de pessoa.
 * 
 * @author Erico.Junior
 */
public abstract class HistoricoPessoaBuilder<E extends HistoricoPessoaCompartilhamento, P extends PessoaCompartilhamento> extends
		HistoricoBuilder<E, P> {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected E preencherDadosHistorico(E historico,
			P entidadeVigente) {
		
		// Atribuindo null no id porque o valor do idPessoa foi atriuido na copia.
		historico.setId(null);
		return historico;
	}
	
}
