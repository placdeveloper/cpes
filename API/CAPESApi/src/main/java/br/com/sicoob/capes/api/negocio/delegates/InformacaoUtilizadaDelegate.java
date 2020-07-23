package br.com.sicoob.capes.api.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.IInformacaoUtilizadaDelegate;
import br.com.sicoob.capes.api.negocio.servicos.InformacaoUtilizadaServico;
import br.com.sicoob.capes.api.negocio.servicos.locator.CAPESApiServiceLocator;
import br.com.sicoob.capes.api.negocio.vo.InformacaoUtilizadaVO;

/**
 * A Classe InformacaoUtilizadaDelegate.
 */
public class InformacaoUtilizadaDelegate extends CAPESApiDelegate<InformacaoUtilizadaServico> implements IInformacaoUtilizadaDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected InformacaoUtilizadaServico localizarServico() {
		return CAPESApiServiceLocator.getInstance().localizarInformacaoUtilizadaServico();
	}
	
	/**
	 * O método Incluir.
	 *
	 * @param informacaoUtilizadaVO o valor de informacao utilizada vo
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void incluir(InformacaoUtilizadaVO informacaoUtilizadaVO) throws BancoobException {
		getServico().incluirInformacaoUtilizada(informacaoUtilizadaVO);
	}

	/**
	 * O método Excluir.
	 *
	 * @param informacaoUtilizadaVO o valor de informacao utilizada vo
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void excluir(InformacaoUtilizadaVO informacaoUtilizadaVO) throws BancoobException {
		getServico().excluirInformacaoUtilizada(informacaoUtilizadaVO);
	}

}