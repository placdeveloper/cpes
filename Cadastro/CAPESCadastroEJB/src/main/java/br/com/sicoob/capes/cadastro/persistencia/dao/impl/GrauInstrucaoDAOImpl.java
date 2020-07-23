/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.GrauInstrucao;
import br.com.sicoob.capes.cadastro.persistencia.dao.GrauInstrucaoDAO;

/**
 * DAO utilizado para grau de instrução
 * 
 * @author Erico.Junior
 */
public class GrauInstrucaoDAOImpl extends CAPESCadastroCrudDao<GrauInstrucao>
		implements GrauInstrucaoDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_FONTE_RENDA";

	/**
	 * Instancia um novo GrauInstrucaoDAOImpl.
	 */
	public GrauInstrucaoDAOImpl() {
		super(GrauInstrucao.class, NOME_COMANDO_PESQUISAR);
	}

}