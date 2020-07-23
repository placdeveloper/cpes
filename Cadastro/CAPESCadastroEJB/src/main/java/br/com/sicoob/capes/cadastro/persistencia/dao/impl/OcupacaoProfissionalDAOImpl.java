/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.cadastro.persistencia.dao.OcupacaoProfissionalDAO;
import br.com.sicoob.capes.negocio.entidades.OcupacaoProfissional;

/**
 * DAO para ocupações profissionais. 
 * @author erico.junior
 */
public class OcupacaoProfissionalDAOImpl extends
		CAPESCadastroCrudDao<OcupacaoProfissional> implements OcupacaoProfissionalDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISAR_OCUPACAO_PROFISSINAL";

	/**
	 * Construtor do DAO.
	 */
	public OcupacaoProfissionalDAOImpl() {
		super(OcupacaoProfissional.class, NOME_COMANDO_PESQUISAR);
	}

}