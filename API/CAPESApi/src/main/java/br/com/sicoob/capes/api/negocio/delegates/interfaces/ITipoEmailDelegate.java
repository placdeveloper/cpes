package br.com.sicoob.capes.api.negocio.delegates.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.TipoEmailVO;

/**
 * A interfacee TipoEmailDelegate.
 */
public interface ITipoEmailDelegate extends ICAPESApiDelegate {

	/**
	 * Obter tipos de email.
	 *
	 * @return List
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	List<TipoEmailVO> listar() throws BancoobException;

}