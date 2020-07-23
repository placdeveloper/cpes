/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.dominio.fabrica;

import br.com.sicoob.capes.negocio.entidades.UnidadeMedida;
import br.com.sicoob.capes.negocio.entidades.interfaces.DominioReplicavel;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.capes.replicacao.negocio.dominio.estrategia.EstrategiaReplicacaoDominio;
import br.com.sicoob.capes.replicacao.negocio.dominio.estrategia.EstrategiaReplicacaoDominioLista;
import br.com.sicoob.capes.replicacao.negocio.dominio.estrategia.EstrategiaReplicacaoUnidadeMedida;
import br.com.sicoob.capes.replicacao.negocio.enums.DominioReplicavelEnum;

/**
 * @author erico.junior
 *
 */
public class FabricaEstrategiaReplicacaoDominio {

	/** O atributo instance. */
	private static FabricaEstrategiaReplicacaoDominio instance;

	/**
	 * Cria a fábrica de estratégias de replicação de domínios
	 * 
	 * @return a fábrica de estratégias de replicação de domínios
	 */
	public static FabricaEstrategiaReplicacaoDominio getInstance() {
		if (instance == null) {
			synchronized (FabricaEstrategiaReplicacaoDominio.class) {
				if (instance == null) {
					instance = new FabricaEstrategiaReplicacaoDominio();
				}
			}
		}
		return instance;
	}

	/**
	 * Recupera o logger para essa instância.
	 * 
	 * @return O logger para essa instância.
	 */
	private ISicoobLogger getLogger() {
		return SicoobLoggerPadrao.getInstance(getClass());
	}

	/**
	 * Recupera a estratégia de replicação para o domínio correspondente.
	 * @param dominioReplicavel O domínio replicável.
	 * @return A estratégia de replicação para o domínio correspondente.
	 */
	@SuppressWarnings("rawtypes")
	public EstrategiaReplicacaoDominio criarEstrategiaReplicacaoDominio(
			DominioReplicavelEnum dominioReplicavel) {
		
		EstrategiaReplicacaoDominio estrategia = null;
		
		if(dominioReplicavel.isLista()) {
			estrategia = new EstrategiaReplicacaoDominioLista(dominioReplicavel);
		} else {
			Class<? extends DominioReplicavel> entidade = dominioReplicavel.getEntidade();
			if (entidade.equals(UnidadeMedida.class)){
				estrategia = new EstrategiaReplicacaoUnidadeMedida(dominioReplicavel);
			} else {
				getLogger().debug("Não existe uma estratégia para replicar a tabela de domínio.");				
			}
		}
		
		return estrategia;
	}
	
}
