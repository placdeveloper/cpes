/*
 * SICOOB
 * 
 * AnotacaoOperacaoTransferidaPrejuizoBuilder.java(br.com.sicoob.capes.api.negocio.anotacao.builder.sisbr.AnotacaoOperacaoTransferidaPrejuizoBuilder)
 */
package br.com.sicoob.capes.api.negocio.anotacao.builder.sisbr;

import br.com.sicoob.capes.comum.negocio.dto.AnotacaoSisbrDTO;
import br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum;


/**
 * Builder para as anotações para operações transferidas para prejuizo do SISBR.  
 * @author Erico.Junior
 */
public class AnotacaoOperacaoTransferidaPrejuizoBuilder extends AnotacaoSisbrBuilder {

	/**
	 * Cria uma nova instância de anotacao operacao transferida prejuizo builder.
	 * 
	 * @param resumo
	 *            the resumo
	 */
	public AnotacaoOperacaoTransferidaPrejuizoBuilder(AnotacaoSisbrDTO resumo) {
		super(resumo);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected Integer obterCodigoTipoAnotacao() {
		return TipoAnotacaoAutomaticaEnum.SISBR_OPERACAO_TRANSFERIDA_PREJUIZO.getCodTipoAnotacao();
	}
	
}
