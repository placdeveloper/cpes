package br.com.sicoob.capes.api.persistencia.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.api.negocio.vo.DadosClienteVO;
import br.com.sicoob.capes.api.persistencia.dao.DadosClienteDAO;

/**
 * A Classe DadosClienteDAOImpl.
 */
public class DadosClienteDAOImpl extends CAPESApiDAO<DadosClienteVO> implements DadosClienteDAO {

	/**
	 * Instancia um novo DadosClienteDAOImpl.
	 */
	public DadosClienteDAOImpl() {
		super("PESQUISAR_DADOS_CLIENTE");
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<DadosClienteVO> obterDadosClientePorInstituicaoFuncionario(Integer idInstituicao, Integer idFuncionario) {
		Comando comando = null;
		try {
			comando = getComando(getNomeComando());
			comando.adicionarVariavel("idFuncionario", idFuncionario);
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.configurar();

			return criarQuery(comando).getResultList();
		} catch (NoResultException e) {
			return null;
		} finally {
			fecharComando(comando);
		}
	}

}