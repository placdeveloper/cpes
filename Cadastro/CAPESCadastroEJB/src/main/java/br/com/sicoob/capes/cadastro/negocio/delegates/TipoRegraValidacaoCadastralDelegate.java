/*
 * SICOOB
 * 
 * TipoRegraValidacaoCadastralDelegate.java(br.com.sicoob.capes.cadastro.negocio.delegates.TipoRegraValidacaoCadastralDelegate)
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.TipoRegraValidacaoCadastralServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.TipoRegraValidacaoCadastral;

/**
 * The Class TipoRegraValidacaoCadastralDelegate.
 */
public class TipoRegraValidacaoCadastralDelegate extends
		CAPESCadastroCrudDelegate<TipoRegraValidacaoCadastral, TipoRegraValidacaoCadastralServico> {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected TipoRegraValidacaoCadastralServico localizarServico() {

		return CAPESCadastroServiceLocator.getInstance().localizarTipoRegraValidacaoCadastralServico();
	}

}
