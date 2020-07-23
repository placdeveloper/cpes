package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.cadastro.persistencia.dao.TipoLocalizacaoBemDAO;
import br.com.sicoob.capes.negocio.entidades.bem.TipoLocalizacaoBem;

public class TipoLocalizacaoBemDAOImpl extends CAPESCadastroCrudDao<TipoLocalizacaoBem> implements TipoLocalizacaoBemDAO {
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_TIPO_LOCALIZACAO_BEM_POR_FILTRO";

	public TipoLocalizacaoBemDAOImpl() {
		super(TipoLocalizacaoBem.class, NOME_COMANDO_PESQUISAR);
	}
}