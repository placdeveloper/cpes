/**
 * 
 */
package br.com.sicoob.capes.relatorio.negocio.fachada;

import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.relatorio.negocio.delegates.CAPESRelatorioDominioDelegate;

/**
 * Fachada para os tipos de anotações.
 * 
 * @author Erico.Junior
 */
@RemoteService
public class GrupoCompartilhamentoFachada extends CAPESRelatorioDominioFachada {

	/**
	 * Instancia um novo GrupoCompartilhamentoFachada.
	 */
	public GrupoCompartilhamentoFachada() {
		super("GrupoCompartilhamento");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESRelatorioDominioDelegate<?> obterDelegate() {
		return obterFabricaDelegates().criarGrupoCompartilhamentoDelegate();
	}

}