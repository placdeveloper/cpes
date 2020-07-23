package br.com.sicoob.capes.corporativo.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobFabricaDelegates;
import br.com.sicoob.capes.api.inclusao.negocio.delegates.AnotacaoDelegate;

/**
 * Fabrica para criacao de Delegates do modulo CAPESCorporativo.
 * 
 * @author bruno.carneiro
 */
public final class CAPESCorporativoFabricaDelegates extends BancoobFabricaDelegates {

	/** O atributo fabrica. */
	private static CAPESCorporativoFabricaDelegates fabrica;

	/**
	 * Obter instancia.
	 * 
	 * @return CAPESCorporativoFabricaDelegates
	 */
	public static CAPESCorporativoFabricaDelegates obterInstancia() {
		if (fabrica == null) {
			synchronized (CAPESCorporativoFabricaDelegates.class) {
				if (fabrica == null) {
					fabrica = new CAPESCorporativoFabricaDelegates();
				}
			}
		}
		return fabrica;
	}

	/**
	 * Instancia um novo CAPESCorporativoFabricaDelegates.
	 */
	private CAPESCorporativoFabricaDelegates() {
		super();
	}

	/**
	 * Cria uma instância do AnotacaoDelegate
	 * 
	 * @return o delegate solicitado.
	 */
	public AnotacaoDelegate criarAnotacaoDelegate() {
		return new AnotacaoDelegate();
	}

}