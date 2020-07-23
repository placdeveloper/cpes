/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoCapturaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoCapturaServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.CAPESCadastroCrudDaoIF;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoCapturaDAO;
import br.com.sicoob.capes.negocio.entidades.TipoCaptura;

/**
 * Implementa as operações do serviço de tipo de captura.
 * 
 * @author Erico.Junior
 */
@Stateless
@Local( { TipoCapturaServicoLocal.class })
@Remote( { TipoCapturaServicoRemote.class })
public class TipoCapturaServicoEJB extends CAPESCadastroCrudServicoEJB<TipoCaptura>
		implements TipoCapturaServicoRemote, TipoCapturaServicoLocal {

	@Inject
	@Default
	private TipoCapturaDAO tipoCapturaDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESCadastroCrudDaoIF<TipoCaptura> getDAO() {
		return tipoCapturaDAO;
	}

}