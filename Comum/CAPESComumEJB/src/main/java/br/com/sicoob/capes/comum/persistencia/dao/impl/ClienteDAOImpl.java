package br.com.sicoob.capes.comum.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.comum.persistencia.dao.CAPESComumDao;
import br.com.sicoob.capes.comum.persistencia.dao.ClienteDAO;

/**
 * A Classe ClienteDAOImpl.
 */
public class ClienteDAOImpl extends CAPESComumDao implements ClienteDAO {

	/** A constante ID_PESSOA. */
	private static final String ID_PESSOA = "idPessoa";
	
	/** A constante ID_INSTITUICAO. */
	private static final String ID_INSTITUICAO = "idInstituicao";
	
	/** A constante DATA_INCLUSAO_SFN. */
	private static final String DATA_INCLUSAO_SFN = "dataInclusaoSFN";
	
	/** A constante ID_PESSOA_LEGADO. */
	private static final String ID_PESSOA_LEGADO = "idPessoaLegado";
	
	/** A constante AUTORIZA_CONSULTA_BACEN. */
	private static final String AUTORIZA_CONSULTA_BACEN = "autorizaConsultaBacen";
	
	/** A constante DATA_INCLUSAO_SISTEMA. */
	private static final String DATA_INCLUSAO_SISTEMA = "dataInclusaoSistema";
	
	/** A constante CODIGO_CNAE_FISCAL. */
	private static final String CODIGO_CNAE_FISCAL = "codigoCnaeFiscal";
	
	/** A constante CODIGO_ATIVIDADE_ECONOMICA. */
	private static final String CODIGO_ATIVIDADE_ECONOMICA = "codigoAtividadeEconomica";
	
	/** A constante DESCRICAO_OBSERVACAO_PESSOA. */
	private static final String DESCRICAO_OBSERVACAO_PESSOA = "descricaoObservacaoPessoa";
	
	/** A constante NOME_APELIDO. */
	private static final String NOME_APELIDO = "nomeApelido";
	
	/** A constante NOME_COMPLETO. */
	private static final String NOME_COMPLETO = "nomeCompleto";
	
	/** A constante NOME_PESSOA. */
	private static final String NOME_PESSOA = "nomePessoa";
	
	/** A constante CODIGO_COMPARTILHAMENTO_CADASTRO. */
	private static final String CODIGO_COMPARTILHAMENTO_CADASTRO = "codigoCompartilhamentoCadastro";
	
	/** A constante COD_TIPO_PESSOA. */
	private static final String COD_TIPO_PESSOA = "codTipoPessoa";
	
	/** A constante CPF_CNPJ. */
	private static final String CPF_CNPJ = "cpfCnpj";

	/**
	 * {@inheritDoc}
	 */
	public Map<String, Object> obterPorIdInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		Map<String, Object> mapa = null;
		try {
			comando = getComando("PESQUISAR_CLIENTE");
			comando.adicionarVariavel(ID_PESSOA, idPessoa);
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.configurar();

			conexao = estabelecerConexao();
			rs = comando.executarConsulta(conexao);
			if (rs.next()) {
				mapa = new HashMap<String, Object>();
				mapa.put(ID_PESSOA, rs.getInt(ID_PESSOA));
				mapa.put(CPF_CNPJ, rs.getString(CPF_CNPJ));
				mapa.put(COD_TIPO_PESSOA, rs.getShort(COD_TIPO_PESSOA));
				mapa.put(CODIGO_COMPARTILHAMENTO_CADASTRO, rs.getShort(CODIGO_COMPARTILHAMENTO_CADASTRO));
				mapa.put(NOME_PESSOA, rs.getString(NOME_PESSOA));
				mapa.put(NOME_COMPLETO, rs.getString(NOME_COMPLETO));
				mapa.put(NOME_APELIDO, rs.getString(NOME_APELIDO));
				mapa.put(DESCRICAO_OBSERVACAO_PESSOA, rs.getString(DESCRICAO_OBSERVACAO_PESSOA));
				mapa.put(CODIGO_ATIVIDADE_ECONOMICA, rs.getShort(CODIGO_ATIVIDADE_ECONOMICA));
				mapa.put(CODIGO_CNAE_FISCAL, rs.getString(CODIGO_CNAE_FISCAL));
				mapa.put(AUTORIZA_CONSULTA_BACEN, rs.getBoolean(AUTORIZA_CONSULTA_BACEN));
				mapa.put(ID_PESSOA_LEGADO, rs.getInt(ID_PESSOA_LEGADO));
				mapa.put(ID_INSTITUICAO, rs.getInt(ID_INSTITUICAO));

				if(rs.getTimestamp(DATA_INCLUSAO_SISTEMA) != null){
					mapa.put(DATA_INCLUSAO_SISTEMA, new DateTimeDB(rs.getTimestamp(DATA_INCLUSAO_SISTEMA).getTime()));
				}
				
				if (rs.getDate(DATA_INCLUSAO_SFN) != null) {
					mapa.put(DATA_INCLUSAO_SFN, new DateTimeDB(rs.getDate(DATA_INCLUSAO_SFN).getTime()));
				}

			}
		} catch (SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			fecharResultSet(rs);
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return mapa;
	}

	/**
	 * {@inheritDoc}
	 */
	public Map<String, Object> obterPorCpfCnpjInstituicao(String cpfCnpj, Integer idInstituicao) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		Map<String, Object> mapa = null;
		try {
			comando = getComando("PESQUISAR_CLIENTE");
			comando.adicionarVariavel(CPF_CNPJ, cpfCnpj);
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.configurar();

			conexao = estabelecerConexao();
			rs = comando.executarConsulta(conexao);
			if (rs.next()) {
				mapa = new HashMap<String, Object>();
				mapa.put(ID_PESSOA, rs.getInt(ID_PESSOA));
				mapa.put(CPF_CNPJ, rs.getString(CPF_CNPJ));
				mapa.put(COD_TIPO_PESSOA, rs.getShort(COD_TIPO_PESSOA));
				mapa.put(CODIGO_COMPARTILHAMENTO_CADASTRO, rs.getShort(CODIGO_COMPARTILHAMENTO_CADASTRO));
				mapa.put(NOME_PESSOA, rs.getString(NOME_PESSOA));
				mapa.put(NOME_COMPLETO, rs.getString(NOME_COMPLETO));
				mapa.put(NOME_APELIDO, rs.getString(NOME_APELIDO));
				mapa.put(DESCRICAO_OBSERVACAO_PESSOA, rs.getString(DESCRICAO_OBSERVACAO_PESSOA));
				mapa.put(CODIGO_ATIVIDADE_ECONOMICA, rs.getShort(CODIGO_ATIVIDADE_ECONOMICA));
				mapa.put(CODIGO_CNAE_FISCAL, rs.getString(CODIGO_CNAE_FISCAL));
				mapa.put(AUTORIZA_CONSULTA_BACEN, rs.getBoolean(AUTORIZA_CONSULTA_BACEN));
				mapa.put(ID_PESSOA_LEGADO, rs.getInt(ID_PESSOA_LEGADO));
				mapa.put(ID_INSTITUICAO, rs.getInt(ID_INSTITUICAO));
				
				if(rs.getTimestamp(DATA_INCLUSAO_SISTEMA) != null){
					mapa.put(DATA_INCLUSAO_SISTEMA, new DateTimeDB(rs.getTimestamp(DATA_INCLUSAO_SISTEMA).getTime()));
				}

				if (rs.getDate(DATA_INCLUSAO_SFN) != null) {
					mapa.put(DATA_INCLUSAO_SFN, new DateTimeDB(rs.getDate(DATA_INCLUSAO_SFN).getTime()));
				}

			}
		} catch (SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			fecharResultSet(rs);
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return mapa;
	}

}