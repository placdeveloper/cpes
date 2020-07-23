/**
 * 
 */
package br.com.sicoob.capes.replicacao.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.legado.UnidadeMedida;
import br.com.sicoob.capes.replicacao.persistencia.dao.CAPESReplicacaoComumCrudDao;
import br.com.sicoob.capes.replicacao.persistencia.dao.UnidadeMedidaDAO;

/**
 * Dao para as unidades de medidas. 
 * @author Erico.Junior
 */
public class UnidadeMedidaDAOImpl extends CAPESReplicacaoComumCrudDao<UnidadeMedida> implements
		UnidadeMedidaDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "";

	/**
	 * Instancia um novo UnidadeMedidaDAOImpl.
	 */
	public UnidadeMedidaDAOImpl() {
		super(UnidadeMedida.class, NOME_COMANDO_PESQUISAR);
	}
}
