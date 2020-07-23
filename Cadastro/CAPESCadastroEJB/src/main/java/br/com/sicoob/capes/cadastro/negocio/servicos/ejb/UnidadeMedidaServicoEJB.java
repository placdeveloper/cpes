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
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.UnidadeMedidaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.UnidadeMedidaServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.UnidadeMedidaDAO;
import br.com.sicoob.capes.negocio.entidades.UnidadeMedida;

/**
 * Serviço para as unidades de medida.
 * @author erico.junior
 */
@Stateless
@Local( { UnidadeMedidaServicoLocal.class })
@Remote( { UnidadeMedidaServicoRemote.class })
public class UnidadeMedidaServicoEJB extends
		CAPESCadastroCrudServicoEJB<UnidadeMedida> implements UnidadeMedidaServicoRemote, UnidadeMedidaServicoLocal {

	@Inject
	@Default
	protected UnidadeMedidaDAO unidadeMedidaDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected UnidadeMedidaDAO getDAO() {
		return unidadeMedidaDao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<UnidadeMedida> listar() throws BancoobException {
		return super.listar();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<UnidadeMedida> listar(ConsultaDto<UnidadeMedida> criterios)
			throws BancoobException {
		return super.listar(criterios);
	}

}
