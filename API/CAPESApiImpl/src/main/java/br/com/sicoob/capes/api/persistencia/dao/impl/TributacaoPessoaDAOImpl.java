package br.com.sicoob.capes.api.persistencia.dao.impl;

import br.com.sicoob.capes.api.negocio.vo.TributacaoPessoaVO;
import br.com.sicoob.capes.api.persistencia.dao.TributacaoPessoaDAO;

/**
 * A Classe TributacaoPessoaDAOImpl.
 */
public class TributacaoPessoaDAOImpl extends CAPESApiDAO<TributacaoPessoaVO> implements TributacaoPessoaDAO {

	/**
	 * Instancia um novo TributacaoPessoaDAOImpl.
	 */
	public TributacaoPessoaDAOImpl() {
		super("PESQUISAR_TRIBUTACAO_PESSOA");
	}

}