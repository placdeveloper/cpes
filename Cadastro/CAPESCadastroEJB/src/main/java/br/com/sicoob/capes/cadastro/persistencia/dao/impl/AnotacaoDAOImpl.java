/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaAnotacaoDTO;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaAnotacaoSisbrDTO;
import br.com.sicoob.capes.cadastro.negocio.vo.SumarioAnotacaoVO;
import br.com.sicoob.capes.cadastro.persistencia.dao.AnotacaoDAO;
import br.com.sicoob.capes.comum.negocio.dto.AnotacaoExternaDTO;
import br.com.sicoob.capes.negocio.entidades.Anotacao;
import br.com.sicoob.capes.negocio.entidades.AnotacaoSisbr;
import br.com.sicoob.capes.negocio.entidades.OrigemInformacao;
import br.com.sicoob.capes.negocio.entidades.TipoAnotacao;
import br.com.sicoob.capes.negocio.entidades.TipoBaixa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * DAO para as anotações dos clientes.
 * 
 * @author Erico.Junior
 */
public class AnotacaoDAOImpl extends CAPESCadastroCrudDao<Anotacao> implements
		AnotacaoDAO {

	/** A constante OBTER_ANOTACOES_EXTERNAS_BAIXA. */
	private static final String OBTER_ANOTACOES_EXTERNAS_BAIXA = "OBTER_ANOTACOES_EXTERNAS_BAIXA";
	
	/** A constante NOME_COMANDO_PESQUISAR_RELATORIO_ANOTACOES. */
	private static final String NOME_COMANDO_PESQUISAR_RELATORIO_ANOTACOES = "PESQUISA_RELATORIO_ANOTACOES";
	
	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_ANOTACOES_POR_FILTRO";
	
	/** A constante NOME_COMANDO_PESQUISAR_SISBR_ATIVAS. */
	private static final String NOME_COMANDO_PESQUISAR_SISBR_ATIVAS = "LISTAR_ANOTACOES_SISBR_ATIVAS_POR_FILTRO";
	
	/** A constante NOME_COMANDO_VERIFICAR_CADASTRO_REGULAR_RECEITA. */
	private static final String NOME_COMANDO_VERIFICAR_CADASTRO_REGULAR_RECEITA = "VERIFICAR_CADASTRO_REGULAR_RECEITA";
	
	/**
	 * Construtor do DAO.
	 */
	public AnotacaoDAOImpl() {
		super(Anotacao.class, NOME_COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Anotacao> listarAnotacoesPorFiltro(ConsultaAnotacaoDTO filtro) {
		return pesquisarLista(Anotacao.class, filtro, NOME_COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<SumarioAnotacaoVO> listarSumarioAnotacoesVigentes(PessoaCompartilhamento pessoa) {
		Connection conx = null;
		Comando comando = null;
		ResultSet rset = null;
		List<SumarioAnotacaoVO> listaRetorno = new ArrayList<SumarioAnotacaoVO>();
		
		try {
			conx = estabelecerConexao();
			comando = getComando("LISTAR_SUMARIO_ANOTACOES_POR_PESSOA");
			comando.adicionarVariavel("idPessoaCompartilhamento", pessoa.getIdPessoaCompartilhamento());			
			comando.configurar();
			
			rset = comando.executarConsulta(conx);
			while (rset.next()) {
				SumarioAnotacaoVO sumario = new SumarioAnotacaoVO();
				sumario.setCategoria(rset.getString("DESCCATEGORIAANOTACAO"));
				sumario.setDataUltima(rset.getTimestamp("DATAULTIMA"));
				sumario.setIdCategoria(rset.getShort("IDCATEGORIAANOTACAO"));
				sumario.setSituacao(rset.getString("SITUACAO_ANOTACAO"));
				sumario.setIdOrigem(rset.getShort("IDORIGEMINFO"));
				sumario.setOrigem(rset.getString("DESCORIGEMINFO"));
				sumario.setQuantidade(rset.getInt("QUANTIDADE"));
				sumario.setValor(rset.getBigDecimal("VALOR"));
				listaRetorno.add(sumario);
			}
			
		} catch (SQLException excecao) {  
			throw new PersistenciaException(excecao);
		} finally {
			fecharComando(comando);
			fecharConexao(conx);
			fecharResultSet(rset);
		}
		
		return listaRetorno;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<AnotacaoSisbr> listarAnotacoesSisbr(ConsultaAnotacaoSisbrDTO criterios) {
		return pesquisarLista(AnotacaoSisbr.class, criterios, NOME_COMANDO_PESQUISAR_SISBR_ATIVAS);
	}

	/**
	 * {@inheritDoc} 
	 */
	public List<Anotacao> listarAnotacoesParaRelatorio(ConsultaAnotacaoDTO criterios)
			throws BancoobException {
		return pesquisarLista(Anotacao.class, criterios, NOME_COMANDO_PESQUISAR_RELATORIO_ANOTACOES);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isCadastroReceitaRegular(PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando(NOME_COMANDO_VERIFICAR_CADASTRO_REGULAR_RECEITA);
			comando.adicionarVariavel("idPessoaCompartilhamento", pessoaCompartilhamento.getIdPessoaCompartilhamento());
			comando.configurar();
			return criarQuery(comando).getResultList().size() == 0;
		} finally {
			fecharComando(comando);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void alterar(List<Anotacao> anotacoes) throws BancoobException {
		if (anotacoes != null) {
			for (Anotacao anotacao : anotacoes) {
				alterar(anotacao);
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Anotacao> obterAnotacoesExternasBaixa(PessoaCompartilhamento pessoaCompartilhamento,
			OrigemInformacao origemInformacao, AnotacaoExternaDTO dto) throws BancoobException {

		Comando comando = null;
		try {
			comando = getComando(OBTER_ANOTACOES_EXTERNAS_BAIXA);
			comando.adicionarVariavel("idPessoaCompartilhamento", pessoaCompartilhamento.getIdPessoaCompartilhamento());
			comando.adicionarVariavel("idOrigemInformacao", origemInformacao.getIdOrigemInfo());
			comando.adicionarVariavel("nomeTipoConsulta", dto.getNomeTipoConsulta());
			comando.configurar();
			return criarQuery(comando).getResultList();
		} finally {
			fecharComando(comando);
		}
	}
	
	/**
	 * {@inheritDoc} 
	 */
	public boolean cooperativaPodeUtilizarTipoAnotacao(TipoAnotacao tipoAnotacao, Integer idInstituicao) throws BancoobException {
		Comando comando = null;
		Connection conn = null;
		ResultSet rs = null;

		try {
			Integer retorno = null;
			conn = estabelecerConexao();

			comando = getComando("TIPO_ANOTACAO_POSSUI_RESTRICAO");
			comando.adicionarVariavel("idTipoAnotacao", tipoAnotacao.getId());
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			comando.configurar();

			rs = comando.executarConsulta(conn);

			if (rs.next()) {
				retorno = rs.getInt(1);
			}

			return (retorno != null ? Boolean.valueOf(retorno != 0) : Boolean.FALSE);
		} catch (SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			fecharResultSet(rs);
			fecharConexao(conn);
			fecharComando(comando);
		}
	}
	
	/**
	 * {@inheritDoc} 
	 */
	public void baixarAnotacoesFiliais(List<Long> filiais, TipoAnotacao tipoAnotacao, TipoBaixa tipoBaixa, DateTimeDB dataHoraBaixa, String usuarioBaixa) throws BancoobException {
		Comando comando = null;
		Connection conn = null;
		try {
			conn = estabelecerConexao();

			comando = getComando("BAIXAR_ANOTACOES_FILIAIS");
			comando.adicionarVariavel("filiais", filiais);
			comando.adicionarVariavel("idTipoAnotacao", tipoAnotacao.getId());
			comando.adicionarVariavel("idTipoBaixa", tipoBaixa.getId());
			comando.adicionarVariavel("dataHoraBaixa", dataHoraBaixa);
			comando.adicionarVariavel("usuarioBaixa", usuarioBaixa);
			comando.configurar();

			comando.executarAtualizacao(conn);
		} finally {
			fecharConexao(conn);
			fecharComando(comando);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void flexibilizarAnotacoesFiliais(List<Long> filiais, TipoAnotacao tipoAnotacao, DateTimeDB dataAlteracao, String usuarioAlteracao, boolean flexibilizar) throws BancoobException {
		Comando comando = null;
		Connection conn = null;
		try {
			conn = estabelecerConexao();

			comando = getComando("FLEXIBILIZAR_ANOTACOES_FILIAIS");
			comando.adicionarVariavel("filiais", filiais);
			comando.adicionarVariavel("dataHoraAlteracao", dataAlteracao);
			comando.adicionarVariavel("usuarioAlteracao", usuarioAlteracao);
			comando.adicionarVariavel("flexibilizar", flexibilizar);
			comando.adicionarVariavel("idTipoAnotacao", tipoAnotacao.getId());
			comando.configurar();

			comando.executarAtualizacao(conn);
		} finally {
			fecharConexao(conn);
			fecharComando(comando);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ConsultaDto<Anotacao> obterAnotacoesVencidasPorGrupoTipoAnotacao(Date dataLimite, Short codigoGrupoTipoAnotacao, int paginaAtual, int tamanhoPagina)	throws BancoobException {
		Comando comando = getComando("OBTER_ANOTACOES_VENCIDAS_POR_GRUPO_TIPO_ANOTACAO");
		comando.adicionarVariavel("dataLimite", dataLimite);
		comando.adicionarVariavel("idGrupoTipoAnotacao", codigoGrupoTipoAnotacao);
		comando.configurar();

		String sql = comando.getSqlEmUso();
		if (comando.getProjecao() != null) {
			sql = comando.getProjecao() + " " + sql;
		}
		
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("parametro0", dataLimite);
		query.setParameter("parametro1", codigoGrupoTipoAnotacao);
		query.setFirstResult(paginaAtual * tamanhoPagina);
		query.setMaxResults(tamanhoPagina);
		
		@SuppressWarnings("unchecked")
		List<Anotacao> listaRetorno = query.getResultList();
		
		Long numeroRegistros = null;
		String sqlCount = comando.getSqlEmUso();
		sqlCount = "SELECT COUNT(*) " + sqlCount;
		
		Query queryCount = getEntityManager().createQuery(sqlCount);
		queryCount.setParameter("parametro0", dataLimite);
		queryCount.setParameter("parametro1", codigoGrupoTipoAnotacao);
		numeroRegistros = (Long) queryCount.getResultList().get(0);

		ConsultaDto<Anotacao> retorno = new ConsultaDto<Anotacao>();
		retorno.setTotalRegistros(Integer.valueOf(numeroRegistros.intValue()));
		retorno.setResultado(listaRetorno);
		
		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Anotacao> obterAnotacoesPorGrupoTipoAnotacao(Long idPessoaCompartilhamento, Short codigoGrupoTipoAnotacao) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("OBTER_ANOTACOES_POR_GRUPO_TIPO_ANOTACAO");
			comando.adicionarVariavel("idPessoaCompartilhamento", idPessoaCompartilhamento);
			comando.adicionarVariavel("idGrupoTipoAnotacao", codigoGrupoTipoAnotacao);
			comando.configurar();
			return criarQuery(comando).getResultList();
		} finally {
			fecharComando(comando);
		}
	}
}