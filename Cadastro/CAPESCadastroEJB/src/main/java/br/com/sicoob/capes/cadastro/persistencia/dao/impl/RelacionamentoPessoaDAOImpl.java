/* 
 * Sicoob
 * RelacionamentoPessoaDAOImpl.java 
 * Criado em: 24/08/2011
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaDtoCUC;
import br.com.sicoob.capes.cadastro.persistencia.dao.RelacionamentoPessoaDAO;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.RelacionamentoPessoaBase;
import br.com.sicoob.capes.negocio.entidades.TipoRelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoRelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;

/**
 * Implementação da DAO de {@link RelacionamentoPessoa}
 *
 * 24/08/2011
 * @author Rodrigo.Chaves
 */
public class RelacionamentoPessoaDAOImpl extends EntidadeCadastroDao<RelacionamentoPessoa>
		implements RelacionamentoPessoaDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISAR_RELACIONAMENTOS_CEDIDOS";
	
	/** A constante NOME_COMANDO_PESQUISAR_VIGENTE. */
	private static final String NOME_COMANDO_PESQUISAR_VIGENTE = "PESQUISAR_RELACIONAMENTOS_VIGENTES_FILTRO";

	/**
	 * Instancia um novo RelacionamentoPessoaDAOImpl.
	 */
	public RelacionamentoPessoaDAOImpl() {
		super(RelacionamentoPessoa.class, NOME_COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	public ConsultaDto<RelacionamentoPessoa> pesquisarRelacionamentosExercidos(
			ConsultaDto<RelacionamentoPessoa> criterios) {
		return pesquisar(RelacionamentoPessoa.class, criterios,
				"PESQUISAR_RELACIONAMENTOS_EXERCIDOS");
	}

	/**
	 * {@inheritDoc}
	 */
	public RelacionamentoPessoa obterRelacionamentoReverso(RelacionamentoPessoa relacionamento) {
		return pesquisarEntidade(RelacionamentoPessoa.class, relacionamento,
				"PESQUISAR_RELACIONAMENTO_REVERSO");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<RelacionamentoPessoa> pesquisarRelacionamentosSemelhantes(Pessoa pessoa,
			Pessoa pessoaRelacionada, TipoRelacionamentoPessoa tipoRelacionamento,
			Integer idInstituicao) {
		
		Comando comando = null;
		try {
			comando = getComando("PESQUISAR_RELACIONAMENTOS_SEMELHANTES");
			comando.adicionarVariavel("pessoa", pessoa);
			comando.adicionarVariavel("pessoaRelacionada", pessoaRelacionada);
			comando.adicionarVariavel("tipoRelacionamento", tipoRelacionamento);
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			comando.configurar();
			return criarQuery(comando).getResultList();
		} finally {
			fecharComando(comando);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public ConsultaDto<RelacionamentoPessoa> recuperarRelacionamentosIncorporacao(
			ConsultaDto<RelacionamentoPessoa> criterios)
			throws BancoobException {

		return pesquisar(RelacionamentoPessoa.class, criterios,
				"RECUPERAR_RELACIONAMENTOS_INCORPORACAO");
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<RelacionamentoPessoa> pesquisarRelacionamentosVigentesPorFiltro(RelacionamentoPessoa relacionamentoPessoa) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando(NOME_COMANDO_PESQUISAR_VIGENTE);
			comando.adicionarVariavel("idPessoa", relacionamentoPessoa.getPessoa() != null ? relacionamentoPessoa.getPessoa().getIdPessoa() : null);
			comando.adicionarVariavel("idInstituicao", relacionamentoPessoa.getIdInstituicao() != null ? relacionamentoPessoa.getIdInstituicao() : null);
			comando.adicionarVariavel("idTipoRelacionamento", relacionamentoPessoa.getTipoRelacionamento() != null ? relacionamentoPessoa.getTipoRelacionamento().getId() : null);
			comando.adicionarVariavel("idPessoaRelacionada", relacionamentoPessoa.getPessoaRelacionada() != null ? relacionamentoPessoa.getPessoaRelacionada().getId() : null);
			comando.configurar();
			return criarQuery(comando).getResultList();
		} finally {
			fecharComando(comando);
		}
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	public void atualizarRelacionamentoReverso(Long idRelacionamento, 
			Long idRelacionamentoReverso) throws BancoobException {

		Comando comando = null;
		Connection conn = null;
		try {
			comando = getComando("ATUALIZAR_RELACIONAMENTO_REVERSO");
			comando.adicionarVariavel("idRelacionamento", idRelacionamento);
			comando.adicionarVariavel("idRelacionamentoReverso", idRelacionamentoReverso);
			comando.configurar();

			conn = estabelecerConexao();
			
			getLogger().debug("\n",comando.getSqlEmUso(), "\nParametros: ",
					comando.getParametros().toString());
			
			comando.executarAtualizacao(conn);
		} finally {
			fecharConexao(conn);
			fecharComando(comando);
		}
	}

	public List<RelacionamentoPessoaBase> listarHistoricoEspecifico(
			ConsultaDtoCUC<RelacionamentoPessoa> criterios) throws BancoobException {		
		
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		List<RelacionamentoPessoaBase> retorno = new ArrayList<RelacionamentoPessoaBase>();

		try {
			conexao = estabelecerConexao();
			comando = getComando("PESQUISAR_HISTORICO_RELACIONAMENTOS_PESSOA_ESPECIFICO");
			comando.adicionarVariavel("criterios", criterios);			
			comando.configurar();

			rs = comando.executarConsulta(conexao);
			while(rs.next()){
				HistoricoRelacionamentoPessoa hist = new HistoricoRelacionamentoPessoa();
				hist.setId(rs.getLong("IDHISTRELACIONAMENTOPESSOA"));
				hist.setIdRelacionamento(rs.getLong("IDRELACIONAMENTOPESSOA"));
				
				Set<PessoaCompartilhamento> listaCompartilhamentos = new HashSet<PessoaCompartilhamento>();
				PessoaFisica pf = new PessoaFisica();
				pf.setNomePessoa(rs.getString("NOMEPESSOARELACIONADA"));
				listaCompartilhamentos.add(pf);
								
				Pessoa pessoa = new Pessoa();
				pessoa.setId(rs.getInt("IDPESSOA"));
				pessoa.setCpfCnpj(rs.getString("CPFCNPJPESSOA"));
				
				Pessoa pessoaRelacionada = new Pessoa();
				pessoaRelacionada.setId(rs.getInt("IDPESSOARELACIONADA"));
				pessoaRelacionada.setCpfCnpj(rs.getString("CPFCNPJPESSOARELACIONADA"));
				pessoaRelacionada.setCompartilhamentos(listaCompartilhamentos);
				
				hist.setPessoa(pessoa);
				hist.setPessoaRelacionada(pessoaRelacionada);
				
				hist.setDataHoraInicio(new DateTimeDB(rs.getTimestamp("DATAHORAINICIO").getTime()));
				hist.setDataHoraFim(new DateTimeDB(rs.getTimestamp("DATAHORAFIM").getTime()));
				
				TipoRelacionamentoPessoa tipoRel = new TipoRelacionamentoPessoa();
				tipoRel.setCodigo(rs.getShort("CODTIPORELACIONAMENTOPESSOA"));
				tipoRel.setDescricao(rs.getString("DESCTIPORELACIONAMENTOPESSOA"));
				
				hist.setTipoRelacionamento(tipoRel);
				hist.setPercentualCapitalEmpresa(rs.getBigDecimal("PERCCAPITALEMPRESA"));
				hist.setDataInicioRelacionamento(rs.getTimestamp("DATAINICIORELACIONAMENTO") != null ? new DateTimeDB(rs.getTimestamp("DATAINICIORELACIONAMENTO").getTime()) : null);
				hist.setDataFimMandato(rs.getTimestamp("DATAFIMMANDATO") != null ?  new DateTimeDB(rs.getTimestamp("DATAFIMMANDATO").getTime()) : null);
				hist.setIdInstituicao(rs.getInt("IDINSTITUICAO"));
				hist.setIdUsuarioAprovacao(rs.getString("IDUSUARIOAPROV"));
				hist.setIdUsuarioEnvio(rs.getString("IDUSUARIOENVIO"));
				hist.setIdUsuarioExclusao(rs.getString("IDUSUARIOEXCL"));
				hist.setDataInicioMandato(rs.getTimestamp("DATAINICIOMANDATO") != null ? new DateTimeDB(rs.getTimestamp("DATAINICIOMANDATO").getTime()) : null);
				
				RelacionamentoPessoaBase base = (RelacionamentoPessoaBase)hist;
				
				retorno.add(base);
			}
			
		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		} finally {
			fecharResultSet(rs);
			fecharConexao(conexao);
			fecharComando(comando);
		}
		return retorno;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(RelacionamentoPessoa objeto) throws BancoobException {
		try {
			getEntityManager().merge(objeto);
			getEntityManager().flush();
		} catch (IllegalArgumentException e) {
			throw new BancoobException("msg.erro.alterar.nao.existe", e);
		}
	}

}