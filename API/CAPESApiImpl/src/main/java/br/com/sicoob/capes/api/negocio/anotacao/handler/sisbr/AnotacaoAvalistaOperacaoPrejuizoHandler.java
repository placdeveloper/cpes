/*
 * SICOOB
 * 
 * AnotacaoAvalistaOperacaoPrejuizoHandler.java(br.com.sicoob.capes.api.negocio.anotacao.handler.sisbr.AnotacaoAvalistaOperacaoPrejuizoHandler)
 */
package br.com.sicoob.capes.api.negocio.anotacao.handler.sisbr;

import br.com.sicoob.capes.api.negocio.anotacao.builder.sisbr.AnotacaoAvalistaOperacaoPrejuizoBuilder;
import br.com.sicoob.capes.api.negocio.anotacao.builder.sisbr.AnotacaoSisbrBuilder;
import br.com.sicoob.capes.comum.negocio.dto.AnotacaoSisbrDTO;
import br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum;

/**
 * Handler para as anotações de avalista de operação em prejuízo do SISBR.  
 * @author Erico.Junior
 */
public class AnotacaoAvalistaOperacaoPrejuizoHandler extends AnotacaoSisbrHandler {

	/**
	 * Cria uma nova instância de anotacao avalista operacao prejuizo handler.
	 * 
	 * @param dto
	 *            the dto
	 */
	public AnotacaoAvalistaOperacaoPrejuizoHandler(AnotacaoSisbrDTO dto) {
		this(null, dto);
	}
	
	/**
	 * Cria uma nova instância de anotacao avalista operacao prejuizo handler.
	 * 
	 * @param proxima
	 *            the proxima
	 * @param dto
	 *            the dto
	 */
	public AnotacaoAvalistaOperacaoPrejuizoHandler(AnotacaoSisbrHandler proxima,
			AnotacaoSisbrDTO dto) {
		super(proxima, dto);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected TipoAnotacaoAutomaticaEnum recuperarTipoAnotacaoEnum() {
		return TipoAnotacaoAutomaticaEnum.SISBR_AVALISTA_OPERACAO_PREJUIZO;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected AnotacaoSisbrBuilder obterAnotacaoSisbrBuilder(AnotacaoSisbrDTO dto) {
		return new AnotacaoAvalistaOperacaoPrejuizoBuilder(dto);
	}
}
