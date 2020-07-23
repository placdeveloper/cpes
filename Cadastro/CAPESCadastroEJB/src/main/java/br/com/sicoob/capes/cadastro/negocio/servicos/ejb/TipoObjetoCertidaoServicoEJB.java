/* 
 * Sicoob
 * TipoObjetoCertidaoServicoEJB.java 
 * Criado em: 12/07/2011
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoObjetoCertidaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoObjetoCertidaoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoObjetoCertidaoDAO;
import br.com.sicoob.capes.negocio.entidades.TipoObjetoCertidao;

/**
 * Servico para {@link TipoObjetoCertidao}
 * 
 * 12/07/2011
 * 
 * @author Rodrigo.Chaves
 */
@Stateless
@Local(TipoObjetoCertidaoServicoLocal.class)
@Remote(TipoObjetoCertidaoServicoRemote.class)
public class TipoObjetoCertidaoServicoEJB extends
		CAPESCadastroCrudServicoEJB<TipoObjetoCertidao> implements
		TipoObjetoCertidaoServicoRemote, TipoObjetoCertidaoServicoLocal {

	@Inject
	@Default
	protected TipoObjetoCertidaoDAO tipoObjetoCertidaoDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoObjetoCertidaoDAO getDAO() {
		return this.tipoObjetoCertidaoDAO;
	}

}
