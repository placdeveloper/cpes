/* 
 * Sicoob
 * OrgaoEmissorCertidaoDelegate.java 
 * Criado em: 12/07/2011
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.OrgaoEmissorCertidaoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.OrgaoEmissorCertidao;

/**
 * 12/07/2011
 * 
 * @author Rodrigo.Chaves
 */
public class OrgaoEmissorCertidaoDelegate extends
		CAPESCadastroCrudDelegate<OrgaoEmissorCertidao, OrgaoEmissorCertidaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected OrgaoEmissorCertidaoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarOrgaoEmissorCertidaoServico();
	}

}
