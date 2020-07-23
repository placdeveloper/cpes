/*
 * SICOOB
 * 
 * ProdutividadePessoaDelegate.java(br.com.sicoob.capes.api.negocio.delegates.ProdutividadePessoaDelegate)
 */
package br.com.sicoob.capes.api.negocio.delegates.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.ProdutividadePessoaVO;

/**
 * @author Lucas.Borges
 */
public interface IProdutividadePessoaDelegate extends IAbstractCAPESApiPessoaDelegate<ProdutividadePessoaVO> {

	/**
	 * Obter todas por pessoa instituicao.
	 *
	 * @param idPessoa
	 *            o valor de id pessoa
	 * @param idInstituicao
	 *            o valor de id instituicao
	 * @return List
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	List<ProdutividadePessoaVO> obterTodasPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

}