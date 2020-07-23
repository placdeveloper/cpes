/* 
 * Sicoob
 * TipoCertidaoDAOImpl.java 
 * Criado em: 12/07/2011
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.TipoCertidao;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoCertidaoDAO;

/**
 * DAO para {@link TipoCertidao}
 *
 * 12/07/2011
 * @author Rodrigo.Chaves
 */
public class TipoCertidaoDAOImpl extends CAPESCadastroCrudDominioDao<TipoCertidao>
		implements TipoCertidaoDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_TIPO_CERTIDAO";
	
	/** A constante NOME_COMANDO_PESQUISAR_PROXIMO_CODIGO. */
	private static final String NOME_COMANDO_PESQUISAR_PROXIMO_CODIGO = 
		"PESQUISA_PROXIMO_CODIGO_TIPO_CERTIDAO";

	/**
	 * Construtor
	 */
	public TipoCertidaoDAOImpl() {
		super(TipoCertidao.class, NOME_COMANDO_PESQUISAR, NOME_COMANDO_PESQUISAR_PROXIMO_CODIGO);
	}

}
