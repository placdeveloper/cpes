/*
 * SICOOB
 * 
 * AnotacaoPessoaDAO.java(br.com.sicoob.capes.comum.persistencia.dao.AnotacaoPessoaDAO)
 */
package br.com.sicoob.capes.comum.persistencia.dao;

import java.util.List;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;

/**
 * A interface AnotacaoPessoaDAO.
 */
public interface AnotacaoPessoaDAO {

	/**
	 * Obter vigentes por pessoa instituicao tipo.
	 * 
	 * @param idPessoa
	 *            the id pessoa
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
	List<Map<String, Object>> obterVigentesPorPessoaInstituicaoTipo(Integer idPessoa, String cpfCnpj,
			Integer idInstituicao, Integer idTipoAnotacao) throws BancoobException;

}
