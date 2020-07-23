/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.CategoriaProdutor;

/**
 * @author Erico.Junior
 * 
 */
public interface CategoriaProdutorServico extends
		CAPESCadastroCrudServico<CategoriaProdutor> {

	/**
	 * Obt�m as categorias por status (ativo/inativo);
	 * @param categoria com o par�metro para a pesquisa.
	 * @return a lista de categorias
	 * @throws BancoobException
	 */
	List<CategoriaProdutor> obterCategoriasPorStatus(CategoriaProdutor categoria) throws BancoobException;

}
