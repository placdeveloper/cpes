/*
 * SICOOB
 * 
 * CAPESArquivosResourceBundle.java(br.com.sicoob.capes.api.infraestrutura.mensagens.CAPESApiResourceBundle)
 */
package br.com.sicoob.capes.comum.arquivos.infraestrutura.mensagens;

import java.util.ResourceBundle;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.infraestrutura.mensagens.CorporativoResourceBundle;

/**
 * Classe responsavel pela carga das mensagens do sistema CAPESApi
 * 
 * @author Stefanini IT Solutions
 */
public final class CAPESArquivosResourceBundle extends BancoobResourceBundle {

	/** Nome do arquivo de mensagens do sistema "CAPESApi". */
	public static final String CAPESAPI_SICOOB_PROPERTIES = "capes.arquivos.bancoob.properties";

	/** Resource bundle a ser usada. */
	private static final CAPESArquivosResourceBundle bundle = new CAPESArquivosResourceBundle();

	/**
	 * Retorna o bundle a ser usado.
	 * 
	 * @return o bundle a ser usado.
	 */
	public static CAPESArquivosResourceBundle getInstance() {
		return bundle;
	}

	/**
	 * Inicia o bundle com as suas propriedades padrao.
	 */
	private CAPESArquivosResourceBundle() {
		this(CAPESAPI_SICOOB_PROPERTIES);
	}

	/**
	 * Cria uma nova instância de CAPES api resource bundle.
	 * 
	 * @param arquivoProperties
	 *            the arquivo properties
	 */
	protected CAPESArquivosResourceBundle(String arquivoProperties) {
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
	protected CAPESArquivosResourceBundle(String arquivoProperties, ResourceBundle resourcePai) {
		super(arquivoProperties, resourcePai);
	}
}
