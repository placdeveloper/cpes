package br.com.sicoob.capes.api.persistencia.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.api.negocio.vo.FuncionarioVO;
import br.com.sicoob.capes.api.persistencia.dao.FuncionarioDAO;

/**
 * A Classe FuncionarioDAOImpl.
 */
public class FuncionarioDAOImpl extends CAPESApiDAO<FuncionarioVO> implements FuncionarioDAO {

	/**
	 * Instancia um novo FuncionarioDAOImpl.
	 */
	public FuncionarioDAOImpl() {
		super("PESQUISAR_FUNCIONARIO");
	}

	/**
	 * {@inheritDoc}
	 */
	public FuncionarioVO obterFuncionarioPorCpfPessoaInstituicao(String cpf, Integer idInstituicao) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando(getNomeComando());
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.adicionarVariavel("cpf", cpf);
			comando.configurar();

			return (FuncionarioVO) criarQuery(comando).getSingleResult();
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
	public List<FuncionarioVO> obterFuncionariosPorInstituicaoFuncao(Integer idInstituicao, Short idFuncao, Integer idUnidadeInst) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando(getNomeComando());
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.adicionarVariavel("idFuncao", idFuncao);
			comando.adicionarVariavel("idUnidadeInst", idUnidadeInst);
			comando.configurar();

			return criarQuery(comando).getResultList();
		} finally {
			fecharComando(comando);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public FuncionarioVO obterGerente(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("OBTER_GERENTE");
			comando.adicionarVariavel("idPessoa", idPessoa);
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			comando.configurar();

			return (FuncionarioVO) criarQuery(comando).getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			fecharComando(comando);
		}
	}
}