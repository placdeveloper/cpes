/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.hibernate.exception.ConstraintViolationException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaPessoaDTO;
import br.com.sicoob.capes.cadastro.negocio.excecao.ClienteNaoEncontradoException;
import br.com.sicoob.capes.cadastro.persistencia.dao.PessoaCompartilhamentoDAO;
import br.com.sicoob.capes.comum.negocio.vo.PessoaPlataformaVO;
import br.com.sicoob.capes.comum.negocio.vo.ProcurarPessoaExternoVO;
import br.com.sicoob.capes.negocio.entidades.CompartilhamentoCadastro;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.TransicaoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaJuridica;

/**
 * @author erico.junior
 *
 */
public class PessoaCompartilhamentoDAOImpl extends EntidadeCadastroDao<PessoaCompartilhamento> 
	implements PessoaCompartilhamentoDAO {

	/** A constante PATTERN_LOG_ERRO_INCLUSAO. */
	private static final String PATTERN_LOG_ERRO_INCLUSAO = "Falha na inclusao de {0}: cpfCnpj#{1},"
	        + " idPessoa#{2,number,#}, idPessoaCompartilhamento#{3,number,#},"
	        + " codCompartilhamentoCadastro#{4,number,#}";
	
	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISAR_PESSOA";

	/** A constante NOME_COMANDO_PESQUISAR_PROXY. */
	private static final String NOME_COMANDO_PESQUISAR_PROXY = "PESQUISAR_PESSOA_PROXY";
	

	/** A constante NOME_COMANDO_PESQUISAR_PROXY. */
	private static final String NOME_COMANDO_PESQUISAR_PROXY_RESUMIDO = "PESQUISAR_PESSOA_PROXY_RESUMIDO";
	
	/** A constante NOME_COMANDO_PESQUISAR_NUMERO_REGISTROS. */
	private static final String NOME_COMANDO_PESQUISAR_NUMERO_REGISTROS = 
			"PESQUISAR_PESSOA_NUMERO_REGISTROS";
	
	/** A constante CONSULTA_PESSOA_POR_DOCUMENTO. */
	private static final String CONSULTA_PESSOA_POR_DOCUMENTO = "CONSULTA_PESSOA_COMPARTILHADA_POR_DOCUMENTO";
	
	/** A constante CONSULTA_PESSOA_POR_COMPARTILHAMENTO_CADASTRO. */
	private static final String CONSULTA_PESSOA_POR_COMPARTILHAMENTO_CADASTRO = 
			"CONSULTAR_PESSOA_COMPARTILHAMENTO_POR_PESSOA_COMPARTILHAMENTO_CADASTRO";
	
	/** A constante COMANDO_COMPONENTE_PROCURAR_PESSOA_EXTERNO. */
	private static final String COMANDO_COMPONENTE_PROCURAR_PESSOA_EXTERNO = "PROCURAR_PESSOA_EXTERNO_CAPES";
	
	/** A constante PESQUISA_CODCOMPARTILHAMENTO_GRUPOCOMAPRTILHAMENTO. */
	private static final String PESQUISA_CODCOMPARTILHAMENTO_GRUPOCOMAPRTILHAMENTO = "PESQUISA_CODCOMPARTILHAMENTO_GRUPOCOMAPRTILHAMENTO";
	
	/** A constante NOME_COMANDO_CONSULTAR_IDDB2. */
	private static final String ATUALIZAR_COD_TIPO_EMPRESA = "ATUALIZA_COD_TIPO_EMPRESA_POR_ID_PESSOA";

	/**
	 * Construtor do DAO.
	 */	
	public PessoaCompartilhamentoDAOImpl() {
		super(PessoaCompartilhamento.class, NOME_COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(PessoaCompartilhamento objeto) throws BancoobException {
		super.alterar(objeto);
		getEntityManager().flush();
	}

	/**
	 * {@inheritDoc}
	 */	
	@Override
	public PessoaCompartilhamento consultarPessoa(TransicaoPessoa transicao) {

		PessoaCompartilhamento pessoa = null;
		Comando comando = getComando("CONSULTA_TRANSICAO_PESSOA_COMPARTILHAMENTO");
		comando.adicionarVariavel("criterios", transicao);
		comando.configurar();

		try {
			Query query = comando.criarQuery(getEntityManager());
			pessoa = (PessoaCompartilhamento) query.getSingleResult();
		} catch (NoResultException e) {
			throw new ClienteNaoEncontradoException(e);
		} catch (PersistenceException e) {
			throw new PersistenciaException(e);
		} finally {
			fecharComando(comando);
		}

		return pessoa;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer pesquisarCodigoCompartilhamento(Instituicao instituicao){
		
		Integer codCompartilhamento = null;
		
		Comando comando = getComando(PESQUISA_CODCOMPARTILHAMENTO_GRUPOCOMAPRTILHAMENTO);
		comando.adicionarVariavel("idInstituicao",instituicao.getIdInstituicao());
		comando.configurar();
		
		try{
			Query query = comando.criarNativeQuery(getEntityManager());
			codCompartilhamento = Integer.parseInt(query.getResultList().get(0).toString());
			
		} catch (NoResultException e) {
			throw new ClienteNaoEncontradoException(e);
		} catch (PersistenceException e) {
			throw new PersistenciaException(e);
		} finally {
			fecharComando(comando);
		}
		
		return codCompartilhamento;
		
	}

	/**
	 * Executa a consulta de pessoa a partir do documento e do comando
	 * informado.
	 * 
	 * @param documento O documento que pode ser cpf ou cnpj.
	 * @return A pessoa encontrada.
	 */
	@Override
	public PessoaCompartilhamento consultarPessoaPorDocumento(Short codCompartilhamentoCadastro, String documento) {

		PessoaCompartilhamento pessoa = null;
		Comando comando = getComando(CONSULTA_PESSOA_POR_DOCUMENTO);
		comando.adicionarVariavel("documento", documento);
		comando.adicionarVariavel("codCompartilhamentoCadastro", codCompartilhamentoCadastro);
		comando.configurar();

		try {
			Query query = comando.criarQuery(getEntityManager());
			pessoa = (PessoaCompartilhamento) query.getSingleResult();
 		} catch (NoResultException e) {
			throw new ClienteNaoEncontradoException(e);
		} finally {
			fecharComando(comando);
		}

		return pessoa;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<PessoaCompartilhamento> listarPessoasMesmoDocumento(String cpfCnpj) throws BancoobException {
	
		List<PessoaCompartilhamento> pessoas = null;
		Comando comando = getComando(CONSULTA_PESSOA_POR_DOCUMENTO);
		comando.adicionarVariavel("documento", cpfCnpj);
		comando.configurar();

		try {
			EntityManager manager = getEntityManager();
			desativarFiltroSistemaCooperativo(manager);
			
			Query query = comando.criarQuery(manager);
			pessoas = query.getResultList();
 		} catch (NoResultException e) {
			throw new ClienteNaoEncontradoException(e);
		} finally {
			fecharComando(comando);
		}

		return pessoas;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PessoaCompartilhamento consultar(Pessoa pessoa, CompartilhamentoCadastro compartilhamentoCadastro) {

		PessoaCompartilhamento retorno = null;
		Comando comando = getComando(CONSULTA_PESSOA_POR_COMPARTILHAMENTO_CADASTRO);
		comando.adicionarVariavel("idPessoa", pessoa.getIdPessoa());
		comando.adicionarVariavel("codCompartilhamento", compartilhamentoCadastro.getCodigo());
		comando.configurar();

		try {
			EntityManager manager = getEntityManager();
			desativarFiltroSistemaCooperativo(manager);
			Query query = comando.criarQuery(manager);
			retorno = (PessoaCompartilhamento) query.getSingleResult();
		} catch (NoResultException e) {
			retorno = null;
		} catch (PersistenceException e) {
			throw new PersistenciaException(e);
		} finally {
			fecharComando(comando);
		}
		
		
		return retorno;
	}
	
	
	
	/**
	 * {@inheritDoc}
	 */
	public List<PessoaCompartilhamento> listarPorNomePessoaInstituicao(String nome, Instituicao instituicao) {

		ConsultaDto<Instituicao> criterios = new ConsultaDto<Instituicao>();
		criterios.setFiltro(instituicao);
		criterios.setProcurarPor(nome);

		return pesquisarLista(PessoaCompartilhamento.class, criterios, 
				"LISTAR_PESSOA_POR_NOME_PESSOA_INSTITUICAO");
	}
	
	/**
	 * Este metodo foi sobrescrito por causa da necessidade de se ter um novo comando para consultar
	 * o numero de registros da pesquisa, devido ao join com a tabela de transicao.
	 */
	@Override
	public ConsultaDto<PessoaCompartilhamento> pesquisar(ConsultaDto<PessoaCompartilhamento> criterios) throws BancoobException {
		return pesquisar(PessoaCompartilhamento.class, criterios, NOME_COMANDO_PESQUISAR, 
				NOME_COMANDO_PESQUISAR_NUMERO_REGISTROS);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConsultaDto<PessoaPlataformaVO> pesquisarPessoaProxy(ConsultaDto<PessoaPlataformaVO> criterios)
			throws BancoobException {
		return pesquisar(PessoaPlataformaVO.class, criterios, NOME_COMANDO_PESQUISAR_PROXY);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConsultaDto<PessoaPlataformaVO> pesquisarPessoaProxyResumido(ConsultaDto<PessoaPlataformaVO> criterios)
			throws BancoobException {
		
		List<PessoaPlataformaVO> lista = new ArrayList<PessoaPlataformaVO>();
		Comando comando = null;
		Connection conx = null;		
		ResultSet rset = null;
		Integer idInstituicao = ((PessoaPlataformaVO) criterios.getFiltro()).getIdInstituicao();
		Short codCompartilhamentoCadastro = ((PessoaPlataformaVO) criterios.getFiltro()).getCodCompartilhamentoCadastro();
		StringBuilder procuraPor = new StringBuilder();
		procuraPor.append(criterios.getProcurarPor());
		
		try {
			conx = estabelecerConexao();
			comando = getComando(NOME_COMANDO_PESQUISAR_PROXY_RESUMIDO);
			if(!criterios.getTipoProcura().equals("POR CPF/CNPJ"))
			{
				procuraPor.append('%');
			}
			criterios.setProcurarPor(procuraPor.toString());
			comando.adicionarVariavel("criterios", criterios);
			comando.configurar();
			rset = comando.executarConsulta(conx);
			PessoaPlataformaVO pessoaPlataforma = null;

			while (rset.next()) {
				pessoaPlataforma = new PessoaPlataformaVO();
				pessoaPlataforma.setCpfCnpj(rset.getString("NUMCPFCNPJ"));
				pessoaPlataforma.setIdInstituicao(idInstituicao);
				pessoaPlataforma.setIdPessoa(rset.getInt("IDPESSOA"));
				pessoaPlataforma.setNomeApelido(rset.getString("NOMEAPELIDO"));
				pessoaPlataforma.setNomeCompleto(rset.getString("NOMECOMPLETO"));
				pessoaPlataforma.setNomePessoa(rset.getString("NOMEPESSOA"));
				pessoaPlataforma.setIdCompartilhamento(rset.getLong("IDPESSOACOMPARTILHAMENTO"));
				pessoaPlataforma.setCodTipoPessoa(rset.getShort("CODTIPOPESSOA"));
				pessoaPlataforma.setCodCompartilhamentoCadastro(codCompartilhamentoCadastro);
				pessoaPlataforma.setUtilizaGedGft(rset.getBoolean("BOLUTILIZAGEDGFT"));
				pessoaPlataforma.setIdPessoaLegado(rset.getInt("IDPESSOALEGADO"));
				pessoaPlataforma.setIdUnidadeInst(rset.getInt("IDUNIDADEINST"));
				pessoaPlataforma.setIdPessoaInstituicao(rset.getInt("IDPESSOAINSTITUICAO"));
				
				lista.add(pessoaPlataforma);
			}
			criterios.setTotalRegistros(lista.size());
			criterios.setResultado(lista);
		} catch (SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			fecharResultSet(rset);
			fecharConexao(conx);
			fecharComando(comando);
		}	
		return criterios;	
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, Object>  obterDadosPessoaLegado(String cpfCnpj, Integer idInstituicao) throws BancoobException {
		
		Comando comando = null;
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			comando = getComando("OBTER_DADOS_PESSOA_LEGADO_PLATAFORMA");
			
			Query query = getEntityManager().createNativeQuery(comando.getSql());
			query.setParameter(1, cpfCnpj);
			query.setParameter(2, idInstituicao);
			
			Object[] resultList = (Object[]) query.getSingleResult();
			if (resultList != null) {
				result.put("NUMPESSOA", resultList[0]);
				result.put("SNUMPESSOA", resultList[1]);
				result.put("DESCNOMEPESSOA", resultList[2]);
				result.put("NUMCGCCPFNUM", resultList[3]);
				result.put("NUMCGC_CPF", resultList[4]);
				result.put("CODSITUACAOCLIENTE", resultList[5]);
				result.put("NUMTELEFONE", resultList[6]);
				result.put("CODPF_PJ", resultList[7]);
			}
		} finally {
			fecharComando(comando);
		}
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConsultaDto<PessoaCompartilhamento> pesquisarParaIncorporacao(ConsultaDto<PessoaCompartilhamento> criterios)
			throws BancoobException {
		return pesquisar(PessoaCompartilhamento.class, criterios, "PESQUISAR_PESSOAS_PARA_INCORPORACAO");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date obterDataUltimaAtualizacao(Long idPessoaCompartilhamento)
			throws BancoobException {

		Date data = null;
		Comando comando = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			comando = getComando("OBTER_DATA_ULTIMA_ATUALIZACAO_PESSOA");
			comando.adicionarVariavel("id", idPessoaCompartilhamento);
			comando.configurar();
			
			conn = estabelecerConexao();
			rs = comando.executarConsulta(conn);
			if (rs.next()) {
				data = new Date(rs.getTimestamp("DATA_ULTIMA_ATUALIZACAO").getTime());
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			fecharResultSet(rs);
			fecharConexao(conn);
			fecharComando(comando);
		}
		return data;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PessoaCompartilhamento> listarCompartilhamento(ConsultaPessoaDTO criterios) throws BancoobException {
		return pesquisarLista(PessoaCompartilhamento.class, criterios, "LISTAR_PESSOAS_POR_COMPARTILHAMENTO");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long obterMaiorIdPessoasPorCompartilhamento(
			CompartilhamentoCadastro compartilhamento) throws BancoobException {
		
		Long maxId = null;
		Comando comando = null;
		Connection conx = null;
		
		try {
			
			conx = estabelecerConexao();			
			comando = getComando("OBTER_MAIOR_IDPESSOACOMPARTILHAMENTO_POR_COMPARTILHAMENTO");
			comando.adicionarVariavel("codigoCompartilhamento", compartilhamento.getCodigo());
			comando.configurar();
			
			Query query = comando.criarQuery(getEntityManager());
			maxId = (Long) query.getSingleResult();
			
			
		} finally {
			fecharConexao(conx);
			fecharComando(comando);
		}
		
		return maxId;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterarGrupoPessoasInexistentesUnicas(Integer idInstituicao, Short codigoCompartilhamento)
			throws BancoobException {
		
		Comando comando = null;
		Connection conn = null;
		try {
			conn = estabelecerConexao();
			comando = getComando("ALTERAR_GRUPO_PESSOAS_INEXISTENTES_UNICAS");
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			comando.adicionarVariavel("codigoCompartilhamento", codigoCompartilhamento);
			comando.configurar();
			getLogger().debug(comando.getSqlEmUso());
			getLogger().debug("Parametros: idInstituicao=", String.valueOf(idInstituicao),
					", codigoCompartilhamento=", String.valueOf(codigoCompartilhamento));
			comando.executarAtualizacao(conn);
		} finally {
			fecharConexao(conn);
			fecharComando(comando);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConsultaDto<PessoaCompartilhamento> buscarPessoasInexistentesCompartilhadas(
			ConsultaDto<PessoaCompartilhamento> criterios) throws BancoobException {

		return pesquisar(PessoaCompartilhamento.class, criterios,
				"BUSCAR_PESSOAS_INEXISTENTES_COMPARTILHADAS");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void marcarEmAlteracao(String nomeTabela, String nomeColunaId,
			Long id, Integer idInstituicaoAtualizacao) throws BancoobException {
		
		super.marcarEmAlteracao("CLI.PESSOACOMPARTILHAMENTO", nomeColunaId,
				id, idInstituicaoAtualizacao);
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public PessoaCompartilhamento incluir(PessoaCompartilhamento objeto) throws BancoobException {
		try {
			return super.incluir(objeto);
		} catch (PersistenciaException e) {
			imprimirLogErroInclusao(objeto, e);
			throw e;
		} catch (ConstraintViolationException e) {
			imprimirLogErroInclusao(objeto, e);
			throw e;
		} catch (EntityExistsException e) {
			imprimirLogErroInclusao(objeto, e);
			throw e;
		} catch (PersistenceException e) {
			imprimirLogErroInclusao(objeto, e);
			throw e;
		}
	}

	/**
	 * O método Imprimir log erro inclusao.
	 *
	 * @param <E> o tipo generico
	 * @param obj o valor de obj
	 * @param e o valor de e
	 */
	private <E extends RuntimeException> void imprimirLogErroInclusao(PessoaCompartilhamento obj, E e) {
		
		String cpfCnpj = null;
		Integer idPessoa = null;
		Short codCompartilhamento = null;
		if (obj != null) {
			Pessoa pessoa = obj.getPessoa();
			if (pessoa != null) {
				cpfCnpj = pessoa.getCpfCnpj();
				idPessoa = pessoa.getIdPessoa();
			}
			
			CompartilhamentoCadastro compartilhamento = obj.getCompartilhamento();
			if (compartilhamento != null) {
				codCompartilhamento = compartilhamento.getCodigo();
			}
			
			getLogger().erro(
			        e,
			        MessageFormat.format(PATTERN_LOG_ERRO_INCLUSAO, obj.getClass().getName(),
			                cpfCnpj, idPessoa, obj.getIdPessoaCompartilhamento(),
			                codCompartilhamento));
		} else {
			getLogger().erro(
			        e,
			        MessageFormat.format(PATTERN_LOG_ERRO_INCLUSAO,
			                PessoaCompartilhamento.class.getName(), cpfCnpj, idPessoa, null,
			                codCompartilhamento));
		}
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConsultaDto<ProcurarPessoaExternoVO> procurarPessoaExterno(ConsultaDto<ProcurarPessoaExternoVO> criterios) throws BancoobException {
		return pesquisar(ProcurarPessoaExternoVO.class, criterios, COMANDO_COMPONENTE_PROCURAR_PESSOA_EXTERNO);
	}
	
	/**
	 * Obtém as filias de uma instituição apartir do CNPJ.
	 * 
	 * @param codCompartilhamentoCadastro
	 * @param cnpj
	 * @return as filiais encontradas.
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<PessoaCompartilhamento> consultarFiliais(Short codCompartilhamentoCadastro, String cnpj) throws BancoobException {
		Comando comando = getComando("OBTER_FILIAIS");
		comando.adicionarVariavel("cnpj", cnpj);
		comando.adicionarVariavel("codCompartilhamentoCadastro", codCompartilhamentoCadastro);
		comando.configurar();

		try {
			return comando.criarQuery(getEntityManager()).getResultList();
		} finally {
			fecharComando(comando);
		}
	}

	@Override
	public void atualizaCodTipoEmpresa(PessoaJuridica pjTemp) throws BancoobException {
		Comando comando = null;
		Connection conx = null;

		try {
			conx = estabelecerConexao();
			
			comando = getComando(ATUALIZAR_COD_TIPO_EMPRESA);
			comando.adicionarVariavel("codTipoEmpresa", pjTemp.getTipoEmpresa() != null ? pjTemp.getTipoEmpresa().getCodigo() : null);
			comando.adicionarVariavel("idPessoaCompartilhamento", pjTemp.getId());
			comando.configurar();
			getLogger().debug(comando.getSqlEmUso());

			comando.executarAtualizacao(conx);
		} finally {
			fecharConexao(conx);
			fecharComando(comando);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterarPerfilCadastro(PessoaCompartilhamento pessoaCompartilhamento, String usuario) throws BancoobException{
		Comando comando = null;
		Connection conx = null;
		try {
			conx = estabelecerConexao();

			comando = getComando("ALTERAR_PERFIL_CADASTRO");
			comando.adicionarVariavel("codigoPerfilCadastro", pessoaCompartilhamento.getPerfilCadastro().getCodigo());
			comando.adicionarVariavel("usuarioAprovacao", usuario);
			comando.adicionarVariavel("idPessoaCompartilhamento", pessoaCompartilhamento.getId());
			comando.configurar();
			getLogger().debug(comando.getSqlEmUso());

			comando.executarAtualizacao(conx);
		} finally {
			fecharConexao(conx);
			fecharComando(comando);
		}
	}
	
	public void incluirHistoricoPessoaCompartilhamento(PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("ADICIONAR_HISTORICO_PESSOACOMPARTILHAMENTO");
			comando.configurar();

			Query query = getEntityManager().createNativeQuery(comando.getSqlEmUso());
			query.setParameter(1, pessoaCompartilhamento.getId());
			query.executeUpdate();
		} finally {
			fecharComando(comando);
		}
	}
}