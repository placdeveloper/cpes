package br.com.sicoob.capes.api.persistencia.dao.impl;

import br.com.sicoob.capes.api.negocio.vo.OcupacaoProfissionalVO;
import br.com.sicoob.capes.api.persistencia.dao.OcupacaoProfissionalDAO;

/**
 * Dao com as operações de ocupação profissional
 * 
 * @author Bruno.Carneiro
 */
public class OcupacaoProfissionalDAOImpl extends CAPESApiDAO<OcupacaoProfissionalVO> implements OcupacaoProfissionalDAO {

	/**
	 * Construtor.
	 */
	public OcupacaoProfissionalDAOImpl() {
		super("PESQUISAR_OCUPACAO_PROFISSIONAL", "PESQUISAR_QUANTIDADE_OCUPACAO_PROFISSIONAL");
	}

}