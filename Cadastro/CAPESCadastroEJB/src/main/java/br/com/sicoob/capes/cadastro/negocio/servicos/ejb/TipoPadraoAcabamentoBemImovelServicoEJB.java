package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoPadraoAcabamentoBemImovelServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoPadraoAcabamentoBemImovelServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoPadraoAcabamentoBemImovelDAO;
import br.com.sicoob.capes.negocio.entidades.bem.TipoPadraoAcabamentoBemImovel;

@Stateless
@Local(TipoPadraoAcabamentoBemImovelServicoLocal.class)
@Remote(TipoPadraoAcabamentoBemImovelServicoRemote.class)
public class TipoPadraoAcabamentoBemImovelServicoEJB extends CAPESCadastroCrudServicoEJB<TipoPadraoAcabamentoBemImovel> implements TipoPadraoAcabamentoBemImovelServicoLocal, TipoPadraoAcabamentoBemImovelServicoRemote {

	@Inject
	@Default
	private TipoPadraoAcabamentoBemImovelDAO dao;

	@Override
	protected TipoPadraoAcabamentoBemImovelDAO getDAO() {
		return dao;
	}

}