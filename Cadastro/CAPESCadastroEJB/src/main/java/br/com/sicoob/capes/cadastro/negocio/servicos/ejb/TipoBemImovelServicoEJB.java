package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoBemImovelServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoBemImovelServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoBemImovelDAO;
import br.com.sicoob.capes.negocio.entidades.bem.TipoBemImovel;

/**
 * Classe com os serviços de tipo de bem imóvel.
 *
 * @author Bruno.Carneiro
 */
@Stateless
@Local(TipoBemImovelServicoLocal.class)
@Remote(TipoBemImovelServicoRemote.class)
public class TipoBemImovelServicoEJB extends CAPESCadastroCrudServicoEJB<TipoBemImovel> implements TipoBemImovelServicoLocal, TipoBemImovelServicoRemote {

	@Inject
	@Default
	private TipoBemImovelDAO dao;

	@Override
	protected TipoBemImovelDAO getDAO() {
		return dao;
	}

}