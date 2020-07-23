package br.com.sicoob.capes.integracao.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.vo.RiscoVO;
import br.com.sicoob.capes.integracao.negocio.servicos.CRLIntegracaoServico;
import br.com.sicoob.capes.integracao.negocio.servicos.locator.CAPESIntegracaoServiceLocator;

/**
 * A Classe CRLIntegracaoDelegate.
 * 
 * @author Bruno.Carneiro
 */
public class CRLIntegracaoDelegate extends CAPESIntegracaoDelegate<CRLIntegracaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CRLIntegracaoServico localizarServico() {
		return CAPESIntegracaoServiceLocator.getInstance().localizarCRLIntegracaoServico();
	}

	/**
	 * Obter risco.
	 * 
	 * @param idPessoa
	 *            o valor de id pessoa
	 * @param idInstituicao
	 *            o valor de id instituicao
	 * @return RiscoVO
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	public RiscoVO obterRisco(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return getServico().obterRisco(idPessoa, idInstituicao);
	}

}