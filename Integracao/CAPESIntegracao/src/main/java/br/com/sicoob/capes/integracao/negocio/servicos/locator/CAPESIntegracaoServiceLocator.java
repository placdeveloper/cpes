package br.com.sicoob.capes.integracao.negocio.servicos.locator;

import br.com.bancoob.negocio.servicos.locator.BancoobServiceLocator;
import br.com.sicoob.capes.integracao.negocio.servicos.ADMIntegracaoServico;
import br.com.sicoob.capes.integracao.negocio.servicos.CRLIntegracaoServico;
import br.com.sicoob.capes.integracao.negocio.servicos.CTAIntegracaoServico;
import br.com.sicoob.capes.integracao.negocio.servicos.CTZIntegracaoServico;
import br.com.sicoob.capes.integracao.negocio.servicos.GEDIntegracaoServico;
import br.com.sicoob.capes.integracao.negocio.servicos.GFTIntegracaoServico;
import br.com.sicoob.capes.integracao.negocio.servicos.ITXIntegracaoServico;
import br.com.sicoob.capes.integracao.negocio.servicos.LOCIntegracaoServico;
import br.com.sicoob.capes.integracao.negocio.servicos.SCIIntegracaoServico;

/**
 * Service Locator usado pelo sistema CAPESIntegracao.
 * 
 * @author Bruno.Carneiro
 */
public final class CAPESIntegracaoServiceLocator extends BancoobServiceLocator {

	/** O atributo locator. */
	private static CAPESIntegracaoServiceLocator locator;

	/**
	 * Singleton da class
	 * 
	 * @return A instancia da classe
	 */
	public static CAPESIntegracaoServiceLocator getInstance() {
		if (locator == null) {
			synchronized (CAPESIntegracaoServiceLocator.class) {
				if (locator == null) {
					locator = new CAPESIntegracaoServiceLocator();
				}
			}
		}

		return locator;
	}

	/**
	 * @param nomeAplicacao
	 */
	private CAPESIntegracaoServiceLocator() {
		super("capes.integracao");
	}

	/**
	 * Localiza o EJB que implementa GEDIntegracaoServico.
	 * 
	 * @return the GED integracao servico
	 */
	public GEDIntegracaoServico localizarGEDIntegracaoServico() {
		return (GEDIntegracaoServico) localizar("locator.capes.GEDIntegracaoServico");
	}

	/**
	 * Localiza o EJB que implementa GFTIntegracaoServico.
	 * 
	 * @return the GFT integracao servico
	 */
	public GFTIntegracaoServico localizarGFTIntegracaoServico() {
		return (GFTIntegracaoServico) localizar("locator.capes.GFTIntegracaoServico");
	}

	/**
	 * Localiza o EJB que implementa ITXIntegracaoServico.
	 * 
	 * @return the ITX integracao servico
	 */
	public ITXIntegracaoServico localizarITXIntegracaoServico() {
		return (ITXIntegracaoServico) localizar("locator.capes.ITXIntegracaoServico");
	}

	/**
	 * Localiza o EJB que implementa LOCIntegracaoServico.
	 * 
	 * @return the LOC integracao servico
	 */
	public LOCIntegracaoServico localizarLOCIntegracaoServico() {
		return (LOCIntegracaoServico) localizar("locator.capes.LOCIntegracaoServico");
	}

	/**
	 * Localiza o EJB que implementa SCIIntegracaoServico.
	 * 
	 * @return the SCI integracao servico
	 */
	public SCIIntegracaoServico localizarSCIIntegracaoServico() {
		return (SCIIntegracaoServico) localizar("locator.capes.SCIIntegracaoServico");
	}

	/**
	 * Localiza o EJB que implementa ADMIntegracaoServico.
	 * 
	 * @return the ADM integracao servico
	 */
	public ADMIntegracaoServico localizarADMIntegracaoServico() {
		return (ADMIntegracaoServico) localizar("locator.capes.ADMIntegracaoServico");
	}

	/**
	 * Localiza o EJB que implementa CTAIntegracaoServico.
	 * 
	 * @return the CTA integracao servico
	 */
	public CTAIntegracaoServico localizarCTAIntegracaoServico() {
		return (CTAIntegracaoServico) localizar("locator.capes.CTAIntegracaoServico");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code CRLIntegracaoServico}.
	 * 
	 * @return O EJB solicitado
	 * @see CRLIntegracaoServico
	 */
	public CRLIntegracaoServico localizarCRLIntegracaoServico() {
		return (CRLIntegracaoServico) localizar("locator.capes.CRLIntegracaoServico");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code CTZIntegracaoServico}.
	 * 
	 * @return O EJB solicitado
	 * @see CTZIntegracaoServico
	 */
	public CTZIntegracaoServico localizarCTZIntegracaoServico() {
		return (CTZIntegracaoServico) localizar("locator.capes.CTZIntegracaoServico");
	}

}