/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Empreendimento;

/**
 * @author erico.junior
 */
public interface EmpreendimentoDAO extends CAPESCadastroCrudDaoIF<Empreendimento> {

	/**
	 * Pesquisar proximo codigo.
	 *
	 * @return Integer
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Integer pesquisarProximoCodigo() throws BancoobException;

}
