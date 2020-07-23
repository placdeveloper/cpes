/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.TributacaoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.Tributacao;

/**
 * Business delegate para certidao.  
 * @author juan.damasceno
 */
public class TributacaoDelegate extends
	EntidadeCadastroDelegate<Tributacao, TributacaoServico> {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TributacaoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarTributacaoServico();
	}
	
	/**
	 * Obter por pessoa.
	 *
	 * @param pessoa o valor de pessoa
	 * @return Tributacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Tributacao obterPorPessoa(PessoaCompartilhamento pessoa) throws BancoobException {
		return getServico().obterPorPessoa(pessoa);
	}
	
	/**
	 * O método Validar cadatro pessoa.
	 *
	 * @param pessoa o valor de pessoa
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void validarCadatroPessoa(PessoaCompartilhamento pessoa) throws BancoobException {
		getServico().validarCadastroPessoa(pessoa);
	}
	
	/**
	 * Obter por pessoa com validacao.
	 *
	 * @param pessoaCompartilhamento o valor de pessoa compartilhamento
	 * @return Tributacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Tributacao obterPorPessoaComValidacao(PessoaCompartilhamento pessoaCompartilhamento) 
			throws BancoobException {
		return getServico().obterPorPessoaComValidacao(pessoaCompartilhamento);
	}
	
	/**
	 * Incluir.
	 *
	 * @param tributacao o valor de tributacao
	 * @param instituicao o valor de instituicao
	 * @return Tributacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Tributacao incluir(Tributacao tributacao, Instituicao instituicao) throws BancoobException {
		return getServico().incluir(tributacao, instituicao);
	}
}
