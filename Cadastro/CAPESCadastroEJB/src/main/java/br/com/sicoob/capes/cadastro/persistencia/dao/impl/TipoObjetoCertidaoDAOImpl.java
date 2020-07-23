/* 
 * Sicoob
 * TipoObjetoCertidaoDAOImpl.java 
 * Criado em: 12/07/2011
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.io.Serializable;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.TipoObjetoCertidao;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoObjetoCertidaoDAO;

/**
 * DAO para {@link TipoObjetoCertidao}
 *
 * 12/07/2011
 * @author Rodrigo.Chaves
 */
public class TipoObjetoCertidaoDAOImpl extends
		CAPESCadastroCrudDao<TipoObjetoCertidao> implements TipoObjetoCertidaoDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_TIPO_OBJETO_CERTIDAO";

	/**
	 * Construtor
	 */
	public TipoObjetoCertidaoDAOImpl() {
		super(TipoObjetoCertidao.class, NOME_COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(TipoObjetoCertidao objeto) throws BancoobException {
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
	public TipoObjetoCertidao incluir(TipoObjetoCertidao objeto) throws BancoobException {
		throw new UnsupportedOperationException("A entidade " + getClasse().getCanonicalName()
				+ " representa um domínio fixo e não pode ser mantida.");
	}

}
