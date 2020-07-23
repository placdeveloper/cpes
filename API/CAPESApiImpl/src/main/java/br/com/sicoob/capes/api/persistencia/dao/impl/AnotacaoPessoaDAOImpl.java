package br.com.sicoob.capes.api.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang.time.DateUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.api.negocio.vo.AnotacaoPeriodoVO;
import br.com.sicoob.capes.api.negocio.vo.AnotacaoPessoaVO;
import br.com.sicoob.capes.api.persistencia.dao.AnotacaoPessoaDAO;

/**
 * A Classe AnotacaoPessoaDAOImpl.
 */
public class AnotacaoPessoaDAOImpl extends CAPESApiDAO<AnotacaoPessoaVO> implements AnotacaoPessoaDAO {
	
	/** A constante PESQUISAR_ANOTACOES_POR_TIPO. */
	private static final String PESQUISAR_ANOTACOES_POR_TIPO = "PESQUISAR_ANOTACOES_POR_TIPO";
	
	/** A constante DATA_BAIXA. */
	private static final String DATA_BAIXA = "dataBaixa";
	
	/** A constante CODIGO_TIPO_ANOTACAO. */
	private static final String CODIGO_TIPO_ANOTACAO = "codigoTipoAnotacao";

	/**
	 * Instancia um novo AnotacaoPessoaDAOImpl.
	 */
	public AnotacaoPessoaDAOImpl(){
		super("OBTER_ANOTACAO_POR_ID");
	}

	/**
	 * {@inheritDoc}
	 */
	public List<AnotacaoPessoaVO> obterAnotacoesVigentesPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return obterAnotacoesPorPessoaInstituicao(idPessoa, idInstituicao, Boolean.FALSE);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<AnotacaoPessoaVO> obterAnotacoesPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, boolean baixadas) throws BancoobException {
		Comando comando = null;
		try{
			comando = getComando("PESQUISAR_ANOTACOES_VIGENTES_PESSOA");
			comando.adicionarVariavel(ID_PESSOA, idPessoa);
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.adicionarVariavel("baixadas", baixadas);
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
	public List<AnotacaoPessoaVO> obterAnotacoesImpeditivasPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		Comando comando = null;
		try{
			comando = getComando("PESQUISAR_ANOTACOES_IMPEDITIVAS_PESSOA");
			comando.adicionarVariavel(ID_PESSOA, idPessoa);
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
	public List<AnotacaoPessoaVO> obterAnotacoesVigentesPorPessoaInstituicaoTipo(Integer idPessoa, Integer idInstituicao, Integer idTipoAnotacao) throws BancoobException {
		Comando comando = null;
		try{
			comando = getComando("PESQUISAR_ANOTACOES_VIGENTES_PESSOA");
			comando.adicionarVariavel(ID_PESSOA, idPessoa);
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.adicionarVariavel("idTipoAnotacao", idTipoAnotacao);
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
	public List<AnotacaoPessoaVO> obterNaoVigentesPorPessoaInstituicaoTipo(Integer idPessoa, Integer idInstituicao, List<Integer> codigoTipoAnotacao, Date dataBaixa) throws BancoobException {
		Comando comando = null;
		try{
			comando = getComando(PESQUISAR_ANOTACOES_POR_TIPO);
			comando.adicionarVariavel(ID_PESSOA, idPessoa);
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.adicionarVariavel(DATA_BAIXA, DateUtils.truncate(dataBaixa, Calendar.DATE));
			comando.adicionarVariavel(CODIGO_TIPO_ANOTACAO, codigoTipoAnotacao);
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
	public List<AnotacaoPessoaVO> obterVigentesPorPessoaInstituicaoTipo(Integer idPessoa, Integer idInstituicao, List<Integer> codigoTipoAnotacao) throws BancoobException {
		Comando comando = null;
		try{
			comando = getComando(PESQUISAR_ANOTACOES_POR_TIPO);
			comando.adicionarVariavel(ID_PESSOA, idPessoa);
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.adicionarVariavel(DATA_BAIXA, null);
			comando.adicionarVariavel(CODIGO_TIPO_ANOTACAO, codigoTipoAnotacao);
			comando.configurar();
			
			return criarQuery(comando).getResultList();
		} finally {
			fecharComando(comando);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<AnotacaoPeriodoVO> obterPessoasSicoobPorTipoAnotacaoPeriodo(List<String> listaCpfCnpj, Integer idTipoAnotacao, Date dataAnotacao) throws BancoobException {
		Connection conx = null;
		ResultSet rset = null;
		Comando comando = null;
		List<AnotacaoPeriodoVO> listaRetorno = new ArrayList<AnotacaoPeriodoVO>();

		try {
			conx = estabelecerConexao();

			comando = getComando("OBTER_PESSOAS_PROVA_VIDA_POR_TIPO_ANOTACAO_PERIODO");
			comando.adicionarVariavel("listaCpfCnpj", listaCpfCnpj);
			comando.adicionarVariavel("idTipoAnotacao", idTipoAnotacao);
			comando.adicionarVariavel("dataAnotacao", dataAnotacao);
			comando.configurar();
			
			getLogger().debug(comando.getSqlEmUso());

			rset = comando.executarConsulta(conx);

			while (rset.next()) {
				AnotacaoPeriodoVO vo = new AnotacaoPeriodoVO();
				vo.setCpfCnpj(rset.getString("CPFCNPJ"));
				vo.setProvaVida(rset.getBoolean("PROVA_VIDA"));

				listaRetorno.add(vo);
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
	@SuppressWarnings("unchecked")
	public List<AnotacaoPeriodoVO> obterPessoasSicoobPorTipoAnotacaoPeriodo(Integer idTipoAnotacao, Date dataAnotacao) throws BancoobException {
		Comando comando = null;
		
		try{
			comando = getComando("OBTER_PESSOAS_POR_TIPO_ANOTACAO_PERIODO");
			comando.adicionarVariavel("idTipoAnotacao", idTipoAnotacao);
			comando.adicionarVariavel("dataAnotacao", dataAnotacao);
			comando.configurar();
			
			Query query = comando.criarQuery(getEntityManager());
			return query.getResultList();
		} finally {
			fecharComando(comando);
		}
	}

	@SuppressWarnings("unchecked")
	public List<AnotacaoPessoaVO> obterVigentesPorTipo(Integer idPessoa, String cpfCnpj, Integer idInstituicao, Integer idTipoAnotacao) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("PESQUISAR_ANOTACOES_VIGENTES_PESSOA");
			comando.adicionarVariavel(ID_PESSOA, idPessoa);
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.adicionarVariavel("cpfCnpj", cpfCnpj);
			comando.adicionarVariavel("idTipoAnotacao", idTipoAnotacao);
			
			comando.configurar();

			return criarQuery(comando).getResultList();
		} finally {
			fecharComando(comando);
		}
	}
}