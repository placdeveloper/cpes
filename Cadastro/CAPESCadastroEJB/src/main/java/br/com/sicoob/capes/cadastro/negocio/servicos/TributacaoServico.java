/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.Tributacao;

/**
 * Define as opera��es do servi�o de manipula��o de tributacao.
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
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	Tributacao obterPorPessoa(PessoaCompartilhamento pessoa) throws BancoobException;

	/**
	 * O m�todo Validar cadastro pessoa.
	 *
	 * @param pessoa o valor de pessoa
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void validarCadastroPessoa(PessoaCompartilhamento pessoa) throws BancoobException;

	/**
	 * Obter por pessoa com validacao.
	 *
	 * @param pessoaCompartilhamento o valor de pessoa compartilhamento
	 * @return Tributacao
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	Tributacao obterPorPessoaComValidacao(PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException;
	
	/**
	 * Incluir.
	 *
	 * @param tributacao o valor de tributacao
	 * @param instituicao o valor de instituicao
	 * @return Tributacao
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	Tributacao incluir(Tributacao tributacao, Instituicao instituicao) throws BancoobException;
}