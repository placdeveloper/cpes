/*
 * SICOOB
 * 
 * InformacaoProfissionalDelegate.java(br.com.sicoob.capes.api.negocio.delegates.InformacaoProfissionalDelegate)
 */
package br.com.sicoob.capes.api.negocio.delegates;

import br.com.sicoob.capes.api.negocio.delegates.interfaces.IInformacaoProfissionalDelegate;
import br.com.sicoob.capes.api.negocio.servicos.InformacaoProfissionalServico;
import br.com.sicoob.capes.api.negocio.servicos.locator.CAPESApiServiceLocator;
import br.com.sicoob.capes.api.negocio.vo.InformacaoProfissionalVO;

/**
 * @author erico.junior
 * 
 */
public class InformacaoProfissionalDelegate extends AbstractCAPESApiPessoaDelegate<InformacaoProfissionalVO, InformacaoProfissionalServico> implements IInformacaoProfissionalDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected InformacaoProfissionalServico localizarServico() {
		return CAPESApiServiceLocator.getInstance().localizarInformacaoProfissionalServico();
	}
}
