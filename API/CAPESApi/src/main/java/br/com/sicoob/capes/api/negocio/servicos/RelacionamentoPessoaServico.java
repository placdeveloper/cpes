/*
 * SICOOB
 * 
 * RelacionamentoPessoaServico.java(br.com.sicoob.capes.api.negocio.servicos.RelacionamentoPessoaServico)
 */
package br.com.sicoob.capes.api.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.RelacionamentoPessoaVO;

/**
 * @author Lucas.Borges
 */
public interface RelacionamentoPessoaServico extends CAPESApiServicoPessoa<RelacionamentoPessoaVO>{

	/**
	 * Obter conjuges por pessoa instituicao.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	List<RelacionamentoPessoaVO> obterConjugesByPessoaInstituicao(
			Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obter quadro societario por pessoa instituicao.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	List<RelacionamentoPessoaVO> obterQuadroSocietarioByPessoaInstituicao(
			Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obter por pessoa instituicao tipo.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 * @param codigoTipoRelacionamento
	 *            the codigo tipo relacionamento
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	List<RelacionamentoPessoaVO> obterByPessoaInstituicaoTipo(
			Integer idPessoa, Integer idInstituicao, Short codigoTipoRelacionamento) throws BancoobException;
}
