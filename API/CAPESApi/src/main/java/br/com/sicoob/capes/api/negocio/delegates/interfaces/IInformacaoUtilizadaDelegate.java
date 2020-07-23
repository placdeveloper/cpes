package br.com.sicoob.capes.api.negocio.delegates.interfaces;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.InformacaoUtilizadaVO;

/**
 * A interfacee InformacaoUtilizadaDelegate.
 */
public interface IInformacaoUtilizadaDelegate extends ICAPESApiDelegate {

	/**
	 * O m�todo Incluir.
	 *
	 * @param informacaoUtilizadaVO
	 *            o valor de informacao utilizada vo
	 * @throws BancoobException
	 *             lan�a a exce��o BancoobException
	 */
	void incluir(InformacaoUtilizadaVO informacaoUtilizadaVO) throws BancoobException;

	/**
	 * O m�todo Excluir.
	 *
	 * @param informacaoUtilizadaVO
	 *            o valor de informacao utilizada vo
	 * @throws BancoobException
	 *             lan�a a exce��o BancoobException
	 */
	void excluir(InformacaoUtilizadaVO informacaoUtilizadaVO) throws BancoobException;

}