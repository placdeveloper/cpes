package br.com.sicoob.capes.integracao.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.sicoob.capes.integracao.negocio.servicos.interfaces.CTAIntegracaoServicoLocal;
import br.com.sicoob.capes.integracao.negocio.servicos.interfaces.CTAIntegracaoServicoRemote;

/**
 * EJB contendo servicos relacionados a CTAIntegracao.
 * 
 * @author Bruno.Carneiro
 */
@Stateless
@Local(CTAIntegracaoServicoLocal.class)
@Remote(CTAIntegracaoServicoRemote.class)
public class CTAIntegracaoServicoEJB extends CAPESIntegracaoServicoEJB implements CTAIntegracaoServicoLocal, CTAIntegracaoServicoRemote {

	/**
	 * {@inheritDoc}
	 */
	public boolean isUsuarioNoGrupoAutorizador(UsuarioBancoob usuarioBancoob, String nomeGrupo) throws BancoobException {
		// TODO: aguardando API... conforme solicita��o, em um primeiro momento, todo usu�rio estar� no grupo de gestores.
		return true;
	}

}