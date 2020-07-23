/**
 * 
 */
package br.com.sicoob.capes.api.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.ITipoEmpresaDelegate;
import br.com.sicoob.capes.api.negocio.servicos.TipoEmpresaServico;
import br.com.sicoob.capes.api.negocio.servicos.locator.CAPESApiServiceLocator;
import br.com.sicoob.capes.api.negocio.vo.TipoEmpresaVO;

/**
 * @author Erico.Junior
 * 
 */
public class TipoEmpresaDelegate extends CAPESApiDelegate<TipoEmpresaServico> implements ITipoEmpresaDelegate {

	@Override
	protected TipoEmpresaServico localizarServico() {
		return CAPESApiServiceLocator.getInstance().localizarTipoEmpresaServico();
	}

	
	/**
	 * Obter tipos de empresa.
	 *
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<TipoEmpresaVO> listar() throws BancoobException {
		return getServico().listar();
	}
}
