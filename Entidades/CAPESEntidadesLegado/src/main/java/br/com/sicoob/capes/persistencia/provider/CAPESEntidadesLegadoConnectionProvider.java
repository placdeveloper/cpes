/*
 * SICOOB
 * 
 * CAPESEntidadesLegadoConnectionProvider.java(br.com.sicoob.capes.persistencia.provider.CAPESEntidadesLegadoConnectionProvider)
 */
package br.com.sicoob.capes.persistencia.provider;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.sql.DataSource;

import br.com.bancoob.infraestrutura.conexao.BancoobConnectionProvider;
import br.com.sicoob.capes.persistencia.CAPESEntidadesLegadoDatasource;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * Connection provider utilizado no projeto de replicação dos dados do cadastro único para o sistema
 * legado em SQLServer.
 * @author Erico.Junior
 */
public class CAPESEntidadesLegadoConnectionProvider extends BancoobConnectionProvider {
	private static final long serialVersionUID = 2821933198249437730L;
	
	/** O atributo logger. */
	private final transient ISicoobLogger logger = SicoobLoggerPadrao.getInstance(getClass());
	
	/**
	 * @see org.hibernate.connection.DatasourceConnectionProvider#configure(java.util.Properties)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void configure(Map configValues) {
		logger.debug("Configurando o Connection Provider ReplicacaoClientesConnectionProvider.");

		Properties propriedades = new Properties();
		
		Iterator<Entry> it = configValues.entrySet().iterator();
		while (it.hasNext()) {
			Entry entrada = it.next();
			logger.debug("Configurando a entrada para o connection provider chave: ", String.valueOf(entrada.getKey()), " valor: ", String.valueOf(entrada.getValue()));
			propriedades.put(entrada.getKey(), entrada.getValue());
		}

		DataSource dataSource = new CAPESEntidadesLegadoDatasource(propriedades.getProperty(NOME_JNDI), propriedades);
		setDataSource(dataSource);

		logger.debug("Connection Provider configurado ReplicacaoClientesConnectionProvider.");
	}
}
