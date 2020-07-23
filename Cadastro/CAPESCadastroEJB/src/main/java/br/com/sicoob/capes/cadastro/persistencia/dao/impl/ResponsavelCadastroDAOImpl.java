/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.ResponsavelCadastro;
import br.com.sicoob.capes.cadastro.negocio.excecao.RegistroNaoEncontradoException;
import br.com.sicoob.capes.cadastro.persistencia.dao.ResponsavelCadastroDAO;

/**
 * Dao para ResponsavelCadastro.
 * 
 * @author Juan.Damasceno
 */
public class ResponsavelCadastroDAOImpl extends EntidadeCadastroDao<ResponsavelCadastro> implements
		ResponsavelCadastroDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_RESPONSAVEL_CADASTRO_POR_PESSOA";

	/**
	 * Construtor do DAO.
	 */
	public ResponsavelCadastroDAOImpl() {
		super(ResponsavelCadastro.class, NOME_COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	public ResponsavelCadastro consultar(PessoaCompartilhamento pessoa) throws BancoobException {
		
		Comando comando = getComando("CONSULTAR_RESPONSAVEL_CADASTRO_POR_PESSOA");
		comando.adicionarVariavel("criterios", pessoa);
		comando.configurar();
		ResponsavelCadastro responsavel = null;
		
		try {
			Query query = comando.criarQuery(getEntityManager());
			responsavel = (ResponsavelCadastro) query.getSingleResult();
		} catch (NoResultException e) {
			throw new RegistroNaoEncontradoException(e);
		} catch (PersistenceException e) {
			throw new PersistenciaException(e);
		} finally {
			fecharComando(comando);
		}

		return responsavel;
	}
}
