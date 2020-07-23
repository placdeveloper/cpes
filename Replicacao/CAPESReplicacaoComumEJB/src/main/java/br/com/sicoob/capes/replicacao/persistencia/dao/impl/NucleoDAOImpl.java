/**
 * 
 */
package br.com.sicoob.capes.replicacao.persistencia.dao.impl;

import java.io.Serializable;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.excecao.ViolacaoIntegridadeException;
import br.com.sicoob.capes.negocio.entidades.legado.Nucleo;
import br.com.sicoob.capes.replicacao.negocio.excecao.ExclusaoNucleoAssociadoException;
import br.com.sicoob.capes.replicacao.persistencia.dao.EntidadeReplicavelDao;
import br.com.sicoob.capes.replicacao.persistencia.dao.NucleoDAO;

/**
 * Dao utilizado para replicação de nucleos.
 * 
 */
public class NucleoDAOImpl extends EntidadeReplicavelDao<Nucleo> implements NucleoDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "";
	
	/** A constante NOME_COMANDO_CONSULTAR_IDDB2. */
	private static final String NOME_COMANDO_CONSULTAR_IDDB2 = "";

	/**
	 * Instancia um novo NucleoDAOImpl.
	 */
	public NucleoDAOImpl() {
		super(Nucleo.class, NOME_COMANDO_PESQUISAR, NOME_COMANDO_CONSULTAR_IDDB2);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Nucleo obterPorIdDB2(Serializable chave, Integer numeroCooperativa)
			throws BancoobException {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(Serializable chave, Integer numeroCooperativa) throws BancoobException {
		try {
			super.excluir(chave, numeroCooperativa);
		} catch (ViolacaoIntegridadeException e) {
			throw new ExclusaoNucleoAssociadoException(e);
		}
	}
}
