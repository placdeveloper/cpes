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
 * Classe abstrata que define o tratador das requisi��es da cadeia de responsabilidade para as
 * anota��es do SISBR.
 * 
 * @author Erico.Junior
 */
public abstract class AnotacaoSisbrHandler extends AnotacaoHandler {

	/** O atributo dto. */
	private transient AnotacaoSisbrDTO dto = new AnotacaoSisbrDTO();
	
	/**
	 * Cria o elo da cadeia de responsabilidade com o pr�ximo elo.
	 * 
	 * @param proxima
	 *            O pr�ximo elo da cadeia de responsabilidade.
	 * @param dto
	 *            O dto com as informa��es para a anota��o proveniente do SISBR.
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
	 * Recupera o AnotacaoSisbrBuilder para o tipo de anota��o.
	 * 
	 * @param dto
	 *            O objeto com os dados para anota��o proveniente do SISBR.
	 * @return o AnotacaoSisbrBuilder para o tipo de anota��o.
	 */
	protected abstract AnotacaoSisbrBuilder obterAnotacaoSisbrBuilder(AnotacaoSisbrDTO dto);
	
	/**
	 * Recupera o TipoAnotacaoAutomaticaEnum que o handler ir� tratar.
	 * @return o TipoAnotacaoAutomaticaEnum que o handler ir� tratar.
	 */
	protected abstract TipoAnotacaoAutomaticaEnum recuperarTipoAnotacaoEnum();
	
}
