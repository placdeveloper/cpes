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
	 * Cria a f�brica de estrat�gias de replica��o de dom�nios
	 * 
	 * @return a f�brica de estrat�gias de replica��o de dom�nios
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
	 * Recupera o logger para essa inst�ncia.
	 * 
	 * @return O logger para essa inst�ncia.
	 */
	private ISicoobLogger getLogger() {
		return SicoobLoggerPadrao.getInstance(getClass());
	}

	/**
	 * Recupera a estrat�gia de replica��o para o dom�nio correspondente.
	 * @param dominioReplicavel O dom�nio replic�vel.
	 * @return A estrat�gia de replica��o para o dom�nio correspondente.
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
				getLogger().debug("N�o existe uma estrat�gia para replicar a tabela de dom�nio.");				
			}
		}
		
		return estrategia;
	}
	
}
