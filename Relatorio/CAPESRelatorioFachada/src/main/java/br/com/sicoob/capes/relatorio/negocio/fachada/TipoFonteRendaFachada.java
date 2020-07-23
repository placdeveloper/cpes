package br.com.sicoob.capes.relatorio.negocio.fachada;

import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.relatorio.negocio.delegates.CAPESRelatorioDominioDelegate;

/**
 * A Classe TipoFonteRendaFachada.
 */
@RemoteService
public class TipoFonteRendaFachada extends CAPESRelatorioDominioFachada {
	
	/**
	 * Instancia um novo TipoFonteRendaFachada.
	 */
	public TipoFonteRendaFachada() {
		super("tipoFonteRenda");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESRelatorioDominioDelegate<?> obterDelegate() {
		return obterFabricaDelegates().criarTipoFonteRendaDelegate();
	}

}
