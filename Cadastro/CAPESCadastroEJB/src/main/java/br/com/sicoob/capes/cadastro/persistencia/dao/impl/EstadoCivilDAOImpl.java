/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.EstadoCivil;
import br.com.sicoob.capes.cadastro.persistencia.dao.EstadoCivilDAO;

/**
 * DAO para o estado civil. 
 * @author Erico.Junior
 */
public class EstadoCivilDAOImpl extends
		CAPESCadastroCrudDao<EstadoCivil> implements EstadoCivilDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "";

	/**
	 * Instancia um novo EstadoCivilDAOImpl.
	 */
	public EstadoCivilDAOImpl() {
		super(EstadoCivil.class, NOME_COMANDO_PESQUISAR);
	}
}