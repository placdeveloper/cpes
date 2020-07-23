/**
 * 
 */
package br.com.sicoob.capes.api.negocio.delegates.crossContainer;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.ITipoEnderecoDelegate;
import br.com.sicoob.capes.api.negocio.servicos.TipoEnderecoServico;
import br.com.sicoob.capes.api.negocio.vo.TipoEnderecoVO;

/**
 * @author Erico.Junior
 * 
 */
public class TipoEnderecoDelegate extends CAPESApiDelegate<TipoEnderecoServico> implements ITipoEnderecoDelegate {
	
	/**
	 * 
	 */
	protected TipoEnderecoDelegate() {
	}

	/**
	 * 
	 * @return
	 */
	public static TipoEnderecoDelegate getInstance() {
		return valueOf(TipoEnderecoDelegate.class);
	}

	@Override
	protected TipoEnderecoServico localizarServico() {
		return getLocator().localizarTipoEnderecoServico();
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
