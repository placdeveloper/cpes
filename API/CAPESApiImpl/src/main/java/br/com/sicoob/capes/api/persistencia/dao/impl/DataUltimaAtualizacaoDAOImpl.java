package br.com.sicoob.capes.api.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.apache.commons.lang.time.DateUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.api.persistencia.dao.DataUltimaAtualizacaoDAO;
import br.com.sicoob.capes.comum.negocio.vo.DataUltimaAtualizacaoVO;

/**
 * A Classe DataUltimaAtualizacaoDAOImpl.
 */
public class DataUltimaAtualizacaoDAOImpl extends CAPESApiDAO<DataUltimaAtualizacaoVO> implements DataUltimaAtualizacaoDAO {

	/**
	 * {@inheritDoc}
	 */
	public Date obterDataUltimaAtualizacaoPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		Connection conx = null;
		ResultSet rs = null;
		Comando comando = null;
		Date dataUltimaAtualizacao = null;

		try {
			conx = estabelecerConexao();

			comando = getComando("RECUPERAR_DATA_ULTIMA_ATUALIZACAO");
			comando.adicionarVariavel(ID_PESSOA, idPessoa);
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.configurar();
			
			getLogger().info(comando.getSqlEmUso());

			rs = comando.executarConsulta(conx);

			if (rs.next()) {
				dataUltimaAtualizacao = rs.getTimestamp(1);
			}

		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		} finally {
			fecharResultSet(rs);
			fecharConexao(conx);
			fecharComando(comando);
		}
		return dataUltimaAtualizacao;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<DataUltimaAtualizacaoVO> consultarDatasUltimaAtualizacaoPorDataReferenciaPessoas(Integer idInstituicao, Date dataReferencia, Integer... idsPessoas) throws BancoobException {
		List<DataUltimaAtualizacaoVO> resultList = null;
		Comando comando = null;
		try {
			comando = getComando("CONSULTAR_DATAS_ULTIMA_ATUALIZACAO_DATA_REFERENCIA_PESSOAS");
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.adicionarVariavel("dataReferencia", DateUtils.truncate(dataReferencia, Calendar.DATE));
			comando.adicionarVariavel("idsPessoas", Arrays.asList(idsPessoas));
			comando.configurar();
			
			getLogger().info(comando.getSqlEmUso());
			
			resultList = criarQuery(comando).getResultList();
		} catch (NoResultException e) {
			resultList = Collections.EMPTY_LIST;
		} finally {
			fecharComando(comando);
		}
		return resultList;
	}

}