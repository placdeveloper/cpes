package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.cadastro.persistencia.dao.TipoImplantacaoBemImovelDAO;
import br.com.sicoob.capes.negocio.entidades.bem.TipoImplantacaoBemImovel;

public class TipoImplantacaoBemImovelDAOImpl extends CAPESCadastroCrudDao<TipoImplantacaoBemImovel> implements TipoImplantacaoBemImovelDAO {
	
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_TIPO_IMPLANTACAO_BEM_IMOVEL_POR_FILTRO";

	public TipoImplantacaoBemImovelDAOImpl() {
		super(TipoImplantacaoBemImovel.class, NOME_COMANDO_PESQUISAR);
	}

}