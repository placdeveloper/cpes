package br.com.sicoob.capes.api.persistencia.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.api.negocio.vo.TipoAnotacaoVO;
import br.com.sicoob.capes.api.persistencia.dao.TipoAnotacaoDAO;

/**
 * A Classe TipoAnotacaoDAOImpl.
 */
public class TipoAnotacaoDAOImpl extends CAPESApiDAO<TipoAnotacaoVO> implements TipoAnotacaoDAO {

	/**
	 * Instancia um novo TipoAnotacaoDAOImpl.
	 */
	public TipoAnotacaoDAOImpl() {
		super("OBTER_TIPOS_ANOTACAO");
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<TipoAnotacaoVO> obterTiposAnotacaoAtivos() throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando(getNomeComando());
			comando.configurar();
			return criarQuery(comando).getResultList();
		} finally {
			fecharComando(comando);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public TipoAnotacaoVO obterTipoAnotacaoPorId(Integer idTipoAnotacao) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando(getNomeComando());
			comando.adicionarVariavel("idTipoAnotacao", idTipoAnotacao);
			comando.configurar();

			return (TipoAnotacaoVO) criarQuery(comando).getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			fecharComando(comando);
		}
	}
}