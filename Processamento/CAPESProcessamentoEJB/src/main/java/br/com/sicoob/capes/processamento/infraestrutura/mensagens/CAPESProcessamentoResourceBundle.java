/*
 * SICOOB
 * 
 * CAPESProcessamentoResourceBundle.java(br.com.sicoob.capes.processamento.infraestrutura.mensagens.CAPESProcessamentoResourceBundle)
 */
package br.com.sicoob.capes.processamento.infraestrutura.mensagens;

import java.util.ResourceBundle;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.infraestrutura.mensagens.CorporativoResourceBundle;

/**
 * Classe responsavel pela carga das mensagens do sistema CAPESProcessamento
 * 
 * @author Stefanini IT Solutions
 */
public final class CAPESProcessamentoResourceBundle extends BancoobResourceBundle {

	/** Nome do arquivo de mensagens do sistema "CAPESProcessamento". */
	public static final String CAPES_PROCESSAMENTO_SICOOB_PROPERTIES = 
			"capes.processamento.bancoob.properties";

	/** Resource bundle a ser usada. */
	private static CAPESProcessamentoResourceBundle bundle = new CAPESProcessamentoResourceBundle();

	/**
	 * Retorna o bundle a ser usado.
	 * 
	 * @return o bundle a ser usado.
	 */
	public static CAPESProcessamentoResourceBundle getInstance() {
		return bundle;
	}

	/**
	 * Inicia o bundle com as suas propriedades padrao.
	 */
	private CAPESProcessamentoResourceBundle() {
		this(CAPES_PROCESSAMENTO_SICOOB_PROPERTIES);
	}
	
	/**
	 * Cria uma nova instância de CAPES processamento resource bundle.
	 * 
	 * @param arquivoProperties
	 *            the arquivo properties
	 */
	protected CAPESProcessamentoResourceBundle(String arquivoProperties) {
		this(arquivoProperties, CorporativoResourceBundle.getInstance());
	}

	/**
	 * Cria uma nova instância de CAPES processamento resource bundle.
	 * 
	 * @param arquivoProperties
	 *            the arquivo properties
	 * @param resourcePai
	 *            the resource pai
	 */
	protected CAPESProcessamentoResourceBundle(String arquivoProperties, ResourceBundle resourcePai) {
		super(arquivoProperties, resourcePai);
	}
}
