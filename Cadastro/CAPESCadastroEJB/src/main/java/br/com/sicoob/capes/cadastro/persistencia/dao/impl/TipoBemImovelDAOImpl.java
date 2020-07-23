package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.cadastro.persistencia.dao.TipoBemImovelDAO;
import br.com.sicoob.capes.negocio.entidades.bem.TipoBemImovel;

public class TipoBemImovelDAOImpl extends CAPESCadastroCrudDao<TipoBemImovel> implements TipoBemImovelDAO {
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_TIPO_BEM_IMOVEL_POR_FILTRO";

	public TipoBemImovelDAOImpl() {
		super(TipoBemImovel.class, NOME_COMANDO_PESQUISAR);
	}

}
