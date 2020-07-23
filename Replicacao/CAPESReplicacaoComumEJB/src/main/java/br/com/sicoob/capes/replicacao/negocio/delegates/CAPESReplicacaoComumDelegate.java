package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobDelegate;
import br.com.sicoob.capes.replicacao.negocio.servicos.CAPESReplicacaoComumServico;

/**
 * Business delegate a ser usado pelo Sistema ReplicacaoClientesBO.
 * 
 * @author Stefanini IT Solutions
 */
public abstract class CAPESReplicacaoComumDelegate<T extends CAPESReplicacaoComumServico> extends
		BancoobDelegate<T> {

}
