/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.RegimeCasamento;
import br.com.sicoob.capes.cadastro.persistencia.dao.RegimeCasamentoDAO;

/**
 * DAO para os regimes de casamento. 
 * 
 * @author Erico.Junior
 */
public class RegimeCasamentoDAOImpl extends CAPESCadastroCrudDao<RegimeCasamento>
		implements RegimeCasamentoDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "";

	/**
	 * Instancia um novo RegimeCasamentoDAOImpl.
	 */
	public RegimeCasamentoDAOImpl() {
		super(RegimeCasamento.class, NOME_COMANDO_PESQUISAR);
	}
}