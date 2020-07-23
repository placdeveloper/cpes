/*
 * SICOOB
 * 
 * ValidacaoCadastralServico.java(br.com.sicoob.capes.cadastro.negocio.servicos.ValidacaoCadastralServico)
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.ValidacaoCadastral;

/**
 * A interface ValidacaoCadastralServico.
 */
public interface ValidacaoCadastralServico extends CAPESCadastroCrudServico<ValidacaoCadastral> {

	/**
	 * Atualiza a data da ultima validacao cadastral de uma pessoa com a data
	 * atual.
	 * 
	 * @param idPessoaCompartilhamento
	 *            O ID da pessoa cuja data de validacao se deseja atualizar
	 * @throws BancoobException
	 */
	void atualizarDataUltimaValidacao(Long idPessoaCompartilhamento) throws BancoobException;

	/**
	 * Atualiza a data da ultima validacao cadastral das pessoas que nunca foram
	 * validadas ou que tenham sofrido alteracao apos a validacao.
	 * 
	 * @param data
	 *            A nova data de validacao
	 * @throws BancoobException
	 */
	void atualizarDataUltimaValidacao(DateTimeDB data) throws BancoobException;
	
	/**
	 * Serviço utilizado em fechamento SWS. 
	 * Verifica se a há cadastrados sem renovar o a mais de 36 meses.
	 * E lança uma anotaçao no cadastro do cliente do tipo 519.
	 * @throws BancoobException
	 */
	void verificarPendenciasPrazoRenovacaoCadastro() throws BancoobException;

}