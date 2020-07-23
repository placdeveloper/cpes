package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.cadastro.persistencia.dao.TipoInformacaoDAO;
import br.com.sicoob.capes.negocio.entidades.TipoInformacao;

/**
 * A Classe TipoInformacaoDAOImpl.
 */
public class TipoInformacaoDAOImpl extends CAPESCadastroCrudDao<TipoInformacao> implements TipoInformacaoDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "CONSULTAR_TIPOS_INFORMACAO";

	/**
	 * Instancia um novo TipoInformacaoDAOImpl.
	 */
	public TipoInformacaoDAOImpl() {
		super(TipoInformacao.class, NOME_COMANDO_PESQUISAR);
	}
}