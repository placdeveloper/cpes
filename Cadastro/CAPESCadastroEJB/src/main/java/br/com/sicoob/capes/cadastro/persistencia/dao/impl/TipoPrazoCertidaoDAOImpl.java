/* 
 * Sicoob
 * TipoPrazoCertidaoDAOImpl.java 
 * Criado em: 12/07/2011
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.io.Serializable;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.TipoPrazoCertidao;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoPrazoCertidaoDAO;

/**
 * DAO para {@link TipoPrazoCertidao}
 *
 * 12/07/2011
 * @author Rodrigo.Chaves
 */
public class TipoPrazoCertidaoDAOImpl extends CAPESCadastroCrudDao<TipoPrazoCertidao> implements
		TipoPrazoCertidaoDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_TIPO_PRAZO_CERTIDAO";

	/**
	 * Construtor
	 */
	public TipoPrazoCertidaoDAOImpl() {
		super(TipoPrazoCertidao.class, NOME_COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(TipoPrazoCertidao objeto) throws BancoobException {
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
	public TipoPrazoCertidao incluir(TipoPrazoCertidao objeto) throws BancoobException {
		throw new UnsupportedOperationException("A entidade " + getClasse().getCanonicalName()
				+ " representa um domínio fixo e não pode ser mantida.");
	}

}
