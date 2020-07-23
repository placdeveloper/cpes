/**
 * 
 */
package br.com.sicoob.capes.relatorio.negocio.fachada;

import br.com.bancoob.sisbrweb.seguranca.RemoteService;

/**
 * Fachada para as anotações.
 * 
 * @author Daniel.Domingues
 */
@RemoteService
public class RelatorioProvaVidaFachada extends CAPESRelatorioComumFachada {

	/**
	 * Constante ANOTACAO.
	 */
	private static final String ANOTACAO = "anotacao";

	/**
	 * Instancia um novo AnotacaoFachada.
	 */
	public RelatorioProvaVidaFachada() {
		super(ANOTACAO);
	}

}