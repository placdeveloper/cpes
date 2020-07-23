/*
 * SICOOB
 * 
 * ValidacaoCadastralBaseDAO.java(br.com.sicoob.capes.cadastro.persistencia.dao.impl.ValidacaoCadastralBaseDAO)
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;

/**
 * The Class ValidacaoCadastralBaseDAO.
 * 
 * @param <E>
 *            the element type
 */
public abstract class ValidacaoCadastralBaseDAO<E extends CAPESEntidade<?>> extends CAPESCadastroCrudDao<E> {

	/** A Constante ARQUIVO_QUERIES. */
	static final String ARQUIVO_QUERIES = "capes.validacao.cadastral.queries.xml";
	
	/** A Constante NOME_COLECAO_COMANDOS. */
	static final String NOME_COLECAO_COMANDOS = "capes-validacaoCadastral";
	
	/**
	 * Cria uma nova instância de validacao cadastral base dao.
	 * 
	 * @param clazz
	 *            the clazz
	 * @param nomeComandoPesquisar
	 *            the nome comando pesquisar
	 */
	protected ValidacaoCadastralBaseDAO(Class<E> clazz, String nomeComandoPesquisar) {

		super(Constantes.Persistencia.DATASOURCE_CAPES, ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS,
		        clazz, nomeComandoPesquisar);
	}


}
