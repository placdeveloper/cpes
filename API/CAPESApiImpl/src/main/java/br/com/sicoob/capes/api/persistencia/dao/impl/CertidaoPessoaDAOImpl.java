package br.com.sicoob.capes.api.persistencia.dao.impl;

import br.com.sicoob.capes.api.negocio.vo.CertidaoPessoaVO;
import br.com.sicoob.capes.api.persistencia.dao.CertidaoPessoaDAO;

/**
 * A Classe CertidaoPessoaDAOImpl.
 */
public class CertidaoPessoaDAOImpl extends CAPESApiDAO<CertidaoPessoaVO> implements CertidaoPessoaDAO {
	
	/**
	 * Instancia um novo CertidaoPessoaDAOImpl.
	 */
	public CertidaoPessoaDAOImpl() {
		super("PESQUISAR_CERTIDAO_PESSOA");
    }
	
}