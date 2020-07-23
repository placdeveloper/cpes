package br.com.sicoob.capes.api.persistencia.dao;

import java.util.List;

import br.com.sicoob.capes.api.negocio.vo.DadosClienteVO;

/**
 * A Interface DadosClienteDAO.
 */
public interface DadosClienteDAO extends CAPESApiDaoIF<DadosClienteVO> {

	/**
	 * Obter dados cliente por instituicao funcionario.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @param idFuncionario o valor de id funcionario
	 * @return List
	 */
	List<DadosClienteVO> obterDadosClientePorInstituicaoFuncionario(Integer idInstituicao, Integer idFuncionario);

}