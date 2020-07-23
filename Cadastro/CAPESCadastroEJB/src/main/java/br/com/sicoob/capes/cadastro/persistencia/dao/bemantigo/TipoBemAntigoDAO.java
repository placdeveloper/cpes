/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.bemantigo;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.persistencia.dao.CAPESCadastroCrudDominioDaoIF;
import br.com.sicoob.capes.negocio.entidades.bemantigo.TipoBem;

/**
 * Dao para tipos de bem
 * @author erico.junior
 */
public interface TipoBemAntigoDAO extends CAPESCadastroCrudDominioDaoIF<TipoBem> {

	/**
	 * Listar tipos com subtipo.
	 *
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<TipoBem> listarTiposComSubtipo() throws BancoobException;

}
