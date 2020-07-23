package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.TipoReferencia;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoReferenciaDAO;

/**
 * Classe que implementa a persistencia de TipoReferencia.
 *
 * @author Juan.Damasceno
 *
 */
public class TipoReferenciaDAOImpl extends CAPESCadastroCrudDao<TipoReferencia> implements TipoReferenciaDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "";

	/**
	 * Instancia um novo TipoReferenciaDAOImpl.
	 */
	public TipoReferenciaDAOImpl() {
		super(TipoReferencia.class, NOME_COMANDO_PESQUISAR);
	}
}