package br.com.sicoob.capes.relatorio.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.comandos.ClausulaIn;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.comandos.Ordenacao;
import br.com.bancoob.persistencia.dao.BancoobDao;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.relatorio.persistencia.CAPESRelatorioDatasource;
import br.com.sicoob.capes.relatorio.persistencia.conversor.ConversorResultado;
import br.com.sicoob.capes.relatorio.util.CAPESRelatorioConstantes;

/**
 * Classe padrao com a infraestrutura de acesso a base de dados do sistema CAPES
 * 
 * @author Stefanini IT Solutions
 */
public abstract class CAPESRelatorioDao extends BancoobDao {

	/**
	 * @param nomeComandoListar
	 * @param nomeComandoConsultar
	 */
	public CAPESRelatorioDao() {

		super(Constantes.Persistencia.DATASOURCE_CAPES,
		        CAPESRelatorioConstantes.Persistencia.ARQUIVO_QUERIES,
		        CAPESRelatorioConstantes.Persistencia.NOME_COLECAO_COMANDOS);
	}

	/**
	 * Estabelece a conexao com o banco de dados.
	 * 
	 * @return a conexao com o banco de dados.
	 */
	@Override
	protected Connection estabelecerConexao() {
		try {
			CAPESRelatorioDatasource datasource = new CAPESRelatorioDatasource(getNomeDatasource(),
			        System.getProperties());
			return datasource.getConnection();
		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		}
	}

	/**
	 * @param rset
	 */
	protected void fecharResultSet(ResultSet rset) {
		if (rset != null) {
			try {
				rset.close();
			} catch (SQLException e) {
				getLogger().erro(e, "Falha no fechamento do ResultSet");
			}
		}
	}

	/**
	 * Criar query relatorio.
	 *
	 * @param <C> o tipo generico
	 * @param criterios o valor de criterios
	 * @param nomeComando o valor de nome comando
	 * @param nativa o valor de nativa
	 * @return Query
	 */
	protected <C> Query criarQueryRelatorio(ConsultaDto<C> criterios, String nomeComando,
	        boolean nativa) {

		Query query = null;
		Comando comando = getComando(nomeComando);
		int indice = 0;
		String sql = null;

		comando.adicionarVariavel("criterios", criterios);
		comando.configurar();

		sql = comando.getSqlEmUso();

		if (comando.getProjecao() != null) {
			sql = comando.getProjecao() + " " + sql;
		}

		Ordenacao ordenacao = comando.getOrdenacao(criterios.getOrdenacao());
		if (ordenacao != null) {
			sql = sql + " " + ordenacao.getTrecho() + " "
			        + (criterios.isOrdemCrescente() ? "asc" : "desc");
		}

		Iterator<Object> itParametros = comando.getParametros().iterator();

		while (itParametros.hasNext()) {
			Object parametro = itParametros.next();

			if (parametro instanceof Collection) {
				String sqlAntigo = sql;

				sql = ClausulaIn.substituir(sql, (Collection<?>) parametro);
				if (!sqlAntigo.equals(sql)) {
					itParametros.remove();
				}
			}
		}
		if (nativa) {
			query = getEntityManager().createNativeQuery(sql);
			for (Object parametro : comando.getParametros()) {
				query.setParameter(++indice, parametro);
			}
		} else {
			query = getEntityManager().createQuery(sql);
			for (Object parametro : comando.getParametros()) {
				query.setParameter("parametro" + indice++, parametro);
			}
		}
		return query;
	}

	/**
	 * Executar query relatorio.
	 *
	 * @param <C> o tipo generico
	 * @param criterios o valor de criterios
	 * @param nomeComando o valor de nome comando
	 * @param nativa o valor de nativa
	 * @param conversor o valor de conversor
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected <C> List<C> executarQueryRelatorio(ConsultaDto<C> criterios, String nomeComando,
	        boolean nativa, ConversorResultado<C> conversor) throws BancoobException {

		List<C> retorno = new ArrayList<C>();
		Query query = criarQueryRelatorio(criterios, nomeComando, nativa);
		List resultadoQuery = query.getResultList();
		if (nativa) {
			retorno.addAll(converterResultado(resultadoQuery, conversor));
		} else {
			retorno.addAll(resultadoQuery);
		}
		return retorno;
	}

	/**
	 * @param rs
	 *            O resultado da query
	 * @param conversor
	 *            O conversor que sera utilizado
	 * @return uma lista de objetos do tipo da {@code classe} recebida
	 */
	protected <C> List<C> converterResultado(ResultSet rs, ConversorResultado<C> conversor)
	        throws BancoobException {
		try {
			List<C> retorno = new ArrayList<C>();
			if (rs != null) {
				while (rs.next()) {
					Object[] tupla = new Object[rs.getMetaData().getColumnCount()];
					for (int i = 0; i < tupla.length; i++) {
						tupla[i] = rs.getObject(i + 1);
					}
					CollectionUtils.addIgnoreNull(retorno, conversor.converterTupla(tupla));
				}
			}
			return conversor.tratarLista(retorno);
		} catch (SQLException e) {
			throw new PersistenciaException(e);
		}
	}

	@Override
	@PersistenceContext(unitName = "emCapes")
	public void setEntityManager(EntityManager manager) {
		super.setEntityManager(manager);
	}

	/**
	 * @param tuplas
	 *            A lista contendo as tuplas obtidas da execucao da query
	 * @param conversor
	 *            O conversor que sera utilizado para realizar a conversao
	 * @return uma lista de objetos do tipo da {@code classe} recebida
	 */
	protected <C> List<C> converterResultado(List<Object[]> tuplas, ConversorResultado<C> conversor)
	        throws BancoobException {

		List<C> retorno = new ArrayList<C>();
		if (tuplas != null) {
			for (Object[] tupla : tuplas) {
				if (tupla != null) {
					CollectionUtils.addIgnoreNull(retorno, conversor.converterTupla(tupla));
				}
			}
		}
		return conversor.tratarLista(retorno);
	}
}