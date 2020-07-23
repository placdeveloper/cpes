package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.exception.ConstraintViolationException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.cadastro.negocio.dto.TransfInformacoesDTO;
import br.com.sicoob.capes.cadastro.negocio.excecao.RegistroJaCadastradoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.RegistroNaoEncontradoException;
import br.com.sicoob.capes.cadastro.negocio.vo.StatusTransferenciaClienteVO;
import br.com.sicoob.capes.cadastro.persistencia.dao.PessoaInstituicaoDAO;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.Funcionario;
import br.com.sicoob.capes.negocio.entidades.Nucleo;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao;

/**
 * Classe que implementa a persistencia de pessoa instituicao.
 *
 * @author Juan.Damasceno
 *
 */
public class PessoaInstituicaoDAOImpl extends CAPESCadastroCrudDao<PessoaInstituicao> implements PessoaInstituicaoDAO {

	/** A constante UPDATE_LOTE_PESSOA_INSTITUICAO_CPFCNPJ. */
	private static final String UPDATE_LOTE_PESSOA_INSTITUICAO_CPFCNPJ = "UPDATE_LOTE_PESSOA_INSTITUICAO_CPFCNPJ";
	
	/** A constante UPDATE_LOTE_PESSOA_INSTITUICAO. */
	private static final String UPDATE_LOTE_PESSOA_INSTITUICAO = "UPDATE_LOTE_PESSOA_INSTITUICAO";
	
	/** A constante UPDATE_LOTE_PESSOA_NUCLEO. */
	private static final String  UPDATE_LOTE_PESSOA_NUCLEO = "UPDATE_LOTE_PESSOA_NUCLEO";

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_PESSOA_INSTITUICAO_POR_PESSOA";
	
	/** A constante NOME_COMANDO_PESQUISAR_IDS. */
	private static final String NOME_COMANDO_PESQUISAR_IDS = "PESQUISA_ID_PESSOA_INSTITUICAO_POR_PESSOA";
	
	/** A constante NOME_COMANDO_PESQUISAR_IDS. */
	private static final String NOME_COMANDO_PESQUISAR_IDS_POR_LISTA_CPFCNPJ = "PESQUISA_ID_PESSOA_INSTITUICAO_POR_LISTA_CPFCNPJ";
	
	/** A constante NOME_COMANDO_PESQUISAR_POR_FUNCIONARIO. */
	private static final String NOME_COMANDO_PESQUISAR_POR_FUNCIONARIO = "PESQUISA_PESSOAINSTITUICAO_POR_FUNCIONARIO";
	
	/** A constante NOME_COMANDO_PESQUISAR_POR_NUCLEO. */
	private static final String NOME_COMANDO_PESQUISAR_POR_NUCLEO = "PESQUISA_PESSOAINSTITUICAO_POR_NUCLEO";
	
	/** A constante GERAR_HISTORICO_PESSOA_INSTITUICAO. */
	private static final String GERAR_HISTORICO_PESSOA_INSTITUICAO = "GERAR_HISTORICO_PESSOA_INSTITUICAO";
	
	/** A constante UPDATE_TRANSICAO_PESSOA. */
	private static final String UPDATE_TRANSICAO_PESSOA = "UPDATE_TRANSICAO_PESSOA";

	
	

	/**
	 * Instancia um novo PessoaInstituicaoDAOImpl.
	 */
	public PessoaInstituicaoDAOImpl() {
		super(PessoaInstituicao.class, NOME_COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	public PessoaInstituicao obterPorPessoaInstituicao(PessoaInstituicao pessoaInstituicao) {

		Comando comando = getComando(NOME_COMANDO_PESQUISAR);
		comando.adicionarVariavel("idPessoa", pessoaInstituicao.getPessoa().getId());
		comando.adicionarVariavel("idInstituicao", pessoaInstituicao.getIdInstituicao());

		comando.configurar();

		Query query = criarQuery(comando);
		PessoaInstituicao pessoaIntituicao = null;

		try {
			pessoaIntituicao = (PessoaInstituicao) query.getSingleResult();
		} catch (NoResultException e) {
			throw new RegistroNaoEncontradoException(e);
		}

		return pessoaIntituicao;
	}

	/**
	 * {@inheritDoc}
	 */
	public Long pesquisarNumeroRegistros(ConsultaDto<PessoaInstituicao> criterios) {
		return pesquisarNumeroRegistros(criterios, NOME_COMANDO_PESQUISAR, true);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<PessoaInstituicao> listarPorFuncionarioResponsavel(Funcionario funcionario) {

		ConsultaDto<Funcionario> criterios = new ConsultaDto<Funcionario>();
		criterios.setFiltro(funcionario);

		return pesquisarLista(PessoaInstituicao.class, criterios, NOME_COMANDO_PESQUISAR_POR_FUNCIONARIO);
	}

	/**
	 * {@inheritDoc}
	 */
	public Long obterQuantidadeClientesPorNucleo(Nucleo nucleo) {

		ConsultaDto<Nucleo> criterios = new ConsultaDto<Nucleo>();
		criterios.setFiltro(nucleo);
		return pesquisarNumeroRegistros(criterios, NOME_COMANDO_PESQUISAR_POR_NUCLEO, true);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<StatusTransferenciaClienteVO> obterStatusTransferenciaCliente(Integer idInstituicao) throws BancoobException {
		List<StatusTransferenciaClienteVO> retorno = new ArrayList<StatusTransferenciaClienteVO>();

		Comando comando = null;
		Connection conn = null;
		ResultSet rs = null;

		try {
			comando = getComando("OBTER_STATUS_CLIENTE_TRANSFERENCIA_INFORMACAO");
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			comando.configurar();

			conn = estabelecerConexao();
			rs = comando.executarConsulta(conn);

			while (rs.next()) {
				StatusTransferenciaClienteVO vo = new StatusTransferenciaClienteVO();
				vo.setIdUnidadeInst(rs.getInt("IDUNIDADEINST"));
				vo.setNumeroNucleo(rs.getInt("NUMERONUCLEO"));
				vo.setNomeNucleo(rs.getString("NOMENUCLEO"));
				vo.setNomePessoa(rs.getString("NOMEPESSOA"));
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
	public ConsultaDto<PessoaInstituicao> pesquisarIdPessoaInstituicao(ConsultaDto<PessoaInstituicao> criterios) throws BancoobException {
		ConsultaDto<PessoaInstituicao> retorno = new ConsultaDto<PessoaInstituicao>();
		retorno.setResultado(new ArrayList<PessoaInstituicao>());
		retorno.setResultado(pesquisarLista(PessoaInstituicao.class, criterios, NOME_COMANDO_PESQUISAR_IDS));
		return retorno;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public ConsultaDto<PessoaInstituicao> pesquisarIdPessoaInstituicaoByCpfCnpj(List<String> listaCpfCnpj, Integer idInstituicaoUsuarioLogado) throws BancoobException {
		
		Comando comando = null;
		ConsultaDto<PessoaInstituicao> retorno = new ConsultaDto<PessoaInstituicao>();
		try {
			comando = getComando(NOME_COMANDO_PESQUISAR_IDS_POR_LISTA_CPFCNPJ);
			comando.adicionarVariavel("listaCpfCnpj", listaCpfCnpj);
			comando.adicionarVariavel("idInstituicaoUsuarioLogado", idInstituicaoUsuarioLogado);
			comando.configurar();
			retorno.setResultado(criarQuery(comando).getResultList());
		} finally {
			fecharComando(comando);
		}
		return retorno ;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public PessoaInstituicao incluir(PessoaInstituicao objeto) throws BancoobException {
		PessoaInstituicao pessoaInstituicao = null;
		try {
			pessoaInstituicao = super.incluir(objeto);
		} catch (ConstraintViolationException e) {
			throw new RegistroJaCadastradoException(e);
		}catch (EntityExistsException e) {
			throw new RegistroJaCadastradoException(e);
		}
		return pessoaInstituicao;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean verificaFuncionarioAssociadoClienteHistorico(Funcionario funcionario) throws BancoobException {
		Comando comando = null;
		Connection conn = null;
		ResultSet rs = null;

		Integer clientesAssociados = 0;
		try {
			comando = getComando("PESQUISA_FUNCIONARIO_ASSOCIADO_CLIENTE_HISTORICO");
			comando.adicionarVariavel("funcionario", funcionario);
			comando.configurar();
			
			getLogger().debug(comando.getSqlEmUso());

			conn = estabelecerConexao();
			rs = comando.executarConsulta(conn);

			if (rs.next()) {
				clientesAssociados = rs.getInt("QTD");
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			fecharResultSet(rs);
			fecharConexao(conn);
			fecharComando(comando);
		}
		return clientesAssociados > 0;
	}

	/**
	 * {@inheritDoc}
	 */
	public void alterarLote(ConsultaDto<? extends CAPESEntidade<?>> listaAtualizacao, TransfInformacoesDTO dto) throws BancoobException{
		Comando comando = null;
		Connection conn = null;
		
		try {
			if(dto.getBuscaPorCpfCnpj()) {	
				comando = getComando(UPDATE_LOTE_PESSOA_INSTITUICAO_CPFCNPJ);
			}else if(dto.getNucleo() != null) {
				comando = getComando(UPDATE_LOTE_PESSOA_NUCLEO);
			}else {
				comando = getComando(UPDATE_LOTE_PESSOA_INSTITUICAO);
			}
			
			conn = estabelecerConexao();
			
			List<Object[]> parametros = montaDadosAtualizacaoClientes(listaAtualizacao, dto);
			
			comando.configurar();
			getLogger().debug(comando.getSqlEmUso());
			
			comando.executarAtualizacaoEmLote(parametros, conn);
				
		} finally {
			fecharConexao(conn);
			fecharComando(comando);
		}
	}

	/**
	 * Monta lista de parametros para processamento em batch.
	 * @param listaAtualizacao
	 * @param dto
	 * @return
	 */
	private List<Object[]> montaDadosAtualizacaoClientes(ConsultaDto<? extends CAPESEntidade<?>> listaAtualizacao, 	TransfInformacoesDTO dto) {
		List<Object[]> listaParametros = new ArrayList<>();
		for (int i = 0; i < listaAtualizacao.getResultado().size(); i++) {
			PessoaInstituicao pessoaInstituicao = (PessoaInstituicao) listaAtualizacao.getResultado().get(i);
			if(dto.getBuscaPorCpfCnpj() ) {	
				Object[] parametro = preencheDadosAtualizacaoClientesCpfCnpj(pessoaInstituicao.getIdPessoaInstituicao(), dto);
				listaParametros.add(parametro);
			}else if (dto.getNucleo() != null) {
				Object[] parametro = preencheDadosAtualizacaoNucleo(pessoaInstituicao.getIdPessoaInstituicao(), dto);
				listaParametros.add(parametro);
			} else {
				Object[] parametro = preencheDadosAtualizacaoClientes(pessoaInstituicao.getIdPessoaInstituicao(), dto);
				listaParametros.add(parametro);
			}
		}
			
		return listaParametros;
	}
	
	/**
	 * Monta item par lista de parametros para processamento em batch.
	 * @param listaAtualizacao
	 * @param dto
	 * @return
	 */
	private Object[] preencheDadosAtualizacaoClientesCpfCnpj(Integer idPessoaInstituicao, TransfInformacoesDTO dto) {
		Object[] parametro = new Object[5];
		if(dto.isPessoaInstituicao() || dto.getBuscaPorCpfCnpj() != null) {
			parametro[0] = dto.getIdUnidadeInst();
			parametro[1] = dto.getGerente().getIdFuncionario();
			parametro[2] = dto.getIdInstituicaoUsuarioLogado();
			parametro[3] = dto.getNucleo().getIdNucleo();
			parametro[4] = idPessoaInstituicao;
		}

		return parametro;
		
	}

	/**
	 * Monta item par lista de parametros para processamento em batch.
	 * @param listaAtualizacao
	 * @param dto
	 * @return
	 */
	private Object[] preencheDadosAtualizacaoClientes(Integer idPessoaInstituicao, TransfInformacoesDTO dto) {
		Object[] parametro = new Object[4];
		if(dto.isPessoaInstituicao() || dto.getBuscaPorCpfCnpj() != null) {
			parametro[0] = dto.getIdUnidadeInst();
			parametro[1] = dto.getGerente().getIdFuncionario();
			parametro[2] = dto.getIdInstituicaoUsuarioLogado();
			parametro[3] = idPessoaInstituicao;
		}

		return parametro;
	}
		
	/**
	 * Monta item par lista de parametros para processamento em batch.
	 * @param listaAtualizacao
	 * @param dto
	 * @return
	 */
	private Object[] preencheDadosAtualizacaoNucleo(Integer idPessoaInstituicao, TransfInformacoesDTO dto) {
		Object[] parametro = new Object[3];
		if(dto.isPessoaInstituicao()) {
			parametro[0] = dto.getNucleo().getIdNucleo();
			parametro[1] = dto.getIdInstituicaoUsuarioLogado();
			parametro[2] = idPessoaInstituicao;
		}

		return parametro;
	}

	/**
	 * {@inheritDoc}
	 */
	public void gerarHistoricoLote(ConsultaDto<? extends CAPESEntidade<?>> pesquisaEntidades, String idUsuarioExclusao) {
		Comando comando = null;
		Connection conn = null;
		
		try {
			comando = getComando(GERAR_HISTORICO_PESSOA_INSTITUICAO);
			conn = estabelecerConexao();
			
			List<Object[]> parametros = montaDadosHistoricoParametros(pesquisaEntidades, idUsuarioExclusao);
			
			comando.configurar();
			getLogger().debug(comando.getSqlEmUso());
			
			comando.executarAtualizacaoEmLote(parametros, conn);
				
		} finally {
			fecharConexao(conn);
			fecharComando(comando);
		}
		
	}

	/**
	 * Montar parametros para historico.
	 * @param pesquisaEntidades
	 * @param idUsuarioExclusao 
	 * @return
	 */
	private List<Object[]> montaDadosHistoricoParametros(ConsultaDto<? extends CAPESEntidade<?>> pesquisaEntidades, String idUsuarioExclusao) {
		List<Object[]> listaParametros = new ArrayList<>();
		
		Object[] parametro = new Object[2];
		for (int i = 0; i < pesquisaEntidades.getResultado().size(); i++) {
			PessoaInstituicao pessoaInstituicao = (PessoaInstituicao) pesquisaEntidades.getResultado().get(i);
			parametro[0] = idUsuarioExclusao;
			parametro[1] = pessoaInstituicao.getIdPessoaInstituicao();
			
			listaParametros.add(parametro);
		}

		return listaParametros;
	}

	/**
	 * {@inheritDoc}
	 */
	public void atualizarTransicaoLote() throws BancoobException {
		Comando comando = null;
		Connection conn = null;
		
		try {
			comando = getComando(UPDATE_TRANSICAO_PESSOA);
			conn = estabelecerConexao();
			
			comando.configurar();
			getLogger().debug(comando.getSqlEmUso());
			
			comando.executarAtualizacao(conn);
				
		} finally {
			fecharConexao(conn);
			fecharComando(comando);
		}
	}
	
}