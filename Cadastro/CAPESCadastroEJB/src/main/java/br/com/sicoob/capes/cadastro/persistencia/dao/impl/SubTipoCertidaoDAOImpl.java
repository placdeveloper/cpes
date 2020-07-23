/* 
 * Sicoob
 * SubTipoCertidaoDAOImpl.java 
 * Criado em: 12/07/2011
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.io.Serializable;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.SubTipoCertidao;
import br.com.sicoob.capes.cadastro.persistencia.dao.SubTipoCertidaoDAO;

/**
 * DAO para {@link SubTipoCertidao}
 *
 * 12/07/2011
 * @author Rodrigo.Chaves
 */
public class SubTipoCertidaoDAOImpl extends CAPESCadastroCrudDao<SubTipoCertidao>
		implements SubTipoCertidaoDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_SUB_TIPO_CERTIDAO";

	/**
	 * Construtor
	 */
	public SubTipoCertidaoDAOImpl() {
		super(SubTipoCertidao.class, NOME_COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(SubTipoCertidao objeto) throws BancoobException {
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
	public SubTipoCertidao incluir(SubTipoCertidao objeto) throws BancoobException {
		throw new UnsupportedOperationException("A entidade " + getClasse().getCanonicalName()
				+ " representa um domínio fixo e não pode ser mantida.");
	}

}
