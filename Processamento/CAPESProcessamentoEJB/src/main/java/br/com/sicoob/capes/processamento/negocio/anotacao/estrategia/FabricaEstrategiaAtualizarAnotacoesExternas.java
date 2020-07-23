/*
 * SICOOB
 * 
 * FabricaEstrategiaAtualizarAnotacoesExternas.java(br.com.sicoob.capes.processamento.negocio.anotacao.estrategia.FabricaEstrategiaAtualizarAnotacoesExternas)
 */
package br.com.sicoob.capes.processamento.negocio.anotacao.estrategia;

import br.com.sicoob.capes.comum.negocio.enums.OrigemInformacaoEnum;
import br.com.sicoob.capes.negocio.entidades.OrigemInformacao;

/**
 * Uma f�brica para criar objetos EstrategiaAtualizarAnotacoesExternas.
 */
public class FabricaEstrategiaAtualizarAnotacoesExternas {

	/** O atributo instance. */
	private static FabricaEstrategiaAtualizarAnotacoesExternas instance = new FabricaEstrategiaAtualizarAnotacoesExternas();

	/**
	 * Cria a f�brica de estrat�gias de atualiza��es das anota��es.
	 * 
	 * @return a f�brica de estrat�gias de atualiza��es das anota��es.
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