package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoChassiBemServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoChassiBemServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoChassiBemDAO;
import br.com.sicoob.capes.negocio.entidades.bem.TipoChassiBem;

/**
 * Classe com os serviços de tipo de chassi do bem.
 *
 * @author Bruno.Carneiro
 */
@Stateless
@Local(TipoChassiBemServicoLocal.class)
@Remote(TipoChassiBemServicoRemote.class)
public class TipoChassiBemServicoEJB extends CAPESCadastroCrudServicoEJB<TipoChassiBem> implements TipoChassiBemServicoLocal, TipoChassiBemServicoRemote {

	@Inject
	@Default
	private TipoChassiBemDAO dao;

	@Override
	protected TipoChassiBemDAO getDAO() {
		return dao;
	}

}