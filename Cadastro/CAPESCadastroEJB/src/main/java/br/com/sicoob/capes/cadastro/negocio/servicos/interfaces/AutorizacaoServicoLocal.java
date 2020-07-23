// 09/08/2013
package br.com.sicoob.capes.cadastro.negocio.servicos.interfaces;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.AutorizacaoServico;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * A Interface AutorizacaoServicoLocal.
 */
public interface AutorizacaoServicoLocal extends AutorizacaoServico {

	/**
	 * Verifica se eh pessoa pendente aprovacao.
	 *
	 * @param pessoa o valor de pessoa
	 * @return {@code true}, se for pessoa pendente aprovacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	boolean isPessoaPendenteAprovacao(PessoaCompartilhamento pessoa) throws BancoobException;

	/**
	 * Verifica se eh pessoa possui autorizacao.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @return {@code true}, se for pessoa possui autorizacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	boolean isPessoaPossuiAutorizacao(Integer idPessoa) throws BancoobException;

	/**
	 * Verifica se eh pessoa possui autorizacao, removendo a autorizacao passada,
	 * do resultado.
	 * @param pessoa
	 * @param autorizacao
	 */
	boolean isPessoaPendenteAprovacaoAutorizacao(PessoaCompartilhamento pessoa, Autorizacao autorizacao);

}