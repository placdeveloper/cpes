/*
 * SICOOB
 * 
 * ProdutividadePessoaServico.java(br.com.sicoob.capes.api.negocio.servicos.ProdutividadePessoaServico)
 */
package br.com.sicoob.capes.api.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.ProdutividadePessoaVO;

/**
 * @author Lucas.Borges
 */
public interface ProdutividadePessoaServico extends CAPESApiServicoPessoa<ProdutividadePessoaVO> {

	/**
	 * Obter todas por pessoa instituicao.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<ProdutividadePessoaVO> obterTodasPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;
	
}