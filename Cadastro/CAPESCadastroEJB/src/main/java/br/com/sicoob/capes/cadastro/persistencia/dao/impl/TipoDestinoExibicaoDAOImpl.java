package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.cadastro.persistencia.dao.TipoDestinoExibicaoDAO;
import br.com.sicoob.capes.negocio.entidades.TipoDestinoExibicao;

/**
 * A Classe TipoDestinoExibicaoDAOImpl.
 */
public class TipoDestinoExibicaoDAOImpl extends CAPESCadastroCrudDao<TipoDestinoExibicao> implements TipoDestinoExibicaoDAO {

	/**
	 * Instancia um novo TipoDestinoExibicaoDAOImpl.
	 */
	public TipoDestinoExibicaoDAOImpl() {
		super(TipoDestinoExibicao.class, "");

	}
}