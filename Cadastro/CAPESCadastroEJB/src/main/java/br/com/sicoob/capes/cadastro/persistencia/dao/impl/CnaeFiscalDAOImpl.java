/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.CnaeFiscal;
import br.com.sicoob.capes.cadastro.persistencia.dao.CnaeFiscalDAO;

/**
 * DAO utilizado para Cnae
 * 
 * @author Erico.Junior
 */
public class CnaeFiscalDAOImpl extends CAPESCadastroCrudDao<CnaeFiscal> implements
		CnaeFiscalDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISAR_CNAE_POR_FILTRO";

	/**
	 * Construtor do DAO.
	 */
	public CnaeFiscalDAOImpl() {
		super(CnaeFiscal.class, NOME_COMANDO_PESQUISAR);
	}

}