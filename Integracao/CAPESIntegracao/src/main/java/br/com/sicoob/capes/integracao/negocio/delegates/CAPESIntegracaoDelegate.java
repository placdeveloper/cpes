package br.com.sicoob.capes.integracao.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobDelegate;
import br.com.sicoob.capes.integracao.negocio.servicos.CAPESIntegracaoServico;

/**
 * Business delegate a ser usado pelo Sistema CAPESIntegracao.
 * 
 * @author Stefanini IT Solutions
 */
public abstract class CAPESIntegracaoDelegate<T extends CAPESIntegracaoServico> extends BancoobDelegate<T> {

}