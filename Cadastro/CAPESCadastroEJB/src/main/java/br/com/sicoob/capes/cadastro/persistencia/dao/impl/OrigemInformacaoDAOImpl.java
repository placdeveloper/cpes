/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import javax.persistence.Query;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.cadastro.persistencia.dao.OrigemInformacaoDAO;
import br.com.sicoob.capes.negocio.entidades.OrigemInformacao;

/**
 * DAO para as origens das informações (Fontes).
 * 
 * @author Erico.Junior
 */
public class OrigemInformacaoDAOImpl extends CAPESCadastroCrudDao<OrigemInformacao>
		implements OrigemInformacaoDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_CONSULTA";
	
	/** A constante OBTER_ORIGEM_POR_NOME. */
	private static final String OBTER_ORIGEM_POR_NOME = "OBTER_ORIGEM_POR_NOME";

	/**
	 * Construtor do DAO.
	 */
	public OrigemInformacaoDAOImpl() {
		super(OrigemInformacao.class, NOME_COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	public OrigemInformacao obterOrigemPorNome(OrigemInformacao origem) throws BancoobException {
		OrigemInformacao origemInformacao = null;
		Comando comando = getComando(OBTER_ORIGEM_POR_NOME);
		comando.adicionarVariavel("nomeOrigemInformacao", origem.getNomeOrigemInfo());
		comando.configurar();

		try {
			Query query = comando.criarQuery(getEntityManager());
			origemInformacao = (OrigemInformacao) query.getSingleResult();
		} finally {
			fecharComando(comando);
		}

		return origemInformacao;
	}
	
}
