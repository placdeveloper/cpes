/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.CategoriaProdutor;

/**
 * @author Erico.Junior
 *
 */
public interface CategoriaProdutorDAO extends CAPESCadastroCrudDaoIF<CategoriaProdutor> {

	/**
	 * Obter categorias por status.
	 *
	 * @param categoria o valor de categoria
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<CategoriaProdutor> obterCategoriasPorStatus(CategoriaProdutor categoria) throws BancoobException;

}
