/* 
 * Sicoob
 * TipoPoderRelacionamentoDAOImpl.java 
 * Criado em: 29/08/2011
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.io.Serializable;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.TipoPoderRelacionamento;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoPoderRelacionamentoDAO;

/**
 * DAO para {@link TipoPoderRelacionamento}
 *
 * 29/08/2011
 * @author rodrigo.chaves
 */
public class TipoPoderRelacionamentoDAOImpl extends
		CAPESCadastroCrudDao<TipoPoderRelacionamento> implements
		TipoPoderRelacionamentoDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_TIPO_PODER_RELACIONAMENTO";

	/**
	 * Instancia um novo TipoPoderRelacionamentoDAOImpl.
	 */
	public TipoPoderRelacionamentoDAOImpl() {
		super(TipoPoderRelacionamento.class, NOME_COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(TipoPoderRelacionamento objeto) throws BancoobException {
		throw new UnsupportedOperationException("A entidade " + getClasse().getCanonicalName()
				+ " representa um domínio fixo e não pode ser mantida.");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(Serializable chave) throws BancoobException {
		throw new UnsupportedOperationException("A entidade " + getClasse().getCanonicalName()
				+ " representa um domínio fixo e não pode ser mantida.");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoPoderRelacionamento incluir(TipoPoderRelacionamento objeto) throws BancoobException {
		throw new UnsupportedOperationException("A entidade " + getClasse().getCanonicalName()
				+ " representa um domínio fixo e não pode ser mantida.");
	}

}
