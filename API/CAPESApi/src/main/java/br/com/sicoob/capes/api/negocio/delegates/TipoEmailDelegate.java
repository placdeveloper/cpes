package br.com.sicoob.capes.api.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.ITipoEmailDelegate;
import br.com.sicoob.capes.api.negocio.servicos.TipoEmailServico;
import br.com.sicoob.capes.api.negocio.servicos.locator.CAPESApiServiceLocator;
import br.com.sicoob.capes.api.negocio.vo.TipoEmailVO;

/**
 * A Classe TipoEmailDelegate.
 */
public class TipoEmailDelegate extends CAPESApiDelegate<TipoEmailServico> implements ITipoEmailDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoEmailServico localizarServico() {
		return CAPESApiServiceLocator.getInstance().localizarTipoEmailServico();
	}
	
	/**
	 * Obter tipos de email.
	 *
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<TipoEmailVO> listar() throws BancoobException {
		return getServico().listar();
	}
	
}