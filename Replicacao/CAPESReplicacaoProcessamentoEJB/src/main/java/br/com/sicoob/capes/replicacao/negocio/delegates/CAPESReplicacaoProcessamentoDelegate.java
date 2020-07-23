package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobDelegate;
import br.com.sicoob.capes.replicacao.negocio.servicos.CAPESReplicacaoProcessamentoServico;

/**
 * Business delegate a ser usado pelo Sistema ReplicacaoClientesProcessamento.
 * 
 * @author Stefanini IT Solutions
 */
public abstract class CAPESReplicacaoProcessamentoDelegate<T extends CAPESReplicacaoProcessamentoServico> extends
		BancoobDelegate<T> {

}
