/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.FinalidadeEmpreendimento;
import br.com.sicoob.capes.cadastro.persistencia.dao.FinalidadeEmpreendimentoDAO;

/**
 * @author diego.rezende
 * 
 */
public class FinalidadeEmpreendimentoDAOImpl extends
		CAPESCadastroCrudDao<FinalidadeEmpreendimento> implements
		FinalidadeEmpreendimentoDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_FinalidadeEmpreendimento";

	/**
	 * Instancia um novo FinalidadeEmpreendimentoDAOImpl.
	 */
	public FinalidadeEmpreendimentoDAOImpl() {
		super(FinalidadeEmpreendimento.class, NOME_COMANDO_PESQUISAR);
	}
}
