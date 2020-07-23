/**
 * 
 */
package br.com.sicoob.capes.replicacao.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.legado.Empreendimento;
import br.com.sicoob.capes.replicacao.persistencia.dao.CAPESReplicacaoComumCrudDao;
import br.com.sicoob.capes.replicacao.persistencia.dao.EmpreendimentoDAO;

/**
 * @author Erico.Junior
 *
 */
public class EmpreendimentoDAOImpl extends CAPESReplicacaoComumCrudDao<Empreendimento> implements EmpreendimentoDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "";

	/**
	 * Instancia um novo EmpreendimentoDAOImpl.
	 */
	public EmpreendimentoDAOImpl() {
		super(Empreendimento.class, NOME_COMANDO_PESQUISAR);
	}

}
