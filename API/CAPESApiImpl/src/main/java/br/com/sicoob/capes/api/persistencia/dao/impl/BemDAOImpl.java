package br.com.sicoob.capes.api.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.api.negocio.vo.BemProprietarioVO;
import br.com.sicoob.capes.api.negocio.vo.BemVO;
import br.com.sicoob.capes.api.negocio.vo.TipoBemVO;
import br.com.sicoob.capes.api.negocio.vo.TipoClassificacaoBemVO;
import br.com.sicoob.capes.api.persistencia.dao.BemDAO;

/**
 * Classe com as operações de bens.
 * 
 * @author Bruno.Carneiro
 */
public class BemDAOImpl extends CAPESApiDAO<BemVO> implements BemDAO {

	/**
	 * Construtor
	 */
	public BemDAOImpl() {
		super("PESQUISAR_BEM");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<BemVO> obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		Connection conx = null;
		ResultSet rset = null;
		Comando comando = null;
		List<BemVO> listaRetorno = new ArrayList<BemVO>();

		try {
			getLogger().debug("CAPESApiDAO obterPorPessoaInstituicao - idPessoa:" + idPessoa + " idInstituicao:" + idInstituicao);

			comando = getComando(getNomeComando());
			comando.adicionarVariavel(ID_PESSOA, idPessoa);
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.configurar();

			getLogger().debug(comando.getSqlEmUso());

			conx = estabelecerConexao();
			rset = comando.executarConsulta(conx);

			while (rset.next()) {
				BemVO bem = preencherBem(rset);
				bem.setPercentualProprietario(rset.getBigDecimal("PERCPROPRIETARIO"));
				listaRetorno.add(bem);
			}

			getLogger().debug("CAPESApiDAO obterPorPessoaInstituicao - Retorno:" + listaRetorno);
			return listaRetorno;
		} catch (SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			fecharResultSet(rset);
			fecharConexao(conx);
			fecharComando(comando);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public BemVO obterPorIdBem(Long idBem) throws BancoobException {
		Connection conx = null;
		ResultSet rset = null;
		Comando comando = null;
		BemVO bem = null;

		try {
			getLogger().debug("CAPESApiDAO obterPorIdBem - idBem:" + idBem);

			comando = getComando("PESQUISAR_BEM_POR_ID");
			comando.adicionarVariavel("idBem", idBem);
			comando.configurar();

			getLogger().debug(comando.getSqlEmUso());

			conx = estabelecerConexao();
			rset = comando.executarConsulta(conx);

			if (rset.next()) {
				bem = preencherBem(rset);
			}

			getLogger().debug("CAPESApiDAO obterPorIdBem - Retorno:" + bem);
			return bem;
		} catch (SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			fecharResultSet(rset);
			fecharConexao(conx);
			fecharComando(comando);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<TipoClassificacaoBemVO> obterClassificacoesBem() throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("OBTER_CLASSIFICACOES_BEM");
			comando.configurar();
			return criarQuery(comando).getResultList();
		} finally {
			fecharComando(comando);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<TipoBemVO> obterTiposBemPorClassificacao(boolean bemImovel) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("OBTER_TIPOS_BEM_POR_CLASSIFICACAO");
			comando.adicionarVariavel("bemImovel", bemImovel);
			comando.configurar();
			return criarQuery(comando).getResultList();
		} finally {
			fecharComando(comando);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<BemProprietarioVO> obterProprietarios(Long idBem) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("OBTER_PROPRIETARIOS_BEM");
			comando.adicionarVariavel("idBem", idBem);
			comando.configurar();
			return criarQuery(comando).getResultList();
		} finally {
			fecharComando(comando);
		}
	}

	private BemVO preencherBem(ResultSet rset) throws SQLException {
		BemVO bem = new BemVO();
		bem.setIdBem(rset.getLong("IDBEM"));
		bem.setCodigoTipoClassificacaoBem(rset.getShort("CODTIPOCLASSIFICACAOBEM"));
		bem.setDescricaoTipoClassificacaoBem(rset.getString("DESCTIPOCLASSIFICACAOBEM"));
		bem.setInterno(rset.getBoolean("BOLBEMINTERNO"));
		bem.setDescricao(rset.getString("DESCBEM"));
		bem.setValor(rset.getBigDecimal("VALORBEM"));
		bem.setValorNaoInformado(rset.getBoolean("BOLVALORBEMNAOINFORMADO"));
		bem.setMesRenovacaoSeguro(rset.getShort("MESRENOVACAOSEGURO"));
		bem.setEmGarantia(rset.getBoolean("EM_GARANTIA"));
		return bem;
	}

}