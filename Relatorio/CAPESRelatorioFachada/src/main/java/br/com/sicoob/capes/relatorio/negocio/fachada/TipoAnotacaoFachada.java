/**
 * 
 */
package br.com.sicoob.capes.relatorio.negocio.fachada;

import br.com.bancoob.sisbrweb.seguranca.RemoteService;

/**
 * Fachada para os tipos de anotações.
 * 
 * @author Erico.Junior
 */
@RemoteService
public class TipoAnotacaoFachada extends CAPESRelatorioComumFachada {

	/** A constante RELATORIO_TIPO_ANOTACAO. */
	private static final String RELATORIO_TIPO_ANOTACAO = "relatorioTipoAnotacao";

	/**
	 * Instancia um novo TipoAnotacaoFachada.
	 */
	public TipoAnotacaoFachada() {
		super(RELATORIO_TIPO_ANOTACAO);
	}

}
