package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.AtividadeEconomicaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.AtividadeEconomicaServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.AtividadeEconomicaDAO;
import br.com.sicoob.capes.negocio.entidades.AtividadeEconomica;

/**
 * EJB contendo servicos relacionados a AtividadeEconomica.
 */
@Stateless
@Local({ AtividadeEconomicaServicoLocal.class })
@Remote({ AtividadeEconomicaServicoRemote.class })
public class AtividadeEconomicaServicoEJB extends CAPESCadastroCrudServicoEJB<AtividadeEconomica> implements AtividadeEconomicaServicoLocal, AtividadeEconomicaServicoRemote {

	@Inject
	@Default
	private AtividadeEconomicaDAO atividadeEconomicaDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AtividadeEconomicaDAO getDAO() {
		return atividadeEconomicaDAO;
	}

}