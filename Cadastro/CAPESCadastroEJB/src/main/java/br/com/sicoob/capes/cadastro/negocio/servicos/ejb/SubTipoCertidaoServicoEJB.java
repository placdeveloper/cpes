/* 
 * Sicoob
 * SubTipoCertidaoServicoEJB.java 
 * Criado em: 12/07/2011
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.SubTipoCertidaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.SubTipoCertidaoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.SubTipoCertidaoDAO;
import br.com.sicoob.capes.negocio.entidades.SubTipoCertidao;

/**
 * Servico para {@link SubTipoCertidao}
 * 
 * 12/07/2011
 * 
 * @author Rodrigo.Chaves
 */
@Stateless
@Local(SubTipoCertidaoServicoLocal.class)
@Remote(SubTipoCertidaoServicoRemote.class)
public class SubTipoCertidaoServicoEJB extends
		CAPESCadastroCrudServicoEJB<SubTipoCertidao> implements
		SubTipoCertidaoServicoRemote, SubTipoCertidaoServicoLocal {

	@Inject
	@Default
	protected SubTipoCertidaoDAO subTipoCertidaoDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected SubTipoCertidaoDAO getDAO() {
		return this.subTipoCertidaoDAO;
	}

}
