package br.com.sicoob.capes.api.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.api.negocio.vo.PessoaVO;
import br.com.sicoob.capes.api.persistencia.dao.PessoaDAO;

/**
 * A Classe PessoaDAOImpl.
 */
public class PessoaDAOImpl extends CAPESApiDAO<PessoaVO> implements PessoaDAO {
	
	/** A constante COMANDO_PESQUISAR_PESSOA. */
	private static final String COMANDO_PESQUISAR_PESSOA = "PESQUISAR_PESSOA";
	
	/** A constante COMANDO_PESQUISAR_QTD_PESSOA. */
	private static final String COMANDO_PESQUISAR_QTD_PESSOA = "PESQUISAR_QTD_PESSOA";
	
	/** A constante COMANDO_OBTER_POR_CPFCNPJ_INSTITUICAOGRUPOCOMPARTILHAMENTO. */
	private static final String COMANDO_OBTER_POR_CPFCNPJ_INSTITUICAOGRUPOCOMPARTILHAMENTO = "OBTER_PESSOA_POR_CPFCNPJ_INSTITUICAOGRUPOCOMPARTILHAMENTO";
	
	/** A constante NOME. */
	private static final String NOME = "nome";
	
	/** A constante NOME_APELIDO. */
	private static final String NOME_APELIDO = "nomeApelido";
	
	/** A constante TIPO_PESSOA. */
	private static final String TIPO_PESSOA = "tipoPessoa";

	/**
	 * Instancia um novo PessoaDAOImpl.
	 */
	public PessoaDAOImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public PessoaVO obterPessoa(Integer idPessoa, Integer idPessoaLegado, String cpfCnpj, Integer idInstituicao) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando(COMANDO_PESQUISAR_PESSOA);
			comando.adicionarVariavel(ID_PESSOA, idPessoa);
			comando.adicionarVariavel("idPessoaLegado", idPessoaLegado);
			comando.adicionarVariavel("cpfCnpj", cpfCnpj);
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.configurar();

			return (PessoaVO) comando.criarQuery(getEntityManager()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			fecharComando(comando);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<PessoaVO> obterPessoaPorCpfCnpj(String cpfCnpj) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando(COMANDO_PESQUISAR_PESSOA);
			comando.adicionarVariavel("cpfCnpj", cpfCnpj);
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
	public List<PessoaVO> obterPessoaPorNome(String nome, Integer idInstituicao) throws BancoobException {
		Comando comando = null;
		try{
			comando = getComando(COMANDO_PESQUISAR_PESSOA);
			comando.adicionarVariavel(NOME, nome);
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
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
	public List<PessoaVO> obterPessoaPorNomeApelido(String nomeApelido, Integer idInstituicao) throws BancoobException {
		Comando comando = null;
		try{
			comando = getComando(COMANDO_PESQUISAR_PESSOA);
			comando.adicionarVariavel(NOME_APELIDO, nomeApelido);
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
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
	public ConsultaDto<PessoaVO> obterPessoaPorNome(String nome, Integer idInstituicao, int pagina, int tamanhoPagina) throws BancoobException {
		ConsultaDto<PessoaVO> consultaDto = new ConsultaDto<PessoaVO>();
		consultaDto.setTamanhoPagina(tamanhoPagina);
		consultaDto.setPagina(pagina);
		Comando comando = null;
		Comando comandoQuantidade = null;
		try{
			comando = getComando(COMANDO_PESQUISAR_PESSOA);
			comando.adicionarVariavel(NOME, nome);
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.configurar();
			
			Query query = criarQuery(comando);
			query.setFirstResult(pagina * tamanhoPagina);
			query.setMaxResults(tamanhoPagina);
			
			consultaDto.setResultado(query.getResultList());
			
			comandoQuantidade = getComando(COMANDO_PESQUISAR_QTD_PESSOA);
			comandoQuantidade.adicionarVariavel(NOME, nome);
			comandoQuantidade.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comandoQuantidade.configurar();
			
			consultaDto.setTotalRegistros(((Long) criarQuery(comandoQuantidade).getSingleResult()).intValue());
			
			return consultaDto;
		} finally {
			fecharComando(comando);
			fecharComando(comandoQuantidade);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public ConsultaDto<PessoaVO> obterPessoaPorNomeApelido(String nomeApelido, Integer idInstituicao, int pagina, int tamanhoPagina) throws BancoobException {
		ConsultaDto<PessoaVO> consultaDto = new ConsultaDto<PessoaVO>();
		consultaDto.setTamanhoPagina(tamanhoPagina);
		consultaDto.setPagina(pagina);
		Comando comando = null;
		Comando comandoQuantidade = null;
		try{
			comando = getComando(COMANDO_PESQUISAR_PESSOA);
			comando.adicionarVariavel(NOME_APELIDO, nomeApelido);
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.configurar();
			
			Query query = criarQuery(comando);
			query.setFirstResult(pagina * tamanhoPagina);
			query.setMaxResults(tamanhoPagina);
			
			consultaDto.setResultado(query.getResultList());
			
			comandoQuantidade = getComando(COMANDO_PESQUISAR_QTD_PESSOA);
			comandoQuantidade.adicionarVariavel(NOME_APELIDO, nomeApelido);
			comandoQuantidade.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comandoQuantidade.configurar();
			
			consultaDto.setTotalRegistros(((Long) criarQuery(comandoQuantidade).getSingleResult()).intValue());
			
			return consultaDto;
		} finally {
			fecharComando(comando);
			fecharComando(comandoQuantidade);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<PessoaVO> obterPessoaPorNomeTipoPessoa(String nome, Integer idInstituicao, Short tipoPessoa) throws BancoobException {
		Comando comando = null;
		try{
			comando = getComando(COMANDO_PESQUISAR_PESSOA);
			comando.adicionarVariavel(NOME, nome);
			comando.adicionarVariavel(TIPO_PESSOA, tipoPessoa);
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
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
	public List<PessoaVO> obterPessoaPorNomeApelidoTipoPessoa(String nomeApelido, Integer idInstituicao, Short tipoPessoa) throws BancoobException {
		Comando comando = null;
		try{
			comando = getComando(COMANDO_PESQUISAR_PESSOA);
			comando.adicionarVariavel(NOME_APELIDO, nomeApelido);
			comando.adicionarVariavel(TIPO_PESSOA, tipoPessoa);
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
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
	public ConsultaDto<PessoaVO> obterPessoaPorNomeTipoPessoa(String nome, Integer idInstituicao, Short tipoPessoa, int pagina, int tamanhoPagina) throws BancoobException {
		ConsultaDto<PessoaVO> consultaDto = new ConsultaDto<PessoaVO>();
		consultaDto.setTamanhoPagina(tamanhoPagina);
		consultaDto.setPagina(pagina);
		Comando comando = null;
		Comando comandoQuantidade = null;
		try{
			comando = getComando(COMANDO_PESQUISAR_PESSOA);
			comando.adicionarVariavel(NOME, nome);
			comando.adicionarVariavel(TIPO_PESSOA, tipoPessoa);
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.configurar();
			
			Query query = criarQuery(comando);
			query.setFirstResult(pagina * tamanhoPagina);
			query.setMaxResults(tamanhoPagina);
			
			consultaDto.setResultado(query.getResultList());
			
			comandoQuantidade = getComando(COMANDO_PESQUISAR_QTD_PESSOA);
			comandoQuantidade.adicionarVariavel(NOME, nome);
			comandoQuantidade.adicionarVariavel(TIPO_PESSOA, tipoPessoa);
			comandoQuantidade.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comandoQuantidade.configurar();
			
			consultaDto.setTotalRegistros(((Long) criarQuery(comandoQuantidade).getSingleResult()).intValue());
			
			return consultaDto;
		} finally {
			fecharComando(comando);
			fecharComando(comandoQuantidade);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public ConsultaDto<PessoaVO> obterPessoaPorNomeApelidoTipoPessoa(String nomeApelido, Integer idInstituicao, Short tipoPessoa, int pagina, int tamanhoPagina) throws BancoobException {
		ConsultaDto<PessoaVO> consultaDto = new ConsultaDto<PessoaVO>();
		consultaDto.setTamanhoPagina(tamanhoPagina);
		consultaDto.setPagina(pagina);
		Comando comando = null;
		Comando comandoQuantidade = null;
		try{
			comando = getComando(COMANDO_PESQUISAR_PESSOA);
			comando.adicionarVariavel(NOME_APELIDO, nomeApelido);
			comando.adicionarVariavel(TIPO_PESSOA, tipoPessoa);
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.configurar();
			
			Query query = criarQuery(comando);
			query.setFirstResult(pagina * tamanhoPagina);
			query.setMaxResults(tamanhoPagina);
			
			consultaDto.setResultado(query.getResultList());
			
			comandoQuantidade = getComando(COMANDO_PESQUISAR_QTD_PESSOA);
			comandoQuantidade.adicionarVariavel(NOME_APELIDO, nomeApelido);
			comandoQuantidade.adicionarVariavel(TIPO_PESSOA, tipoPessoa);
			comandoQuantidade.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comandoQuantidade.configurar();
			
			consultaDto.setTotalRegistros(((Long) criarQuery(comandoQuantidade).getSingleResult()).intValue());
			
			return consultaDto;
		} finally {
			fecharComando(comando);
			fecharComando(comandoQuantidade);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<String> consultarPessoaPossuemAutorizacaoBacen(List<String> listaCpfCnpj, Integer idInstituicao) throws BancoobException {
		Connection conx = null;
		ResultSet rset = null;
		Comando comando = null;
		List<String> listaRetorno = new ArrayList<String>();

		try {
			conx = estabelecerConexao();

			comando = getComando("CONSULTAR_PESSOA_POSSUEM_AUTORIZACAO_BACEN");
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.adicionarVariavel("listaCpfCnpj", listaCpfCnpj);
			comando.configurar();

			rset = comando.executarConsulta(conx);

			while (rset.next()) {
				listaRetorno.add(rset.getString(1));
			}

		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		} finally {
			fecharResultSet(rset);
			fecharConexao(conx);
			fecharComando(comando);
		}

		return listaRetorno;
	}

	/**
	 * {@inheritDoc}
	 */
	public PessoaVO obterPorCpfCnpjInstituicaoGrupoCompartilhamento(String cpfCnpj, Integer idInstituicao) throws BancoobException {
		Connection conx = null;
		ResultSet rset = null;
		Comando comando = null;
		PessoaVO retorno = null;
		
		try {
			conx = estabelecerConexao();

			comando = getComando(COMANDO_OBTER_POR_CPFCNPJ_INSTITUICAOGRUPOCOMPARTILHAMENTO);
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.adicionarVariavel("cpfCnpj", cpfCnpj);
			comando.configurar();

			rset = comando.executarConsulta(conx);

			if (rset.next()) {
				retorno = new PessoaVO();
				retorno.setIdPessoa(rset.getInt("IDPESSOA"));
				retorno.setCpfCnpj(rset.getString("NUMCPFCNPJ"));
				retorno.setNomeCompleto(rset.getString("NOMECOMPLETO"));
				retorno.setCodigoCnaeFiscal(rset.getString("CODCNAE"));
				retorno.setDescricaoCnaeFiscal(rset.getString("DESCCNAE"));
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
}