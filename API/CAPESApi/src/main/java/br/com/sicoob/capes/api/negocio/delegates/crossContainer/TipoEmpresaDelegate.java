/**
 * 
 */
package br.com.sicoob.capes.api.negocio.delegates.crossContainer;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.ITipoEmpresaDelegate;
import br.com.sicoob.capes.api.negocio.servicos.TipoEmpresaServico;
import br.com.sicoob.capes.api.negocio.vo.TipoEmpresaVO;

/**
 * @author Erico.Junior
 * 
 */
public class TipoEmpresaDelegate extends CAPESApiDelegate<TipoEmpresaServico> implements ITipoEmpresaDelegate {
	
	/**
	 * 
	 */
	protected TipoEmpresaDelegate() {
	}

	/**
	 * 
	 * @return
	 */
	public static TipoEmpresaDelegate getInstance() {
		return valueOf(TipoEmpresaDelegate.class);
	}

	@Override
	protected TipoEmpresaServico localizarServico() {
		return getLocator().localizarTipoEmpresaServico();
	}

	
	/**
	 * Obter tipos de empresa.
	 *
	 * @return List
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public List<TipoEmpresaVO> listar() throws BancoobException {
		return getServico().listar();
	}
}
