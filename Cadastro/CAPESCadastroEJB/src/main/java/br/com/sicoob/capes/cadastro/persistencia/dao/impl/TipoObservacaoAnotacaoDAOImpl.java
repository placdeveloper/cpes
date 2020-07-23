package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.cadastro.persistencia.dao.TipoObservacaoAnotacaoDAO;
import br.com.sicoob.capes.negocio.entidades.TipoObservacaoAnotacao;

/**
 * A Classe TipoObservacaoAnotacaoDAOImpl.
 */
public class TipoObservacaoAnotacaoDAOImpl extends CAPESCadastroCrudDao<TipoObservacaoAnotacao> implements TipoObservacaoAnotacaoDAO {
	
	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISAR_TIPO_OBSERVACAO_ANOTACAO";

	/**
	 * Construtor
	 * 
	 * @param clazz
	 * @param nomeComandoPesquisar
	 */
	public TipoObservacaoAnotacaoDAOImpl() {
		super(TipoObservacaoAnotacao.class, NOME_COMANDO_PESQUISAR);
	}

}
