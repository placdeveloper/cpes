package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.cadastro.persistencia.dao.GrupoEconomicoAutomaticoPessoaDAO;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoAutomaticoPessoa;

/**
 * A Classe GrupoEconomicoAutomaticoPessoaDAOImpl.
 */
public class GrupoEconomicoAutomaticoPessoaDAOImpl extends 	CAPESCadastroCrudDao<GrupoEconomicoAutomaticoPessoa> implements GrupoEconomicoAutomaticoPessoaDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_GRUPO_ECONOMICO_AUTOMATICO_PESSOA_POR_FILTRO";
	

	/**
	 * Instancia um novo GrupoEconomicoAutomaticoPessoaDAOImpl.
	 */
	public GrupoEconomicoAutomaticoPessoaDAOImpl() {
		super(GrupoEconomicoAutomaticoPessoa.class, NOME_COMANDO_PESQUISAR);
	}

	
}