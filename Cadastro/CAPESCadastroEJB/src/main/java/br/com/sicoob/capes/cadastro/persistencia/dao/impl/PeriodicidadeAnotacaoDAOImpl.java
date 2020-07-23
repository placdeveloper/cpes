package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.PeriodicidadeAnotacao;
import br.com.sicoob.capes.cadastro.persistencia.dao.PeriodicidadeAnotacaoDAO;

/**
 * A Classe PeriodicidadeAnotacaoDAOImpl.
 */
public class PeriodicidadeAnotacaoDAOImpl extends
		CAPESCadastroCrudDao<PeriodicidadeAnotacao> implements
		PeriodicidadeAnotacaoDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "";

	/**
	 * Instancia um novo PeriodicidadeAnotacaoDAOImpl.
	 */
	public PeriodicidadeAnotacaoDAOImpl() {
		super(PeriodicidadeAnotacao.class, NOME_COMANDO_PESQUISAR);
	}

}
