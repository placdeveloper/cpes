/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.vigente.Endereco;

/**
 * Dao para os endereços.
 * @author Erico.Junior
 */
public interface EnderecoDAO extends EntidadeCadastroDaoIF<Endereco> {

	/**
	 * Verifica se eh endereco repetido.
	 *
	 * @param novoEnd o valor de novo end
	 * @return {@code true}, se for endereco repetido
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public boolean isEnderecoRepetido(Endereco novoEnd) throws BancoobException;
}
