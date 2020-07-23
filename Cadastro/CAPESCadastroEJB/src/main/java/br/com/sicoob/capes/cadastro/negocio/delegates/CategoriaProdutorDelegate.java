/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.CategoriaProdutorServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.CategoriaProdutor;

/**
 * @author Erico.Junior
 * 
 */
public class CategoriaProdutorDelegate extends
		CAPESCadastroCrudDelegate<CategoriaProdutor, CategoriaProdutorServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CategoriaProdutorServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarCategoriaProdutorServico();
	}

	/**
	 * Obter categorias por status.
	 *
	 * @param categoria o valor de categoria
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<CategoriaProdutor> obterCategoriasPorStatus(CategoriaProdutor categoria) throws BancoobException {
		return getServico().obterCategoriasPorStatus(categoria);
	}
	
}