package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.cadastro.persistencia.dao.TipoPessoaContatoDAO;
import br.com.sicoob.capes.negocio.entidades.TipoPessoaContato;

/**
 * @author diego.rezende
 *
 */
public class TipoPessoaContatoDAOImpl extends CAPESCadastroCrudDao<TipoPessoaContato> implements TipoPessoaContatoDAO {
	
	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISAR_TIPO_PESSOA_CONTATO";

	/**
	 * Construtor
	 * 
	 * @param clazz
	 * @param nomeComandoPesquisar
	 */
	public TipoPessoaContatoDAOImpl() {
		super(TipoPessoaContato.class, NOME_COMANDO_PESQUISAR);
	}

}
