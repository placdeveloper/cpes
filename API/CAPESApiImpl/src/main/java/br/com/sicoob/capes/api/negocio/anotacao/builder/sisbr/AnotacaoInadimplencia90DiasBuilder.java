/*
 * SICOOB
 * 
 * AnotacaoInadimplencia90DiasBuilder.java(br.com.sicoob.capes.api.negocio.anotacao.builder.sisbr.AnotacaoInadimplencia90DiasBuilder)
 */
package br.com.sicoob.capes.api.negocio.anotacao.builder.sisbr;

import br.com.sicoob.capes.comum.negocio.dto.AnotacaoSisbrDTO;
import br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum;

/**
 * Builder para as anota��es com inadimpl�ncia a mais de 90 dias do SISBR.  
 * @author Erico.Junior
 */
public class AnotacaoInadimplencia90DiasBuilder extends AnotacaoSisbrBuilder {

	/**
	 * Cria uma nova inst�ncia de anotacao inadimplencia90 dias builder.
	 * 
	 * @param resumo
	 *            the resumo
	 */
	public AnotacaoInadimplencia90DiasBuilder(AnotacaoSisbrDTO resumo) {
		super(resumo);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected Integer obterCodigoTipoAnotacao() {
		return TipoAnotacaoAutomaticaEnum.SISBR_INADIMPLENCIA_90_DIAS.getCodTipoAnotacao();
	}
}
