package br.com.sicoob.capes.api.negocio.delegates.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.TipoTelefoneVO;

/**
 * A interfacee TipoTelefoneDelegate.
 */
public interface ITipoTelefoneDelegate extends ICAPESApiDelegate {

	/**
	 * Obter tipos de telefone.
	 *
	 * @return List
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	List<TipoTelefoneVO> listar() throws BancoobException;

}