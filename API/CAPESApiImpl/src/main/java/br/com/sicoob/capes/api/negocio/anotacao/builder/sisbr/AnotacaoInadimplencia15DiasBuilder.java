/*
 * SICOOB
 * 
 * AnotacaoInadimplencia15DiasBuilder.java(br.com.sicoob.capes.api.negocio.anotacao.builder.sisbr.AnotacaoInadimplencia15DiasBuilder)
 */
package br.com.sicoob.capes.api.negocio.anotacao.builder.sisbr;

import br.com.sicoob.capes.comum.negocio.dto.AnotacaoSisbrDTO;
import br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum;

/**
 * Builder para as anotações com inadimplência a mais de 15 dias do SISBR.  
 * @author Erico.Junior
 */
public class AnotacaoInadimplencia15DiasBuilder extends AnotacaoSisbrBuilder {

	/**
	 * Cria uma nova instância de anotacao inadimplencia15 dias builder.
	 * 
	 * @param resumo
	 *            the resumo
	 */
	public AnotacaoInadimplencia15DiasBuilder(AnotacaoSisbrDTO resumo) {
		super(resumo);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected Integer obterCodigoTipoAnotacao() {
		return TipoAnotacaoAutomaticaEnum.SISBR_INADIMPLENCIA_15_DIAS.getCodTipoAnotacao();
	}
}
