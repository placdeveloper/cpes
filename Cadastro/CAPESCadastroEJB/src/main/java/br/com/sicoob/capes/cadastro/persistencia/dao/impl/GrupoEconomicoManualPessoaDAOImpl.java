package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.persistencia.dao.GrupoEconomicoManualPessoaDAO;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoManualPessoa;

/**
 * A Classe GrupoEconomicoManualPessoaDAOImpl.
 */
public class GrupoEconomicoManualPessoaDAOImpl extends CAPESCadastroCrudDao<GrupoEconomicoManualPessoa> implements GrupoEconomicoManualPessoaDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_GRUPO_ECONOMICO_MANUAL_PESSOA_POR_FILTRO";

	/**
	 * Instancia um novo GrupoEconomicoPessoaDAOImpl.
	 */
	public GrupoEconomicoManualPessoaDAOImpl() {
		super(GrupoEconomicoManualPessoa.class, NOME_COMANDO_PESQUISAR);
	}

	@Override
	public void excluirEntidade(GrupoEconomicoManualPessoa objeto) throws BancoobException {
		getEntityManager().remove(objeto);
	}

}