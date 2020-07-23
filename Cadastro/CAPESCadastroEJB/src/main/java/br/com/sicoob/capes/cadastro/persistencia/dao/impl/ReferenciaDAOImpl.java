package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.cadastro.persistencia.dao.ReferenciaDAO;
import br.com.sicoob.capes.negocio.entidades.vigente.Referencia;

/**
 * Classe que implementa a persistencia de Referencia.
 *
 * @author Juan.Damasceno
 *
 */
public class ReferenciaDAOImpl extends CAPESCadastroCrudDao<Referencia> implements ReferenciaDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_REFERENCIA_POR_PESSOA";
	
	/**
	 * Instancia um novo ReferenciaDAOImpl.
	 */
	public ReferenciaDAOImpl() {
		super(Referencia.class, NOME_COMANDO_PESQUISAR);
	}
	
}