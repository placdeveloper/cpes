/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.cadastro.negocio.vo.DadosAlteracaoGrupoVO;
import br.com.sicoob.capes.cadastro.negocio.vo.TransicaoPessoaVO;
import br.com.sicoob.capes.cadastro.persistencia.dao.TransicaoPessoaDAO;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.TransicaoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Dao para TransicaoPessoa.
 * 
 * @author juan.damasceno
 */
public class TransicaoPessoaDAOImpl extends CAPESCadastroCrudDao<TransicaoPessoa> 
	implements TransicaoPessoaDAO {

	/** A constante ID_INSTITUICAO. */
	private static final String ID_INSTITUICAO = "idInstituicao";
	
	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISAR_TRANSICAO_PESSOA";

	/**
	 * Construtor do DAO.
	 */
	public TransicaoPessoaDAOImpl() {
		super(TransicaoPessoa.class, NOME_COMANDO_PESQUISAR);
	}
	
	/**
	 * {@inheritDoc}
	 */	
	public List<TransicaoPessoa> listar(TransicaoPessoa transicao) {
		return pesquisarListaEntidade(TransicaoPessoa.class, transicao,	
				"CONSULTA_TRANSICAO_PESSOA_POR_FILTRO");
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TransicaoPessoaVO> listarTodasTransicoes(Pessoa pessoa) {
		
		Connection conx = null;
		ResultSet rset = null;
		Comando comando = null;
		List<TransicaoPessoaVO> lista = new ArrayList<TransicaoPessoaVO>();

		try {
			
			conx = estabelecerConexao();
			comando = getComando("LISTAR_TRANSICOES_PESSOA");
			comando.adicionarVariavel("idPessoa", pessoa.getIdPessoa());
			comando.configurar();
			
			rset = comando.executarConsulta(conx);
			TransicaoPessoaVO vo = null;

			while (rset.next()) {
				vo = new TransicaoPessoaVO();
				vo.setIdInstituicao(rset.getInt("IDINSTITUICAO"));
				vo.setIdUnidadeInst(rset.getInt("IDUNIDADEINST"));
				vo.setNomePessoaLegado(rset.getString("NOMEPESSOALEGADO"));
				vo.setIdPessoaLegado(rset.getInt("IDPESSOALEGADO"));
				lista.add(vo);
			}

		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		} finally {
			fecharResultSet(rset);
			fecharConexao(conx);
			fecharComando(comando);
		}

		return lista;
	}

	/**
	 * {@inheritDoc}
	 */
	public Boolean verificarInstituicao(Integer idInstituicao) {
		
		Connection conx = null;
		ResultSet rset = null;
		Comando comando = null;
		Boolean retorno = false;

		try {
			
			conx = estabelecerConexao();
			comando = getComando("CONSULTAR_INSTITUICAO_EM_TRANSICAO_PESSOA");
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.configurar();
			
			rset = comando.executarConsulta(conx);
			
			if(rset.next()){
				retorno = true;
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
	@SuppressWarnings("unchecked")
	public List<TransicaoPessoa> listar(ConsultaDto<TransicaoPessoa> criterios) {

		List<TransicaoPessoa> lista;
		Comando comando = getComando(NOME_COMANDO_PESQUISAR);
		
		try {
			
			comando.adicionarVariavel("criterios", criterios);
			comando.configurar();
			
			Query query = comando.criarQuery(getEntityManager());
			query.setFlushMode(FlushModeType.COMMIT);
			lista = query.getResultList();
		} finally {
			fecharComando(comando);
		}
		
		return lista;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TransicaoPessoa obterTransicaoPorPessoaInstituicao(Integer idPessoa,
			Integer idInstituicao) throws BancoobException {
	
		Comando comando = getComando("OBTER_TRANSICAO_POR_PESSOA_INSTITUICAO");
		TransicaoPessoa retorno;
		try {
			comando.adicionarVariavel("idPessoa", idPessoa);
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.configurar();
			
			Query query = comando.criarQuery(getEntityManager());
			retorno = (TransicaoPessoa) query.getSingleResult();
		} catch (NoResultException e) {
			retorno = null;
		} finally {
			fecharComando(comando);
		}
		return retorno;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TransicaoPessoa obterTransicaoPorPessoaCompartilhamentoInstituicao(Long idPessoaCompartilhamento, Integer idInstituicao) throws BancoobException {
		Comando comando = getComando("OBTER_TRANSICAO_POR_PESSOACOMPARTILHAMENTO_INSTITUICAO");
		TransicaoPessoa retorno;
		try {
			comando.adicionarVariavel("idPessoaCompartilhamento", idPessoaCompartilhamento);
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.configurar();

			Query query = comando.criarQuery(getEntityManager());
			retorno = (TransicaoPessoa) query.getSingleResult();
		} catch (NoResultException e) {
			retorno = null;
		} finally {
			fecharComando(comando);
		}
		return retorno;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<TransicaoPessoa> listarTransicoesParaReplicacao(PessoaCompartilhamento pessoaCompartilhamento) {

		List<TransicaoPessoa> lista = new ArrayList<TransicaoPessoa>();
		Comando comando = null;
		Connection conx = null;		
		ResultSet rset = null;
		
		try {
			
			conx = estabelecerConexao();
			comando = getComando("LISTAR_TRANSICOES_PARA_REPLICACAO");
			comando.adicionarVariavel("idPessoaCompartilhamento", pessoaCompartilhamento.getIdPessoaCompartilhamento());
			comando.configurar();
			
			rset = comando.executarConsulta(conx);
			TransicaoPessoa transicao = null;
			Instituicao instituicao = null;

			while (rset.next()) {
				instituicao = new Instituicao();
				instituicao.setIdInstituicao(rset.getInt("IDINSTITUICAO"));
				instituicao.setIdUnidadeInst(rset.getInt("IDUNIDADEINST"));
				
				transicao = new TransicaoPessoa();
				transicao.setInstituicao(instituicao);
				transicao.setIdTransicaoPessoa(rset.getInt("IDTRANSICAOPESSOA"));
				transicao.setNomePessoaLegado(rset.getString("NOMEPESSOALEGADO"));
				transicao.setIdPessoaLegado(rset.getInt("IDPESSOALEGADO"));
				transicao.setDataHoraIntegracao(rset.getDate("DATAHORAINTEGRACAO"));
				lista.add(transicao);
			}

		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		} finally {
			fecharResultSet(rset);
			fecharConexao(conx);
			fecharComando(comando);
		}		
		
		return lista;
	}	
	
	/**
	 * {@inheritDoc}
	 */
	public Boolean isIncorporacaoFinalizada(Integer idInsituicaoIncorporadora,
			Integer idInsituicaoIncorporada) throws BancoobException {
		
		Comando comando = null;
		Connection conn = null;		
		ResultSet rs = null;
		try {
			Integer count = null;
			conn = estabelecerConexao();
			comando = getComando("VERIFICAR_FIM_INCORPORACAO");
			comando.adicionarVariavel("idInsituicaoIncorporadora", idInsituicaoIncorporadora);
			comando.adicionarVariavel("idInsituicaoIncorporada", idInsituicaoIncorporada);
			comando.configurar();
			rs = comando.executarConsulta(conn);
			if (rs.next()) {
				count = rs.getInt(1);
			}
			return (count != null ? Boolean.valueOf(count == 0) : Boolean.TRUE);
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
	public Integer buscarQuantidadePessoasInstituicao(Integer idInstituicao) throws BancoobException {
		
		Comando comando = null;
		try {
			
			comando = getComando("BUSCAR_QTD_PESSOAS_INSTITUICAO");
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.configurar();
			Query query = comando.criarQuery(getEntityManager());
			Long qtd = (Long) query.getSingleResult();
			return qtd.intValue();
		} finally {
			fecharComando(comando);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Map<String, Integer> buscarQuantidadesPessoasAlteracaoGrupo(
			Integer idInstituicao, Short codigoCompartilhamento)
			throws BancoobException {
	
		Map<String, Integer>  quantidades = new HashMap<String, Integer>();
		Comando comando = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = estabelecerConexao();
			comando = getComando("BUSCAR_QTDS_PESSOAS_ALTERACAO_GRUPO");
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.adicionarVariavel("codigoCompartilhamento", codigoCompartilhamento);
			comando.configurar();
			rs = comando.executarConsulta(conn);
			while (rs.next()) {
				quantidades.put(rs.getString("TIPO"), rs.getInt("QTD"));
			}
			return quantidades;
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
	@SuppressWarnings("unchecked")
	public List<TransicaoPessoa> buscarPessoasExistentesGrupo(Integer idInstituicao,
			Short codigoCompartilhamento) throws BancoobException {
		
		Comando comando = null;
		try {
			comando = getComando("BUSCAR_PESSOAS_EXISTENTES_GRUPO");
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.adicionarVariavel("codigoCompartilhamento", codigoCompartilhamento);
			comando.configurar();
			return comando.criarQuery(getEntityManager()).getResultList();
		} finally {
			fecharComando(comando);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public ConsultaDto<DadosAlteracaoGrupoVO> buscarDadosAlteracaoGrupoPessoasNoDestino(
			ConsultaDto<DadosAlteracaoGrupoVO> criterios) throws BancoobException {
		
		return pesquisar(DadosAlteracaoGrupoVO.class, criterios, 
				"BUSCAR_DADOS_ALTERACAO_GRUPO_PESSOAS_NO_DESTINO");
	}

}