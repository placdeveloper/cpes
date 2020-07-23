/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.ConselhoRegional;
import br.com.sicoob.capes.cadastro.persistencia.dao.ConselhoRegionalDAO;

/**
 * @author Erico.Junior
 * 
 */
public class ConselhoRegionalDAOImpl extends
		CAPESCadastroCrudDao<ConselhoRegional> implements
		ConselhoRegionalDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "";

	/**
	 * Instancia um novo ConselhoRegionalDAOImpl.
	 */
	public ConselhoRegionalDAOImpl() {
		super(ConselhoRegional.class, NOME_COMANDO_PESQUISAR);
	}
}
