/*
 * SICOOB
 * 
 * CAPESComumDatasource.java(br.com.sicoob.capes.comum.persistencia.CAPESComumDatasource)
 */
package br.com.sicoob.capes.comum.persistencia;

import java.util.Properties;

import br.com.bancoob.infraestrutura.conexao.BancoobDataSource;

/**
 * Datasource padrao para o sistema CAPESComum
 * 
 * @author Stefanini IT Solutions
 */
public class CAPESComumDatasource extends BancoobDataSource {

		
	
	/**
	 * @param nomeJndi
	 * @param propriedades
	 */
	public CAPESComumDatasource(String nomeJndi, Properties propriedades) {
		super(nomeJndi, propriedades);
				
	}
	
		
}
