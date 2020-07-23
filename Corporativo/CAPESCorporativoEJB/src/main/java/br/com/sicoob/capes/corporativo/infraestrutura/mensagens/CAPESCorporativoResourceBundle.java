package br.com.sicoob.capes.corporativo.infraestrutura.mensagens;

import java.util.ResourceBundle;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.infraestrutura.mensagens.CorporativoResourceBundle;

/**
 * A Classe CAPESCorporativoResourceBundle.
 * 
 * @author bruno.carneiro
 */
public final class CAPESCorporativoResourceBundle extends BancoobResourceBundle {

	/** A constante CAPES_CORPORATIVO_SICOOB_PROPERTIES. */
	private static final String CAPES_CORPORATIVO_SICOOB_PROPERTIES = "capes.corporativo.bancoob.properties";

	/** O atributo bundle. */
	private static CAPESCorporativoResourceBundle bundle = new CAPESCorporativoResourceBundle();

	/**
	 * Recupera a unica instancia de CAPESCorporativoResourceBundle.
	 * 
	 * @return uma instancia de CAPESCorporativoResourceBundle
	 */
	public static CAPESCorporativoResourceBundle getInstance() {
		return bundle;
	}

	/**
	 * Instancia um novo CAPESCorporativoResourceBundle.
	 */
	private CAPESCorporativoResourceBundle() {
		this(CAPES_CORPORATIVO_SICOOB_PROPERTIES);
	}

	/**
	 * Instancia um novo CAPESCorporativoResourceBundle.
	 * 
	 * @param arquivoProperties
	 *            o valor de arquivo properties
	 */
	protected CAPESCorporativoResourceBundle(String arquivoProperties) {
		this(arquivoProperties, CorporativoResourceBundle.getInstance());
	}

	/**
	 * Instancia um novo CAPESCorporativoResourceBundle.
	 * 
	 * @param arquivoProperties
	 *            o valor de arquivo properties
	 * @param resourcePai
	 *            o valor de resource pai
	 */
	protected CAPESCorporativoResourceBundle(String arquivoProperties, ResourceBundle resourcePai) {
		super(arquivoProperties, resourcePai);
	}
}