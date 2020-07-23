/*
 * SICOOB
 * 
 * AnotacaoAvalistaOperacaoPrejuizoBuilder.java(br.com.sicoob.capes.api.negocio.anotacao.builder.sisbr.AnotacaoAvalistaOperacaoPrejuizoBuilder)
 */
package br.com.sicoob.capes.api.negocio.anotacao.builder.sisbr;

import br.com.sicoob.capes.comum.negocio.dto.AnotacaoSisbrDTO;
import br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum;

/**
 * Builder para as anotações de avalista de operação em prejuízo do SISBR.  
 * @author Erico.Junior
 */
public class AnotacaoAvalistaOperacaoPrejuizoBuilder extends AnotacaoSisbrBuilder {

	/**
	 * Cria uma nova instância de anotacao avalista operacao prejuizo builder.
	 * 
	 * @param resumo
	 *            the resumo
	 */
	public AnotacaoAvalistaOperacaoPrejuizoBuilder(AnotacaoSisbrDTO resumo) {
		super(resumo);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected Integer obterCodigoTipoAnotacao() {
		return TipoAnotacaoAutomaticaEnum.SISBR_AVALISTA_OPERACAO_PREJUIZO.getCodTipoAnotacao();
	}
}
