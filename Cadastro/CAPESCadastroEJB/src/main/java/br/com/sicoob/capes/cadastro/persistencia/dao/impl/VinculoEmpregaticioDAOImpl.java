/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.cadastro.persistencia.dao.VinculoEmpregaticioDAO;
import br.com.sicoob.capes.negocio.entidades.VinculoEmpregaticio;

/**
 * DAO utilizado para vinculo empregatício.
 * 
 * @author Erico.Junior
 * 
 */
public class VinculoEmpregaticioDAOImpl extends
		CAPESCadastroCrudDominioDao<VinculoEmpregaticio> implements VinculoEmpregaticioDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISAR_VINCULO_EMPREGATICIO";
	
	/** A constante COMANDO_PESQUISAR_PROXIMO_CODIGO. */
	private static final String COMANDO_PESQUISAR_PROXIMO_CODIGO = "PESQUISAR_PROXIMO_CODIGO_VINCULO_EMPREGATICIO";

	/**
	 * Instancia um novo VinculoEmpregaticioDAOImpl.
	 */
	public VinculoEmpregaticioDAOImpl() {
		super(VinculoEmpregaticio.class, NOME_COMANDO_PESQUISAR, COMANDO_PESQUISAR_PROXIMO_CODIGO);
	}

}
