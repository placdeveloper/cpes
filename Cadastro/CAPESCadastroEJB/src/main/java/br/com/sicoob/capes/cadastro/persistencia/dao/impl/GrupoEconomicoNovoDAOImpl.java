package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.cadastro.persistencia.dao.GrupoEconomicoNovoDAO;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoNovo;

/**
 * Classe que implementa a persistencia de Referencia GrupoEconomicoNovoDAOImpl.
 * 
 * @author valdemar.xavier
 * 
 */
public class GrupoEconomicoNovoDAOImpl extends CAPESCadastroCrudDao<GrupoEconomicoNovo> implements GrupoEconomicoNovoDAO {

	/** A constante COMANDO_PESQUISAR_POR_FILTRO. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISAR_GRUPO_ECONOMICO_NOVO_POR_FILTRO";

	private static final String NOME_COMANDO_PESQUISAR_POR_PESSOA = "PESQUISA_GRUPO_ECONOMICO_NOVO_POR_PESSOA";

	/**
	 * Instancia um novo GrupoEconomicoNovoDAOImpl.
	 */
	public GrupoEconomicoNovoDAOImpl() {
		super(GrupoEconomicoNovo.class, NOME_COMANDO_PESQUISAR);
	}

	public void excluirEntidade(GrupoEconomicoNovo objeto) throws BancoobException {
		getEntityManager().remove(objeto);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<GrupoEconomicoNovo> listarPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) {
		Comando comando = null;
		try {
			comando = getComando(NOME_COMANDO_PESQUISAR_POR_PESSOA);
			comando.adicionarVariavel("idPessoa", idPessoa);
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			comando.configurar();
			Query query = comando.criarNativeQuery(getEntityManager());
			List<Object[]> list = query.getResultList();
			List<GrupoEconomicoNovo> lista = new ArrayList<>();
			if (list != null) {
				for (Object[] objectArray : list) {
					GrupoEconomicoNovo objeto = new GrupoEconomicoNovo((Integer) objectArray[0], (String) objectArray[1]);
					lista.add(objeto);
				}
			}
			return lista;
		} finally {
			fecharComando(comando);
		}
	}

}