/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.cadastro.persistencia.dao.EmailDAO;
import br.com.sicoob.capes.negocio.entidades.vigente.Email;

/**
 * Dao concreta para emails.
 * @author Erico.Junior
 */
public class EmailDAOImpl extends CAPESCadastroCrudDao<Email> implements EmailDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_EMAIL_POR_PESSOA_COMPARTILHAMENTO";
	
	/** A constante BUSCA_EMAIL_REPETIDO. */
	private static final String BUSCA_EMAIL_REPETIDO = "PESQUISA_EMAIL_POR_FILTROS";

	/**
	 * Instancia um novo EmailDAOImpl.
	 */
	public EmailDAOImpl() {
		super(Email.class, NOME_COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isEmailRepetido(Email novoEmail) throws BancoobException {

		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;

		try {
			conexao = estabelecerConexao();
			comando = getComando(BUSCA_EMAIL_REPETIDO);
			comando.adicionarVariavel("descEmail", novoEmail.getDescricao());
			comando.adicionarVariavel("idPessoaCompartilhamento", novoEmail.getPessoaCompartilhamento().getIdPessoaCompartilhamento());
			comando.adicionarVariavel("idEmail", novoEmail.getIdEmail());
			
			comando.configurar();

			rs = comando.executarConsulta(conexao);

			if (rs.next()) {
				return rs.getInt("CONTADOR") > 0;
			}

		} catch (SQLException e) {
			throw new BancoobException(e);
		} finally {
			fecharResultSet(rs);
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return true;
	}
}
