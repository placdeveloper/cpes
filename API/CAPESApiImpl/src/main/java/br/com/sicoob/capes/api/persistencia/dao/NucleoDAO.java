package br.com.sicoob.capes.api.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.NucleoVO;

/**
 * A Interface NucleoDAO.
 */
public interface NucleoDAO extends CAPESApiDaoIF<NucleoVO> {

	/**
	 * Listar nucleos.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<NucleoVO> listarNucleos(Integer idInstituicao) throws BancoobException;

}