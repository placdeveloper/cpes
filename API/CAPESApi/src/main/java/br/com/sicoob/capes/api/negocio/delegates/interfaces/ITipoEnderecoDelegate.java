package br.com.sicoob.capes.api.negocio.delegates.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.TipoEnderecoVO;

/**
 * 
 * @author paulo.stoppa
 *
 */
public interface ITipoEnderecoDelegate extends ICAPESApiDelegate {

	/**
	 * Obter tipos de endereco.
	 *
	 * @return List
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	List<TipoEnderecoVO> listar() throws BancoobException;

}
