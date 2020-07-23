package br.com.sicoob.capes.integracao.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.sicoob.capes.integracao.negocio.servicos.interfaces.CTAIntegracaoServicoLocal;
import br.com.sicoob.capes.integracao.negocio.servicos.interfaces.CTAIntegracaoServicoRemote;
import br.com.sicoob.cta.backoffice.cliente.negocio.delegate.CTABackofficeClienteDelegate;

/**
 * EJB contendo servicos relacionados a CTAIntegracao.
 * 
 * @author Bruno.Carneiro
 */
@Stateless
@Local(CTAIntegracaoServicoLocal.class)
@Remote(CTAIntegracaoServicoRemote.class)
public class CTAIntegracaoServicoEJB extends CAPESIntegracaoServicoEJB implements CTAIntegracaoServicoLocal, CTAIntegracaoServicoRemote {

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public boolean isUsuarioNoGrupoAutorizador(UsuarioBancoob usuarioBancoob, String nomeGrupo) throws BancoobException {
		Integer idInstituicao = Integer.valueOf(usuarioBancoob.getIdInstituicao());
		Integer idUnidadeInstituicao = Integer.valueOf(usuarioBancoob.getIdUnidadeInstituicao());
		
		boolean retorno = CTABackofficeClienteDelegate.getInstancia().usuarioVinculadoGrupo(usuarioBancoob.getLogin(), nomeGrupo, idInstituicao, idUnidadeInstituicao);
		getLogger().debug("[CTAIntegracaoServicoEJB] CTABackofficeClienteDelegate.usuarioVinculadoGrupo ->  " + 
					" {"+ 	
					" login : " + usuarioBancoob.getLogin() + 
					" nomeGrupo : " + nomeGrupo +
					" idInstituicao : " + idInstituicao +
					" idUnidadeInstituicao : " + idUnidadeInstituicao +
					" }"+ 
					" RETORNO : " + retorno); 
		return retorno;
	}

}