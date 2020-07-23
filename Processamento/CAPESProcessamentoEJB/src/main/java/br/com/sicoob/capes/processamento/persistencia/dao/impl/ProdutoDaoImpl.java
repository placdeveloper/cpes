/**
 * 
 */
package br.com.sicoob.capes.processamento.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.legado.Produto;
import br.com.sicoob.capes.processamento.persistencia.dao.CAPESProcessamentoCrudDao;
import br.com.sicoob.capes.processamento.persistencia.dao.ProdutoDao;

/**
 * @author Erico.Junior
 * 
 */
public class ProdutoDaoImpl extends CAPESProcessamentoCrudDao<Produto>
		implements ProdutoDao {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "";

	/**
	 * Instancia um novo ProdutoDaoImpl.
	 */
	public ProdutoDaoImpl() {
		super(Produto.class, NOME_COMANDO_PESQUISAR);
	}

}
