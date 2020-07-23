package br.com.sicoob.capes.processamento.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobDelegate;
import br.com.sicoob.capes.processamento.negocio.servicos.CAPESProcessamentoServico;

/**
 * Business delegate a ser usado pelo Sistema
 * CAPESProcessamento.
 * 
 * @author Stefanini IT Solutions
 */
public abstract class CAPESProcessamentoDelegate<T extends CAPESProcessamentoServico> extends
		BancoobDelegate<T> {

}
