package br.com.sicoob.capes.api.negocio.anotacao.handler.sisbr;

import br.com.sicoob.capes.api.negocio.anotacao.builder.sisbr.AnotacaoInadimplencia30DiasBuilder;
import br.com.sicoob.capes.api.negocio.anotacao.builder.sisbr.AnotacaoSisbrBuilder;
import br.com.sicoob.capes.comum.negocio.dto.AnotacaoSisbrDTO;
import br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum;

/**
 * Handler para as anotações com inadimplência a mais de 30 dias do SISBR.
 * 
 * @author bruno.carneiro
 */
public class AnotacaoInadimplencia30DiasHandler extends AnotacaoSisbrHandler {

	/**
	 * Cria uma nova instância de anotacao AnotacaoInadimplencia30DiasHandler.
	 * 
	 * @param dto
	 *            the dto
	 */
	public AnotacaoInadimplencia30DiasHandler(AnotacaoSisbrDTO dto) {
		this(null, dto);
	}

	/**
	 * Cria uma nova instância de anotacao AnotacaoInadimplencia30DiasHandler.
	 * 
	 * @param proxima
	 *            the proxima
	 * @param dto
	 *            the dto
	 */
	public AnotacaoInadimplencia30DiasHandler(AnotacaoSisbrHandler proxima, AnotacaoSisbrDTO dto) {
		super(proxima, dto);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoAnotacaoAutomaticaEnum recuperarTipoAnotacaoEnum() {
		return TipoAnotacaoAutomaticaEnum.SISBR_INADIMPLENCIA_30_DIAS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AnotacaoSisbrBuilder obterAnotacaoSisbrBuilder(AnotacaoSisbrDTO dto) {
		return new AnotacaoInadimplencia30DiasBuilder(dto);
	}

}