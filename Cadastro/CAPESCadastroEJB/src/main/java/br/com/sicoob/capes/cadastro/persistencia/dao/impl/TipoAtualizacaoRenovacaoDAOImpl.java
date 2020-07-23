/* 
 * Sicoob
 * TipoAtualizacaoRenovacaoDAOImpl.java 
 * Criado em: 19/12/2017
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.cadastro.persistencia.dao.TipoAtualizacaoRenovacaoDAO;
import br.com.sicoob.capes.negocio.entidades.TipoAtualizacaoRenovacao;

/**
 * 
 * @author valdemar.xavier
 *
 */
public class TipoAtualizacaoRenovacaoDAOImpl extends CAPESCadastroCrudDao<TipoAtualizacaoRenovacao> implements TipoAtualizacaoRenovacaoDAO {
	
	/**
	 * Construtor
	 */
	public TipoAtualizacaoRenovacaoDAOImpl() {
		super(TipoAtualizacaoRenovacao.class, "PESQUISAR_TIPO_ATUALIZACAO_RENOVACAO");
	}

}
