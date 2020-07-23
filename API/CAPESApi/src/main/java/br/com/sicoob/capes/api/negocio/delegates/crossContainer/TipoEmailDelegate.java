package br.com.sicoob.capes.api.negocio.delegates.crossContainer;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.ITipoEmailDelegate;
import br.com.sicoob.capes.api.negocio.servicos.TipoEmailServico;
import br.com.sicoob.capes.api.negocio.vo.TipoEmailVO;

/**
 * A Classe TipoEmailDelegate.
 */
public class TipoEmailDelegate extends CAPESApiDelegate<TipoEmailServico> implements ITipoEmailDelegate {
	
	/**
	 * 
	 */
	protected TipoEmailDelegate() {
	}

	/**
	 * 
	 * @return
	 */
	public static TipoEmailDelegate getInstance() {
		return valueOf(TipoEmailDelegate.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoEmailServico localizarServico() {
		return getLocator().localizarTipoEmailServico();
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