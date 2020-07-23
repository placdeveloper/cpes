/*
 * SICOOB
 * 
 * CAPESApiResourceBundle.java(br.com.sicoob.capes.api.infraestrutura.mensagens.CAPESApiResourceBundle)
 */
package br.com.sicoob.capes.api.infraestrutura.mensagens;

import java.util.ResourceBundle;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.infraestrutura.mensagens.CorporativoResourceBundle;

/**
 * Classe responsavel pela carga das mensagens do sistema CAPESApi
 * 
 * @author Stefanini IT Solutions
 */
public final class CAPESApiResourceBundle extends BancoobResourceBundle {

	/** Nome do arquivo de mensagens do sistema "CAPESApi". */
	public static final String CAPESAPI_SICOOB_PROPERTIES = "capes.api.bancoob.properties";

	/** Resource bundle a ser usada. */
	private static final CAPESApiResourceBundle bundle = new CAPESApiResourceBundle();

	/**
	 * Retorna o bundle a ser usado.
	 * 
	 * @return o bundle a ser usado.
	 */
	public static CAPESApiResourceBundle getInstance() {
		return bundle;
	}

	/**
	 * Inicia o bundle com as suas propriedades padrao.
	 */
	private CAPESApiResourceBundle() {
		this(CAPESAPI_SICOOB_PROPERTIES);
	}

	/**
	 * Cria uma nova instância de CAPES api resource bundle.
	 * 
	 * @param arquivoProperties
	 *            the arquivo properties
	 */
	protected CAPESApiResourceBundle(String arquivoProperties) {
		this(arquivoProperties, CorporativoResourceBundle.getInstance());
	}

	/**
	 * Cria uma nova instância de CAPES api resource bundle.
	 * 
	 * @param arquivoProperties
	 *            the arquivo properties
	 * @param resourcePai
	 *            the resource pai
	 */
	protected CAPESApiResourceBundle(String arquivoProperties, ResourceBundle resourcePai) {
		super(arquivoProperties, resourcePai);
	}
}
