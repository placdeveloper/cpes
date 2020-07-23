/*
 * SICOOB
 * 
 * AnotacaoInadimplencia90DiasHandler.java(br.com.sicoob.capes.api.negocio.anotacao.handler.sisbr.AnotacaoInadimplencia90DiasHandler)
 */
package br.com.sicoob.capes.api.negocio.anotacao.handler.sisbr;

import br.com.sicoob.capes.api.negocio.anotacao.builder.sisbr.AnotacaoInadimplencia90DiasBuilder;
import br.com.sicoob.capes.api.negocio.anotacao.builder.sisbr.AnotacaoSisbrBuilder;
import br.com.sicoob.capes.comum.negocio.dto.AnotacaoSisbrDTO;
import br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum;

/**
 * Handler para as anotações com inadimplência a mais de 90 dias do SISBR.  
 * @author Erico.Junior
 */
public class AnotacaoInadimplencia90DiasHandler extends AnotacaoSisbrHandler{

	/**
	 * Cria uma nova instância de anotacao inadimplencia90 dias handler.
	 * 
	 * @param dto
	 *            the dto
	 */
	public AnotacaoInadimplencia90DiasHandler(AnotacaoSisbrDTO dto) {
		this(null, dto);
	}
	
	/**
	 * Cria uma nova instância de anotacao inadimplencia90 dias handler.
	 * 
	 * @param proxima
	 *            the proxima
	 * @param dto
	 *            the dto
	 */
	public AnotacaoInadimplencia90DiasHandler(AnotacaoSisbrHandler proxima,
			AnotacaoSisbrDTO dto) {
		super(proxima, dto);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected TipoAnotacaoAutomaticaEnum recuperarTipoAnotacaoEnum() {
		return TipoAnotacaoAutomaticaEnum.SISBR_INADIMPLENCIA_90_DIAS;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected AnotacaoSisbrBuilder obterAnotacaoSisbrBuilder(AnotacaoSisbrDTO dto) {
		return new AnotacaoInadimplencia90DiasBuilder(dto);
	}
}
