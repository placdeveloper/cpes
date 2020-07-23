/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.TipoDocumentoIdentificacao;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoDocumentoIdentificacaoDAO;

/**
 * DAO para o tipo de documento de identificação.
 * 
 * @author Erico.Junior
 */
public class TipoDocumentoIdentificacaoDAOImpl extends
		CAPESCadastroCrudDao<TipoDocumentoIdentificacao> implements
		TipoDocumentoIdentificacaoDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "";

	/**
	 * Construtor do DAO.
	 */
	public TipoDocumentoIdentificacaoDAOImpl() {
		super(TipoDocumentoIdentificacao.class, NOME_COMANDO_PESQUISAR);
	}

}
