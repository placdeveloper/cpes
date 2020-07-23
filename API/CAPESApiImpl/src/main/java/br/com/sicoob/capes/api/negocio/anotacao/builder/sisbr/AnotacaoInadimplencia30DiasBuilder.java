package br.com.sicoob.capes.api.negocio.anotacao.builder.sisbr;

import br.com.sicoob.capes.comum.negocio.dto.AnotacaoSisbrDTO;
import br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum;

/**
 * Builder para as anota��es com inadimpl�ncia a mais de 30 dias do SISBR.
 * 
 * @author bruno.carneiro
 */
public class AnotacaoInadimplencia30DiasBuilder extends AnotacaoSisbrBuilder {

	/**
	 * Cria uma nova inst�ncia de anotacao AnotacaoInadimplencia30DiasBuilder.
	 * 
	 * @param resumo
	 *            the resumo
	 */
	public AnotacaoInadimplencia30DiasBuilder(AnotacaoSisbrDTO resumo) {
		super(resumo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Integer obterCodigoTipoAnotacao() {
		return TipoAnotacaoAutomaticaEnum.SISBR_INADIMPLENCIA_30_DIAS.getCodTipoAnotacao();
	}
}