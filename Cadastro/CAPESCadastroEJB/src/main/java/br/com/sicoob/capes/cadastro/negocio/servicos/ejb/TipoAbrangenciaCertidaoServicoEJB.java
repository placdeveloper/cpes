/* 
 * Sicoob
 * TipoAbrangenciaCertidaoServicoEJB.java 
 * Criado em: 12/07/2011
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoAbrangenciaCertidaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoAbrangenciaCertidaoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoAbrangenciaCertidaoDAO;
import br.com.sicoob.capes.negocio.entidades.TipoAbrangenciaCertidao;

/**
 * Servico para {@link TipoAbrangenciaCertidao}
 * 
 * 12/07/2011
 * 
 * @author Rodrigo.Chaves
 */
@Stateless
@Local(TipoAbrangenciaCertidaoServicoLocal.class)
@Remote(TipoAbrangenciaCertidaoServicoRemote.class)
public class TipoAbrangenciaCertidaoServicoEJB extends
		CAPESCadastroCrudServicoEJB<TipoAbrangenciaCertidao> implements
		TipoAbrangenciaCertidaoServicoRemote, TipoAbrangenciaCertidaoServicoLocal {

	@Inject
	@Default
	protected TipoAbrangenciaCertidaoDAO tipoAbrangenciaCertidaoDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoAbrangenciaCertidaoDAO getDAO() {
		return this.tipoAbrangenciaCertidaoDAO;
	}

}
