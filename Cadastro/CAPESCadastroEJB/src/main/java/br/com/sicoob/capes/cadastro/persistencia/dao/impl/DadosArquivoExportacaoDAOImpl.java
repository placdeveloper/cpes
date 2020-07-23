package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.cadastro.persistencia.dao.DadosArquivoExportacaoDAO;
import br.com.sicoob.capes.negocio.entidades.DadosArquivoExportacao;
import br.com.sicoob.capes.negocio.entidades.Exportacao;
import br.com.sicoob.capes.negocio.entidades.TipoInformacao;

/**
 * @since 1.2.1.0
 * @author rodrigo.chaves
 */
public class DadosArquivoExportacaoDAOImpl extends CAPESCadastroCrudDao<DadosArquivoExportacao> implements DadosArquivoExportacaoDAO {

	/**
	 * Construtor
	 * 
	 * @param classe
	 * @param nomeComandoPesquisar
	 */
	public DadosArquivoExportacaoDAOImpl() {
		super(DadosArquivoExportacao.class, "PESQUISAR_DADOS_ARQUIVO_EXPORTACAO");
	}

	/**
	 * {@inheritDoc}
	 */
	public void incluir(List<DadosArquivoExportacao> listaDados) throws BancoobException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = estabelecerConexao();
			String sql = "INSERT INTO CLI.DADOSARQUIVOEXPORTACAO (IDEXPORTACAO, NUMLINHA, DESCLINHA) VALUES (?, ?, ?)";
			ps = conn.prepareStatement(sql);
			
			getLogger().debug("[CAPES] DadosArquivoExportacao: incluindo ", String.valueOf(listaDados.size()), " registros.");

			for (DadosArquivoExportacao dadosArquivo : listaDados) {
				adicionarParametroIntPrepareStatement(ps, 1, dadosArquivo.getPk().getIdExportacao());
				adicionarParametroIntPrepareStatement(ps, 2, dadosArquivo.getPk().getNumeroLinha());
				adicionarParametroStringPrepareStatement(ps, 3, dadosArquivo.getLinha());
				ps.addBatch();
			}
			ps.executeBatch();
		} catch (SQLException e) {
			throw new BancoobException(e);
		} finally {
			fecharStatement(ps);
			fecharConexao(conn);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<TipoInformacao> obterCodigosTipoInformacaoExportacao(Exportacao exportacao) throws BancoobException {
		Comando comando = null;
		List<TipoInformacao> codigos = null;
		try {
			comando = getComando("OBTER_CODIGO_TIPOS_INFORMACAO_DADOS_ARQUIVO");
			comando.adicionarVariavel("idExportacao", exportacao.getIdExportacao());
			comando.configurar();
			codigos = criarQuery(comando).getResultList();
		} finally {
			fecharComando(comando);
		}
		return codigos;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Integer obterProximoNumeroLinha(Exportacao exportacao) throws BancoobException {
		Comando comando = null;
		Integer numeroLinha = null;
		try {
			comando = getComando("OBTER_NUMERO_LINHA_DADOS_ARQUIVO");
			comando.adicionarVariavel("idExportacao", exportacao.getId());
			comando.configurar();
			numeroLinha = (Integer) criarQuery(comando).getSingleResult();
		} finally {
			fecharComando(comando);
		}

		if (numeroLinha == null) {
			numeroLinha = NumberUtils.INTEGER_ONE;
		}

		return numeroLinha;
	}
	
	/**
	 * O método Adicionar parametro string prepare statement.
	 *
	 * @param ps o valor de ps
	 * @param indexParametro o valor de index parametro
	 * @param valor o valor de valor
	 * @throws SQLException lança a exceção SQLException
	 */
	private void adicionarParametroStringPrepareStatement(PreparedStatement ps, int indexParametro, String valor) throws SQLException {
		if (valor != null) {
			ps.setString(indexParametro, valor);
		} else {
			ps.setNull(indexParametro, Types.VARCHAR);
		}
	}

	/**
	 * O método Adicionar parametro int prepare statement.
	 *
	 * @param ps o valor de ps
	 * @param indexParametro o valor de index parametro
	 * @param valor o valor de valor
	 * @throws SQLException lança a exceção SQLException
	 */
	private void adicionarParametroIntPrepareStatement(PreparedStatement ps, int indexParametro, Integer valor) throws SQLException {
		if (valor != null) {
			ps.setInt(indexParametro, valor);
		} else {
			ps.setNull(indexParametro, Types.INTEGER);
		}
	}
}