/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.CategoriaAnotacao;
import br.com.sicoob.capes.cadastro.persistencia.dao.CategoriaAnotacaoDAO;

/**
 * DAO para as categorias das anotações.
 * 
 * @author Erico.Junior
 *
 */
public class CategoriaAnotacaoDAOImpl extends CAPESCadastroCrudDao<CategoriaAnotacao>
		implements CategoriaAnotacaoDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "";

	/**
	 * Construtor do DAO.
	 */
	public CategoriaAnotacaoDAOImpl() {
		super(CategoriaAnotacao.class, NOME_COMANDO_PESQUISAR);
	}
}
