/**
 * 
 */
package br.com.sicoob.capes.processamento.negocio.dominio.informacoesvencidas;

import java.util.Properties;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.propriedades.RepositorioPropriedades;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * @author Erico.Junior
 *
 */
public abstract class InformacoesVencidas {

	/** O atributo propriedades. */
	private final transient Properties propriedades = RepositorioPropriedades.getInstance().recuperarPropriedades(
			"capes.processamento.propriedades");	
	
	/** O atributo tamanhoPagina. */
	private final transient int tamanhoPagina = 
			Integer.valueOf(propriedades.getProperty("tamanho.pagina.informacoesvencidas"));

	/**
	 * Recupera o logger para essa instância.
	 * @return O logger para essa instância.
	 */
	protected ISicoobLogger getLogger() {
		return SicoobLoggerPadrao.getInstance(getClass());
	}	
	
	/**
	 * Recupera o valor de fabricaDelegate.
	 *
	 * @return o valor de fabricaDelegate
	 */
	protected CAPESCadastroFabricaDelegates getFabricaDelegate() {
		return CAPESCadastroFabricaDelegates.getInstance(); 
	}

	/**
	 * Recupera o valor de tamanhoPagina.
	 *
	 * @return o valor de tamanhoPagina
	 */
	protected int getTamanhoPagina() {
		return tamanhoPagina;
	}
	
	/**
	 * O método Excluir.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public abstract void excluir() throws BancoobException;
	
}
