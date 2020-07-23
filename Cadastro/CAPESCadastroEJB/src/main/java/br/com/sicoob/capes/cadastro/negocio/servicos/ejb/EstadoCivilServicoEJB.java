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
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.EstadoCivilServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.EstadoCivilServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.EstadoCivilDAO;
import br.com.sicoob.capes.negocio.entidades.EstadoCivil;

/**
 * Serviço para o estado civil
 * 
 * @author erico.junior
 */
@Stateless
@Local( { EstadoCivilServicoLocal.class })
@Remote( { EstadoCivilServicoRemote.class })
public class EstadoCivilServicoEJB extends
		CAPESCadastroCrudServicoEJB<EstadoCivil> implements EstadoCivilServicoRemote, EstadoCivilServicoLocal{

	@Inject
	@Default
	private EstadoCivilDAO dao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EstadoCivilDAO getDAO() {
		return dao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<EstadoCivil> listar() throws BancoobException {
		return super.listar();
	}

}
