// 21/03/2013 - 12:19:54
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.CnaeFiscalServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.CnaeFiscalServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.CnaeFiscalDAO;
import br.com.sicoob.capes.negocio.entidades.CnaeFiscal;

/**
 * @author Rodrigo.Chaves
 */
@Stateless
@Local(CnaeFiscalServicoLocal.class)
@Remote(CnaeFiscalServicoRemote.class)
public class CnaeFiscalServicoEJB extends CAPESCadastroCrudServicoEJB<CnaeFiscal>
		implements CnaeFiscalServicoRemote, CnaeFiscalServicoLocal {
	
	@Inject
	@Default
	protected CnaeFiscalDAO dao;

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected CnaeFiscalDAO getDAO() {
		return dao;
	}

}
