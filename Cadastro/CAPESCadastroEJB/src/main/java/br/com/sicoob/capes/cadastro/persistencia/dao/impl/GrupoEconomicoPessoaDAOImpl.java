package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.util.Arrays;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.cadastro.persistencia.dao.GrupoEconomicoPessoaDAO;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoEconomicoPessoa;

/**
 * A Classe GrupoEconomicoPessoaDAOImpl.
 */
public class GrupoEconomicoPessoaDAOImpl extends
		CAPESCadastroCrudDao<GrupoEconomicoPessoa> implements GrupoEconomicoPessoaDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_GRUPO_ECONOMICO_PESSOA_POR_FILTRO";
	
	/** A constante NOME_COMANDO_PESQUISAR_POR_PESSOA. */
	private static final String NOME_COMANDO_PESQUISAR_POR_PESSOA = "PESQUISAR_INTEGRANTES_GRUPO_ECONOMICO_POR_PESSOA";

	/**
	 * Instancia um novo GrupoEconomicoPessoaDAOImpl.
	 */
	public GrupoEconomicoPessoaDAOImpl() {
		super(GrupoEconomicoPessoa.class, NOME_COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<GrupoEconomicoPessoa> pesquisarPorPessoaInstituicao(Integer... idsPessoaInstituicao)
		throws BancoobException {
		
		Comando comando = getComando(NOME_COMANDO_PESQUISAR_POR_PESSOA);
		try {
			List<Integer> ids = Arrays.asList(idsPessoaInstituicao);
			comando.adicionarVariavel("idPessoas", ids);
			comando.configurar();
			return criarQuery(comando).getResultList();
		} finally {
			fecharComando(comando);
		}
	}

	public Long pesquisarNumeroRegistros(Integer idGrupo) throws BancoobException {
		Comando comando = getComando("PESQUISA_QUANTIDADE_GRUPO_ECONOMICO_PESSOA_POR_FILTRO");
		try {
			comando.adicionarVariavel("idGrupoEconomico", idGrupo);
			comando.configurar();
			return (Long) criarQuery(comando).getSingleResult();
		} finally {
			fecharComando(comando);
		}
	}
}