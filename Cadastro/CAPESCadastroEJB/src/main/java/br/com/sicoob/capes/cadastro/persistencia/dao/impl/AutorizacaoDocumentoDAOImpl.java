package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.cadastro.persistencia.dao.AutorizacaoDocumentoDAO;
import br.com.sicoob.capes.negocio.entidades.AutorizacaoDocumento;

/**
 * A Classe AutorizacaoDocumentoDAOImpl.
 */
public class AutorizacaoDocumentoDAOImpl extends
		CAPESCadastroCrudDao<AutorizacaoDocumento> implements AutorizacaoDocumentoDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "nomeComandoPesquisar";// FIXME: rodrigo.chaves

	/**
	 * Instancia um novo AutorizacaoDocumentoDAOImpl.
	 */
	public AutorizacaoDocumentoDAOImpl() {
		super(AutorizacaoDocumento.class, NOME_COMANDO_PESQUISAR);
	}

	
}
