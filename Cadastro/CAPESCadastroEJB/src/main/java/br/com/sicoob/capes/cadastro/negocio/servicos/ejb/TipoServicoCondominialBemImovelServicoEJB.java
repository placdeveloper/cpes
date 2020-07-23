package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoServicoCondominialBemImovelServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoServicoCondominialBemImovelServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoServicoCondominialBemImovelDAO;
import br.com.sicoob.capes.negocio.entidades.bem.TipoServicoCondominialBemImovel;

@Stateless
@Local(TipoServicoCondominialBemImovelServicoLocal.class)
@Remote(TipoServicoCondominialBemImovelServicoRemote.class)
public class TipoServicoCondominialBemImovelServicoEJB extends CAPESCadastroCrudServicoEJB<TipoServicoCondominialBemImovel> implements TipoServicoCondominialBemImovelServicoLocal, TipoServicoCondominialBemImovelServicoRemote {

	@Inject
	@Default
	private TipoServicoCondominialBemImovelDAO dao;

	@Override
	protected TipoServicoCondominialBemImovelDAO getDAO() {
		return dao;
	}

}