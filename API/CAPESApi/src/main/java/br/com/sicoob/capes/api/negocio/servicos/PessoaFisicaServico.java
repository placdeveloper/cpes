/*
 * SICOOB
 * 
 * PessoaFisicaServico.java(br.com.sicoob.capes.api.negocio.servicos.PessoaFisicaServico)
 */
package br.com.sicoob.capes.api.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.PessoaFisicaVO;

/**
 * @author Lucas.Borges
 */
public interface PessoaFisicaServico extends CAPESApiServico {

	/**
	 * Obter por pessoa instituicao.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 * @return pessoa fisica vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	PessoaFisicaVO obterByPessoaInstituicao(Integer idPessoa,
			Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obtém a pessoa fisica por CPF e instituição
	 * 
	 * @param cpf
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	PessoaFisicaVO obterPorCPFInstituicao(String cpf, Integer idInstituicao) throws BancoobException;
}
