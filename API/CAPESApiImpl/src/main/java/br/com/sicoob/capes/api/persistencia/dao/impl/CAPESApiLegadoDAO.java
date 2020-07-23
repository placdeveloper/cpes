package br.com.sicoob.capes.api.persistencia.dao.impl;

import br.com.bancoob.persistencia.dao.BancoobDao;
import br.com.sicoob.capes.api.persistencia.dao.CAPESApiLegadoDao;
import br.com.sicoob.capes.comum.util.Constantes;

public abstract class CAPESApiLegadoDAO extends BancoobDao implements CAPESApiLegadoDao {

	private static final String NOME_DATASOURCE = "jdbc/BancoobDS";
	private static final String ARQUIVO_QUERIES = "capes.api.legado.queries.xml";
	private static final String NOME_COLECAO_COMANDOS = "COMANDOS_CAPES_API_LEGADO_DAO";

	public CAPESApiLegadoDAO() {
		this(NOME_DATASOURCE, ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS);
	}

	public CAPESApiLegadoDAO(String nomeDatasource, String arquivoQueries, String nomeColecaoComandos) {
		super(nomeDatasource, arquivoQueries, nomeColecaoComandos);
	}

	public CAPESApiLegadoDAO(String nomeComando) {
		this(Constantes.Persistencia.DATASOURCE_CAPES, ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS);
	}

}