package br.com.sicoob.capes.api.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.INucleoDelegate;
import br.com.sicoob.capes.api.negocio.servicos.NucleoServico;
import br.com.sicoob.capes.api.negocio.servicos.locator.CAPESApiServiceLocator;
import br.com.sicoob.capes.api.negocio.vo.NucleoVO;

/**
 * A Classe NucleoDelegate.
 */
public class NucleoDelegate extends CAPESApiDelegate<NucleoServico> implements INucleoDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected NucleoServico localizarServico() {
		return CAPESApiServiceLocator.getInstance().localizarNucleoServico();
	}

	/**
	 * Listar nucleos.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<NucleoVO> listarNucleos(Integer idInstituicao) throws BancoobException {
		return getServico().listarNucleos(idInstituicao);
	}

}