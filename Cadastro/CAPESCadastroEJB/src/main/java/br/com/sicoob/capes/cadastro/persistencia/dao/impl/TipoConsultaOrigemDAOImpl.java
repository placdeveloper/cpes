package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.cadastro.persistencia.dao.TipoConsultaOrigemDAO;
import br.com.sicoob.capes.negocio.entidades.TipoConsultaOrigem;

/**
 * A Classe TipoConsultaOrigemDAOImpl.
 */
public class TipoConsultaOrigemDAOImpl extends CAPESCadastroCrudDao<TipoConsultaOrigem> implements TipoConsultaOrigemDAO {
	
	/** A constante NOME_CONSULTA_LISTAR. */
	private static final String NOME_CONSULTA_LISTAR = "PESQUISAR_TIPO_CONSULTA_ORIGEM_POR_FILTRO";

	/**
	 * Instancia um novo TipoConsultaOrigemDAOImpl.
	 */
	public TipoConsultaOrigemDAOImpl() {
		super(TipoConsultaOrigem.class, NOME_CONSULTA_LISTAR);
	}

}
