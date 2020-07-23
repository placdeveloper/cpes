package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.vo.CentralSingularVO;
import br.com.sicoob.capes.cadastro.negocio.vo.UnidadeVO;

/**
 * A Interface CentraisSingularesDAO.
 */
public interface CentraisSingularesDAO {

	/**
	 * Obter lista centrais.
	 *
	 * @param idInstituicaoLogada o valor de id instituicao logada
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<CentralSingularVO> obterListaCentrais(Integer idInstituicaoLogada) throws BancoobException;

	/**
	 * Obter lista singulares.
	 *
	 * @param numeroCooperativa o valor de numero cooperativa
	 * @param idInstituicaoLogada o valor de id instituicao logada
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<CentralSingularVO> obterListaSingulares(Integer numeroCooperativa) throws BancoobException;
	
	/**
	 * Obter lista pacs.
	 *
	 * @param numeroCooperativa o valor de numero cooperativa
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<UnidadeVO> obterListaPacs(Integer numeroCooperativa) throws BancoobException;

}