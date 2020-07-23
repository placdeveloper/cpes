package br.com.sicoob.capes.cadastro.infraestrutura.mensagens;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;

/**
 * Classe responsavel pela carga das mensagens do sistema CadastroUnicoClientesComum
 * 
 * @author Stefanini IT Solutions
 */
public final class CAPESCadastroResourceBundle extends BancoobResourceBundle {

	/** Nome do arquivo de mensagens do sistema "CadastroUnicoClientesComum". */
	public static final String CAPES_SICOOB_PROPERTIES = "capes.cadastro.bancoob.properties";

	/** Resource bundle a ser usada. */
	private static CAPESCadastroResourceBundle bundle;

	/**
	 * Retorna o bundle a ser usado.
	 * 
	 * @return o bundle a ser usado.
	 */
	public static CAPESCadastroResourceBundle getInstance() {
		if (bundle == null) {
			synchronized (CAPESCadastroResourceBundle.class) {
				if (bundle == null) {
					bundle = new CAPESCadastroResourceBundle();
				}
			}
		}
		return bundle;
	}

	/**
	 * Inicia o bundle com as suas propriedades padrao.
	 */
	private CAPESCadastroResourceBundle() {
		super(CAPES_SICOOB_PROPERTIES);
	}
	
}