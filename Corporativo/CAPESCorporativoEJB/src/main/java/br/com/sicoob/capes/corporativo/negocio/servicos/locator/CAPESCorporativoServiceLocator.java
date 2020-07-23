package br.com.sicoob.capes.corporativo.negocio.servicos.locator;

import br.com.bancoob.negocio.servicos.locator.BancoobServiceLocator;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.AnotacaoServico;

/**
 * Classe responsável por localizar um EJB.
 * 
 * @author bruno.carneiro
 */
public final class CAPESCorporativoServiceLocator extends BancoobServiceLocator {

	/** O atributo locator. */
	private static CAPESCorporativoServiceLocator locator = new CAPESCorporativoServiceLocator();

	/**
	 * Recupera a unica instancia de CAPESCorporativoServiceLocator.
	 * 
	 * @return uma instancia de CAPESCorporativoServiceLocator
	 */
	public static CAPESCorporativoServiceLocator getInstance() {
		return locator;
	}

	/**
	 * Instancia um novo CAPESCorporativoServiceLocator.
	 */
	private CAPESCorporativoServiceLocator() {
		super("capes.corporativo");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code AnotacaoServico}.
	 * 
	 * @return O EJB solicitado
	 * @see AnotacaoServico
	 */
	public AnotacaoServico localizarAnotacaoServico() {
		return (AnotacaoServico) localizar("locator.capes.AnotacaoServico");
	}

}