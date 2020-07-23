/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.FabricaServicos;
import br.com.bancoob.infraestrutura.cache.ServicoCache;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.GrupoCompartilhamentoDelegate;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoCompartilhamento;

/**
 * @author erico.junior
 *
 */
public final class GrupoCompartilhamentoCache {

	/** A constante TIMEOUT_CACHE. */
	private static final Long TIMEOUT_CACHE = 600000L;
	
	/** O atributo instance. */
	private static GrupoCompartilhamentoCache instance;

	/**
	 * Construtor privado para garantis o Singleton. 
	 */
	private GrupoCompartilhamentoCache() {
	}
	
	/**
	 * Cria o GrupoCompartilhamentoCache
	 * 
	 * @return o GrupoCompartilhamentoCache
	 */
	public static GrupoCompartilhamentoCache getInstance() {
		if (instance == null) {
			synchronized (GrupoCompartilhamentoCache.class) {
				if (instance == null) {
					instance = new GrupoCompartilhamentoCache();
				}
			}
		}
		return instance;
	}
	
	/**
	 * Recupera o grupo de compartilhamento para o idInstituicao informado.
	 * 
	 * @param idInstituicao
	 *            o identificador de instituição.
	 * @return o grupo de compartilhamento para o idInstituicao informado.
	 */
	public Short recuperarCodigoCompartilhamento(Integer idInstituicao) throws BancoobException {

		ServicoCache gerenciadorCache = FabricaServicos.getInstance().criarServicoCache();

		String chave = criarChaveCache(idInstituicao);
		Short codigo = (Short) gerenciadorCache.recuperar(chave);

		if (codigo == null) {
			GrupoCompartilhamento grupo = recuperarGrupoCompartilhamento(idInstituicao);
			if(grupo != null && grupo.getCompartilhamentoCadastro() != null) {
				codigo = grupo.getCompartilhamentoCadastro().getCodigo();
				gerenciadorCache.armazenar(chave, codigo, TIMEOUT_CACHE);
			}
		}

		return codigo;
	}

	/**
	 * Cria a chave para o cache do compartilhamento da instituicao.
	 * 
	 * @param idInstituicao
	 *            O identificador da instituição.
	 * @return a chave para o cache para o idInstituicao.
	 */
	private String criarChaveCache(Integer idInstituicao) {

		StringBuilder builder = new StringBuilder();
		builder.append("CAPES_CODCOMPARTILHAMENTOCADASTRO_");
		builder.append(idInstituicao);

		return builder.toString();
	}

	/**
	 * Recuperar grupo compartilhamento.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @return GrupoCompartilhamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private GrupoCompartilhamento recuperarGrupoCompartilhamento(Integer idInstituicao) throws BancoobException {
		
		GrupoCompartilhamentoDelegate delegate = 
				CAPESCadastroFabricaDelegates.getInstance().criarGrupoCompartilhamentoDelegate();
		return delegate.obterPorInstituicao(idInstituicao);
	}		
}
