package br.com.sicoob.capes.api.negocio.testes;

import java.security.Principal;

import org.jboss.security.SecurityAssociation;

import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;

/**
 * A Classe CAPESTesteAbstract.
 */
public abstract class CAPESTesteAbstract {

	/**
	 * O método Sets the principal contexto.
	 */
	protected void setPrincipalContexto() {
		SecurityAssociation.setPrincipal(getUsuarioBancoob());
	}

	/**
	 * Recupera o valor de usuarioBancoob.
	 *
	 * @return o valor de usuarioBancoob
	 */
	private Principal getUsuarioBancoob() {
		UsuarioBancoob u = new UsuarioBancoob();
		u.setLogin("daniel.feitosa");
		u.setCooperativa("3353");
		u.setDominio("bancoob_df");
		u.setIdInstituicao("910");
		u.setIdUnidadeInstituicao("2");
		u.setPac("02");
		return u;
	}
}