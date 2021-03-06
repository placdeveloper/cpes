package br.com.sicoob.capes.comum.negocio.servicos;

import java.util.Map;

import br.com.bancoob.excecao.BancoobException;

/**
 * A Interface ClienteServico.
 */
public interface ClienteServico extends CAPESComumServico {
	
	/**
	 * Obter por id instituicao.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @return Map
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	Map<String, Object> obterPorIdInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obter por cpf cnpj instituicao.
	 *
	 * @param cpfCnpj o valor de cpf cnpj
	 * @param idInstituicao o valor de id instituicao
	 * @return Map
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	Map<String, Object> obterPorCpfCnpjInstituicao(String cpfCnpj, Integer idInstituicao) throws BancoobException;

}