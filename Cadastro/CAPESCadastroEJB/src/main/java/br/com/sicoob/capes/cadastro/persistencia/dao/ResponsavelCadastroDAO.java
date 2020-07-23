/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.ResponsavelCadastro;

/**
 * Interface para o DAO de ResponsavelCadastro.
 * 
 * @author juan.damasceno
 */
public interface ResponsavelCadastroDAO extends EntidadeCadastroDaoIF<ResponsavelCadastro> {

	/**
	 * Recupera o respons�vel pelo cadastro da pessoa informada.
	 * 
	 * @param pessoa
	 *            A pessoa a ser obtida.
	 * @return O respons�vel pelo cadastro da pessoa informada.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	ResponsavelCadastro consultar(PessoaCompartilhamento pessoa) throws BancoobException;
}
