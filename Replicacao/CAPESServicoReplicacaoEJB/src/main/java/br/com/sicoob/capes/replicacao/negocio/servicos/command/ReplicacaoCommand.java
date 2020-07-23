package br.com.sicoob.capes.replicacao.negocio.servicos.command;

import br.com.sicoob.capes.negocio.entidades.legado.TransicaoReplicacao;

/**
 * Define o contrato das classes que executar�o a tarefa de replica��o.
 * @author Juan.Damasceno
 *
 */
public interface ReplicacaoCommand {
	
	/**
	 * Executa a opera��o de replica��o de TransicaoReplicacao para a base do DB2.
	 * @param transicaoReplicacao a transicaoReplicacao que ser� replicada.
	 */
	void execute(TransicaoReplicacao transicaoReplicacao);
}
