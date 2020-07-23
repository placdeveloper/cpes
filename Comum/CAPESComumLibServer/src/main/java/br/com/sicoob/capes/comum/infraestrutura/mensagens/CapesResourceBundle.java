package br.com.sicoob.capes.comum.infraestrutura.mensagens;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;

/**
 * Classe responsavel pela carga das mensagens do sistema CapesComumLibServer
 * 
 */
public final class CapesResourceBundle extends BancoobResourceBundle {

	/** Nome do arquivo de mensagens do sistema "CapesComumLibServer". */
	public static final String NOME_ARQUIVO_MENSAGENS = "capes_mensagens_lib.bancoob.properties";

	/** Resource bundle a ser usada. */
	private static CapesResourceBundle bundle;

	/**
	 * Retorna o bundle a ser usado.
	 * 
	 * @return o bundle a ser usado.
	 */
	public static CapesResourceBundle getInstance() {
		if (bundle == null) {
			obterInstancia();
		}
		return bundle;
	}
	
	private static synchronized void obterInstancia() {
		if (bundle == null) {
			bundle = new CapesResourceBundle();
		}
	}

	/**
	 * Inicia o bundle com as suas propriedades padrao.
	 */
	private CapesResourceBundle() {
		super(NOME_ARQUIVO_MENSAGENS);
	}
	
}