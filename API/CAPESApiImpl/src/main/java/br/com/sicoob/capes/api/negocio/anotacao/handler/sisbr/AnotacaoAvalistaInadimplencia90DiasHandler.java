/*
 * SICOOB
 * 
 * AnotacaoAvalistaInadimplencia90DiasHandler.java(br.com.sicoob.capes.api.negocio.anotacao.handler.sisbr.AnotacaoAvalistaInadimplencia90DiasHandler)
 */
package br.com.sicoob.capes.api.negocio.anotacao.handler.sisbr;

import br.com.sicoob.capes.api.negocio.anotacao.builder.sisbr.AnotacaoAvalistaInadimplencia90DiasBuilder;
import br.com.sicoob.capes.api.negocio.anotacao.builder.sisbr.AnotacaoSisbrBuilder;
import br.com.sicoob.capes.comum.negocio.dto.AnotacaoSisbrDTO;
import br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum;

/**
 * Handler para as anotações de avalista de operação com inadimplencia a mais de
 * 90 dias do SISBR.
 * 
 * @author Erico.Junior
 */
public class AnotacaoAvalistaInadimplencia90DiasHandler extends AnotacaoSisbrHandler {

	/**
	 * Cria uma nova instância de anotacao avalista inadimplencia90 dias handler.
	 * 
	 * @param dto
	 *            the dto
	 */
	public AnotacaoAvalistaInadimplencia90DiasHandler(AnotacaoSisbrDTO dto) {
		this(null, dto);
	}

	/**
	 * Cria uma nova instância de anotacao avalista inadimplencia90 dias handler.
	 * 
	 * @param proxima
	 *            the proxima
	 * @param dto
	 *            the dto
	 */
	public AnotacaoAvalistaInadimplencia90DiasHandler(AnotacaoSisbrHandler proxima,
			AnotacaoSisbrDTO dto) {
		super(proxima, dto);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected AnotacaoSisbrBuilder obterAnotacaoSisbrBuilder(AnotacaoSisbrDTO dto) {
		return new AnotacaoAvalistaInadimplencia90DiasBuilder(dto);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected TipoAnotacaoAutomaticaEnum recuperarTipoAnotacaoEnum() {
		return TipoAnotacaoAutomaticaEnum.SISBR_AVALISTA_INADIMPLENCIA_90_DIAS;
	}

}
