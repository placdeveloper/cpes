/*
 * SICOOB
 * 
 * AnotacaoOperacaoTransferidaPrejuizoHandler.java(br.com.sicoob.capes.api.negocio.anotacao.handler.sisbr.AnotacaoOperacaoTransferidaPrejuizoHandler)
 */
package br.com.sicoob.capes.api.negocio.anotacao.handler.sisbr;

import br.com.sicoob.capes.api.negocio.anotacao.builder.sisbr.AnotacaoOperacaoTransferidaPrejuizoBuilder;
import br.com.sicoob.capes.api.negocio.anotacao.builder.sisbr.AnotacaoSisbrBuilder;
import br.com.sicoob.capes.comum.negocio.dto.AnotacaoSisbrDTO;
import br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum;


/**
 * Handler para as anotações para operações transferidas para prejuizo do SISBR.  
 * @author Erico.Junior
 */
public class AnotacaoOperacaoTransferidaPrejuizoHandler extends AnotacaoSisbrHandler{

	/**
	 * Cria uma nova instância de anotacao operacao transferida prejuizo handler.
	 * 
	 * @param dto
	 *            the dto
	 */
	public AnotacaoOperacaoTransferidaPrejuizoHandler(AnotacaoSisbrDTO dto) {
		this(null, dto);
	}
	
	/**
	 * Cria uma nova instância de anotacao operacao transferida prejuizo handler.
	 * 
	 * @param proxima
	 *            the proxima
	 * @param dto
	 *            the dto
	 */
	public AnotacaoOperacaoTransferidaPrejuizoHandler(AnotacaoSisbrHandler proxima,
			AnotacaoSisbrDTO dto) {
		super(proxima, dto);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected TipoAnotacaoAutomaticaEnum recuperarTipoAnotacaoEnum() {
		return TipoAnotacaoAutomaticaEnum.SISBR_OPERACAO_TRANSFERIDA_PREJUIZO;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected AnotacaoSisbrBuilder obterAnotacaoSisbrBuilder(AnotacaoSisbrDTO dto) {
		return new AnotacaoOperacaoTransferidaPrejuizoBuilder(dto);
	}
}
