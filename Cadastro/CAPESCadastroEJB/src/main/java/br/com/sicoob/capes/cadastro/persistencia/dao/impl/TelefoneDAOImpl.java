/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.cadastro.persistencia.dao.TelefoneDAO;
import br.com.sicoob.capes.negocio.entidades.vigente.Telefone;

/**
 * Dao concreta para telefones.
 * 
 * @author Erico.Junior
 */
public class TelefoneDAOImpl extends CAPESCadastroCrudDao<Telefone> implements TelefoneDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_TELEFONE_POR_PESSOA_COMPARTILHAMENTO";
	
	/** A constante BUSCA_TELEFONE_REPETIDO. */
	private static final String BUSCA_TELEFONE_REPETIDO = "PESQUISA_TELEFONE_POR_FILTROS";

	/**
	 * Instancia um novo TelefoneDAOImpl.
	 */
	public TelefoneDAOImpl() {
		super(Telefone.class, NOME_COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isTelefoneRepetido(Telefone novoTel) throws BancoobException {
		
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		
		try {
			conexao = estabelecerConexao();
			comando = getComando(BUSCA_TELEFONE_REPETIDO);
			comando.adicionarVariavel("numTelefone", novoTel.getTelefone());
			comando.adicionarVariavel("idPessoaCompartilhamento", novoTel.getPessoaCompartilhamento().getIdPessoaCompartilhamento());
			comando.adicionarVariavel("numDDD", novoTel.getDdd());
			comando.adicionarVariavel("numRamal", novoTel.getRamal());
			comando.adicionarVariavel("idTelefonePessoa", novoTel.getIdTelefonePessoa());
			
			comando.configurar();
			
			rs = comando.executarConsulta(conexao);
			
			if(rs.next()){
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