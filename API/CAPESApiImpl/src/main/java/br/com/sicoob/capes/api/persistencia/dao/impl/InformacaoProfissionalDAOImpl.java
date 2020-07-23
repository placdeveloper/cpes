package br.com.sicoob.capes.api.persistencia.dao.impl;

import br.com.sicoob.capes.api.negocio.vo.InformacaoProfissionalVO;
import br.com.sicoob.capes.api.persistencia.dao.InformacaoProfissionalDAO;

/**
 * A Classe InformacaoProfissionalDAOImpl.
 */
public class InformacaoProfissionalDAOImpl extends CAPESApiDAO<InformacaoProfissionalVO> implements InformacaoProfissionalDAO {

	/**
	 * Instancia um novo InformacaoProfissionalDAOImpl.
	 */
	public InformacaoProfissionalDAOImpl() {
		super("PESQUISAR_INFORMACOES_PROFISSIONAIS");
	}

}