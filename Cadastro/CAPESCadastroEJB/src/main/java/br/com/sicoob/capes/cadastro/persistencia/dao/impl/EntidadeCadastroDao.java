/*
 * SICOOB
 * 
 * EntidadeCadastroDao.java(br.com.sicoob.capes.cadastro.persistencia.dao.impl.EntidadeCadastroDao)
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.cadastro.persistencia.dao.EntidadeCadastroDaoIF;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.EntidadeCadastroBase;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;

/**
 * A Classe EntidadeCadastroDao.
 *
 * @param <E> o tipo generico
 */
public abstract class EntidadeCadastroDao<E extends EntidadeCadastroBase & Aprovavel> extends
		CAPESCadastroCrudDao<E> implements EntidadeCadastroDaoIF<E> {

	/** A constante COMANDO_MARCAR_DESMARCAR_EM_ALTERACAO. */
	protected static final String COMANDO_MARCAR_DESMARCAR_EM_ALTERACAO =
			"MARCAR_DESMARCAR_EM_ALTERACAO";

	/**
	 * Instancia um novo EntidadeCadastroDao.
	 *
	 * @param clazz o valor de clazz
	 * @param nomeComandoPesquisar o valor de nome comando pesquisar
	 */
	public EntidadeCadastroDao(Class<E> clazz, String nomeComandoPesquisar) {
		super(clazz, nomeComandoPesquisar);
	}

	/**
	 * {@inheritDoc}
	 */
	public void marcarEmAlteracao(E objeto, Integer idInstituicaoAtualizacao) throws BancoobException {

		AbstractEntityPersister metadados = getMetadados(objeto.getClass());
		marcarEmAlteracao(metadados.getTableName(), metadados.getIdentifierColumnNames()[0],
				((CAPESEntidade<Long>) objeto).getId(),
				idInstituicaoAtualizacao);
	}
	
	/**
	 * O método Marcar em alteracao.
	 *
	 * @param nomeTabela o valor de nome tabela
	 * @param nomeColunaId o valor de nome coluna id
	 * @param id o valor de id
	 * @param idInstituicaoAtualizacao o valor de id instituicao atualizacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected void marcarEmAlteracao(String nomeTabela, String nomeColunaId,
			Long id, Integer idInstituicaoAtualizacao) throws BancoobException {

		Comando comando = null;
		try {
			comando = getComando(COMANDO_MARCAR_DESMARCAR_EM_ALTERACAO);
			comando.adicionarVariavel("nomeTabela", nomeTabela);
			comando.adicionarVariavel("nomeColunaId", nomeColunaId);
			comando.configurar();

			Query query = getEntityManager().createNativeQuery(comando.getSqlEmUso());
			query.setParameter(1, idInstituicaoAtualizacao);
			query.setParameter(2, id);
			
			query.executeUpdate();
		} finally {
			fecharComando(comando);
		}
	}
	
	/**
	 * Recupera o valor de metadados.
	 *
	 * @param clazz o valor de clazz
	 * @return o valor de metadados
	 */
	@SuppressWarnings("rawtypes")
	private AbstractEntityPersister getMetadados(
			Class<? extends CAPESEntidade> clazz) {

		AbstractEntityPersister persister = null;
		Session session = (Session) getEntityManager().getDelegate();
		SessionFactory sessionFactory = session.getSessionFactory();
		ClassMetadata metadata = sessionFactory.getClassMetadata(clazz);
		if (metadata instanceof AbstractEntityPersister) {
			persister = (AbstractEntityPersister) metadata;
		}
		return persister;
	}
}
