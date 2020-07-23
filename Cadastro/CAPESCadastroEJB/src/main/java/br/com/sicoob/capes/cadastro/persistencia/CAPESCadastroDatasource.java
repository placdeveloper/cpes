package br.com.sicoob.capes.cadastro.persistencia;

import java.util.Properties;

import br.com.bancoob.infraestrutura.conexao.CorporativoDataSource;

/**
 * Datasource padrao para o sistema CadastroUnicoClientesComum
 * 
 * @author Stefanini IT Solutions
 */
public class CAPESCadastroDatasource extends CorporativoDataSource {

	/**
	 * Instancia um novo CAPESCadastroDatasource.
	 *
	 * @param nomeJndi o valor de nome jndi
	 * @param propriedades o valor de propriedades
	 */
	public CAPESCadastroDatasource(String nomeJndi, Properties propriedades) {
		super(nomeJndi, propriedades);
	}
}
