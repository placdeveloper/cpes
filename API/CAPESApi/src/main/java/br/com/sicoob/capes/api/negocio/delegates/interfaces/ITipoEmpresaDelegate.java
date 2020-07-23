package br.com.sicoob.capes.api.negocio.delegates.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.TipoEmpresaVO;

/**
 * 
 * @author paulo.stoppa
 *
 */
public interface ITipoEmpresaDelegate extends ICAPESApiDelegate {

	/**
	 * Obter tipos de empresa.
	 *
	 * @return List
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	List<TipoEmpresaVO> listar() throws BancoobException;

}
