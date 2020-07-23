package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoBemMovelServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoBemMovelServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoBemMovelDAO;
import br.com.sicoob.capes.negocio.entidades.bem.TipoBemMovel;

/**
 * Classe com os servi�os de tipo de bem m�vel 
 *
 * @author Bruno.Carneiro
 */
@Stateless
@Local(TipoBemMovelServicoLocal.class)
@Remote(TipoBemMovelServicoRemote.class)
public class TipoBemMovelServicoEJB extends CAPESCadastroCrudServicoEJB<TipoBemMovel> implements TipoBemMovelServicoLocal, TipoBemMovelServicoRemote {

	@Inject
	@Default
	private TipoBemMovelDAO dao;

	@Override
	protected TipoBemMovelDAO getDAO() {
		return dao;
	}

}