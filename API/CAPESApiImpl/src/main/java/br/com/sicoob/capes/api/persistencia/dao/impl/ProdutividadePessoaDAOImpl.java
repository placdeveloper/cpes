package br.com.sicoob.capes.api.persistencia.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.api.negocio.vo.ProdutividadePessoaVO;
import br.com.sicoob.capes.api.persistencia.dao.ProdutividadePessoaDAO;

/**
 * A Classe ProdutividadePessoaDAOImpl.
 */
public class ProdutividadePessoaDAOImpl extends CAPESApiDAO<ProdutividadePessoaVO> implements ProdutividadePessoaDAO {

	/**
	 * Instancia um novo ProdutividadePessoaDAOImpl.
	 */
	public ProdutividadePessoaDAOImpl() {
		super("PESQUISAR_PRODUTIVIDADE_PESSOA");
	}
	
	/**
	 * {@inheritDoc}
	 */
	public ProdutividadePessoaVO obterPorId(Serializable id, List<Short> situacoes) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando(getNomeComando());
			comando.adicionarVariavel("id", id);
			comando.adicionarVariavel("situacoes", situacoes);
			comando.configurar();
			return (ProdutividadePessoaVO) comando.criarQuery(getEntityManager()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			fecharComando(comando);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<ProdutividadePessoaVO> obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, List<Short> situacoes) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando(getNomeComando());
			getLogger().debug("ProdutividadePessoaDAO obterPorPessoaInstituicao - idPessoa:" + idPessoa + " idInstituicao:" + idInstituicao);

			comando.adicionarVariavel(ID_PESSOA, idPessoa);
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.adicionarVariavel("inserirDataHoraInicio", Boolean.TRUE);
			comando.adicionarVariavel("situacoes", situacoes);
			comando.configurar();

			List<ProdutividadePessoaVO> retorno = criarQuery(comando).getResultList();
			getLogger().debug("ProdutividadePessoaDAO obterPorPessoaInstituicao - Retorno:" + retorno);
			return retorno;
		} finally {
			fecharComando(comando);
		}
	}
	
}