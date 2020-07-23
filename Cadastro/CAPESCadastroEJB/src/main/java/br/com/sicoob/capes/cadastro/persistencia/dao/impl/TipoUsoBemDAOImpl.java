package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.cadastro.persistencia.dao.TipoUsoBemDAO;
import br.com.sicoob.capes.negocio.entidades.bem.TipoUsoBemImovel;

/**
 * Classe com as operações de tipo de uso do bem imóvel
 *
 * @author Bruno.Carneiro
 */
public class TipoUsoBemDAOImpl extends CAPESCadastroCrudDao<TipoUsoBemImovel> implements TipoUsoBemDAO {
	
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_TIPO_USO_BEM_POR_FILTRO";

	public TipoUsoBemDAOImpl() {
		super(TipoUsoBemImovel.class, NOME_COMANDO_PESQUISAR);
	}

}