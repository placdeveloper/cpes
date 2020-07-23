package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.cadastro.persistencia.dao.TipoChassiBemDAO;
import br.com.sicoob.capes.negocio.entidades.bem.TipoChassiBem;

public class TipoChassiBemDAOImpl extends CAPESCadastroCrudDao<TipoChassiBem> implements TipoChassiBemDAO {

	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_TIPO_CHASSI_BEM_POR_FILTRO";

	public TipoChassiBemDAOImpl() {
		super(TipoChassiBem.class, NOME_COMANDO_PESQUISAR);
	}

}