/*
 * SICOOB
 * 
 * AnotacaoInadimplencia15DiasHandler.java(br.com.sicoob.capes.api.negocio.anotacao.handler.sisbr.AnotacaoInadimplencia15DiasHandler)
 */
package br.com.sicoob.capes.api.negocio.anotacao.handler.sisbr;

import br.com.sicoob.capes.api.negocio.anotacao.builder.sisbr.AnotacaoInadimplencia15DiasBuilder;
import br.com.sicoob.capes.api.negocio.anotacao.builder.sisbr.AnotacaoSisbrBuilder;
import br.com.sicoob.capes.comum.negocio.dto.AnotacaoSisbrDTO;
import br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum;

/**
 * Handler para as anotações com inadimplência a mais de 15 dias do SISBR.
 * 
 * @author Erico.Junior
 */
public class AnotacaoInadimplencia15DiasHandler extends AnotacaoSisbrHandler {

	/**
	 * Cria uma nova instância de anotacao inadimplencia15 dias handler.
	 * 
	 * @param dto
	 *            the dto
	 */
	public AnotacaoInadimplencia15DiasHandler(AnotacaoSisbrDTO dto) {
		this(null, dto);
	}

	/**
	 * Cria uma nova instância de anotacao inadimplencia15 dias handler.
	 * 
	 * @param proxima
	 *            the proxima
	 * @param dto
	 *            the dto
	 */
	public AnotacaoInadimplencia15DiasHandler(AnotacaoSisbrHandler proxima,
			AnotacaoSisbrDTO dto) {
		super(proxima, dto);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected TipoAnotacaoAutomaticaEnum recuperarTipoAnotacaoEnum() {
		return TipoAnotacaoAutomaticaEnum.SISBR_INADIMPLENCIA_15_DIAS;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected AnotacaoSisbrBuilder obterAnotacaoSisbrBuilder(AnotacaoSisbrDTO dto) {
		return new AnotacaoInadimplencia15DiasBuilder(dto);
	}
	
}