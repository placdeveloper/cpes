/*
 * SICOOB
 * 
 * DadosClienteServico.java(br.com.sicoob.capes.api.negocio.servicos.DadosClienteServico)
 */
package br.com.sicoob.capes.api.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.DadosClienteVO;

/**
 * @author Lucas.Borges
 */
public interface DadosClienteServico extends CAPESApiServico {

	/**
	 * Obter por pessoa instituicao.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 * @return dados cliente vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	DadosClienteVO obterByPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obter por instituicao funcionario.
	 * 
	 * @param idInstituicao
	 *            the id instituicao
	 * @param idFuncionario
	 *            the id funcionario
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	List<DadosClienteVO> obterByInstituicaoFuncionario(Integer idInstituicao,
			Integer idFuncionario)throws BancoobException;
}
