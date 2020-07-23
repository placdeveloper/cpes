/* 
 * Sicoob
 * OrgaoEmissorCertidaoDAOImpl.java 
 * Criado em: 12/07/2011
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.io.Serializable;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.OrgaoEmissorCertidao;
import br.com.sicoob.capes.cadastro.persistencia.dao.OrgaoEmissorCertidaoDAO;

/**
 * DAO para {@link OrgaoEmissorCertidao}
 *
 * 12/07/2011
 * @author Rodrigo.Chaves
 */
public class OrgaoEmissorCertidaoDAOImpl extends
		CAPESCadastroCrudDao<OrgaoEmissorCertidao> implements OrgaoEmissorCertidaoDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_ORGAO_EMISSOR_CERTIDAO";

	/**
	 * Construtor
	 */
	public OrgaoEmissorCertidaoDAOImpl() {
		super(OrgaoEmissorCertidao.class, NOME_COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(OrgaoEmissorCertidao objeto) throws BancoobException {
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
	public OrgaoEmissorCertidao incluir(OrgaoEmissorCertidao objeto) throws BancoobException {
		throw new UnsupportedOperationException("A entidade " + getClasse().getCanonicalName()
				+ " representa um domínio fixo e não pode ser mantida.");
	}

}
