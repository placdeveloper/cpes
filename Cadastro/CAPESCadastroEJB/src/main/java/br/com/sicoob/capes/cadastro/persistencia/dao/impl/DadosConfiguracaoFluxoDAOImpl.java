package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.cadastro.persistencia.dao.DadosConfiguracaoFluxoDAO;
import br.com.sicoob.capes.negocio.entidades.DadosConfiguracaoFluxo;

/**
 * A Classe DadosConfiguracaoFluxoDAOImpl.
 */
public class DadosConfiguracaoFluxoDAOImpl extends
		CAPESCadastroCrudDao<DadosConfiguracaoFluxo> implements
		DadosConfiguracaoFluxoDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISAR_DADOS_FLUXO_POR_FILTRO";
	
	/** A constante COMANDO_OBTER_SIGLAS_PROCESSO. */
	private static final String COMANDO_OBTER_SIGLAS_PROCESSO = "OBTER_SIGLAS_PROCESSO";

	/**
	 * Instancia um novo DadosConfiguracaoFluxoDAOImpl.
	 */
	public DadosConfiguracaoFluxoDAOImpl() {
		super(DadosConfiguracaoFluxo.class, NOME_COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DadosConfiguracaoFluxo incluir(DadosConfiguracaoFluxo objeto) throws BancoobException {
		throw new UnsupportedOperationException();
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
	@Override
	public void alterar(DadosConfiguracaoFluxo objeto) throws BancoobException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	public DadosConfiguracaoFluxo obter(Boolean isResponsavel, Boolean isGestor, Boolean possuiDocumento) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando(NOME_COMANDO_PESQUISAR);

			comando.adicionarVariavel("isResponsavel", isResponsavel);
			comando.adicionarVariavel("isGestor", isGestor);
			comando.adicionarVariavel("possuiDocumento", possuiDocumento);

			comando.configurar();

			Query query = comando.criarQuery(getEntityManager());

			return (DadosConfiguracaoFluxo) query.getSingleResult();
		} finally {
			fecharComando(comando);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<String> obterSiglasProcesso() throws BancoobException {
		List<String> retorno;
		Comando comando = null;
		try {
			comando = getComando(COMANDO_OBTER_SIGLAS_PROCESSO);
			comando.configurar();

			retorno = criarQuery(comando).getResultList();
		} finally {
			fecharComando(comando);
		}
		return retorno;
	}

}
