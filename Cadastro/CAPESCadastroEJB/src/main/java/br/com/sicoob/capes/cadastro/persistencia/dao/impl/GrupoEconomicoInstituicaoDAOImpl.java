package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.cadastro.persistencia.dao.GrupoEconomicoInstituicaoDAO;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoInstituicao;

/**
 * A Classe GrupoEconomicoInstituicaoDAOImpl.
 */
public class GrupoEconomicoInstituicaoDAOImpl extends CAPESCadastroCrudDao<GrupoEconomicoInstituicao> implements GrupoEconomicoInstituicaoDAO {


	/**
	 * Instancia um novo GrupoEconomicoPessoaDAOImpl.
	 */
	public GrupoEconomicoInstituicaoDAOImpl() {
		super(GrupoEconomicoInstituicao.class, "PESQUISA_GRUPO_ECONOMICO_INSTITUICAO_POR_FILTRO");
	}

}