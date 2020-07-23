// 09/08/2013
package br.com.sicoob.capes.replicacao.negocio.servicos.interfaces;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.BancoServidor;
import br.com.sicoob.capes.negocio.entidades.legado.TransicaoReplicacao;
import br.com.sicoob.capes.replicacao.negocio.servicos.ReplicarCadastroClientesServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.command.ReplicacaoCommand;
import br.com.sicoob.capes.replicacao.negocio.servicos.command.ReplicacaoCommandFactory;

/**
 * A Interface ReplicarCadastroClientesServicoLocal.
 */
public interface ReplicarCadastroClientesServicoLocal extends ReplicarCadastroClientesServico {

	/**
	 * A replicação é efetuada por instancias de ReplicacaoCommand, que são criadas apartir da operação registrada na base do SQL server.
	 * 
	 * @param transicaoReplicacao
	 *            a entidade que será replicada.
	 * @throws BancoobException
	 *             exceção lançada durante a replicação.
	 * @see {@link ReplicacaoCommandFactory#createCommand()}
	 * @see {@link ReplicacaoCommand#execute(TransicaoReplicacao)}}
	 */
	void replicar(TransicaoReplicacao transicaoReplicacao) throws BancoobException;

	/**
	 * Atualiza a data de processamento de TransicaoReplicacao e chama o TransicaoReplicacaoDelegate para salvar as alterações na entidade.
	 * 
	 * @param transicaoReplicacao
	 *            a instancia de TransicaoReplicacao que será atualizada.
	 */
	void atualizar(TransicaoReplicacao transicaoReplicacao);

	/**
	 * O método Centraliza.
	 *
	 * @param bancoServidor
	 *            o valor de banco servidor
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	void centraliza(BancoServidor bancoServidor) throws BancoobException;

}