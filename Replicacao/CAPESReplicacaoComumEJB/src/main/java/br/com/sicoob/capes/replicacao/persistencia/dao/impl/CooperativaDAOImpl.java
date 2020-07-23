package br.com.sicoob.capes.replicacao.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.legado.Cooperativa;
import br.com.sicoob.capes.replicacao.persistencia.dao.CAPESReplicacaoComumCrudDao;
import br.com.sicoob.capes.replicacao.persistencia.dao.CooperativaDAO;

/**
 * A Classe CooperativaDAOImpl.
 */
public class CooperativaDAOImpl extends CAPESReplicacaoComumCrudDao<Cooperativa> implements
		CooperativaDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISAR_COOPERATIVA_POR_FILTRO";

	/**
	 * Instancia um novo CooperativaDAOImpl.
	 */
	public CooperativaDAOImpl() {
		super(Cooperativa.class, NOME_COMANDO_PESQUISAR);
	}

}
