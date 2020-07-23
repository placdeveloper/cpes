/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.TipoFuncionario;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoFuncionarioDAO;

/**
 * @author Erico.Junior
 * 
 */
public class TipoFuncionarioDAOImpl extends
		CAPESCadastroCrudDao<TipoFuncionario> implements
		TipoFuncionarioDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "";

	/**
	 * Instancia um novo TipoFuncionarioDAOImpl.
	 */
	public TipoFuncionarioDAOImpl() {
		super(TipoFuncionario.class, NOME_COMANDO_PESQUISAR);
	}
}
