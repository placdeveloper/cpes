/*
 * SICOOB
 * 
 * CAPESComumResourceBundle.java(br.com.sicoob.capes.comum.infraestrutura.mensagens.CAPESComumResourceBundle)
 */
package br.com.sicoob.capes.comum.infraestrutura.mensagens;

import java.util.ResourceBundle;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.infraestrutura.mensagens.CorporativoResourceBundle;

/**
 * Classe responsavel pela carga das mensagens do sistema CAPESComum
 * 
 * @author Stefanini IT Solutions
 */
public final class CAPESComumResourceBundle extends BancoobResourceBundle {

	/** Nome do arquivo de mensagens do sistema "CAPESComum". */
	public static final String CAPESCOMUM_SICOOB_PROPERTIES = "capes.comum.bancoob.properties";

	/** Resource bundle a ser usada. */
	private static CAPESComumResourceBundle bundle = new CAPESComumResourceBundle();

	/**
	 * Retorna o bundle a ser usado.
	 * 
	 * @return o bundle a ser usado.
	 */
	public static CAPESComumResourceBundle getInstance() {
		return bundle;
	}

	/**
	 * Inicia o bundle com as suas propriedades padrao.
	 */
	private CAPESComumResourceBundle() {
		this(CAPESCOMUM_SICOOB_PROPERTIES);
	}
	
	/**
	 * Cria uma nova instância de CAPES comum resource bundle.
	 * 
	 * @param arquivoProperties
	 *            the arquivo properties
	 */
	protected CAPESComumResourceBundle(String arquivoProperties) {
		this(arquivoProperties, CorporativoResourceBundle.getInstance());
	}

	/**
	 * Cria uma nova instância de CAPES comum resource bundle.
	 * 
	 * @param arquivoProperties
	 *            the arquivo properties
	 * @param resourcePai
	 *            the resource pai
	 */
	protected CAPESComumResourceBundle(String arquivoProperties, ResourceBundle resourcePai) {
		super(arquivoProperties, resourcePai);
	}
}
