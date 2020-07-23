/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.TipoCaptura;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoCapturaDAO;

/**
 * Dao para os tipos de captura.
 * 
 * @author Erico.Junior
 */
public class TipoCapturaDAOImpl extends CAPESCadastroCrudDao<TipoCaptura> implements
		TipoCapturaDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_CONSULTA";

	/**
	 * Construtor do DAO.
	 */
	public TipoCapturaDAOImpl() {
		super(TipoCaptura.class, NOME_COMANDO_PESQUISAR);
	}
}
