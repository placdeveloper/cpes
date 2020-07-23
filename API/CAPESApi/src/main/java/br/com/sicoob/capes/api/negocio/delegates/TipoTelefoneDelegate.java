package br.com.sicoob.capes.api.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.ITipoTelefoneDelegate;
import br.com.sicoob.capes.api.negocio.servicos.TipoTelefoneServico;
import br.com.sicoob.capes.api.negocio.servicos.locator.CAPESApiServiceLocator;
import br.com.sicoob.capes.api.negocio.vo.TipoTelefoneVO;

/**
 * A Classe TipoTelefoneDelegate.
 */
public class TipoTelefoneDelegate extends CAPESApiDelegate<TipoTelefoneServico> implements ITipoTelefoneDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoTelefoneServico localizarServico() {
		return CAPESApiServiceLocator.getInstance().localizarTipoTelefoneServico();
	}
	
	/**
	 * Obter tipos de telefone.
	 *
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<TipoTelefoneVO> listar() throws BancoobException {
		return getServico().listar();
	}
	
}