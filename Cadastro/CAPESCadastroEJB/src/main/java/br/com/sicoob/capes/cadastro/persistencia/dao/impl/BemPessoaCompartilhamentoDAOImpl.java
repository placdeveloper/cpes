package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Hibernate;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.cadastro.negocio.vo.DadosListagemBemVO;
import br.com.sicoob.capes.cadastro.negocio.vo.DadosListagemParceriasBemVO;
import br.com.sicoob.capes.cadastro.persistencia.dao.BemPessoaCompartilhamentoDAO;
import br.com.sicoob.capes.negocio.entidades.bem.BemPessoaCompartilhamento;

/**
 * Classe com a implementação das DAO's de {@link BemPessoaCompartilhamento}.
 * 
 * @author Bruno.Carneiro
 */
public class BemPessoaCompartilhamentoDAOImpl extends EntidadeCadastroDao<BemPessoaCompartilhamento> implements BemPessoaCompartilhamentoDAO {

	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_BEM_PESSOA_COMPARTILHAMENTO_POR_FILTRO";

	/**
	 * Método construtor.
	 */
	public BemPessoaCompartilhamentoDAOImpl() {
		super(BemPessoaCompartilhamento.class, NOME_COMANDO_PESQUISAR);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public BemPessoaCompartilhamento obter(Serializable chave) throws BancoobException {
		BemPessoaCompartilhamento bemPessoaCompartilhamento = super.obter(chave);
		if(bemPessoaCompartilhamento != null) {
			Hibernate.initialize(bemPessoaCompartilhamento.getBem());
			Hibernate.initialize(bemPessoaCompartilhamento.getPessoaCompartilhamento());
		}
		return bemPessoaCompartilhamento;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluirCompartilhamentosMarcados(Long idBem) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("EXCLUIR_COMPARTILHAMENTOS_MARCADOS");
			comando.configurar();

			Query query = getEntityManager().createNativeQuery(comando.getSqlEmUso());
			query.setParameter(1, idBem);
			query.executeUpdate();
		} finally {
			fecharComando(comando);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void desmarcarResponsaveisBem(Long idBem) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("DESMARCAR_COMPARTILHAMENTOS_RESPONSAVEIS_BEM");
			comando.configurar();

			Query query = getEntityManager().createNativeQuery(comando.getSqlEmUso());
			query.setParameter(1, idBem);
			query.executeUpdate();
		} finally {
			fecharComando(comando);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void incluirHistoricoBemPessoaCompartilhamento(Long idBem, String idUsuario) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("ADICIONAR_HISTORICO_BEM_PESSOA_COMPARTILHAMENTO");
			comando.configurar();

			Query query = getEntityManager().createNativeQuery(comando.getSqlEmUso());
			query.setParameter(1, idUsuario);
			query.setParameter(2, idBem);
			query.executeUpdate();
		} finally {
			fecharComando(comando);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DadosListagemBemVO> obterDadosListagem(Long idPessoaCompartilhamento) {
		Connection conx = null;
		ResultSet rset = null;
		Comando comando = null;
		List<DadosListagemBemVO> retorno = new ArrayList<DadosListagemBemVO>();
		
		try {
			comando = getComando("OBTER_DADOS_LISTAGEM_BEM");
			comando.adicionarVariavel("idPessoaCompartilhamento", idPessoaCompartilhamento);
			comando.configurar();
			
			conx = estabelecerConexao();
			rset = comando.executarConsulta(conx);

			while(rset.next()) {
				DadosListagemBemVO vo = new DadosListagemBemVO();
				preencherInformacoesDadosListagem(rset, vo);
				vo.setDataAvaliacao(rset.getDate("DATAAVALIACAO"));
				vo.setDescricaoTipoBem(rset.getString("DESCTIPOBEM"));
				vo.setIdBemPessoaCompartilhamento(rset.getLong("IDBEMPESSOACOMPARTILHAMENTO"));
				vo.setPercentualProprietario(rset.getBigDecimal("PERCPROPRIETARIO"));
				vo.setValor(rset.getBigDecimal("VALORBEM"));
				vo.setValorAvaliacao(rset.getBigDecimal("VALORAVALIACAO"));
				vo.setStatus(rset.getString("STATUS"));
				retorno.add(vo);
			}

		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		} finally {
			fecharResultSet(rset);
			fecharConexao(conx);
			fecharComando(comando);
		}

		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DadosListagemParceriasBemVO> obterDadosListagemParcerias(Long idPessoaCompartilhamento) throws BancoobException {
		Connection conx = null;
		ResultSet rset = null;
		Comando comando = null;
		List<DadosListagemParceriasBemVO> retorno = new ArrayList<DadosListagemParceriasBemVO>();
		
		try {
			comando = getComando("OBTER_DADOS_LISTAGEM_PARCERIAS_BEM");
			comando.adicionarVariavel("idPessoaCompartilhamento", idPessoaCompartilhamento);
			comando.configurar();
			
			conx = estabelecerConexao();
			rset = comando.executarConsulta(conx);

			while(rset.next()) {
				DadosListagemParceriasBemVO vo = new DadosListagemParceriasBemVO();
				preencherInformacoesDadosListagem(rset, vo);
				vo.setAreaTotal(rset.getBigDecimal("AREATOTALIMOVEL"));
				vo.setAreaPosse(rset.getBigDecimal("TAMAREAPOSSE"));
				vo.setMatricula(rset.getString("DESCMATRICULA"));
				vo.setMunicipio(rset.getString("NOMELIMPOLOCALIDADE"));
				vo.setNirf(rset.getString("DESCNIRF"));
				vo.setIncra(rset.getString("DESCINCRA"));
				vo.setDescricaoTipoBem(rset.getString("DESCTIPOBEMIMOVEL"));
				retorno.add(vo);
			}

		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		} finally {
			fecharResultSet(rset);
			fecharConexao(conx);
			fecharComando(comando);
		}

		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean pessoaPossuiBensCadastrados(Long idPessoaCompartilhamento) throws BancoobException {
		Connection conx = null;
		ResultSet rset = null;
		Comando comando = null;

		try {
			comando = getComando("PESSOA_POSSUI_BENS_CADASTRADOS");
			comando.adicionarVariavel("idPessoaCompartilhamento", idPessoaCompartilhamento);
			comando.configurar();

			conx = estabelecerConexao();
			rset = comando.executarConsulta(conx);

			if (rset.next()) {
				return rset.getInt("CONTADOR") > 0;
			}

		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		} finally {
			fecharResultSet(rset);
			fecharConexao(conx);
			fecharComando(comando);
		}

		return false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public BemPessoaCompartilhamento obterBemPessoaCompartilhamentoInternoIdPessoaCompartilhamento(Long idPessoaCompartilhamento) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("OBTER_BEM_PESSOA_COMPARTILHAMENTO_INTERNO_POR_IDPESSOACOMPARTILHAMENTO");
			comando.adicionarVariavel("idPessoaCompartilhamento", idPessoaCompartilhamento);
			comando.configurar();
			return (BemPessoaCompartilhamento) criarQuery(comando).getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			fecharComando(comando);
		}
	}
	
	private void preencherInformacoesDadosListagem(ResultSet rset, DadosListagemBemVO vo) throws SQLException {
		vo.setBemInterno(rset.getBoolean("BOLBEMINTERNO"));
		vo.setCodigoSituacaoAprovacao(rset.getShort("CODIGOSITUACAOAPROVACAO"));
		vo.setCodigoTipoClassificacaoBem(rset.getShort("CODTIPOCLASSIFICACAOBEM"));
		vo.setDataHoraInicio(rset.getDate("DATAHORAINICIO"));
		vo.setDescricaoBem(rset.getString("DESCBEM"));
		vo.setDenominacaoBem(rset.getString("DESCDENOMINACAO"));
		vo.setDescricaoTipoClassificacaoBem(rset.getString("DESCTIPOCLASSIFICACAOBEM"));
		vo.setIdBem(rset.getLong("IDBEM"));
		vo.setIdInstituicaoAtualizacao(rset.getInt("IDINSTITUICAOATUALIZACAO"));
		vo.setIdRegistroControlado(rset.getString("IDREGISTROCONTROLADO"));
		vo.setIdUsuarioAprovacao(rset.getString("IDUSUARIOAPROV"));
//		vo.setBloqueadoPor(rset.getString("BLOQUEADO_POR"));
	}

}