package br.com.sicoob.capes.api.negocio.delegates.interfaces;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.InformacaoUtilizadaVO;

/**
 * A interfacee InformacaoUtilizadaDelegate.
 */
public interface IInformacaoUtilizadaDelegate extends ICAPESApiDelegate {

	/**
	 * O método Incluir.
	 *
	 * @param informacaoUtilizadaVO
	 *            o valor de informacao utilizada vo
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	void incluir(InformacaoUtilizadaVO informacaoUtilizadaVO) throws BancoobException;

	/**
	 * O método Excluir.
	 *
	 * @param informacaoUtilizadaVO
	 *            o valor de informacao utilizada vo
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	void excluir(InformacaoUtilizadaVO informacaoUtilizadaVO) throws BancoobException;

}