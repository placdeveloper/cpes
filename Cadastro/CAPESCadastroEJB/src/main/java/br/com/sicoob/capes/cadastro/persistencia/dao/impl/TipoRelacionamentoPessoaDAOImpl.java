/* 
 * Sicoob
 * TipoRelacionamentoPessoaDAOImpl.java 
 * Criado em: 08/08/2011
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.io.Serializable;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;
import br.com.sicoob.capes.negocio.entidades.TipoRelacionamentoPessoa;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoRelacionamentoPessoaDAO;

/**
 * DAO para {@link TipoRelacionamentoPessoa}
 *
 * 08/08/2011
 * @author Rodrigo.Chaves
 */
public class TipoRelacionamentoPessoaDAOImpl extends
		CAPESCadastroCrudDominioDao<TipoRelacionamentoPessoa> implements
		TipoRelacionamentoPessoaDAO {

	/** A constante PESQUISA_TIPO_RELACIONAMENTO_POR_TIPOS_PESSOA. */
	private static final String PESQUISA_TIPO_RELACIONAMENTO_POR_TIPOS_PESSOA = 
		"PESQUISA_TIPO_RELACIONAMENTO_POR_TIPOS_PESSOA";
	
	/** A constante PESQUISA_TIPO_RELACIONAMENTO_PRODUTOS_BANCOOB. */
	private static final String PESQUISA_TIPO_RELACIONAMENTO_PRODUTOS_BANCOOB = 
		"PESQUISA_TIPO_RELACIONAMENTO_PRODUTOS_BANCOOB";
	
	/** A constante NOME_COMANDO_PESQUISAR_PROXIMO_CODIGO. */
	private static final String NOME_COMANDO_PESQUISAR_PROXIMO_CODIGO = 
		"PESQUISA_PROXIMO_CODIGO_TIPO_RELACIONAMENTO_PESSOA";
	
	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = 
		"PESQUISA_TIPO_RELACIONAMENTO_PESSOA";

	/**
	 * Construtor
	 */
	public TipoRelacionamentoPessoaDAOImpl() {
		super(TipoRelacionamentoPessoa.class, NOME_COMANDO_PESQUISAR,
				NOME_COMANDO_PESQUISAR_PROXIMO_CODIGO);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<TipoRelacionamentoPessoa> pesquisarPorTiposPessoa(
			TipoPessoa tipoPessoaRelacionamento, TipoPessoa tipoPessoaRelacionada)
			throws BancoobException {
		
		Comando comando = null;
		try {
			comando = getComando(PESQUISA_TIPO_RELACIONAMENTO_POR_TIPOS_PESSOA);
			comando.adicionarVariavel("tipoPessoaRelacionamento", tipoPessoaRelacionamento);
			comando.adicionarVariavel("tipoPessoaRelacionada", tipoPessoaRelacionada);
			comando.configurar();
			return criarQuery(comando).getResultList();
		} finally {
			fecharComando(comando);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(Serializable chave) throws BancoobException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<TipoRelacionamentoPessoa> pesquisarTiposRelacionamentosProdutosBancoob()
			throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando(PESQUISA_TIPO_RELACIONAMENTO_PRODUTOS_BANCOOB);
			comando.configurar();
			return criarQuery(comando).getResultList();
		} finally {
			fecharComando(comando);
		}
	}
	
}
