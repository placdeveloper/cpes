/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.TipoFormaConstituicao;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoFormaConstituicaoDAO;

/**
 * DAO utilizado para os tipos de empresa.
 * 
 * @author Erico.Junior
 */
public class TipoFormaConstituicaoDAOImpl extends
		CAPESCadastroCrudDao<TipoFormaConstituicao> implements
		TipoFormaConstituicaoDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "";

	/**
	 * Construtor do DAO.
	 */
	public TipoFormaConstituicaoDAOImpl() {
		super(TipoFormaConstituicao.class, NOME_COMANDO_PESQUISAR);
	}

}