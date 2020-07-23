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
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrauInstrucaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrauInstrucaoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.GrauInstrucaoDAO;
import br.com.sicoob.capes.negocio.entidades.GrauInstrucao;

/**
 * @author Erico.Junior
 * 
 */
@Stateless
@Local( { GrauInstrucaoServicoLocal.class })
@Remote( { GrauInstrucaoServicoRemote.class })
public class GrauInstrucaoServicoEJB extends
		CAPESCadastroCrudServicoEJB<GrauInstrucao> implements GrauInstrucaoServicoRemote, GrauInstrucaoServicoLocal {

	@Inject
	@Default
	private GrauInstrucaoDAO dao;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected GrauInstrucaoDAO getDAO() {
		return dao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<GrauInstrucao> listar() throws BancoobException {
		return super.listar();
	}

}
