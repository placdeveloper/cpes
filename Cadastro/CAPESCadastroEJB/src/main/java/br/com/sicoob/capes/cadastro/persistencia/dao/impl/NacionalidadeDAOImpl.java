/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.cadastro.persistencia.dao.NacionalidadeDAO;
import br.com.sicoob.capes.negocio.entidades.Nacionalidade;

/**
 * @author Erico.Junior
 * 
 */
public class NacionalidadeDAOImpl extends CAPESCadastroCrudDominioDao<Nacionalidade> implements NacionalidadeDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISAR_NACIONALIDADE";

	/**
	 * Construtor do DAO.
	 */
	public NacionalidadeDAOImpl() {
		super(Nacionalidade.class, NOME_COMANDO_PESQUISAR, "PESQUISAR_PROXIMO_CODIGO_NACIONALIDADE");
	}
	
}