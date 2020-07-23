package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.cadastro.negocio.vo.StatusTransferenciaGrupoEconomicoVO;
import br.com.sicoob.capes.cadastro.persistencia.dao.GrupoEconomicoDAO;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomico;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao;

/**
 * Classe que implementa a persistencia de Referencia.
 * 
 * @author Juan.Damasceno
 * 
 */
public class GrupoEconomicoDAOImpl extends
		CAPESCadastroCrudDao<GrupoEconomico> implements
		GrupoEconomicoDAO {

	/** A constante COMANDO_PESQUISAR_POR_FILTRO. */
	private static final String COMANDO_PESQUISAR_POR_FILTRO = "PESQUISAR_GRUPO_ECONOMICO_POR_FILTRO";
	
	/** A constante COMANDO_PESQUISAR_POR_PESSOA. */
	private static final String COMANDO_PESQUISAR_POR_PESSOA = "PESQUISA_GRUPO_ECONOMICO_POR_PESSOA";
	
	/** A constante COMANDO_VERIFICAR_NOME. */
	private static final String COMANDO_VERIFICAR_NOME = "VERIFICA_NOME_GRUPO_ECONOMICO";
	
	private static final String PESQUISAR_GRUPO_ECONOMICO_POR_INSTITUICAO = "PESQUISAR_GRUPO_ECONOMICO_POR_INSTITUICAO";

	/**
	 * Instancia um novo GrupoEconomicoDAOImpl.
	 */
	public GrupoEconomicoDAOImpl() {
		super(GrupoEconomico.class, COMANDO_PESQUISAR_POR_FILTRO);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<GrupoEconomico> listarPorPessoa(Pessoa pessoa) {
		String idInstituicaoStr = InformacoesUsuario.getInstance().getIdInstituicao();
		Integer idInstituicao = Integer.parseInt(idInstituicaoStr);
		
		PessoaInstituicao pessoaInstituicao = new PessoaInstituicao();
		pessoaInstituicao.setPessoa(pessoa);
		pessoaInstituicao.setIdInstituicao(idInstituicao);

		ConsultaDto<GrupoEconomico> consultaDto = new ConsultaDto<GrupoEconomico>();
		consultaDto.setFiltro(pessoaInstituicao);

		return pesquisarLista(GrupoEconomico.class, consultaDto, 
				COMANDO_PESQUISAR_POR_PESSOA);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean isGrupoEconomicoExistente(GrupoEconomico grupo) throws BancoobException {
		
		Long quantidadeExistente;
		Comando comando = getComando(COMANDO_VERIFICAR_NOME);
		try {
			comando.adicionarVariavel("grupo", grupo);
			comando.configurar();
			quantidadeExistente = (Long) criarQuery(comando).getSingleResult();
		} finally {
			fecharComando(comando);
		}
		return quantidadeExistente > 0L;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<StatusTransferenciaGrupoEconomicoVO> obterStatusTransferenciaGrupoEconomico(Integer idInstituicao) throws BancoobException {
		List<StatusTransferenciaGrupoEconomicoVO> retorno = new ArrayList<StatusTransferenciaGrupoEconomicoVO>();
		
		Comando comando = null;
		Connection conn = null;
		ResultSet rs = null;
		
		try {
			comando = getComando("OBTER_STATUS_GRUPO_ECONOMICO_TRANSFERENCIA_INFORMACAO");
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			comando.configurar();

			conn = estabelecerConexao();
			rs = comando.executarConsulta(conn);
			
			while (rs.next()) {
				StatusTransferenciaGrupoEconomicoVO vo = new StatusTransferenciaGrupoEconomicoVO();
				vo.setNomeGrupoEconomico(rs.getString("NOMEGRUPOECONOMICO"));
				vo.setQuantidade(rs.getLong("QUANTIDADE"));
				retorno.add(vo);
			}
			
		} catch (SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			fecharResultSet(rs);
			fecharConexao(conn);
			fecharComando(comando);
		}

		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<GrupoEconomico> gerarGruposEconomicos() throws BancoobException {
		List<GrupoEconomico> retorno = new ArrayList<GrupoEconomico>();
		
		return retorno;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<GrupoEconomico> obterListaGrupoEconomico(Integer idInstituicao) {
		Comando comando = null;
		try {
			comando = getComando(PESQUISAR_GRUPO_ECONOMICO_POR_INSTITUICAO);
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			comando.configurar();
			Query query = criarQuery(comando);
			return query.getResultList();
		} finally {
			fecharComando(comando);
		}
	}
	
}