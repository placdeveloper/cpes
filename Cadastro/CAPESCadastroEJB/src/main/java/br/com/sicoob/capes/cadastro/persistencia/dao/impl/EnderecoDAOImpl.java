/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.cadastro.persistencia.dao.EnderecoDAO;
import br.com.sicoob.capes.negocio.entidades.vigente.Endereco;

/**
 * Dao concreta para endereços.
 * 
 * @author Erico.Junior
 */
public class EnderecoDAOImpl extends EntidadeCadastroDao<Endereco> implements
		EnderecoDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_ENDERECO_POR_PESSOA_COMPARTILHAMENTO";
	
	/** A constante BUSCA_ENDERECO_REPETIDO. */
	private static final String BUSCA_ENDERECO_REPETIDO = "PESQUISA_ENDERECO_POR_FILTROS";

	/**
	 * Instancia um novo EnderecoDAOImpl.
	 */
	public EnderecoDAOImpl() {
		super(Endereco.class, NOME_COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isEnderecoRepetido(Endereco novoEnd) throws BancoobException{
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		
		try {
			conexao = estabelecerConexao();
			comando = getComando(BUSCA_ENDERECO_REPETIDO);
			comando.adicionarVariavel("descricao", novoEnd.getDescricao());
			comando.adicionarVariavel("numero", novoEnd.getNumero());
			comando.adicionarVariavel("cep", novoEnd.getCep());
			comando.adicionarVariavel("bairro", novoEnd.getBairro());
			comando.adicionarVariavel("idLocalidade", novoEnd.getLocalidade().getIdLocalidade());
			comando.adicionarVariavel("idPessoaCompartilhamento", novoEnd.getPessoaCompartilhamento().getIdPessoaCompartilhamento());
			comando.adicionarVariavel("idEnderecoPessoa", novoEnd.getIdEndereco());
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
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(Endereco objeto) throws BancoobException {
		try {
			getEntityManager().merge(objeto);
			getEntityManager().flush();
		} catch (IllegalArgumentException e) {
			throw new BancoobException("msg.erro.alterar.nao.existe", e);
		}
	}
	
}
