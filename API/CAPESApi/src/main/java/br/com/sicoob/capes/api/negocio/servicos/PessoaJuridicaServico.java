/*
 * SICOOB
 * 
 * PessoaJuridicaServico.java(br.com.sicoob.capes.api.negocio.servicos.PessoaJuridicaServico)
 */
package br.com.sicoob.capes.api.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.PessoaJuridicaVO;

/**
 * @author Lucas.Borges
 */
public interface PessoaJuridicaServico extends CAPESApiServico {

	/**
	 * Obter por pessoa instituicao.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 * @return pessoa juridica vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	PessoaJuridicaVO obterByPessoaInstituicao(Integer idPessoa,
			Integer idInstituicao) throws BancoobException;
	
	
	/**
	 * Obtém a matriz e as filiais de uma determinada raiz de um CNPJ
	 * 
	 * @param raizCNPJ
	 *            A raiz do CNPJ
	 * @param idInstituicao
	 *            A instituição para a pesquisa
	 * @return {@code List} lista de pessoas jurídicas.
	 * @throws BancoobException
	 */
	List<PessoaJuridicaVO> obterMatrizFiliais(String raizCNPJ, Integer idInstituicao) throws BancoobException;
}