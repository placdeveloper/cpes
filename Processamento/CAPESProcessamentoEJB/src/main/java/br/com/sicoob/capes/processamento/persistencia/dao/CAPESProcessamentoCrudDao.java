package br.com.sicoob.capes.processamento.persistencia.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.conexao.BancoobDataSource;
import br.com.bancoob.persistencia.dao.BancoobCrudDao;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidadeBase;
import br.com.sicoob.capes.persistencia.CAPESEntidadesLegadoDatasource;
import br.com.sicoob.capes.processamento.util.Constantes;

/**
 * Classe padrao com a infraestrutura de acesso a base de dados do sistema
 * IntegracaoCooperativasCapes
 * 
 */
public abstract class CAPESProcessamentoCrudDao<T extends CAPESEntidadeBase<?>>
		extends BancoobCrudDao<T> implements CAPESProcessamentoCrudDaoIF<T> {

	/**
	 * Instancia um novo CAPESProcessamentoCrudDao.
	 *
	 * @param clazz o valor de clazz
	 * @param nomeComandoPesquisar o valor de nome comando pesquisar
	 */
	public CAPESProcessamentoCrudDao(Class<T> clazz, String nomeComandoPesquisar) {
		super(Constantes.Persistencia.DATASOURCE, 
				Constantes.Persistencia.ARQUIVO_QUERIES,
				Constantes.Persistencia.NOME_COLECAO_COMANDOS, clazz, nomeComandoPesquisar);
	}
	
	/**
	 * @param nomeComandoListar
	 * @param nomeComandoConsultar
	 */
	public CAPESProcessamentoCrudDao(String nomeDatasource,
			String arquivoQueries, String nomeColecaoComandos, Class<T> clazz,
			String nomeComandoPesquisar) {

		super(nomeDatasource, arquivoQueries, nomeColecaoComandos, clazz,
				nomeComandoPesquisar);
	}

	/**
	 * Estabelece a conexao com o banco de dados.
	 * 
	 * @return a conexao com o banco de dados.
	 */
	@Override
	protected Connection estabelecerConexao() {
		try {
			BancoobDataSource datasource = new BancoobDataSource(
					getNomeDatasource(), System.getProperties());

			return datasource.getConnection();
		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public T obter(Serializable chave, Integer numeroCooperativa) throws BancoobException {
		CAPESEntidadesLegadoDatasource.definirNumeroCooperativa(numeroCooperativa);
		return super.obter(chave);
	}	
}