/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.LogCompartilhamentoCadastro;
import br.com.sicoob.capes.cadastro.persistencia.dao.LogCompartilhamentoCadastroDAO;

/**
 * A Classe LogCompartilhamentoCadastroDAOImpl.
 */
public class LogCompartilhamentoCadastroDAOImpl extends
		CAPESCadastroCrudDao<LogCompartilhamentoCadastro> implements
		LogCompartilhamentoCadastroDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_LOG_COMPARTILHAMENTO";

	/**
	 * Construtor do DAO.
	 */
	public LogCompartilhamentoCadastroDAOImpl() {
		super(LogCompartilhamentoCadastro.class, NOME_COMANDO_PESQUISAR);
	}

}