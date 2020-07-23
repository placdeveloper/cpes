/**
 * 
 */
package br.com.sicoob.capes.replicacao.persistencia.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.negocio.entidades.legado.EntidadeReplicavel;
import br.com.sicoob.capes.persistencia.CAPESEntidadesLegadoDatasource;
import br.com.sicoob.capes.replicacao.persistencia.excecao.CAPESReplicacaoComumPersistenciaException;

/**
 * Superclasse para os DAOs de entidades replicáveis no SQL.
 * 
 * @author erico.junior
 */
public abstract class EntidadeReplicavelDao<T extends EntidadeReplicavel<? extends Serializable>>
		extends CAPESReplicacaoComumCrudDao<T> implements EntidadeReplicavelDaoIF<T> {

	/** O atributo nomeComandoConsultarIdDB2. */
	private transient String nomeComandoConsultarIdDB2 = "";
	
	/**
	 * Instancia um novo EntidadeReplicavelDao.
	 *
	 * @param clazz o valor de clazz
	 * @param nomeComandoPesquisar o valor de nome comando pesquisar
	 * @param nomeComandoConsultarIdDB2 o valor de nome comando consultar id d b2
	 */
	public EntidadeReplicavelDao(Class<T> clazz, String nomeComandoPesquisar,
			String nomeComandoConsultarIdDB2) {
		super(clazz, nomeComandoPesquisar);
		this.nomeComandoConsultarIdDB2 = nomeComandoConsultarIdDB2;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public T obterPorIdDB2(Serializable chave, Integer numeroCooperativa) throws BancoobException {

		CAPESEntidadesLegadoDatasource.definirNumeroCooperativa(numeroCooperativa);

		Comando comando = getComando(nomeComandoConsultarIdDB2);
		comando.adicionarVariavel("idDB2", chave);
		comando.configurar();

		Connection conx = estabelecerConexao();
		T entidade = null;

		try {
			Query query = comando.criarQuery(getEntityManager());
			entidade = (T) query.getSingleResult();
		} catch (NoResultException e) {
			getLogger().alerta("Registro não encontrado no legado com a chave: " + chave);
		} catch (PersistenceException e) {
			throw new CAPESReplicacaoComumPersistenciaException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conx);
		}
		return entidade;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> obterPorIdsDB2(Serializable chave, Integer numeroCooperativa) throws BancoobException {

		CAPESEntidadesLegadoDatasource.definirNumeroCooperativa(numeroCooperativa);

		Comando comando = getComando(nomeComandoConsultarIdDB2);
		comando.adicionarVariavel("idDB2", chave);
		comando.configurar();

		Connection conx = estabelecerConexao();
		List<T> entidades = null;

		try {
			Query query = comando.criarQuery(getEntityManager());
			entidades = (List<T>) query.getResultList();
		} catch (NoResultException e) {
			getLogger().alerta("Registro não encontrado no legado com a chave: " + chave);
		} catch (PersistenceException e) {
			throw new CAPESReplicacaoComumPersistenciaException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conx);
		}
		return entidades;
	}

}
