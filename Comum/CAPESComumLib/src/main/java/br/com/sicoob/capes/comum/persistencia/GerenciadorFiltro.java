/**
 * 
 */
package br.com.sicoob.capes.comum.persistencia;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;

import org.hibernate.Filter;
import org.hibernate.Session;

/**
 * Classe utilitaria utilizada para ativacao/desativacao de filtros de persistencia
 * 
 * @author rodrigo.chaves
 * @since 1.2.0.x
 */
public final class GerenciadorFiltro {

	/**
	 * Construtor privado para evitar instancias de classe utilitaria
	 */
	private GerenciadorFiltro() {
	}

	/**
	 * Ativa um filtro pelo nome
	 * 
	 * @param manager
	 *            O {@link EntityManager} que esta sendo utilizado
	 * @param nomeFiltro
	 *            O nome do filtro a ser ativado
	 * @param parametros
	 *            Os parametros do filtro a ser ativado
	 * @return O filtro ativado
	 */
	public static Filter ativarFiltro(EntityManager manager, String nomeFiltro,
	        Map<String, Object> parametros) {

		Session session = (Session) manager.getDelegate();
		Filter filtro = session.getEnabledFilter(nomeFiltro);
		if (filtro == null) {
			filtro = session.enableFilter(nomeFiltro);
		}
		if (parametros != null) {
			for (Entry<String, Object> parametro : parametros.entrySet()) {
				Object valorParametro = parametro.getValue();
				if (Collection.class.isAssignableFrom(valorParametro.getClass())) {
					filtro.setParameterList(parametro.getKey(), (Collection<?>) valorParametro);
				} else if (valorParametro.getClass().isArray()) {
					filtro.setParameterList(parametro.getKey(), (Object[]) valorParametro);
				} else {
					filtro.setParameter(parametro.getKey(), valorParametro);
				}
			}
		}
		return filtro;
	}

	/**
	 * Desativa um filtro pelo nome
	 * 
	 * @param manager
	 *            O {@link EntityManager} que esta sendo utilizado
	 * @param nomeFiltro
	 *            O nome do filtro a ser desativado
	 */
	public static void desativarFiltro(EntityManager manager, String nomeFiltro) {

		Session session = (Session) manager.getDelegate();
		if (session.getEnabledFilter(nomeFiltro) != null) {
			session.disableFilter(nomeFiltro);
		}
	}

}
