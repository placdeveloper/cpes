/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.FabricaServicos;
import br.com.bancoob.infraestrutura.cache.ServicoCache;
import br.com.sicoob.capes.comum.negocio.vo.SistemaCooperativoVO;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * Cache para o sistema cooperativo das instituições.
 * @author erico.junior
 */
public final class SistemaCooperativoCache {
	
	/** A constante LOGGER. */
	private static final ISicoobLogger LOGGER = SicoobLoggerPadrao.getInstance(SistemaCooperativoCache.class);
	
	/** A constante TIMEOUT_CACHE. */
	private static final Long TIMEOUT_CACHE = 7200000L;
	
	/** A constante SISTEMA_COOPERATIVO_SICOOB. */
	private static final Integer SISTEMA_COOPERATIVO_SICOOB = 1;
	
	/** O atributo instance. */
	private static SistemaCooperativoCache instance;

	/**
	 * Construtor privado para garantis o Singleton. 
	 */
	private SistemaCooperativoCache() {
	}
	
	/**
	 * Cria o SistemaCooperativoCache
	 * 
	 * @return o SistemaCooperativoCache
	 */
	public static SistemaCooperativoCache getInstance() {
		if (instance == null) {
			synchronized (SistemaCooperativoCache.class) {
				if (instance == null) {
					instance = new SistemaCooperativoCache();
				}
			}
		}
		return instance;
	}
	
	/**
	 * Recupera o idSistemaCooperativo para o idInstituicao informado.
	 * 
	 * @param idInstituicao
	 *            o identificador de instituição.
	 * @return o idSistemaCooperativo para o idInstituicao informado.
	 */
	public Integer recuperarIdSistemaCooperativo(Integer idInstituicao) throws BancoobException {

		ServicoCache gerenciadorCache = FabricaServicos.getInstance().criarServicoCache();

		String chave = criarChaveCache(idInstituicao);
		
		LOGGER.info("Recuperando o ID do Sistema Cooperativo do cache: ", chave);
		
		Integer idSistema = (Integer) gerenciadorCache.recuperar(chave);

		LOGGER.info("ID do Sistema Cooperativo recuperado do cache: ", chave, "=", String.valueOf(idSistema));
		
		if (idSistema == null) {
			
			LOGGER.info("Recuperando o Sistema Cooperativo do SCI: ", String.valueOf(idInstituicao));
			
			SistemaCooperativoVO sistema = recuperarSistemaCooperativo(idInstituicao);
			
			LOGGER.info("Sistema Cooperativo recuperado do SCI: ", String.valueOf(idInstituicao), "=",
					String.valueOf(sistema));
			
			if (sistema != null) {
				idSistema = sistema.getIdSistemaCooperativo();
				
				LOGGER.info("Armazenando o ID do Sistema Cooperativo no cache: ", chave, "=", String.valueOf(idSistema));
				
				gerenciadorCache.armazenar(chave, idSistema, TIMEOUT_CACHE);
				
				LOGGER.info("ID do Sistema Cooperativo armazendao no cache: ", chave, "=",
						String.valueOf(gerenciadorCache.recuperar(chave)));
			}
		}

		return idSistema;
	}

	/**
	 * Cria a chave para o cache para o idInstituicao.
	 * 
	 * @param idInstituicao
	 *            O identificador da instituição.
	 * @return a chave para o cache para o idInstituicao.
	 */
	private String criarChaveCache(Integer idInstituicao) {

		StringBuilder builder = new StringBuilder();
		builder.append("CAPES_ID_SISTEMACOOPERATIVO_");
		builder.append(idInstituicao);

		return builder.toString();
	}

	/**
	 * Recupera o SistemaCooperativo para o idInstituicao informado.
	 * 
	 * @param idInstituicao
	 *            O identificador da instituição.
	 * @return o SistemaCooperativo para o idInstituicao informado.
	 */
	private SistemaCooperativoVO recuperarSistemaCooperativo(Integer idInstituicao) throws BancoobException {
		SistemaCooperativoVO sistema = null;

		if (Constantes.Comum.ID_INSTITUICAO_BANCOOB.equals(idInstituicao)) {
			sistema = new SistemaCooperativoVO();
			sistema.setIdSistemaCooperativo(SISTEMA_COOPERATIVO_SICOOB);
		} else {
			SCIIntegracaoDelegate sciIntegracaoDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
			sistema = sciIntegracaoDelegate.obterSistemaCooperativo(idInstituicao);
		}

		return sistema;
	}
}
