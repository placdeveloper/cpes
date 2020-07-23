/*
 * SICOOB
 * 
 * CAPESFrontofficeResourceBundle.java(br.com.sicoob.capes.frontoffice.infraestrutura.mensagens.CAPESFrontofficeResourceBundle)
 */
package br.com.sicoob.capes.frontoffice.infraestrutura.mensagens;

import java.util.ResourceBundle;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.infraestrutura.mensagens.CorporativoResourceBundle;

/**
 * Classe responsavel pela carga das mensagens do sistema CAPESFrontOffice
 * 
 * @author Stefanini IT Solutions
 */
public final class CAPESFrontofficeResourceBundle extends BancoobResourceBundle {

	/** Nome do arquivo de mensagens do sistema "CAPESFrontOffice". */
	public static final String CAPESFRONTOFFICE_SICOOB_PROPERTIES = "capes.frontoffice.bancoob.properties";

	/** Resource bundle a ser usada. */
	private static CAPESFrontofficeResourceBundle bundle;

	/**
	 * Retorna o bundle a ser usado.
	 * 
	 * @return o bundle a ser usado.
	 */
	public static CAPESFrontofficeResourceBundle getInstance() {
		if (bundle == null) {
			synchronized (CAPESFrontofficeResourceBundle.class) {
				if (bundle == null) {
					bundle = new CAPESFrontofficeResourceBundle();
				}
			}
		}
		return bundle;
	}

	/**
	 * Inicia o bundle com as suas propriedades padrao.
	 */
	private CAPESFrontofficeResourceBundle() {
		this(CAPESFRONTOFFICE_SICOOB_PROPERTIES);
	}
	
	/**
	 * Cria uma nova instância de CAPES frontoffice resource bundle.
	 * 
	 * @param arquivoProperties
	 *            the arquivo properties
	 */
	protected CAPESFrontofficeResourceBundle(String arquivoProperties) {
		this(arquivoProperties, CorporativoResourceBundle.getInstance());
	}

	/**
	 * Cria uma nova instância de CAPES frontoffice resource bundle.
	 * 
	 * @param arquivoProperties
	 *            the arquivo properties
	 * @param resourcePai
	 *            the resource pai
	 */
	protected CAPESFrontofficeResourceBundle(String arquivoProperties, ResourceBundle resourcePai) {
		super(arquivoProperties, resourcePai);
	}
}
