package br.com.sicoob.capes.integracao.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.sicoob.capes.integracao.negocio.servicos.interfaces.CTAIntegracaoServicoLocal;
import br.com.sicoob.capes.integracao.negocio.servicos.interfaces.CTAIntegracaoServicoRemote;
import br.com.sicoob.cta.caa.processamento.negocio.delegates.AutenticacaoAutorizacaoDelegate;

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
		Integer idInstituicao = Integer.valueOf(usuarioBancoob.getIdInstituicao());
		Integer idUnidadeInstituicao = Integer.valueOf(usuarioBancoob.getIdUnidadeInstituicao());

		AutenticacaoAutorizacaoDelegate ctaDelegate = new AutenticacaoAutorizacaoDelegate();
		return ctaDelegate.usuarioVinculadoGrupo(usuarioBancoob.getLogin(), nomeGrupo, idInstituicao, idUnidadeInstituicao);
	}

}