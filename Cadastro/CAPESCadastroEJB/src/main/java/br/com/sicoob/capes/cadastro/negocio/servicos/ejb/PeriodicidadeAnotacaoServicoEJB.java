package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.PeriodicidadeAnotacaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.PeriodicidadeAnotacaoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.CAPESCadastroCrudDaoIF;
import br.com.sicoob.capes.cadastro.persistencia.dao.PeriodicidadeAnotacaoDAO;
import br.com.sicoob.capes.negocio.entidades.PeriodicidadeAnotacao;

/**
 * Implementa as operações do serviço de anotações.
 * 
 * @author Rodrigo.Chaves
 */
@Stateless
@Local( { PeriodicidadeAnotacaoServicoLocal.class })
@Remote( { PeriodicidadeAnotacaoServicoRemote.class })
public class PeriodicidadeAnotacaoServicoEJB extends
		CAPESCadastroCrudServicoEJB<PeriodicidadeAnotacao>
		implements PeriodicidadeAnotacaoServicoRemote, PeriodicidadeAnotacaoServicoLocal {

	@Inject
	@Default
	private PeriodicidadeAnotacaoDAO periodicidadeAnotacaoDAO; 
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESCadastroCrudDaoIF<PeriodicidadeAnotacao> getDAO() {
		return periodicidadeAnotacaoDAO;
	}

}
