/**
 * 
 */
package br.com.sicoob.capes.api.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.ITipoEnderecoDelegate;
import br.com.sicoob.capes.api.negocio.servicos.TipoEnderecoServico;
import br.com.sicoob.capes.api.negocio.servicos.locator.CAPESApiServiceLocator;
import br.com.sicoob.capes.api.negocio.vo.TipoEnderecoVO;

/**
 * @author Erico.Junior
 * 
 */
public class TipoEnderecoDelegate extends CAPESApiDelegate<TipoEnderecoServico> implements ITipoEnderecoDelegate {

	@Override
	protected TipoEnderecoServico localizarServico() {
		return CAPESApiServiceLocator.getInstance().localizarTipoEnderecoServico();
	}

	
	/**
	 * Obter tipos de endereco.
	 *
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<TipoEnderecoVO> listar() throws BancoobException {
		return getServico().listar();
	}
}
