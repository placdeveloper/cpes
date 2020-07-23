/*
 * SICOOB
 * 
 * AnotacaoPessoaServico.java(br.com.sicoob.capes.comum.negocio.servicos.AnotacaoPessoaServico)
 */
package br.com.sicoob.capes.comum.negocio.servicos;

import java.util.List;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;

/**
 * A interface AnotacaoPessoaServico.
 */
public interface AnotacaoPessoaServico extends CAPESComumServico {

	/**
	 * Obter vigentes por pessoa instituicao tipo.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 * @param idTipoAnotacao
	 *            the id tipo anotacao
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	List<Map<String, Object>> obterVigentesPorPessoaInstituicaoTipo(Integer idPessoa, Integer idInstituicao, Integer idTipoAnotacao)
			throws BancoobException;

	/**
	 * Obter vigentes por pessoa instituicao tipo.
	 * 
	 * @param cpfCnpj
	 *            the cpf cnpj
	 * @param idInstituicao
	 *            the id instituicao
	 * @param idTipoAnotacao
	 *            the id tipo anotacao
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	List<Map<String, Object>> obterVigentesPorPessoaInstituicaoTipo(String cpfCnpj, Integer idInstituicao, Integer idTipoAnotacao)
			throws BancoobException;

}
