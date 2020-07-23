package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.cadastro.negocio.vo.MonitoracaoMensagensVO;
import br.com.sicoob.capes.cadastro.persistencia.dao.MensagemReplicacaoDAO;
import br.com.sicoob.capes.cadastro.util.CAPESCadastroConstantes.Persistencia;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.negocio.entidades.MensagemReplicacao;

/**
 * A Classe MensagemReplicacaoDAOImpl.
 */
public class MensagemReplicacaoDAOImpl extends
		CAPESCadastroCrudDao<MensagemReplicacao> implements MensagemReplicacaoDAO {

	/** A constante RECUPERAR_MENSAGENS_NAO_PROCESSADAS_POR_FILTRO. */
	private static final String RECUPERAR_MENSAGENS_NAO_PROCESSADAS_POR_FILTRO = "RECUPERAR_MENSAGENS_NAO_PROCESSADAS_POR_FILTRO";
	
	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISAR_MENSAGEM_REPLICACAO_POR_FILTRO";

	/**
	 * Instancia um novo MensagemReplicacaoDAOImpl.
	 */
	public MensagemReplicacaoDAOImpl() {
		super(Constantes.Persistencia.DATASOURCE_CAPES,
		        Persistencia.ARQUIVO_QUERIES_MENSAGEM_REPLICACAO,
		        Persistencia.COMANDOS_CADASTRO_UNICO_CLIENTE_MENSAGEM_REPLICACAO,
		        MensagemReplicacao.class, NOME_COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MensagemReplicacao incluir(MensagemReplicacao mensagem) throws BancoobException {
		
		Connection conn = null;
		Comando comando = null;
		try {
			conn = estabelecerConexao();
			comando = getComando("INSERIR_MENSAGEM_REPLICACAO");
			
			comando.adicionarVariavel("mensagem", mensagem);
			comando.configurar();
			
//			FIXME rodrigo.chaves: tornar a inclusão de MensagemReplicacao transacional
//			Query query = getEntityManager().createNativeQuery(comando.getSqlEmUso());
//			query.setParameter(1, mensagem.getIdentificadorOperacao());
//			query.setParameter(2, mensagem.getIdInstituicao());
//			query.setParameter(3, mensagem.getIdPessoaLegado());
//			query.setParameter(4, mensagem.getTipoOperacao());
//			query.setParameter(5, mensagem.getConteudoMensagem());
//			query.setParameter(6, mensagem.getDataCadastro());
//			query.setParameter(7, mensagem.getDataEnvio());
//			query.setParameter(8, mensagem.getDataProcessamento());
//			query.setParameter(9, mensagem.getErro());
//			query.setParameter(10, mensagem.getEntidadeCadastro());
//			query.setParameter(11, mensagem.getIdRegistro());
//			query.executeUpdate();
			getLogger().debug(comando.getSqlEmUso());
			
			comando.executarAtualizacao(conn);
		}finally {
			fecharComando(comando);
			fecharConexao(conn);
		}
		return mensagem;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void atualizarDatasEnvio(MensagemReplicacao mensagem) throws BancoobException {
		
		Connection conn = null;
		Comando comando = null;
		try {
			conn = estabelecerConexao();
			comando = getComando("ATUALIZAR_DATAS_ENVIO_MENSAGEM_REPLICACAO");
			
			comando.adicionarVariavel("mensagem", mensagem);
			comando.configurar();
			getLogger().debug(comando.getSqlEmUso());
			getLogger().debug("Parâmetros: identificadorOperacao=",
					mensagem.getIdentificadorOperacao(), ", idInstituicao=",
					String.valueOf(mensagem.getIdInstituicao()), ", dataEnvio=",
					String.valueOf(mensagem.getDataEnvio()));
			
			comando.executarAtualizacao(conn);
		} finally {
			fecharComando(comando);
			fecharConexao(conn);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void atualizarDataProcessamentoErro(MensagemReplicacao mensagem)
			throws BancoobException {
		
		Connection conn = null;
		Comando comando = null;
		try {
			conn = estabelecerConexao();
			comando = getComando("ATUALIZAR_DATA_PROCESSAMENTO_MENSAGEM_REPLICACAO");
			
			comando.adicionarVariavel("mensagem", mensagem);
			comando.configurar();
			getLogger().debug(comando.getSqlEmUso());
			getLogger().debug("Parâmetros: idMensagemReplicacao=",
					String.valueOf(mensagem.getIdMensagemReplicacao()), ", dataProcessamento=",
					String.valueOf(mensagem.getDataProcessamento()), ", erro=", mensagem.getErro());
			
			comando.executarAtualizacao(conn);
		} finally {
			fecharComando(comando);
			fecharConexao(conn);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ConsultaDto<MensagemReplicacao> pesquisarMensagensNaoEnviadasPorFiltro(
			ConsultaDto<MensagemReplicacao> criterios) throws BancoobException {
		return pesquisar(MensagemReplicacao.class, criterios,
				"PESQUISAR_MENSAGEM_REPLICACAO_NAO_ENVIADA_POR_FILTRO");
	}

	/**
	 * {@inheritDoc}
	 */
	public List<MensagemReplicacao> listarMensagensNaoProcessadasPorFiltro(
			MensagemReplicacao filtro) throws BancoobException {
		
		return listarMensagensNaoProcessadasPorFiltro(filtro, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<MensagemReplicacao> listarMensagensNaoProcessadasPorFiltro(
			MensagemReplicacao filtro, Boolean incluiMsgComErro)
			throws BancoobException {
		
		List<MensagemReplicacao> mensagens = null;
		Comando comando = getComando(RECUPERAR_MENSAGENS_NAO_PROCESSADAS_POR_FILTRO);
		try {
			comando.adicionarVariavel("filtro", filtro);
			comando.adicionarVariavel("incluiMsgComErro", incluiMsgComErro);
			comando.configurar();
			Query query = comando.criarQuery(getEntityManager());
			mensagens = query.getResultList();
		} finally {
			fecharComando(comando);
		}
		return mensagens;
	}

	/**
	 * {@inheritDoc}
	 */
	public void expurgarMensagensReplicacao() throws BancoobException {

		Connection conx = null;
		Comando comando = getComando("EXPURGO_MENSAGENS_REPLICACAO");
		
		try {
			
			conx = estabelecerConexao();
			comando.configurar();
			comando.executarAtualizacao(conx);
			
		} finally {
			fecharComando(comando);
			fecharConexao(conx);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void expurgarMensagensReplicacaoComErro() throws BancoobException {

		Connection conx = null;
		Comando comando = getComando("EXPURGO_MENSAGENS_REPLICACAO_COM_ERRO");
		
		try {
			
			conx = estabelecerConexao();
			comando.configurar();
			comando.executarAtualizacao(conx);
			
		} finally {
			fecharComando(comando);
			fecharConexao(conx);
		}
	}	
	
	/**
	 * {@inheritDoc}
	 */
	public MonitoracaoMensagensVO monitorarMensagensNaoEnviadas() throws BancoobException {

		Connection conx = null;
		ResultSet rs = null;
		Comando comando = null;
		MonitoracaoMensagensVO vo = null;
		try {
			conx = estabelecerConexao();
			comando = getComando("CONTAGEM_MENSAGENS_NAO_ENVIADAS");
			comando.configurar();
			
			rs = comando.executarConsulta(conx);
			if (rs.next()) {
				vo = new MonitoracaoMensagensVO();
				vo.setDataPrimeira(rs.getTimestamp("DATAPRIMEIRA"));
				vo.setDataUltima(rs.getTimestamp("DATAULTIMA"));
				vo.setQuantidadeMensagens(rs.getInt("QUANTIDADE"));
			}
			return vo;
		} catch (SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			fecharResultSet(rs);
			fecharConexao(conx);
			fecharComando(comando);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public MonitoracaoMensagensVO monitorarMensagensNaoProcessadas() throws BancoobException {
		
		Connection conx = null;
		ResultSet rs = null;
		Comando comando = null;
		MonitoracaoMensagensVO vo = null;
		try {
			conx = estabelecerConexao();
			comando = getComando("CONTAGEM_MENSAGENS_NAO_PROCESSADAS");              
			comando.configurar();
			
			rs = comando.executarConsulta(conx);
			if (rs.next()) {
				vo = new MonitoracaoMensagensVO();
				vo.setDataPrimeira(rs.getTimestamp("DATAPRIMEIRA"));
				vo.setDataUltima(rs.getTimestamp("DATAULTIMA"));
				vo.setQuantidadeMensagens(rs.getInt("QUANTIDADE"));
			}
			return vo;
		} catch (SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			fecharResultSet(rs);
			fecharConexao(conx);
			fecharComando(comando);
		}
	}
	
}