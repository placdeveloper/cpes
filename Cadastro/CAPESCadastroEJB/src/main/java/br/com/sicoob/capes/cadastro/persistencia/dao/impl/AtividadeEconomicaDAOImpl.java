/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.cadastro.persistencia.dao.AtividadeEconomicaDAO;
import br.com.sicoob.capes.negocio.entidades.AtividadeEconomica;

/**
 * DAO utilizado para atividades econômicas
 * 
 * @author Erico.Junior
 */
public class AtividadeEconomicaDAOImpl extends CAPESCadastroCrudDao<AtividadeEconomica> implements AtividadeEconomicaDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISAR_ATIVIDADE_ECONOMICA";

	/**
	 * Construtor do DAO.
	 */
	public AtividadeEconomicaDAOImpl() {
		super(AtividadeEconomica.class, NOME_COMANDO_PESQUISAR);
	}

}