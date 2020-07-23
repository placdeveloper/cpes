package br.com.sicoob.capes.replicacao.infraestrutura.mensagens;

import java.util.ResourceBundle;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.infraestrutura.mensagens.CorporativoResourceBundle;

/**
 * Classe responsavel pela carga das mensagens do sistema ReplicacaoClientesBO
 * 
 * @author Stefanini IT Solutions
 */
public final class CAPESReplicacaoComumResourceBundle extends BancoobResourceBundle {

	/** Nome do arquivo de mensagens do sistema "ReplicacaoClientesBO". */
	public static final String CAPES_REPLICACAO_COMUM_PROPERTIES = 
			"capes.replicacao.comum.bancoob.properties";

	/** Resource bundle a ser usada. */
	private static CAPESReplicacaoComumResourceBundle bundle;

	/**
	 * Retorna o bundle a ser usado.
	 * 
	 * @return o bundle a ser usado.
	 */
	public static CAPESReplicacaoComumResourceBundle getInstance() {
		if (bundle == null) {
			synchronized (CAPESReplicacaoComumResourceBundle.class) {
				if (bundle == null) {
					bundle = new CAPESReplicacaoComumResourceBundle();
				}
			}
		}
		return bundle;
	}

	/**
	 * Inicia o bundle com as suas propriedades padrao.
	 */
	private CAPESReplicacaoComumResourceBundle() {
		this(CAPES_REPLICACAO_COMUM_PROPERTIES);
	}

	/**
	 * Instancia um novo CAPESReplicacaoComumResourceBundle.
	 *
	 * @param arquivoProperties o valor de arquivo properties
	 */
	protected CAPESReplicacaoComumResourceBundle(String arquivoProperties) {
		this(arquivoProperties, CorporativoResourceBundle.getInstance());
	}

	/**
	 * Instancia um novo CAPESReplicacaoComumResourceBundle.
	 *
	 * @param arquivoProperties o valor de arquivo properties
	 * @param resourcePai o valor de resource pai
	 */
	protected CAPESReplicacaoComumResourceBundle(String arquivoProperties,
			ResourceBundle resourcePai) {
		super(arquivoProperties, resourcePai);
	}
}
