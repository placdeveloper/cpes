package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoBeneficiarioSicorServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoBeneficiarioSicorServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoBeneficiarioSicorDAO;
import br.com.sicoob.capes.negocio.entidades.TipoBeneficiarioSicor;

/**
 * Classe com os serviços de tipo beneficiários do sicor.
 *
 * @author Bruno.Carneiro
 */
@Stateless
@Local(TipoBeneficiarioSicorServicoLocal.class)
@Remote(TipoBeneficiarioSicorServicoRemote.class)
public class TipoBeneficiarioSicorServicoEJB extends CAPESCadastroCrudServicoEJB<TipoBeneficiarioSicor> implements TipoBeneficiarioSicorServicoLocal, TipoBeneficiarioSicorServicoRemote {

	@Inject
	@Default
	private TipoBeneficiarioSicorDAO dao;

	@Override
	protected TipoBeneficiarioSicorDAO getDAO() {
		return dao;
	}

}