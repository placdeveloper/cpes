/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl.bemantigo;

import br.com.sicoob.capes.negocio.entidades.bemantigo.TipoPosseBem;
import br.com.sicoob.capes.cadastro.persistencia.dao.bemantigo.TipoPosseBemAntigoDAO;
import br.com.sicoob.capes.cadastro.persistencia.dao.impl.CAPESCadastroCrudDao;

/**
 * Dao para tipos posse de bem
 * @author juan.damasceno
 */
public class TipoPosseBemAntigoDAOImpl extends CAPESCadastroCrudDao<TipoPosseBem> implements
		TipoPosseBemAntigoDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "";

	/**
	 * Instancia um novo TipoPosseBemDAOImpl.
	 */
	public TipoPosseBemAntigoDAOImpl() {
		super(TipoPosseBem.class, NOME_COMANDO_PESQUISAR);
	}
}