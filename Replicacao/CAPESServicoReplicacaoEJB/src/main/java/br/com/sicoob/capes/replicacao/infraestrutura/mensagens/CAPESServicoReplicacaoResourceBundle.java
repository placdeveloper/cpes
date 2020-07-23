package br.com.sicoob.capes.replicacao.infraestrutura.mensagens;

import java.util.ResourceBundle;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.infraestrutura.mensagens.CorporativoResourceBundle;

/**
 * Classe responsavel pela carga das mensagens do sistema ServicoReplicacaoCadastroUnicoClientes
 * 
 * @author Stefanini IT Solutions
 */
public final class CAPESServicoReplicacaoResourceBundle extends BancoobResourceBundle {

	/** Nome do arquivo de mensagens do sistema "ServicoReplicacaoCadastroUnicoClientes". */
	public static final String CAPES_SERVICO_REPLICACAO_PROPERTIES = 
			"capes.servico.replicacao.bancoob.properties";

	/** Resource bundle a ser usada. */
	private static CAPESServicoReplicacaoResourceBundle bundle;

	/**
	 * Retorna o bundle a ser usado.
	 * 
	 * @return o bundle a ser usado.
	 */
	public static CAPESServicoReplicacaoResourceBundle getInstance() {
		if (bundle == null) {
			synchronized (CAPESServicoReplicacaoResourceBundle.class) {
				if (bundle == null) {
					bundle = new CAPESServicoReplicacaoResourceBundle();
				}
			}
		}
		return bundle;
	}
	
	/**
	 * Inicia o bundle com as suas propriedades padrao.
	 */
	private CAPESServicoReplicacaoResourceBundle() {
		this(CAPES_SERVICO_REPLICACAO_PROPERTIES);
	}
	
	/**
	 * Instancia um novo CAPESServicoReplicacaoResourceBundle.
	 *
	 * @param arquivoProperties o valor de arquivo properties
	 */
	protected CAPESServicoReplicacaoResourceBundle(String arquivoProperties) {
		this(arquivoProperties, CorporativoResourceBundle.getInstance());
	}

	/**
	 * Instancia um novo CAPESServicoReplicacaoResourceBundle.
	 *
	 * @param arquivoProperties o valor de arquivo properties
	 * @param resourcePai o valor de resource pai
	 */
	protected CAPESServicoReplicacaoResourceBundle(String arquivoProperties, ResourceBundle resourcePai) {
		super(arquivoProperties, resourcePai);
	}
}
