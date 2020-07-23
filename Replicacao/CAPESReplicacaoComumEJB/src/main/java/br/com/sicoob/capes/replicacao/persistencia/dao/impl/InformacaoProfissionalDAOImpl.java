/**
 * 
 */
package br.com.sicoob.capes.replicacao.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.legado.InformacaoProfissional;
import br.com.sicoob.capes.replicacao.persistencia.dao.EntidadeReplicavelDao;
import br.com.sicoob.capes.replicacao.persistencia.dao.InformacaoProfissionalDAO;

/**
 * @author Erico.Junior
 * 
 */
public class InformacaoProfissionalDAOImpl extends
		EntidadeReplicavelDao<InformacaoProfissional> implements
		InformacaoProfissionalDAO {

	/** A constante NOME_COMANDO_CONSULTAR_IDDB2. */
	private static final String NOME_COMANDO_CONSULTAR_IDDB2 = 
			"CONSULTAR_INFORMACAO_PROFISSIONAL_POR_IDDB2";

	/**
	 * Instancia um novo InformacaoProfissionalDAOImpl.
	 */
	public InformacaoProfissionalDAOImpl() {
		super(InformacaoProfissional.class, null, NOME_COMANDO_CONSULTAR_IDDB2);
	}
}