package br.com.sicoob.capes.comum.negocio.servicos.ejb;

import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.servicos.interfaces.ClienteServicoLocal;
import br.com.sicoob.capes.comum.negocio.servicos.interfaces.ClienteServicoRemote;
import br.com.sicoob.capes.comum.persistencia.dao.ClienteDAO;

/**
 * EJB contendo servicos relacionados a Cliente.
 */
@Stateless
@Local(ClienteServicoLocal.class)
@Remote(ClienteServicoRemote.class)
public class ClienteServicoEJB extends CAPESComumServicoEJB implements ClienteServicoLocal, ClienteServicoRemote {
	
	/** O atributo cliente dao. */
	@Inject
	@Default
	private ClienteDAO clienteDAO;

	/**
	 * Recupera dao.
	 * 
	 * @return dao
	 */
	private ClienteDAO getDao() {
		return clienteDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Map<String, Object> obterPorIdInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return getDao().obterPorIdInstituicao(idPessoa, idInstituicao);
	}

	/**
	 * {@inheritDoc}
	 */
	public Map<String, Object> obterPorCpfCnpjInstituicao(String cpfCnpj, Integer idInstituicao) throws BancoobException {
		return getDao().obterPorCpfCnpjInstituicao(cpfCnpj, idInstituicao);
	}

}