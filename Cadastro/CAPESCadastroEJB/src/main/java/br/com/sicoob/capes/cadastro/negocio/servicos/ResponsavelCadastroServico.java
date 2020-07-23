/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.ResponsavelCadastro;

/**
 * Define as operações do serviço de manipulação de ResponsavelCadastro.
 * 
 * @author Juan.Damasceno
 */
public interface ResponsavelCadastroServico extends EntidadeCadastroServico<ResponsavelCadastro> {
	
	/**
	 * Recupera o responsável pelo cadastro da pessoa informada.
	 * @param pessoa A pessoa a ser obtida.
	 * @return O responsável pelo cadastro da pessoa informada.
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	ResponsavelCadastro consultar(PessoaCompartilhamento pessoa) throws BancoobException;

	/**
	 * Altera a instituição responsável pelo cadastro.
	 * @param responsavel O responsável pelo cadastro.
	 * @param instituicao a nova instituição responsável.
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	void alterar(ResponsavelCadastro responsavel, Instituicao instituicao) throws BancoobException;
}
