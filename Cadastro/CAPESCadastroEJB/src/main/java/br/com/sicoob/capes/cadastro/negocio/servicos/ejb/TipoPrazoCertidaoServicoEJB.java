/* 
 * Sicoob
 * TipoPrazoCertidaoServicoEJB.java 
 * Criado em: 12/07/2011
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoPrazoCertidaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoPrazoCertidaoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoPrazoCertidaoDAO;
import br.com.sicoob.capes.negocio.entidades.TipoPrazoCertidao;

/**
 * Serviço para {@link TipoPrazoCertidao}
 * 
 * 12/07/2011
 * 
 * @author Rodrigo.Chaves
 */
@Stateless
@Local(TipoPrazoCertidaoServicoLocal.class)
@Remote(TipoPrazoCertidaoServicoRemote.class)
public class TipoPrazoCertidaoServicoEJB extends
		CAPESCadastroCrudServicoEJB<TipoPrazoCertidao> implements
		TipoPrazoCertidaoServicoRemote, TipoPrazoCertidaoServicoLocal {

	@Inject
	@Default
	protected TipoPrazoCertidaoDAO tipoPrazoCertidaoDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoPrazoCertidaoDAO getDAO() {
		return this.tipoPrazoCertidaoDAO;
	}


}
