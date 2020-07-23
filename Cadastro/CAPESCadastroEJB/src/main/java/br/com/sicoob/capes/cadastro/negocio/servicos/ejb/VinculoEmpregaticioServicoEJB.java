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
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.VinculoEmpregaticioServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.VinculoEmpregaticioServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.VinculoEmpregaticioDAO;
import br.com.sicoob.capes.negocio.entidades.VinculoEmpregaticio;

/**
 * @author Erico.Junior
 * 
 */
@Stateless
@Local( { VinculoEmpregaticioServicoLocal.class })
@Remote( { VinculoEmpregaticioServicoRemote.class })
public class VinculoEmpregaticioServicoEJB extends
		CAPESCadastroCrudDominioServicoEJB<VinculoEmpregaticio> implements
		VinculoEmpregaticioServicoRemote, VinculoEmpregaticioServicoLocal {

	@Inject
	@Default
	protected VinculoEmpregaticioDAO dao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected VinculoEmpregaticioDAO getDAO() {
		return dao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<VinculoEmpregaticio> listar() throws BancoobException {
		return super.listar();
	}

}
