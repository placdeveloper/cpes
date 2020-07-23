package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.sql.Connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;

/**
 * A Classe AbstractTestDAO.
 */
public abstract class AbstractTestDAO {

//    /** O atributo entityManagerFactory. */
//    private static EntityManagerFactory entityManagerFactory;
//    
//    /** O atributo configuration. */
//    private static Ejb3Configuration configuration;

    /** O atributo entityManager. */
    private EntityManager entityManager;
    
    /** O atributo transaction. */
    private EntityTransaction transaction;
    
    /** O atributo schemaExport. */
    private SchemaExport schemaExport;

    /** O atributo create. */
    private Boolean create = Boolean.TRUE;
    
    /** O atributo drop. */
    private Boolean drop = Boolean.FALSE;
    
    /** O atributo exportDDL. */
    private Boolean exportDDL = Boolean.TRUE;

    /**
     * Instancia um novo AbstractTestDAO.
     */
    public AbstractTestDAO() {
//        if (configuration == null) {
//            configuration = newEjb3Configuration();
//        }
//        if (entityManagerFactory == null) {
//            entityManagerFactory = configuration.buildEntityManagerFactory();
//        }
//        if (entityManager == null) {
//            entityManager = entityManagerFactory.createEntityManager();
//        }
//        schemaExport = new SchemaExport(configuration.getHibernateConfiguration());
    }

    /**
     * Cria banco de dados.
     */
    @Before
    public void before() {
        if (exportDDL) {
            schemaExport.setDelimiter(";");
            schemaExport.setOutputFile("target/ddl.sql");
        }
        if (create) {
            schemaExport.create(Boolean.TRUE, exportDDL);
        }
    }

    /**
     * Apaga o banco de dados.
     */
    @After
    public void after() {
        if (drop) {
            schemaExport.drop(Boolean.TRUE, exportDDL);
        }

    }

    /**
     * Entity manager configurado.
     * 
     * @return {@link EntityManager}
     */
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Recupera o valor de connection.
     *
     * @return o valor de connection
     */
    protected Connection getConnection() {
		return null;

//        return ((HibernateEntityManager) entityManager).getSession().connection();
    }

    /**
     * O método Rool back.
     */
    protected void roolBack() {
        if (transaction != null && transaction.isActive()) {
            transaction.rollback();
        }
    }

    /**
     * O método Commit.
     */
    protected void commit() {
        if (transaction != null && transaction.isActive()) {
            transaction.commit();
        }
    }

    /**
     * O método Begin.
     */
    protected void begin() {
        if (transaction == null) {
            transaction = entityManager.getTransaction();
        }
        transaction.begin();
    }

//    /**
//     * New ejb3 configuration.
//     *
//     * @return Ejb3Configuration
//     */
//    private Ejb3Configuration newEjb3Configuration() {
//        Ejb3Configuration pConfiguration = new Ejb3Configuration();
//
//        Properties properties = new Properties();
//        properties.put("hibernate.connection.url", "jdbc:hsqldb:mem:unit-testing-jpa");
////        properties.put("hibernate.connection.url", "jdbc:hsqldb:file:db/capes");
//        properties.put("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver");
//        properties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
//        properties.put("hibernate.connection.username", "sa");
//        properties.put("hibernate.connection.password", "");
//        properties.put("hibernate.show_sql", "false");
//        properties.put("hibernate.format_sql", "false");
//        properties.put("hibernate.hbm2ddl.auto", "create-drop");
//        properties.put("hibernate.jdbc.batch_size", 0);
//        properties.put("hibernate.jacc.enabled", true);
//       
//        pConfiguration.setProperties(properties);
//       
//        pConfiguration.getEventListeners().setPreDeleteEventListeners(new PreDeleteEventListener[0]);
//        pConfiguration.getEventListeners().setDeleteEventListeners(new DeleteEventListener[]{new DefaultDeleteEventListener()});
//        pConfiguration.getEventListeners().setPostDeleteEventListeners(new PostDeleteEventListener[0]);
//        pConfiguration.getEventListeners().setPreInsertEventListeners(new PreInsertEventListener[0]);
//        pConfiguration.getEventListeners().setPersistEventListeners(new PersistEventListener[] { new DefaultPersistEventListener() });
//        pConfiguration.getEventListeners().setPostInsertEventListeners(new PostInsertEventListener[0]);
//        pConfiguration.getEventListeners().setMergeEventListeners(new MergeEventListener[]{new DefaultMergeEventListener()});
//        pConfiguration.getEventListeners().setPreLoadEventListeners(new PreLoadEventListener[]{ new DefaultPreLoadEventListener()});
//        pConfiguration.getEventListeners().setPostLoadEventListeners(new PostLoadEventListener[]{new DefaultPostLoadEventListener()});
//			
//			
//        
//        configureAnnotedClasses(pConfiguration);
//
//        return pConfiguration;
//    }
//
//    /**
//     * O método Configure annoted classes.
//     *
//     * @param configuration o valor de configuration
//     */
//    protected abstract void configureAnnotedClasses(Ejb3Configuration configuration);

}
