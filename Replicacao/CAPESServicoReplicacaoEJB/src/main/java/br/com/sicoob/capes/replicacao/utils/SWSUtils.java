package br.com.sicoob.capes.replicacao.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.sicoob.sws.parametro.ParametroFluxo;
import br.com.sicoob.sws.parametro.ParametroJob;
import br.com.sicoob.sws.parametro.ParametroStep;

/**
 * Classe que fornece servicos utilit?rios para integracão com o SWS
 * 
 * @author diego.rezende
 */
public final class SWSUtils {

	/**
	 * Cria um mapa com os parametros do fluxo onde a chave ? a ordem do
	 * parametro
	 * 
	 * @param parametros
	 *            a lista de parametros do fluxo.
	 * @return um mapa em que a chave ? a ordem do parametro e o valor ? o
	 *         pr?prio par?metro
	 */
	public static Map<Short, ParametroFluxo> criarMapaParametrosFluxo(List<ParametroFluxo> parametros) {
		Map<Short, ParametroFluxo> mapaParametros = new HashMap<Short, ParametroFluxo>();
		for (ParametroFluxo parametro : parametros) {
			mapaParametros.put(parametro.getOrdem(), parametro);
		}

		return mapaParametros;
	}
	
	/**
	 * Cria um mapa com os parametros do job onde a chave ? a ordem do
	 * parametro
	 * 
	 * @param parametros
	 *            a lista de parametros do job.
	 * @return um mapa em que a chave ? a ordem do parametro e o valor ? o
	 *         pr?prio par?metro
	 */
	public static Map<Short, ParametroJob> criarMapaParametrosJob(List<ParametroJob> parametros) {
		Map<Short, ParametroJob> mapaParametros = new HashMap<Short, ParametroJob>();
		for (ParametroJob parametro : parametros) {
			mapaParametros.put(parametro.getOrdem(), parametro);
		}

		return mapaParametros;
	}

	/**
	 * Cria um mapa com os parametros do step onde a chave ? a ordem do
	 * parametro
	 * 
	 * @param parametros
	 *            a lista de parametros do step.
	 * @return um mapa em que a chave ? a ordem do parametro e o valor ? o
	 *         pr?prio par?metro
	 */
	public static Map<Short, ParametroStep> criarMapaParametrosStep(List<ParametroStep> parametros) {
		Map<Short, ParametroStep> mapaParametros = new HashMap<Short, ParametroStep>();
		for (ParametroStep parametro : parametros) {
			mapaParametros.put(parametro.getOrdem(), parametro);
		}

		return mapaParametros;
	}

	/**
	 * Instancia um novo SWSUtils.
	 */
	private SWSUtils() {
	}

}
