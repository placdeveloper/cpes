package br.com.sicoob.capes.api.persistencia.dao;

import java.io.Serializable;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.ProdutividadePessoaVO;

/**
 * A Interface ProdutividadePessoaDAO.
 */
public interface ProdutividadePessoaDAO extends CAPESApiDaoIF<ProdutividadePessoaVO> {
	
	/**
	 * Obter por id.
	 *
	 * @param id o valor de id
	 * @param situacao o valor de situacao
	 * @return ProdutividadePessoaVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	ProdutividadePessoaVO obterPorId(Serializable id, List<Short> situacao) throws BancoobException;
	
	/**
	 * Obter por pessoa instituicao.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @param situacoes o valor de situacoes
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<ProdutividadePessoaVO> obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, List<Short> situacoes) throws BancoobException;

}