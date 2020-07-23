/* 
 * Sicoob
 * OrgaoEmissorCertidaoServicoEJB.java 
 * Criado em: 12/07/2011
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.OrgaoEmissorCertidaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.OrgaoEmissorCertidaoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.OrgaoEmissorCertidaoDAO;
import br.com.sicoob.capes.negocio.entidades.OrgaoEmissorCertidao;

/**
 * Servico para {@link OrgaoEmissorCertidao}
 * 
 * 12/07/2011
 * 
 * @author Rodrigo.Chaves
 */
@Stateless
@Local(OrgaoEmissorCertidaoServicoLocal.class)
@Remote(OrgaoEmissorCertidaoServicoRemote.class)
public class OrgaoEmissorCertidaoServicoEJB extends
		CAPESCadastroCrudServicoEJB<OrgaoEmissorCertidao> implements
		OrgaoEmissorCertidaoServicoRemote, OrgaoEmissorCertidaoServicoLocal {

	@Inject
	@Default
	private OrgaoEmissorCertidaoDAO orgaoEmissorCertidaoDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected OrgaoEmissorCertidaoDAO getDAO() {
		return this.orgaoEmissorCertidaoDAO;
	}

}
