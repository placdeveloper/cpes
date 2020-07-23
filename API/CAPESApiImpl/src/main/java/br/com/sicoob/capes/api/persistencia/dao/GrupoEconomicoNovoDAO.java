package br.com.sicoob.capes.api.persistencia.dao;

import java.util.List;

import br.com.sicoob.capes.api.negocio.vo.GrupoEconomicoVO;
import br.com.sicoob.capes.api.negocio.vo.PessoaGrupoEconomicoVO;

/**
 * A Interface GrupoEconomicoDAO.
 */
public interface GrupoEconomicoNovoDAO extends CAPESApiDaoIF<GrupoEconomicoVO> {

	/**
	 * Obtem o grupo de uma pessoa e institui��o
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 */
	GrupoEconomicoVO obterGrupoPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao);

	/**
	 * Obter pessoas por pessoa e institui��o
	 *
	 * @param idPessoa
	 *            o valor de id pessoa
	 * @param idInstituicao
	 *            o valor de id instituicao
	 * @return List
	 */
	List<PessoaGrupoEconomicoVO> obterPessoasPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao);

	/**
	 * Obter pessoas por CPF/CNPJ e institui��o
	 * 
	 * @param listaCpfCnpj
	 * @param idInstituicao
	 * @return
	 */
	List<PessoaGrupoEconomicoVO> obterPessoasGruposPorCpfCnpj(List<String> listaCpfCnpj, Integer idInstituicao);

	/**
	 * 
	 * Obter pessoas por CPF/CNPJ e institui��o
	 * 
	 * @param cpfCnpj
	 * @param idInstituicao
	 * @return
	 */
	List<PessoaGrupoEconomicoVO> obterPessoasPorCpfCnpj(String cpfCnpj, Integer idInstituicao);

	/**
	 * Obt�m as pessoas de um terminado grupo econ�mico
	 * 
	 * @param idGrupoEconomico
	 * @return
	 */
	List<PessoaGrupoEconomicoVO> obterPessoasPorGrupo(Integer idGrupoEconomico);

}