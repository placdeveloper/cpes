package br.com.sicoob.capes.api.negocio.delegates.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.NucleoVO;

/**
 * A interfacee NucleoDelegate.
 */
public interface INucleoDelegate extends ICAPESApiDelegate {

	/**
	 * Listar nucleos.
	 *
	 * @param idInstituicao
	 *            o valor de id instituicao
	 * @return List
	 * @throws BancoobException
	 *             lan�a a exce��o BancoobException
	 */
	List<NucleoVO> listarNucleos(Integer idInstituicao) throws BancoobException;

}