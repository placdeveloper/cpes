package br.com.sicoob.capes.replicacao.negocio.infraestrutura.mensagens;

import java.util.ResourceBundle;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.infraestrutura.mensagens.CorporativoResourceBundle;
import br.com.sicoob.capes.replicacao.infraestrutura.mensagens.CAPESReplicacaoComumResourceBundle;

/**
 * Classe responsavel pela carga das mensagens do sistema ReplicacaoClientesProcessamento
 * 
 * @author Stefanini IT Solutions
 */
public class CAPESReplicacaoProcessamentoResourceBundle extends BancoobResourceBundle {

	/** Nome do arquivo de mensagens do sistema "ReplicacaoClientesProcessamento". */
	public static final String CAPES_REPLICACAO_PROCESSAMENTO_PROPERTIES =
			"capes.replicacao.processamento.bancoob.properties";

	/** Resource bundle a ser usada. */
	public static CAPESReplicacaoProcessamentoResourceBundle bundle;

	/**
	 * Retorna o bundle a ser usado.
	 * 
	 * @return o bundle a ser usado.
	 */
	public static CAPESReplicacaoProcessamentoResourceBundle getInstance() {
		if (bundle == null) {
			synchronized (CAPESReplicacaoProcessamentoResourceBundle.class) {
				if (bundle == null) {
					bundle = new CAPESReplicacaoProcessamentoResourceBundle();
				}
			}
		}
		return bundle;
	}

	/**
	 * Inicia o bundle com as suas propriedades padrao.
	 */
	private CAPESReplicacaoProcessamentoResourceBundle() {
		this(CAPES_REPLICACAO_PROCESSAMENTO_PROPERTIES);
	}
	
	/**
	 * Instancia um novo CAPESReplicacaoProcessamentoResourceBundle.
	 *
	 * @param arquivoProperties o valor de arquivo properties
	 */
	protected CAPESReplicacaoProcessamentoResourceBundle(String arquivoProperties) {
		this(arquivoProperties, CorporativoResourceBundle.getInstance());
		super.setParent(CAPESReplicacaoComumResourceBundle.getInstance());
	}

	/**
	 * Instancia um novo CAPESReplicacaoProcessamentoResourceBundle.
	 *
	 * @param arquivoProperties o valor de arquivo properties
	 * @param resourcePai o valor de resource pai
	 */
	protected CAPESReplicacaoProcessamentoResourceBundle(String arquivoProperties, ResourceBundle resourcePai) {
		super(arquivoProperties, resourcePai);
	}
}
