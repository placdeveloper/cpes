/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.LogCompartilhamentoCadastro;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * @author Erico.Junior
 * 
 */
public interface LogCompartilhamentoCadastroServico extends
		CAPESCadastroCrudServico<LogCompartilhamentoCadastro> {

	/**
	 * Inclui um registro no log de compartilhamento do cadastro da pessoa.
	 * @param pessoa A pessoa que terá seu cadastro compartilhado.
	 * @return o log do compartilhamento do cadastro da pessoa.
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	LogCompartilhamentoCadastro incluir(PessoaCompartilhamento pessoa) throws BancoobException;

	/**
	 * Altera o registro de log de compartilhamento para "Sim".
	 * @param pessoa A pessoa que terá o seu cadastro compartilhado.
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	void compartilhar(PessoaCompartilhamento pessoa) throws BancoobException;
}
