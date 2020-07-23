/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.PerfilTarifario;
import br.com.sicoob.capes.cadastro.persistencia.dao.PerfilTarifarioDAO;

/**
 * @author juan.damasceno
 *
 */
public class PerfilTarifarioDAOImpl extends CAPESCadastroCrudDao<PerfilTarifario>
		implements PerfilTarifarioDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_PERFIL_TARIFARIO_POR_INSTITUICAO";

	/**
	 * Construtor do DAO.
	 */
	public PerfilTarifarioDAOImpl() {
		super(PerfilTarifario.class, NOME_COMANDO_PESQUISAR);
	}
}