package br.com.sicoob.capes.integracao.infraestrutura.mensagens;

import java.util.ResourceBundle;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.infraestrutura.mensagens.CorporativoResourceBundle;

/**
 * Classe responsavel pela carga das mensagens do sistema CAPESIntegracao
 * 
 * @author Stefanini IT Solutions
 */
public final class CAPESIntegracaoResourceBundle extends BancoobResourceBundle {

	/** Nome do arquivo de mensagens do sistema "CAPESIntegracao". */
	public static final String CAPESINTEGRACAO_SICOOB_PROPERTIES = "capes.integracao.bancoob.properties";

	/** Resource bundle a ser usada. */
	private static CAPESIntegracaoResourceBundle bundle;

	/**
	 * Retorna o bundle a ser usado.
	 * 
	 * @return o bundle a ser usado.
	 */
	public static CAPESIntegracaoResourceBundle getInstance() {
		if (bundle == null) {
			synchronized (CAPESIntegracaoResourceBundle.class) {
				if (bundle == null) {
					bundle = new CAPESIntegracaoResourceBundle();
				}
			}
		}
		return bundle;
	}

	/**
	 * Inicia o bundle com as suas propriedades padrao.
	 */
	private CAPESIntegracaoResourceBundle() {
		this(CAPESINTEGRACAO_SICOOB_PROPERTIES);
	}

	/**
	 * Instancia um novo CAPESIntegracaoResourceBundle.
	 * 
	 * @param arquivoProperties
	 *            o valor de arquivo properties
	 */
	protected CAPESIntegracaoResourceBundle(String arquivoProperties) {
		this(arquivoProperties, CorporativoResourceBundle.getInstance());
	}

	/**
	 * Instancia um novo CAPESIntegracaoResourceBundle.
	 * 
	 * @param arquivoProperties
	 *            o valor de arquivo properties
	 * @param resourcePai
	 *            o valor de resource pai
	 */
	protected CAPESIntegracaoResourceBundle(String arquivoProperties, ResourceBundle resourcePai) {
		super(arquivoProperties, resourcePai);
	}
}