package br.com.sicoob.capes.integracao.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.sicoob.capes.integracao.negocio.servicos.CTAIntegracaoServico;
import br.com.sicoob.capes.integracao.negocio.servicos.locator.CAPESIntegracaoServiceLocator;

/**
 * The Class CTAIntegracaoDelegate.
 * 
 * @author Bruno.Carneiro
 */
public class CTAIntegracaoDelegate extends CAPESIntegracaoDelegate<CTAIntegracaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CTAIntegracaoServico localizarServico() {
		return CAPESIntegracaoServiceLocator.getInstance().localizarCTAIntegracaoServico();
	}

	/**
	 * Verifica se é usuario no grupo autorizador.
	 * 
	 * @param usuarioBancoob
	 *            the usuario bancoob
	 * @param nomeGrupo
	 *            the nome grupo
	 * @return true, se for usuario no grupo autorizador
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public boolean isUsuarioNoGrupoAutorizador(UsuarioBancoob usuarioBancoob, String nomeGrupo) throws BancoobException {
		return getServico().isUsuarioNoGrupoAutorizador(usuarioBancoob, nomeGrupo);
	}

}