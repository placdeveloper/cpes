package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.cadastro.persistencia.dao.TipoClassificacaoBemDAO;
import br.com.sicoob.capes.negocio.entidades.bem.TipoClassificacaoBem;

public class TipoClassificacaoBemDAOImpl extends CAPESCadastroCrudDao<TipoClassificacaoBem> implements TipoClassificacaoBemDAO {
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_TIPO_CLASSIFICACAO_BEM_POR_FILTRO";

	public TipoClassificacaoBemDAOImpl() {
		super(TipoClassificacaoBem.class, NOME_COMANDO_PESQUISAR);
	}

}