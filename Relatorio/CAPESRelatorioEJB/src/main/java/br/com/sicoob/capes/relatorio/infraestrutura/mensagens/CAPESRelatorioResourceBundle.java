package br.com.sicoob.capes.relatorio.infraestrutura.mensagens;

import java.util.ResourceBundle;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.infraestrutura.mensagens.CorporativoResourceBundle;

/**
 * Classe responsavel pela carga das mensagens do sistema CAPESRelatorio
 * 
 * @author Stefanini IT Solutions
 */
public final class CAPESRelatorioResourceBundle extends BancoobResourceBundle {

	/** Nome do arquivo de mensagens do sistema "CAPESRelatorio". */
	public static final String RELATORIOCAPES_SICOOB_PROPERTIES = "capes.relatorio.bancoob.properties";

	/** Resource bundle a ser usada. */
	private static CAPESRelatorioResourceBundle bundle;

	/**
	 * Retorna o bundle a ser usado.
	 * 
	 * @return o bundle a ser usado.
	 */
	public static CAPESRelatorioResourceBundle getInstance() {
		if (bundle == null) {
			synchronized (CAPESRelatorioResourceBundle.class) {
				if (bundle == null) {
					bundle = new CAPESRelatorioResourceBundle();
				}
			}
		}
		return bundle;
	}

	/**
	 * Inicia o bundle com as suas propriedades padrao.
	 */
	private CAPESRelatorioResourceBundle() {
		this(RELATORIOCAPES_SICOOB_PROPERTIES);
	}
	
	/**
	 * Instancia um novo CAPESRelatorioResourceBundle.
	 *
	 * @param arquivoProperties o valor de arquivo properties
	 */
	protected CAPESRelatorioResourceBundle(String arquivoProperties) {
		this(arquivoProperties, CorporativoResourceBundle.getInstance());
	}

	/**
	 * Instancia um novo CAPESRelatorioResourceBundle.
	 *
	 * @param arquivoProperties o valor de arquivo properties
	 * @param resourcePai o valor de resource pai
	 */
	protected CAPESRelatorioResourceBundle(String arquivoProperties, ResourceBundle resourcePai) {
		super(arquivoProperties, resourcePai);
	}
}
