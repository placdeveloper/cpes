package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.cadastro.persistencia.dao.TipoRelacionamentoBemImovelDAO;
import br.com.sicoob.capes.negocio.entidades.bem.TipoRelacionamentoBemImovel;

public class TipoRelacionamentoBemImovelDAOImpl extends CAPESCadastroCrudDao<TipoRelacionamentoBemImovel> implements TipoRelacionamentoBemImovelDAO {
	
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_TIPO_RELACIONAMENTO_BEM_IMOVEL_POR_FILTRO";

	public TipoRelacionamentoBemImovelDAOImpl() {
		super(TipoRelacionamentoBemImovel.class, NOME_COMANDO_PESQUISAR);
	}

}