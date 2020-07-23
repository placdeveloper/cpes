package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.cadastro.persistencia.dao.ObservacaoAnotacaoDAO;
import br.com.sicoob.capes.negocio.entidades.ObservacaoAnotacao;

/**
 * A Classe ObservacaoAnotacaoDAOImpl.
 */
public class ObservacaoAnotacaoDAOImpl extends CAPESCadastroCrudDao<ObservacaoAnotacao> implements ObservacaoAnotacaoDAO {

	/**
	 * Instancia um novo ObservacaoAnotacaoDAOImpl.
	 */
	public ObservacaoAnotacaoDAOImpl() {
		super(ObservacaoAnotacao.class, "PESQUISA_OBSERVACAO_ANOTACAO");
	}

}
