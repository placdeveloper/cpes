// 09/08/2013
package br.com.sicoob.capes.cadastro.negocio.servicos.interfaces;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.PessoaCompartilhamentoServico;
import br.com.sicoob.capes.comum.negocio.vo.DadosReceitaFederalVO;
import br.com.sicoob.capes.negocio.entidades.vigente.Email;
import br.com.sicoob.capes.negocio.entidades.vigente.Endereco;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.Telefone;

/**
 * A Interface PessoaCompartilhamentoServicoLocal.
 */
public interface PessoaCompartilhamentoServicoLocal extends PessoaCompartilhamentoServico {

	/**
	 * Realiza a inclusao de contatos com dados oriundos da Receita Federal
	 * 
	 * @param pessoaCompartilhamento
	 *            a pessoa para a qual serão incluidos os contatos
	 * @param dadosReceita
	 *            os dados recebidos da Receita Federal
	 * @throws BancoobException
	 * 
	 * @see Endereco
	 * @see Telefone
	 * @see Email
	 */
	void incluirContatosReceitaPJ(PessoaCompartilhamento pessoaCompartilhamento, DadosReceitaFederalVO dadosReceita) throws BancoobException;

	/**
	 * Incluir contatos da receita
	 * 
	 * @param pessoaIncluida
	 * @param dadosReceita
	 * @throws BancoobException
	 */
	void incluirAnotacaoReceita(PessoaCompartilhamento pessoaIncluida, DadosReceitaFederalVO dadosReceita) throws BancoobException;
	
	/**
	 * Inclusao da pessoa com dados da receita.
	 * 
	 * @param pessoaCompartilhamento
	 * @throws BancoobException
	 */
	PessoaCompartilhamento incluirComDadosReceita(PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException;

	/**
	 * Inclusao da pessoa com dados da receita.
	 * 
	 * @param pessoaCompartilhamento
	 * @throws BancoobException
	 */
	PessoaCompartilhamento incluirComDadosReceita(PessoaCompartilhamento pessoaCompartilhamento, Integer numeroCooperativa, Integer unidadeInstituicao) throws BancoobException;
}