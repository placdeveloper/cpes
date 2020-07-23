package br.com.sicoob.capes.api.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.GrupoEconomicoVO;
import br.com.sicoob.capes.api.negocio.vo.PessoaGrupoEconomicoVO;

/**
 * A Interface GrupoEconomicoDAO.
 */
public interface GrupoEconomicoDAO extends CAPESApiDaoIF<GrupoEconomicoVO> {
	
	/**
	 * Obter pessoas por grupo instituicao.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<PessoaGrupoEconomicoVO> obterPessoasPorGrupoInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	List<PessoaGrupoEconomicoVO> obterGruposPorCpfCnpj(List<String> listaCpfCnpj, Integer idInstituicao);
	
	/**
	 * Obtém as pessoas de um terminado grupo econômico
	 * 
	 * @param idGrupoEconomico
	 * @return
	 */
	List<PessoaGrupoEconomicoVO> obterPessoasPorGrupo(Integer idGrupoEconomico);

}