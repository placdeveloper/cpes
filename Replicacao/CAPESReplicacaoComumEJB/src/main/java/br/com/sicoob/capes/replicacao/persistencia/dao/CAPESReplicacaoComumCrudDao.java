package br.com.sicoob.capes.replicacao.persistencia.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.dao.BancoobCrudDao;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.negocio.entidades.legado.CAPESEntidadeLegado;
import br.com.sicoob.capes.persistencia.CAPESEntidadesLegadoDatasource;

/**
 * Classe padrao com a infraestrutura de acesso a base de dados do sistema
 * ReplicacaoClientesBO
 * 
 * @author Jarbasjstefanini
 */
public abstract class CAPESReplicacaoComumCrudDao<T extends CAPESEntidadeLegado<?>>
		extends BancoobCrudDao<T> implements CAPESReplicacaoComumCrudDaoIF<T> {

	/** Nome da coleção de comandos do sistema replicação de clientes. */
	private static final String NOME_COLECAO_COMANDOS = "COMANDOS_CAPES_REPLICACAO";

	/** Caminho para o arquivo de queries do replicação de clientes. */
	private static final String ARQUIVO_QUERIES = "capes.replicacao.comum.queries.xml";

	/** Caminho para o datasource. */
	private static final String DATASOURCE = "jdbc/BancoobDS";

	/**
	 * Construtor dos DAOs que realizam operações de CRUD no sistema Replicação
	 * de clientes.
	 * 
	 * @param clazz
	 *            A entidade
	 * @param nomeComandoPesquisar
	 *            O nome do comando para pesquisar.
	 */
	public CAPESReplicacaoComumCrudDao(Class<T> clazz, String nomeComandoPesquisar) {
		super(DATASOURCE, ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, clazz, nomeComandoPesquisar);
	}

	/**
	 * Estabelece a conexao com o banco de dados.
	 * 
	 * @return a conexao com o banco de dados.
	 */
	@Override
	protected Connection estabelecerConexao() {
		try {
			CAPESEntidadesLegadoDatasource datasource = 
					new CAPESEntidadesLegadoDatasource(
							getNomeDatasource(), System.getProperties());
			return datasource.getConnection();
		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(T objeto) throws BancoobException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(Serializable chave) throws BancoobException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T incluir(T objeto) throws BancoobException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<T> listar() throws BancoobException {
		throw new UnsupportedOperationException();
	}

	/** {@inheritDoc} */
	public T incluir(T objeto, Integer numeroCooperativa) throws BancoobException {
		CAPESEntidadesLegadoDatasource.definirNumeroCooperativa(numeroCooperativa);
		return super.incluir(objeto);
	}

	/** {@inheritDoc} */
	public void alterar(T objeto, Integer numeroCooperativa) throws BancoobException {
		CAPESEntidadesLegadoDatasource.definirNumeroCooperativa(numeroCooperativa);
		super.alterar(objeto);
	}

	/** {@inheritDoc} */
	public void excluir(Serializable chave, Integer numeroCooperativa) throws BancoobException {
		CAPESEntidadesLegadoDatasource.definirNumeroCooperativa(numeroCooperativa);
		super.excluir(chave);
	}

	/** {@inheritDoc} */
	public T obter(Serializable chave, Integer numeroCooperativa) throws BancoobException {
		CAPESEntidadesLegadoDatasource.definirNumeroCooperativa(numeroCooperativa);
		return super.obter(chave);
	}

	/** {@inheritDoc} */
	public List<T> listar(Integer numeroCooperativa) throws BancoobException {
		CAPESEntidadesLegadoDatasource.definirNumeroCooperativa(numeroCooperativa);
		return super.listar();
	}

	/** {@inheritDoc} */
	public List<T> listar(ConsultaDto<T> criterios, Integer numeroCooperativa) throws BancoobException {
		CAPESEntidadesLegadoDatasource.definirNumeroCooperativa(numeroCooperativa);
		return super.listar(criterios);
	}

	/**
	 * O método Fechar result set.
	 *
	 * @param rs o valor de rs
	 */
	protected void fecharResultSet(ResultSet rs) {
    	
    	if (rs != null) {
    		try {
                rs.close();
            } catch (SQLException e) {
            	getLogger().erro(e, "Falha no fechamento do ResultSet");
            }
    	}
    }
	
	@Override
	@PersistenceContext(unitName = "emCapesLegado")
	public void setEntityManager(EntityManager manager) {
		super.setEntityManager(manager);
	}
	
}