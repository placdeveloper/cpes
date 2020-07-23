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
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.RegimeCasamentoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.RegimeCasamentoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.RegimeCasamentoDAO;
import br.com.sicoob.capes.negocio.entidades.RegimeCasamento;

/**
 * Servico para o regime de casamento
 * 
 * @author erico.junior
 */
@Stateless
@Local( { RegimeCasamentoServicoLocal.class })
@Remote( { RegimeCasamentoServicoRemote.class })
public class RegimeCasamentoServicoEJB extends
		CAPESCadastroCrudServicoEJB<RegimeCasamento> implements RegimeCasamentoServicoRemote, RegimeCasamentoServicoLocal{

	@Inject
	@Default
	protected RegimeCasamentoDAO dao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RegimeCasamentoDAO getDAO() {
		return dao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RegimeCasamento> listar() throws BancoobException {
		return super.listar();
	}

}
