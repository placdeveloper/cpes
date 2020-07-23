/*
 * SICOOB
 * 
 * TributacaoPessoaServico.java(br.com.sicoob.capes.api.negocio.servicos.TributacaoPessoaServico)
 */
package br.com.sicoob.capes.api.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.TributacaoPessoaVO;

/**
 * @author Lucas.Borges
 */
public interface TributacaoPessoaServico extends CAPESApiServico {

	/**
	 * Obter por pessoa instituicao.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 * @return tributacao pessoa vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	TributacaoPessoaVO obterByPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;
}
