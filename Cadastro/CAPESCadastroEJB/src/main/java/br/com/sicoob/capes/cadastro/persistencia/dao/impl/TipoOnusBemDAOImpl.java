package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.cadastro.persistencia.dao.TipoOnusBemDAO;
import br.com.sicoob.capes.negocio.entidades.bem.TipoOnusBem;

public class TipoOnusBemDAOImpl extends CAPESCadastroCrudDao<TipoOnusBem> implements TipoOnusBemDAO {

	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_TIPO_ONUS_BEM_POR_FILTRO";

	public TipoOnusBemDAOImpl() {
		super(TipoOnusBem.class, NOME_COMANDO_PESQUISAR);
	}

}