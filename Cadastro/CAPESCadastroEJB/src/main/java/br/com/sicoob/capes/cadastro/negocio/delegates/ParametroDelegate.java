/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.ParametroServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.cadastro.negocio.vo.ParametroVO;

/**
 * 
 * @author Paulo.Stoppa
 *
 */
public final class ParametroDelegate extends CAPESCadastroDelegate<ParametroServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ParametroServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarParametroServico();
	}

	/**
	 * Servi�o para buscar o parametro
	 * 
	 * @param codigo
	 * @param idInstituicao
	 * @return
	 */
	public ParametroVO obterParametro(Integer codigo, Integer idInstituicao) {
		return getServico().obterParametro(codigo, idInstituicao);
	}

	/**
	 * M�todo para buscar o valor Boolean do parametro
	 * 
	 * @param codigo
	 * @param idInstituicao
	 * @return boolean
	 */
	public boolean obterParametroValorBoolean(Integer codigo, Integer idInstituicao) {
		return getServico().obterParametroValorBoolean(codigo, idInstituicao);
	}
}
