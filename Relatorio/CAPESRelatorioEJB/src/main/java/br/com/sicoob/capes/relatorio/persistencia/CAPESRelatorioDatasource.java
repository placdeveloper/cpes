package br.com.sicoob.capes.relatorio.persistencia;

import java.util.Properties;

import br.com.bancoob.infraestrutura.conexao.CorporativoDataSource;

/**
 * Datasource padrao para o sistema CAPES
 * 
 * @author Stefanini IT Solutions
 */
public class CAPESRelatorioDatasource extends CorporativoDataSource {

	/**
	 * Instancia um novo CAPESRelatorioDatasource.
	 *
	 * @param nomeJndi o valor de nome jndi
	 * @param propriedades o valor de propriedades
	 */
	public CAPESRelatorioDatasource(String nomeJndi, Properties propriedades) {
		super(nomeJndi, propriedades);
	}
}
