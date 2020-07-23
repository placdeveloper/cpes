/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.vigente.Telefone;

/**
 * Serviço para manutenção do cadastro de telefones das pessoas.
 * 
 * @author Erico.Junior
 */
public interface TelefoneServico extends CAPESCadastroCrudServico<Telefone> {

	/**
	 * Importa o telefone para pessoa;
	 * @param telefone
	 * @return
	 * @throws BancoobException
	 */
	Telefone importar(Telefone telefone) throws BancoobException;
}
