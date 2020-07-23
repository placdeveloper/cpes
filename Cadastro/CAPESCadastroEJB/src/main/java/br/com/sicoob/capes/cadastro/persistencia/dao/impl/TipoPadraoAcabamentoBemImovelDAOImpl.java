package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.cadastro.persistencia.dao.TipoPadraoAcabamentoBemImovelDAO;
import br.com.sicoob.capes.negocio.entidades.bem.TipoPadraoAcabamentoBemImovel;

public class TipoPadraoAcabamentoBemImovelDAOImpl extends CAPESCadastroCrudDao<TipoPadraoAcabamentoBemImovel> implements TipoPadraoAcabamentoBemImovelDAO {
	
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_TIPO_PADRAO_ACABAMENTO_BEM_IMOVEL_POR_FILTRO";

	public TipoPadraoAcabamentoBemImovelDAOImpl() {
		super(TipoPadraoAcabamentoBemImovel.class, NOME_COMANDO_PESQUISAR);
	}

}