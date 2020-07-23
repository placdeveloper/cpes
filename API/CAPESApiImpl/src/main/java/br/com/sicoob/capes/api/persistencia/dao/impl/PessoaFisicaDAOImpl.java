package br.com.sicoob.capes.api.persistencia.dao.impl;

import javax.persistence.NoResultException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.api.negocio.vo.PessoaFisicaVO;
import br.com.sicoob.capes.api.persistencia.dao.PessoaFisicaDAO;

/**
 * A Classe PessoaFisicaDAOImpl.
 */
public class PessoaFisicaDAOImpl extends CAPESApiDAO<PessoaFisicaVO> implements PessoaFisicaDAO {

	/**
	 * Instancia um novo PessoaFisicaDAOImpl.
	 */
	public PessoaFisicaDAOImpl() {
		super("PESQUISAR_PESSOA_FISICA");
	}

	public PessoaFisicaVO obterPorCPFInstituicao(String cpf, Integer idInstituicao) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando(getNomeComando());
			comando.adicionarVariavel("cpf", cpf);
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.configurar();

			return (PessoaFisicaVO) comando.criarQuery(getEntityManager()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			fecharComando(comando);
		}
	}

}