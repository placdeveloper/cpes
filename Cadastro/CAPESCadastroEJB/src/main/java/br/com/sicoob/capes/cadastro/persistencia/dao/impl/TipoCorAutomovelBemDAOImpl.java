package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.cadastro.persistencia.dao.TipoCorAutomovelBemDAO;
import br.com.sicoob.capes.negocio.entidades.bem.TipoCorAutomovelBem;

public class TipoCorAutomovelBemDAOImpl extends CAPESCadastroCrudDao<TipoCorAutomovelBem> implements TipoCorAutomovelBemDAO {

	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_TIPO_COR_AUTOMOVEL_BEM_POR_FILTRO";

	public TipoCorAutomovelBemDAOImpl() {
		super(TipoCorAutomovelBem.class, NOME_COMANDO_PESQUISAR);
	}

}