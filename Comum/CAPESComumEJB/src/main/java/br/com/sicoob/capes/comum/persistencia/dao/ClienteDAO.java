package br.com.sicoob.capes.comum.persistencia.dao;

import java.util.Map;

import br.com.bancoob.excecao.BancoobException;

/**
 * A Interface ClienteDAO.
 */
public interface ClienteDAO {

	/**
	 * Obter por id instituicao.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @return Map
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Map<String, Object> obterPorIdInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obter por cpf cnpj instituicao.
	 *
	 * @param cpfCnpj o valor de cpf cnpj
	 * @param idInstituicao o valor de id instituicao
	 * @return Map
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Map<String, Object> obterPorCpfCnpjInstituicao(String cpfCnpj, Integer idInstituicao) throws BancoobException;

}