package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.cadastro.persistencia.dao.TipoServicoCondominialBemImovelDAO;
import br.com.sicoob.capes.negocio.entidades.bem.TipoServicoCondominialBemImovel;

public class TipoServicoCondominialBemImovelDAOImpl extends CAPESCadastroCrudDao<TipoServicoCondominialBemImovel> implements TipoServicoCondominialBemImovelDAO {
	
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_TIPO_SERVICO_CONDOMINIAL_BEM_IMOVEL_POR_FILTRO";

	public TipoServicoCondominialBemImovelDAOImpl() {
		super(TipoServicoCondominialBemImovel.class, NOME_COMANDO_PESQUISAR);
	}

}