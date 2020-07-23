package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoRelacionamentoBemImovelServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoRelacionamentoBemImovelServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoRelacionamentoBemImovelDAO;
import br.com.sicoob.capes.negocio.entidades.bem.TipoRelacionamentoBemImovel;

@Stateless
@Local(TipoRelacionamentoBemImovelServicoLocal.class)
@Remote(TipoRelacionamentoBemImovelServicoRemote.class)
public class TipoRelacionamentoBemImovelServicoEJB extends CAPESCadastroCrudServicoEJB<TipoRelacionamentoBemImovel> implements TipoRelacionamentoBemImovelServicoLocal, TipoRelacionamentoBemImovelServicoRemote {

	@Inject
	@Default
	private TipoRelacionamentoBemImovelDAO dao;

	@Override
	protected TipoRelacionamentoBemImovelDAO getDAO() {
		return dao;
	}
}
