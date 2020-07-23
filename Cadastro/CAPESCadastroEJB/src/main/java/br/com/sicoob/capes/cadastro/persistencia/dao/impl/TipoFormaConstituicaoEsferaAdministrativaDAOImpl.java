package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.cadastro.persistencia.dao.TipoFormaConstituicaoEsferaAdministrativaDAO;
import br.com.sicoob.capes.negocio.entidades.TipoFormaConstituicaoEsferaAdministrativa;

/**
 * A Classe TipoFormaConstituicaoEsferaAdministrativaDAOImpl.
 */
public class TipoFormaConstituicaoEsferaAdministrativaDAOImpl extends CAPESCadastroCrudDao<TipoFormaConstituicaoEsferaAdministrativa> implements
		TipoFormaConstituicaoEsferaAdministrativaDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISAR_TIPO_FORMA_CONSTITUICAO_ESFERAADM_POR_FILTRO";

	/**
	 * Instancia um novo TipoFormaConstituicaoEsferaAdministrativaDAOImpl.
	 */
	public TipoFormaConstituicaoEsferaAdministrativaDAOImpl() {
		super(TipoFormaConstituicaoEsferaAdministrativa.class, NOME_COMANDO_PESQUISAR);
	}
}