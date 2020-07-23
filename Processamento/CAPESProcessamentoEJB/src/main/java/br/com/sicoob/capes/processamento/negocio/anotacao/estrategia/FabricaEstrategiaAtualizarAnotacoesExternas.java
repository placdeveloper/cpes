/*
 * SICOOB
 * 
 * FabricaEstrategiaAtualizarAnotacoesExternas.java(br.com.sicoob.capes.processamento.negocio.anotacao.estrategia.FabricaEstrategiaAtualizarAnotacoesExternas)
 */
package br.com.sicoob.capes.processamento.negocio.anotacao.estrategia;

import br.com.sicoob.capes.comum.negocio.enums.OrigemInformacaoEnum;
import br.com.sicoob.capes.negocio.entidades.OrigemInformacao;

/**
 * Uma fábrica para criar objetos EstrategiaAtualizarAnotacoesExternas.
 */
public class FabricaEstrategiaAtualizarAnotacoesExternas {

	/** O atributo instance. */
	private static FabricaEstrategiaAtualizarAnotacoesExternas instance = new FabricaEstrategiaAtualizarAnotacoesExternas();

	/**
	 * Cria a fábrica de estratégias de atualizações das anotações.
	 * 
	 * @return a fábrica de estratégias de atualizações das anotações.
	 */
	public static FabricaEstrategiaAtualizarAnotacoesExternas getInstance() {
		return instance;
	}
	
	/**
	 * Cria um novo objeto EstrategiaAtualizacaoAnotacoes.
	 * 
	 * @param origem
	 *            the origem
	 * @return the estrategia atualizar anotacoes externas
	 */
	public EstrategiaAtualizarAnotacoesExternas criarEstrategiaAtualizacaoAnotacoes(OrigemInformacao origem) {
		EstrategiaAtualizarAnotacoesExternas estrategia = null;
		
		if (origem != null) {
			if (OrigemInformacaoEnum.BACEN.getIdOrigemInformacao().equals(origem.getId())) {
				estrategia = new EstrategiaAtualizarAnotacoesExternasBacen();
			} else if (OrigemInformacaoEnum.RECEITA.getIdOrigemInformacao().equals(origem.getId())) {
				estrategia = new EstrategiaAtualizarAnotacoesExternasReceita();
			} else {
				estrategia = new EstrategiaAtualizarAnotacoesExternasPadrao();
			}
		}
		
		return estrategia;
	}

}