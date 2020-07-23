package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.Tributacao;
import br.com.sicoob.capes.cadastro.persistencia.dao.TributacaoDAO;

/**
 * Classe que implementa a persistencia de Tributacao.
 *
 * @author Juan.Damasceno
 *
 */
public class TributacaoDAOImpl extends EntidadeCadastroDao<Tributacao> implements TributacaoDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_TRIBUTACAO_POR_PESSOA";

	/**
	 * Instancia um novo TributacaoDAOImpl.
	 */
	public TributacaoDAOImpl() {
		super(Tributacao.class, NOME_COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	public Tributacao obterPorPessoa(PessoaCompartilhamento pessoa) {

		Comando comando = getComando(NOME_COMANDO_PESQUISAR);
		comando.adicionarVariavel("idPessoa", pessoa.getId());

		comando.configurar();

		Query query = criarQuery(comando);
		Tributacao tributacao = null;

		try {
			tributacao = (Tributacao) query.getSingleResult();
		} catch (NoResultException e) {
			getLogger().alerta("Tributação não encontrada");
		}

		return tributacao;
	}
}