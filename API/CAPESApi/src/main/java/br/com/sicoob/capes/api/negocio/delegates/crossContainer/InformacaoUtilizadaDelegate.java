package br.com.sicoob.capes.api.negocio.delegates.crossContainer;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.IInformacaoUtilizadaDelegate;
import br.com.sicoob.capes.api.negocio.servicos.InformacaoUtilizadaServico;
import br.com.sicoob.capes.api.negocio.vo.InformacaoUtilizadaVO;

/**
 * A Classe InformacaoUtilizadaDelegate.
 */
public class InformacaoUtilizadaDelegate extends CAPESApiDelegate<InformacaoUtilizadaServico> implements IInformacaoUtilizadaDelegate {
	
	/**
	 * 
	 */
	protected InformacaoUtilizadaDelegate() {
	}

	/**
	 * 
	 * @return
	 */
	public static InformacaoUtilizadaDelegate getInstance() {
		return valueOf(InformacaoUtilizadaDelegate.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected InformacaoUtilizadaServico localizarServico() {
		return getLocator().localizarInformacaoUtilizadaServico();
	}
	
	/**
	 * O m�todo Incluir.
	 *
	 * @param informacaoUtilizadaVO o valor de informacao utilizada vo
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public void incluir(InformacaoUtilizadaVO informacaoUtilizadaVO) throws BancoobException {
		getServico().incluirInformacaoUtilizada(informacaoUtilizadaVO);
	}

	/**
	 * O m�todo Excluir.
	 *
	 * @param informacaoUtilizadaVO o valor de informacao utilizada vo
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public void excluir(InformacaoUtilizadaVO informacaoUtilizadaVO) throws BancoobException {
		getServico().excluirInformacaoUtilizada(informacaoUtilizadaVO);
	}

}