package br.com.sicoob.capes.api.inclusao.infraestrutura.mensagens;

import java.util.ResourceBundle;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.infraestrutura.mensagens.CorporativoResourceBundle;

/**
 * A Classe CAPESApiInclusaoResourceBundle.
 * 
 * @author bruno.carneiro
 */
public final class CAPESApiInclusaoResourceBundle extends BancoobResourceBundle {

	/** A constante CAPES_API_INCLUSAO_SICOOB_PROPERTIES. */
	private static final String CAPES_API_INCLUSAO_SICOOB_PROPERTIES = "capes.api.inclusao.bancoob.properties";

	/** O atributo bundle. */
	private static CAPESApiInclusaoResourceBundle bundle = new CAPESApiInclusaoResourceBundle();

	/**
	 * Recupera a unica instancia de CAPESApiInclusaoResourceBundle.
	 * 
	 * @return uma instancia de CAPESApiInclusaoResourceBundle
	 */
	public static CAPESApiInclusaoResourceBundle getInstance() {
		return bundle;
	}

	/**
	 * Instancia um novo CAPESApiInclusaoResourceBundle.
	 */
	private CAPESApiInclusaoResourceBundle() {
		this(CAPES_API_INCLUSAO_SICOOB_PROPERTIES);
	}

	/**
	 * Instancia um novo CAPESApiInclusaoResourceBundle.
	 * 
	 * @param arquivoProperties
	 *            o valor de arquivo properties
	 */
	protected CAPESApiInclusaoResourceBundle(String arquivoProperties) {
		this(arquivoProperties, CorporativoResourceBundle.getInstance());
	}

	/**
	 * Instancia um novo CAPESApiInclusaoResourceBundle.
	 * 
	 * @param arquivoProperties
	 *            o valor de arquivo properties
	 * @param resourcePai
	 *            o valor de resource pai
	 */
	protected CAPESApiInclusaoResourceBundle(String arquivoProperties, ResourceBundle resourcePai) {
		super(arquivoProperties, resourcePai);
	}
}