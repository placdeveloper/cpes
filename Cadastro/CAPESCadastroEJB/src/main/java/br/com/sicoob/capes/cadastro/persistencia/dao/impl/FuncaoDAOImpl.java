package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.Funcao;
import br.com.sicoob.capes.cadastro.persistencia.dao.FuncaoDAO;

/**
 * Classe que implementa a persistencia de {@link Funcao}. 
 * @author lucas.borges
 */
public class FuncaoDAOImpl extends CAPESCadastroCrudDao<Funcao> implements
		FuncaoDAO {


	/**
	 * Instancia um novo FuncaoDAOImpl.
	 */
	public FuncaoDAOImpl() {
		super(Funcao.class, "PESQUISA_FUNCAO");
	}
	
}