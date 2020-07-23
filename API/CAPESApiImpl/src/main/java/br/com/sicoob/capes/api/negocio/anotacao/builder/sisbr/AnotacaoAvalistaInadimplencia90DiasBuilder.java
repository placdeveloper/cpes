/*
 * SICOOB
 * 
 * AnotacaoAvalistaInadimplencia90DiasBuilder.java(br.com.sicoob.capes.api.negocio.anotacao.builder.sisbr.AnotacaoAvalistaInadimplencia90DiasBuilder)
 */
package br.com.sicoob.capes.api.negocio.anotacao.builder.sisbr;

import br.com.sicoob.capes.comum.negocio.dto.AnotacaoSisbrDTO;
import br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum;

/**
 * Builder para as anotações de avalista de operação com inadimplencia a mais de 90 dias do SISBR.  
 * @author Erico.Junior
 */
public class AnotacaoAvalistaInadimplencia90DiasBuilder extends AnotacaoSisbrBuilder {
	
	/**
	 * Cria uma nova instância de anotacao avalista inadimplencia90 dias builder.
	 * 
	 * @param resumo
	 *            the resumo
	 */
	public AnotacaoAvalistaInadimplencia90DiasBuilder(AnotacaoSisbrDTO resumo) {
		super(resumo);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected Integer obterCodigoTipoAnotacao() {
		return TipoAnotacaoAutomaticaEnum.SISBR_AVALISTA_INADIMPLENCIA_90_DIAS.getCodTipoAnotacao();
	}
}
