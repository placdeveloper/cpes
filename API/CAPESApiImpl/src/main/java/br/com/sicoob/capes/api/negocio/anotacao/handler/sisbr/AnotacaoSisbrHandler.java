/*
 * SICOOB
 * 
 * AnotacaoSisbrHandler.java(br.com.sicoob.capes.api.negocio.anotacao.handler.sisbr.AnotacaoSisbrHandler)
 */
package br.com.sicoob.capes.api.negocio.anotacao.handler.sisbr;

import br.com.sicoob.capes.api.negocio.anotacao.builder.AnotacaoBuilder;
import br.com.sicoob.capes.api.negocio.anotacao.builder.sisbr.AnotacaoSisbrBuilder;
import br.com.sicoob.capes.api.negocio.anotacao.handler.AnotacaoHandler;
import br.com.sicoob.capes.comum.negocio.dto.AnotacaoSisbrDTO;
import br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum;

/**
 * Classe abstrata que define o tratador das requisições da cadeia de responsabilidade para as
 * anotações do SISBR.
 * 
 * @author Erico.Junior
 */
public abstract class AnotacaoSisbrHandler extends AnotacaoHandler {

	/** O atributo dto. */
	private transient AnotacaoSisbrDTO dto = new AnotacaoSisbrDTO();
	
	/**
	 * Cria o elo da cadeia de responsabilidade com o próximo elo.
	 * 
	 * @param proxima
	 *            O próximo elo da cadeia de responsabilidade.
	 * @param dto
	 *            O dto com as informações para a anotação proveniente do SISBR.
	 */
	public AnotacaoSisbrHandler(AnotacaoSisbrHandler proxima, AnotacaoSisbrDTO dto) {
		super(proxima);
		if (dto != null) {
			this.dto = dto;
		}
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected boolean deveTratarRequisicao() {
		TipoAnotacaoAutomaticaEnum tipoEnum = recuperarTipoAnotacaoEnum();
		return tipoEnum.getCodTipoAnotacao().equals(dto.getCodigoTipoAnotacao());
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected AnotacaoBuilder obterAnotacaoBuilder() {
		return obterAnotacaoSisbrBuilder(dto);
	}
	
	/**
	 * Recupera o AnotacaoSisbrBuilder para o tipo de anotação.
	 * 
	 * @param dto
	 *            O objeto com os dados para anotação proveniente do SISBR.
	 * @return o AnotacaoSisbrBuilder para o tipo de anotação.
	 */
	protected abstract AnotacaoSisbrBuilder obterAnotacaoSisbrBuilder(AnotacaoSisbrDTO dto);
	
	/**
	 * Recupera o TipoAnotacaoAutomaticaEnum que o handler irá tratar.
	 * @return o TipoAnotacaoAutomaticaEnum que o handler irá tratar.
	 */
	protected abstract TipoAnotacaoAutomaticaEnum recuperarTipoAnotacaoEnum();
	
}
