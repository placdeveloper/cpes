/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.cadastro.negocio.excecao.ClienteNaoEncontradoException;
import br.com.sicoob.capes.cadastro.persistencia.dao.PessoaDAO;
import br.com.sicoob.capes.comum.negocio.vo.PessoaBasicaVO;
import br.com.sicoob.capes.negocio.entidades.Pessoa;

/**
 * Dao para as pessoas.
 * 
 * @author Erico.Junior
 */
public class PessoaDAOImpl extends CAPESCadastroCrudDao<Pessoa> implements PessoaDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISAR_PESSOA";
	
	/** A constante CONSULTA_PESSOA_POR_DOCUMENTO. */
	private static final String CONSULTA_PESSOA_POR_DOCUMENTO = "CONSULTA_PESSOA_POR_DOCUMENTO";
	
	/** A constante PESQUISA_PESSOA_POR_ID_PESSOAS_E_INSTITUICAO. */
	private static final String PESQUISA_PESSOA_POR_ID_PESSOAS_E_INSTITUICAO  = "PESQUISA_PESSOA_POR_ID_PESSOAS_E_INSTITUICAO";
	
	/** A constante CONSULTA_PESSOA_POR_CPF_CNPJ_UR. */
	private static final String CONSULTA_PESSOA_POR_CPF_CNPJ_UR  = "CONSULTA_PESSOA_POR_CPF_CNPJ_UR";
	
	/**
	 * Construtor do DAO.
	 */
	public PessoaDAOImpl() {
		super(Pessoa.class, NOME_COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	public Pessoa consultarPessoaPorDocumento(String documento) throws ClienteNaoEncontradoException {
		
		Pessoa pessoa = null;
		Comando comando = getComando(CONSULTA_PESSOA_POR_DOCUMENTO);
		comando.adicionarVariavel("documento", documento);
		comando.configurar();

		try {
			Query query = comando.criarQuery(getEntityManager());
			pessoa = (Pessoa) query.getSingleResult();
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
	@SuppressWarnings("unchecked")
	public List<PessoaBasicaVO> obterPorListaPessoaInstituicao(List<Integer> idPessoas, Integer idInstituicao) {
		Comando comando = getComando(PESQUISA_PESSOA_POR_ID_PESSOAS_E_INSTITUICAO);
		try {
			comando.adicionarVariavel("idPessoas", idPessoas);
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
	public Long consultarPessoaPorCpfCnpjUR(String cpfCnpj) throws BancoobException {
		Connection conx = null;
		ResultSet rset = null;
		Comando comando = null;
		Long idPessoa = null;

		try {
			
			conx = estabelecerConexao();
			comando = getComando(CONSULTA_PESSOA_POR_CPF_CNPJ_UR);
			comando.adicionarVariavel("cpfcnpj", cpfCnpj);
			comando.configurar();
			
			rset = comando.executarConsulta(conx);

			if(rset.next()) {
				idPessoa = rset.getLong("IDPESSOA");
			}

		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		} finally {
			fecharResultSet(rset);
			fecharConexao(conx);
			fecharComando(comando);
		}

		return idPessoa;
	}
	
}