package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.io.IOException;
import java.util.Collection;

import javax.persistence.Query;

import br.com.sicoob.capes.cadastro.util.QueriesLoader;

/**
 * 
 * @author diego.rezende
 *
 */
public abstract class CapesTestDAO extends AbstractTestDAO {
	
	
	
	/**
	 * Instancia um novo CapesTestDAO.
	 */
	public CapesTestDAO() {
		try { 
			begin();
			getEntityManager().createNativeQuery("CREATE SCHEMA CLI AUTHORIZATION DBA").executeUpdate();
			commit();
		} catch (Exception e) {
			roolBack();
		}
	}
	
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	protected void configureAnnotedClasses(Ejb3Configuration configuration) {
//		configuration.addAnnotatedClass(Empreendimento.class);
//		configuration.addAnnotatedClass(UnidadeMedida.class);
//		configuration.addAnnotatedClass(FinalidadeEmpreendimento.class);
//		configuration.addAnnotatedClass(SubTipoBem.class);
//		configuration.addAnnotatedClass(TipoBem.class);		
//	}
		
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void before() {
		super.before();
		begin();
		setUp();
		QueriesLoader queriesResource = null;
		Collection<String> comandosSQL = null;
		try {
			queriesResource = new QueriesLoader();
			comandosSQL = queriesResource.getComandosSQL();
			for(String sql:comandosSQL) {
				Query query = getEntityManager().createNativeQuery(sql);
				query.executeUpdate();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		commit();
	}

	/**
	 * O método Sets the up.
	 */
	protected abstract  void setUp();

}
