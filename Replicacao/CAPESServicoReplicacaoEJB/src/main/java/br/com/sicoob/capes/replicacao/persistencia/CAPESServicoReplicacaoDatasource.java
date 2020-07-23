package br.com.sicoob.capes.replicacao.persistencia;

import java.util.Properties;

import br.com.bancoob.infraestrutura.conexao.BancoobDataSource;

/**
 * Datasource padrao para o sistema ServicoReplicacaoCadastroUnicoClientes
 * 
 * @author Stefanini IT Solutions
 */
public class CAPESServicoReplicacaoDatasource extends BancoobDataSource {

	
	/**
	 * @param nomeJndi
	 * @param propriedades
	 */
	public CAPESServicoReplicacaoDatasource(String nomeJndi, Properties propriedades) {
		super(nomeJndi, propriedades);
	}
	
}
