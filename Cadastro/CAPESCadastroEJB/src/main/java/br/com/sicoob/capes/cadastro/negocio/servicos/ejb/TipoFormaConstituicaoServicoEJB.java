/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoFormaConstituicaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoFormaConstituicaoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoFormaConstituicaoDAO;
import br.com.sicoob.capes.negocio.entidades.TipoFormaConstituicao;

/**
 * @author Erico.Junior
 * 
 */
@Stateless
@Local( { TipoFormaConstituicaoServicoLocal.class })
@Remote( { TipoFormaConstituicaoServicoRemote.class })
public class TipoFormaConstituicaoServicoEJB extends
		CAPESCadastroCrudServicoEJB<TipoFormaConstituicao> implements
		TipoFormaConstituicaoServicoRemote, TipoFormaConstituicaoServicoLocal {

	@Inject
	@Default
	protected TipoFormaConstituicaoDAO dao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoFormaConstituicaoDAO getDAO() {
		return dao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<TipoFormaConstituicao> listar() throws BancoobException {
		return super.listar();
	}

}
