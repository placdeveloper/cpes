package br.com.sicoob.capes.frontoffice.negocio.testeremoto;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Classe para obter o serviço remoto
 *
 * @author Bruno.Carneiro
 */
public final class ServiceLocator {

	/** O atributo instance. */
	private static ServiceLocator instance;

	/** O atributo initialContext. */
	private InitialContext initialContext;

	/** O atributo cache. */
	private Map<String, Object> cache;

	/**
	 * Recupera a unica instancia de ServiceLocator.
	 * 
	 * @return uma instancia de ServiceLocator
	 * @throws NamingException
	 *             lança a exceção NamingException
	 */
	public synchronized static ServiceLocator getInstance() throws NamingException {
		if (instance == null) {
			instance = new ServiceLocator();
		}
		return instance;
	}

	/**
	 * Construtor default para lookup local
	 * 
	 * @throws NamingException
	 */
	private ServiceLocator() throws NamingException {
		Hashtable<String, String> mapa = new Hashtable<String, String>();
		mapa.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
		mapa.put("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
		mapa.put("java.naming.provider.url", "jnp://localhost:1099");
		initialContext = new InitialContext(mapa);
		cache = Collections.synchronizedMap(new HashMap<String, Object>());
		System.out.println("### cache: " + cache);
	}

	/**
	 * Faz o lookup do EJB Remoto
	 * 
	 * @param ejbInterface
	 * @return
	 * @throws RuntimeException
	 */
	public Object getObjetoRemoto(String ejbInterface) throws RuntimeException {
		System.out.println("### EJB: " + ejbInterface);
		System.out.println("### cache: " + cache);
		Object result = cache.get(ejbInterface);
		if (result == null) {
			try {
				result = initialContext.lookup(ejbInterface + "Remote");
				cache.put(ejbInterface, result);
			} catch (NamingException e) {
				throw new RuntimeException(e);
			}
		}
		return result;
	}

}