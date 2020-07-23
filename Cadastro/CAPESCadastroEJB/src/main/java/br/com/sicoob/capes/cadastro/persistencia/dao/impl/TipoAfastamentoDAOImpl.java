/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.TipoAfastamento;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoAfastamentoDAO;

/**
 * @author Erico.Junior
 * 
 */
public class TipoAfastamentoDAOImpl extends
		CAPESCadastroCrudDao<TipoAfastamento> implements
		TipoAfastamentoDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "";

	/**
	 * Instancia um novo TipoAfastamentoDAOImpl.
	 */
	public TipoAfastamentoDAOImpl() {
		super(TipoAfastamento.class, NOME_COMANDO_PESQUISAR);
	}
}
