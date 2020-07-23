package br.com.sicoob.capes.replicacao.negocio.servicos.command;

import br.com.sicoob.capes.negocio.entidades.legado.TransicaoReplicacao;

/**
 * Define o contrato das classes que executarão a tarefa de replicação.
 * @author Juan.Damasceno
 *
 */
public interface ReplicacaoCommand {
	
	/**
	 * Executa a operação de replicação de TransicaoReplicacao para a base do DB2.
	 * @param transicaoReplicacao a transicaoReplicacao que será replicada.
	 */
	void execute(TransicaoReplicacao transicaoReplicacao);
}
