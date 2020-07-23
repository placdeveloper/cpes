package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoEstadoConservacaoBemDAO;
import br.com.sicoob.capes.negocio.entidades.bem.TipoEstadoConservacaoBem;

/**
 * Classe com as operações de tipo estado conservação
 *
 * @author Bruno.Carneiro
 */
public class TipoEstadoConservacaoBemDAOImpl extends CAPESCadastroCrudDao<TipoEstadoConservacaoBem> implements TipoEstadoConservacaoBemDAO {

	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_TIPO_ESTADO_CONSERVACAO_POR_FILTRO";

	/**
	 * Construtor
	 */
	public TipoEstadoConservacaoBemDAOImpl() {
		super(TipoEstadoConservacaoBem.class, NOME_COMANDO_PESQUISAR);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<TipoEstadoConservacaoBem> listar(boolean bemImovel) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando(NOME_COMANDO_PESQUISAR);
			comando.adicionarVariavel("bemImovel", bemImovel);
			comando.configurar();

			return criarQuery(comando).getResultList();
		} finally {
			fecharComando(comando);
		}
	}

}