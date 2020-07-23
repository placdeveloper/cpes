/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.negocio.entidades.EnderecoCorrespondencia;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.Endereco;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.cadastro.persistencia.dao.EnderecoCorrespondenciaDAO;

/**
 * DAO para endereço de correspondência.
 * 
 * @author Erico.Junior
 */
public class EnderecoCorrespondenciaDAOImpl extends
		CAPESCadastroCrudDao<EnderecoCorrespondencia> implements
		EnderecoCorrespondenciaDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISAR_ENDERECOS_CORRESPONDENCIA";
	
	/** A constante NOME_COMANDO_CONSULTAR_POR_PESSOA. */
	private static final String NOME_COMANDO_CONSULTAR_POR_PESSOA = 
			"CONSULTAR_ENDERECO_CORRESPONDENCIA_PESSOA";
	
	/** A constante NOME_COMANDO_CONSULTAR_INSTITUICOES_ENDERECO_POR_PESSOA. */
	private static final String NOME_COMANDO_CONSULTAR_INSTITUICOES_ENDERECO_POR_PESSOA = 
			"CONSULTAR_INSTITUICOES_ENDERECO_CORRESPONDENCIA_PESSOA";
	
	/**
	 * Instancia um novo EnderecoCorrespondenciaDAOImpl.
	 */
	public EnderecoCorrespondenciaDAOImpl() {
		super(EnderecoCorrespondencia.class, NOME_COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	public EnderecoCorrespondencia consultar(PessoaCompartilhamento pessoa, Instituicao instituicao)
			throws BancoobException {
		
		EnderecoCorrespondencia correspondencia = null;

		Comando comando = getComando(NOME_COMANDO_CONSULTAR_POR_PESSOA);
		comando.adicionarVariavel("instituicao", instituicao);
		comando.adicionarVariavel("pessoaCompartilhamento", pessoa);
		comando.configurar();

		try {
			Query query = comando.criarQuery(getEntityManager());
			correspondencia = (EnderecoCorrespondencia) query.getSingleResult();
 		} catch (NoResultException e) {
 			getLogger().debug("Endereco de correspondencia nao encontrado");
		} finally {
			fecharComando(comando);
		}

		return correspondencia;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> listarInstituicoesVinculadas(Endereco endereco)
			throws BancoobException {
		
		Comando comando = getComando(NOME_COMANDO_CONSULTAR_INSTITUICOES_ENDERECO_POR_PESSOA);
		comando.adicionarVariavel("endereco", endereco);
		comando.configurar();
		
		try {
			return comando.criarQuery(getEntityManager()).getResultList();
		} finally {
			fecharComando(comando);
		}
		
	}
	
}
