/**
 * 
 */
package br.com.sicoob.capes.replicacao.persistencia.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;
import br.com.sicoob.capes.negocio.entidades.legado.PessoaFisica;
import br.com.sicoob.capes.negocio.entidades.legado.PessoaJuridica;
import br.com.sicoob.capes.persistencia.CAPESEntidadesLegadoDatasource;
import br.com.sicoob.capes.replicacao.persistencia.dao.EntidadeReplicavelDao;
import br.com.sicoob.capes.replicacao.persistencia.dao.PessoaDAO;
import br.com.sicoob.capes.replicacao.persistencia.excecao.CAPESReplicacaoComumPersistenciaException;

/**
 * Dao utilizado para replicação de pessoas.
 * 
 * @author Erico.Junior
 */
public class PessoaDAOImpl extends EntidadeReplicavelDao<Pessoa> implements PessoaDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "";
	
	/** A constante NOME_COMANDO_CONSULTAR_IDDB2. */
	private static final String NOME_COMANDO_CONSULTAR_IDDB2 = "";
	
	/** A constante NOME_COMANDO_CONSULTAR_IDDB2. */
	private static final String ATUALIZAR_COD_TIPO_EMPRESA = "ATUALIZA_COD_TIPO_EMPRESA_POR_PESSOA_LEGADO";

	/**
	 * Instancia um novo PessoaDAOImpl.
	 */
	public PessoaDAOImpl() {
		super(Pessoa.class, NOME_COMANDO_PESQUISAR, NOME_COMANDO_CONSULTAR_IDDB2);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Pessoa obterPorIdDB2(Serializable chave, Integer numeroCooperativa)
			throws BancoobException {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void atualizarAssinaturaFotoBancoob(Integer idPessoa,
			Integer numeroCooperativa) throws BancoobException {
		Comando comando = null;
		Comando comando2 = null;
		Connection conx = null;
		ResultSet rs = null;
		try {
			comando = getComando("PESQUISAR_ASSINATURA_FOTO_PESSOA");
			comando.adicionarVariavel("NumPessoa", idPessoa);
			comando.configurar();
			
			conx = estabelecerConexao(numeroCooperativa);
			rs = comando.executarConsulta(conx);
			if (rs.next()) {
				comando2 = getComando("ATUALIZAR_ASSINATURA_FOTO_PESSOA");
				comando2.adicionarVariavel("NumCGC_CPF", rs.getString("NumCGC_CPF"));
				comando2.adicionarVariavel("FotoPessoa", rs.getBytes("FotoPessoa"));
				comando2.adicionarVariavel("AssinaturaPessoa", rs.getBytes("AssinaturaPessoa"));
				comando2.configurar();
				fecharConexao(conx);
				conx = estabelecerConexao(1);
				comando2.executarAtualizacao(conx);
			}
		} catch (SQLException e) {
			throw new PersistenciaException("Erro ao atualizar assinatura/foto.", e);
		} finally {
			fecharResultSet(rs);
			fecharConexao(conx);
			fecharComando(comando);
			fecharComando(comando2);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void alterarComFlush(Pessoa pessoa, Integer numeroCooperativa)
			throws BancoobException {
		
		CAPESEntidadesLegadoDatasource.definirNumeroCooperativa(numeroCooperativa);
		try {
			getEntityManager().merge(pessoa);
			getEntityManager().flush();
		} catch (IllegalArgumentException e) {
			throw new BancoobException("msg.erro.alterar.nao.existe", e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<PessoaFisica> listarPessoasPorCpfConjuge(String cpfConjuge, Integer numCooperativa)
			throws BancoobException {

		CAPESEntidadesLegadoDatasource.definirNumeroCooperativa(numCooperativa);
		
		Comando comando = getComando("LISTA_PESSOA_POR_CPF_CONJUGE");
		List<PessoaFisica> lista = null;

		try {

			comando.adicionarVariavel("cpfConjuge", cpfConjuge);
			comando.configurar();
		
			Query query = comando.criarQuery(getEntityManager());
			lista = query.getResultList();
			
		} catch (PersistenceException e) {
			throw new CAPESReplicacaoComumPersistenciaException(e);
		} finally {
			fecharComando(comando);
		}
		
		return lista;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void atualizarConjuge(Pessoa objeto, Integer numeroCooperativa) throws BancoobException {
		Comando comando = null;
		Connection conx = null;
		try {
			PessoaFisica pessoa = (PessoaFisica) objeto;
			
			comando = getComando("ATUALIZAR_CONJUGE");
			comando.adicionarVariavel("nomeConjuge", pessoa.getNomeConjuge());
			comando.adicionarVariavel("cpfConjuge", pessoa.getCpfConjuge());
			comando.adicionarVariavel("codProfissaoConjuge", pessoa.getCodigoProfissaoConjuge());
			comando.adicionarVariavel("numPessoa", pessoa.getId());
			comando.configurar();
			
			getLogger().debug(comando.getSqlEmUso());

			conx = estabelecerConexao(numeroCooperativa);
			comando.executarAtualizacao(conx);
		} finally {
			fecharConexao(conx);
			fecharComando(comando);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void alterarEmail(Pessoa pessoa, Integer numeroCooperativa) throws BancoobException {
		Comando comando = null;
		Connection conx = null;
		try {
			comando = getComando("ALTERAR_EMAIL");
			comando.adicionarVariavel("email", pessoa.getEmail());
			comando.adicionarVariavel("numPessoa", pessoa.getId());
			comando.configurar();
			
			getLogger().debug(comando.getSqlEmUso());

			conx = estabelecerConexao(numeroCooperativa);
			comando.executarAtualizacao(conx);
		} finally {
			fecharConexao(conx);
			fecharComando(comando);
		}
	}

	public void atualizaCodTipoEmpresa(PessoaJuridica pjTemp) throws BancoobException {
		Comando comando = null;
		Connection conx = null;

		try {
			comando = getComando(ATUALIZAR_COD_TIPO_EMPRESA);
			comando.adicionarVariavel("codTipoEmpresa", pjTemp.getTipoEmpresa() != null ? pjTemp.getTipoEmpresa() : null);
			comando.adicionarVariavel("idPessoaLegado", pjTemp.getId());
			comando.configurar();
			
			getLogger().debug(comando.getSqlEmUso());

			conx = estabelecerConexao();
			comando.executarAtualizacao(conx);
		} finally {
			fecharConexao(conx);
			fecharComando(comando);
		}
	}
	
}