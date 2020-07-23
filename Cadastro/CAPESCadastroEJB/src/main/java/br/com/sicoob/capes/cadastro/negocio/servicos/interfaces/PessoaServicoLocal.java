// 09/08/2013
package br.com.sicoob.capes.cadastro.negocio.servicos.interfaces;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.PessoaServico;

/**
 * A Interface PessoaServicoLocal.
 */
public interface PessoaServicoLocal extends PessoaServico {

	/**
	 * Consulta se a pessoa com o CPF/CNPJ informado j� foi inserida, olhando
	 * tamb�m o 'lixo' (with ur), para a tela de inclus�o de pessoas
	 * 
	 * @param cpfCnpj
	 * @return {@code Long} idPessoa
	 * @throws BancoobException
	 */
	Long consultarPessoaPorCpfCnpjUR(String cpfCnpj) throws BancoobException;

}