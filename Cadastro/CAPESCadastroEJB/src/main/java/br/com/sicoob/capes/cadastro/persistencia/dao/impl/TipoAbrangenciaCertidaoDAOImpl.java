/* 
 * Sicoob
 * TipoAbrangenciaCertidaoDAOImpl.java 
 * Criado em: 12/07/2011
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.io.Serializable;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.TipoAbrangenciaCertidao;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoAbrangenciaCertidaoDAO;

/**
 * DAO para {@link TipoAbrangenciaCertidao}
 * 
 * 12/07/2011
 * 
 * @author Rodrigo.Chaves
 */
public class TipoAbrangenciaCertidaoDAOImpl extends
		CAPESCadastroCrudDao<TipoAbrangenciaCertidao> implements
		TipoAbrangenciaCertidaoDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_TIPO_ABRANGENCIA_CERTIDAO";

	/**
	 * Construtor
	 */
	public TipoAbrangenciaCertidaoDAOImpl() {
		super(TipoAbrangenciaCertidao.class, NOME_COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(TipoAbrangenciaCertidao objeto) throws BancoobException {
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
	public TipoAbrangenciaCertidao incluir(TipoAbrangenciaCertidao objeto) throws BancoobException {
		throw new UnsupportedOperationException("A entidade " + getClasse().getCanonicalName()
				+ " representa um domínio fixo e não pode ser mantida.");
	}

}
