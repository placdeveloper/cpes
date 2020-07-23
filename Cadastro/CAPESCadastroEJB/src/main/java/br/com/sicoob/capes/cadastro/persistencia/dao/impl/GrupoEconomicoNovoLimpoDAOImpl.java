package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.cadastro.persistencia.dao.GrupoEconomicoNovoLimpoDAO;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoNovoLimpo;

/**
 * Classe que implementa a persistencia de Referencia GrupoEconomicoNovoLimpoDAOImpl.
 * 
 * @author paulo.stoppa
 * 
 */
public class GrupoEconomicoNovoLimpoDAOImpl extends CAPESCadastroCrudDao<GrupoEconomicoNovoLimpo> implements GrupoEconomicoNovoLimpoDAO {

	/** A constante COMANDO_PESQUISAR_POR_FILTRO. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISAR_GRUPO_ECONOMICO_LIMPO_POR_FILTRO";

	/**
	 * Instancia um novo GrupoEconomicoNovoDAOImpl.
	 */
	public GrupoEconomicoNovoLimpoDAOImpl() {
		super(GrupoEconomicoNovoLimpo.class, NOME_COMANDO_PESQUISAR);
	}

}