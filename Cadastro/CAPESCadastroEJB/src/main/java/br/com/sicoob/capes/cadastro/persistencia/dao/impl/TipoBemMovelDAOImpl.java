package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.cadastro.persistencia.dao.TipoBemMovelDAO;
import br.com.sicoob.capes.negocio.entidades.bem.TipoBemMovel;

public class TipoBemMovelDAOImpl extends CAPESCadastroCrudDao<TipoBemMovel> implements TipoBemMovelDAO {
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_TIPO_BEM_MOVEL_POR_FILTRO";

	public TipoBemMovelDAOImpl() {
		super(TipoBemMovel.class, NOME_COMANDO_PESQUISAR);
	}

}