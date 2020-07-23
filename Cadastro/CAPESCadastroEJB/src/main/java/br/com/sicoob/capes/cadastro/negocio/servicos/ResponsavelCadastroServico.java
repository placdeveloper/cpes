/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.ResponsavelCadastro;

/**
 * Define as opera��es do servi�o de manipula��o de ResponsavelCadastro.
 * 
 * @author Juan.Damasceno
 */
public interface ResponsavelCadastroServico extends EntidadeCadastroServico<ResponsavelCadastro> {
	
	/**
	 * Recupera o respons�vel pelo cadastro da pessoa informada.
	 * @param pessoa A pessoa a ser obtida.
	 * @return O respons�vel pelo cadastro da pessoa informada.
	 * @throws BancoobException Caso ocorra alguma exce��o.
	 */
	ResponsavelCadastro consultar(PessoaCompartilhamento pessoa) throws BancoobException;

	/**
	 * Altera a institui��o respons�vel pelo cadastro.
	 * @param responsavel O respons�vel pelo cadastro.
	 * @param instituicao a nova institui��o respons�vel.
	 * @throws BancoobException Caso ocorra alguma exce��o.
	 */
	void alterar(ResponsavelCadastro responsavel, Instituicao instituicao) throws BancoobException;
}
