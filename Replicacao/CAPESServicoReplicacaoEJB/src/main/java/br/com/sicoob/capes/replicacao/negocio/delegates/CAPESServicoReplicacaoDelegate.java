package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobDelegate;
import br.com.sicoob.capes.replicacao.negocio.servicos.CAPESServicoReplicacaoServico;

/**
 * Business delegate a ser usado pelo Sistema CAPES.
 * 
 * @author Stefanini IT Solutions
 */
public abstract class CAPESServicoReplicacaoDelegate<T extends CAPESServicoReplicacaoServico> extends
		BancoobDelegate<T> {

}
