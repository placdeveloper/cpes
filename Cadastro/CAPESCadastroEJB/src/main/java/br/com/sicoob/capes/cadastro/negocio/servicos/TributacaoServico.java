/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.Tributacao;

/**
 * Define as operações do serviço de manipulação de tributacao.
 * 
 * @author Juan.Damasceno
 */
public interface TributacaoServico extends
		EntidadeCadastroServico<Tributacao> {

	/**
	 * Obter por pessoa.
	 *
	 * @param pessoa o valor de pessoa
	 * @return Tributacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Tributacao obterPorPessoa(PessoaCompartilhamento pessoa) throws BancoobException;

	/**
	 * O método Validar cadastro pessoa.
	 *
	 * @param pessoa o valor de pessoa
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void validarCadastroPessoa(PessoaCompartilhamento pessoa) throws BancoobException;

	/**
	 * Obter por pessoa com validacao.
	 *
	 * @param pessoaCompartilhamento o valor de pessoa compartilhamento
	 * @return Tributacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Tributacao obterPorPessoaComValidacao(PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException;
	
	/**
	 * Incluir.
	 *
	 * @param tributacao o valor de tributacao
	 * @param instituicao o valor de instituicao
	 * @return Tributacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Tributacao incluir(Tributacao tributacao, Instituicao instituicao) throws BancoobException;
}